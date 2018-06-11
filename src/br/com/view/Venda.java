package br.com.view;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.controller.ControllerDocumento;
import br.com.controller.ControllerItem;
import br.com.controller.ControllerProduto;
import br.com.controller.DB;
import br.com.model.Documento;
import br.com.model.Produto;

public class Venda extends JFrame {

	protected static final boolean True = false;
	private JPanel contentPane;
	private JTextField textCodProd;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();
	private double somaItens = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Venda frame = new Venda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Venda() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ControllerDocumento docto = new ControllerDocumento();
		Integer iNumDocto = Integer.parseInt(docto.SelectProcimoNumDocto());
		JLabel lblNumVenda = new JLabel("Venda atual: " + iNumDocto);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumVenda, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumVenda);

		JLabel lblNewLabel_1 = new JLabel("Produto:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumVenda, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNumVenda, -135, SpringLayout.WEST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 10, SpringLayout.NORTH, contentPane);
		contentPane.add(lblNewLabel_1);

		textCodProd = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textCodProd, 6, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, textCodProd, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, textCodProd);
		contentPane.add(textCodProd);
		textCodProd.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Lista Produtos:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, textCodProd);
		contentPane.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -96, SpringLayout.SOUTH, contentPane);
		contentPane.add(scrollPane);

		modelo.addColumn("Código");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço");
		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		JLabel lblValDocto = new JLabel("Valor Documento:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblValDocto, 9, SpringLayout.SOUTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblValDocto, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblValDocto);

		JFormattedTextField textVolDocto = new JFormattedTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textVolDocto, 6, SpringLayout.SOUTH, lblValDocto);
		sl_contentPane.putConstraint(SpringLayout.WEST, textVolDocto, 10, SpringLayout.WEST, contentPane);
		textVolDocto.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textVolDocto.setText("0");
		textVolDocto.setEditable(false);
		contentPane.add(textVolDocto);

		JButton btnOk = new JButton("Ok");
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -7, SpringLayout.WEST, btnOk);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOk, 6, SpringLayout.SOUTH, textCodProd);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOk, 0, SpringLayout.WEST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnOk, 0, SpringLayout.EAST, textCodProd);

		// Add Iten no Grid
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerProduto cpProd = new ControllerProduto();
				List<Produto> listPord;
				somaItens = Double.parseDouble(textVolDocto.getText());

				try {
					listPord = cpProd.SelectProduto(textCodProd.getText());
					if (listPord.size() == 0) {
						JOptionPane.showMessageDialog(null, "Produto não cadastrado.");

					} else {
						for (Produto prod : listPord) {
							modelo.addRow(new Object[] { prod.getCodigo(), prod.getDescricao(), prod.getPreco(),
									prod.getIdItem() });

							somaItens = somaItens + prod.getPreco();
						}
					}

					textVolDocto.setText(Double.toString(somaItens));

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		JComboBox cbStatusDocto = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.WEST, cbStatusDocto, 118, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textVolDocto, -6, SpringLayout.WEST, cbStatusDocto);
		sl_contentPane.putConstraint(SpringLayout.NORTH, cbStatusDocto, 0, SpringLayout.NORTH, textVolDocto);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, cbStatusDocto, 0, SpringLayout.SOUTH, textVolDocto);
		cbStatusDocto.setModel(new DefaultComboBoxModel(new String[] { "Confirmado", "N\u00E3o Confirmado" }));
		cbStatusDocto.setSelectedIndex(1);
		contentPane.add(cbStatusDocto);

		JLabel lblStatusDocto = new JLabel("Status:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblStatusDocto, 0, SpringLayout.NORTH, lblValDocto);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblStatusDocto, 23, SpringLayout.EAST, lblValDocto);
		contentPane.add(lblStatusDocto);

		JButton btnConfirmar = new JButton("Confirmar");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnConfirmar, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnConfirmar, 0, SpringLayout.EAST, textCodProd);
		btnConfirmar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ControllerDocumento ctDocto = new ControllerDocumento();
				ControllerItem ctItem = new ControllerItem();

				double totalDocto = 0;

				Documento docto = new Documento();
				DB db = new DB();

				try {
					ctDocto.setNumero(iNumDocto);
					ctDocto.setTotal(totalDocto);

					if (cbStatusDocto.getSelectedIndex() == 0) {
						ctDocto.setConfirmado(true);

					} else {
						ctDocto.setConfirmado(false);
					}

					if (ctDocto.addDocto()) {

						for (int i = 0; i < modelo.getRowCount(); i++) {

							ctItem.setNumDocumento(iNumDocto);
							ctItem.setCodProduto((String) modelo.getValueAt(i, 0));

							ctItem.addItem();
							totalDocto = totalDocto + (Double) modelo.getValueAt(i, 2);
						}
						ctDocto.setTotal(totalDocto);
						ctDocto.updateDocto();
						totalDocto = 0;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				/*
				 * try { ctDocto.setConfirmado(True); ctDocto.setTotal(totalDocto);
				 * ctDocto.addDocto(); } finally { dispose(); }
				 */
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.WEST, contentPane);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, btnConfirmar);

		contentPane.add(btnOk);
		contentPane.add(btnConfirmar);
		contentPane.add(btnCancelar);

	}
}
