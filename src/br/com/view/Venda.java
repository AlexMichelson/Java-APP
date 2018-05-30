package br.com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.controller.ControllerDocumento;
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
	private List<Produto> list;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ControllerDocumento docto = new ControllerDocumento();
		Integer sNumDocto = Integer.parseInt(docto.SelectProcimoNumDocto());
		JLabel lblNumVenda = new JLabel("Venda atual: " + sNumDocto);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumVenda, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumVenda, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumVenda);

		JLabel lblNewLabel_1 = new JLabel("Produto:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, lblNumVenda);
		contentPane.add(lblNewLabel_1);

		textCodProd = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textCodProd, 6, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, textCodProd, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, textCodProd);
		contentPane.add(textCodProd);
		textCodProd.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Lista Produtos:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNumVenda);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, textCodProd);
		contentPane.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblNumVenda);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -96, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -49, SpringLayout.EAST, lblNewLabel_1);
		contentPane.add(scrollPane);

		modelo.addColumn("Código");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço");
		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		JButton btnOk = new JButton("Ok");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOk, 6, SpringLayout.SOUTH, textCodProd);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOk, 0, SpringLayout.WEST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnOk, 0, SpringLayout.EAST, textCodProd);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerProduto cp = new ControllerProduto();
				List<Produto> listPord;
				try {
					listPord = cp.SelectProduto(textCodProd.getText());
					for (Produto prod : listPord) {
						modelo.addRow(new Object[] { prod.getCodigo(), prod.getDescricao(), prod.getPreco() });
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton btnConfirmar = new JButton("Confirmar");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnConfirmar, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnConfirmar, 0, SpringLayout.EAST, textCodProd);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double totalDocto = 0.0;
				ControllerDocumento ctDocto = new ControllerDocumento();
				Documento docto = new Documento();
				DB db = new DB();

				try {

					String sql = "Insert Into public.\"Item\" (\"COD_PRODUTO\", \"NUM_DOCTO\") Values(?, ?)";
					PreparedStatement preparedStatement = db.getCon().prepareStatement(sql);

					for (int i = 0; i < modelo.getRowCount(); i++) {
						totalDocto = totalDocto + (Double) modelo.getValueAt(i, 2);

						preparedStatement.setString(1, (String) modelo.getValueAt(i, 0));
						preparedStatement.setInt(2, sNumDocto);
						preparedStatement.execute();

						System.out.println("Código: " + modelo.getValueAt(i, 1));
						System.out.println("Desc.: " + modelo.getValueAt(i, 1));
						System.out.println("Preço: " + modelo.getValueAt(i, 1));
						System.out.println("**********************************");
					}
					preparedStatement = db.getCon().prepareStatement(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					docto.setConfirmado(True);
					docto.setTotal(totalDocto);
					ctDocto.addDocto(docto);
				} finally {
					dispose();
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancelar, 0, SpringLayout.WEST, lblNumVenda);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, btnConfirmar);

		contentPane.add(btnOk);
		contentPane.add(btnConfirmar);
		contentPane.add(btnCancelar);

	}
}
