package br.com.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.controller.ControllerDocumento;

public class Documento extends JFrame {

	private JPanel contentPaneDocumento;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Documento frame = new Documento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Documento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 529, 379);
		contentPaneDocumento = new JPanel();
		contentPaneDocumento.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneDocumento);

		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 0));
		panel.setPreferredSize(new Dimension(0, 0));

		JLabel lblStatusDocumento = new JLabel("Status:");

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBox.getSelectedIndex() == 0) {
					// Confirmado

					tabDocto("T");
				} else {
					// Não Confirmado
					tabDocto("F");
				}

			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Confirmado", "N\u00E3o Confirmado" }));
		comboBox.setSelectedIndex(1);

		JButton btnConfDocto = new JButton("Confirmar Documento");
		btnConfDocto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmaDocto();
			}
		});
		GroupLayout gl_contentPaneDocumento = new GroupLayout(contentPaneDocumento);
		gl_contentPaneDocumento.setHorizontalGroup(gl_contentPaneDocumento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneDocumento.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPaneDocumento.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStatusDocumento).addComponent(btnConfDocto))
						.addContainerGap()));
		gl_contentPaneDocumento.setVerticalGroup(gl_contentPaneDocumento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneDocumento.createSequentialGroup().addComponent(lblStatusDocumento)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(11).addComponent(panel, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnConfDocto)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(0, 0));
		scrollPane.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension(0, 0));
		panel.add(scrollPane);

		// Colunas da Tabela
		modelo.addColumn("Código");
		modelo.addColumn("Preço");
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		contentPaneDocumento.setLayout(gl_contentPaneDocumento);
	}

	public void tabDocto(String status) {

		ControllerDocumento ctDocto = new ControllerDocumento();
		List<br.com.model.Documento> listDocto;

		try {
			double total = 0;
			listDocto = ctDocto.selectDocto(status);
			modelo.setNumRows(0);

			for (br.com.model.Documento docto : listDocto) {
				modelo.addRow(new Object[] { docto.getNumero(), docto.getTotal() });
				total = total + docto.getTotal();
			}

			if (total > 0.0) {
				modelo.addRow(new Object[] { "Total", total });
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void confirmaDocto() {
		ControllerDocumento ctDocto = new ControllerDocumento();
		ctDocto.setNumero((Integer) modelo.getValueAt(table.getSelectedRow(), 0));
		ctDocto.setConfirmado(true);
		ctDocto.updateStatusDocto();
	}
}
