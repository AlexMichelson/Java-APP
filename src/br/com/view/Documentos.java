package br.com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.controller.ControllerDocumento;
import br.com.model.Documento;

public class Documentos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnValtar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Documentos frame = new Documentos();
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
	public Documentos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		btnValtar = new JButton("Voltar");
		btnValtar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(10).addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addContainerGap(353, Short.MAX_VALUE).addComponent(btnValtar)))
						.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE).addGap(17)
								.addComponent(btnValtar)));

		modelo.addColumn("Documento");
		modelo.addColumn("Total Docto");
		table = new JTable(modelo);

		ControllerDocumento ctDocto = new ControllerDocumento();
		List<Documento> listDocto;
		try {
			Double total = 0.0;
			listDocto = ctDocto.SelectDocumento();

			for (Documento docto : listDocto) {
				modelo.addRow(new Object[] { docto.getNumero(), docto.getTotal() });
				total = total + docto.getTotal();
			}

			modelo.addRow(new Object[] { "Total", total });

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
