package br.com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import br.com.controller.ControllerProduto;

public class CadastroProduto extends JFrame {

	private JPanel contentPaneCadProd;
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JLabel lbDescricao;
	private JLabel lbPreco;
	private JTextField tfPreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto();
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
	public CadastroProduto() {
		setResizable(false);
		setTitle("Cadastro Produto");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 172);
		contentPaneCadProd = new JPanel();
		contentPaneCadProd.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCadProd);
		SpringLayout sl_contentPaneCadProd = new SpringLayout();
		contentPaneCadProd.setLayout(sl_contentPaneCadProd);

		JButton btCacelar = new JButton("Cancelar");
		btCacelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, btCacelar, 5, SpringLayout.WEST, contentPaneCadProd);
		sl_contentPaneCadProd.putConstraint(SpringLayout.SOUTH, btCacelar, -5, SpringLayout.SOUTH, contentPaneCadProd);
		sl_contentPaneCadProd.putConstraint(SpringLayout.EAST, btCacelar, 205, SpringLayout.WEST, contentPaneCadProd);
		contentPaneCadProd.add(btCacelar);

		JButton btAdicionar = new JButton("Adicionar");
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, btAdicionar, 102, SpringLayout.EAST, btCacelar);
		sl_contentPaneCadProd.putConstraint(SpringLayout.SOUTH, btAdicionar, 0, SpringLayout.SOUTH, contentPaneCadProd);
		sl_contentPaneCadProd.putConstraint(SpringLayout.EAST, btAdicionar, -5, SpringLayout.EAST, contentPaneCadProd);
		btAdicionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Inclui Produto
				ControllerProduto contProd = new ControllerProduto(tfCodigo.getText(), tfDescricao.getText(),
						Double.parseDouble(tfPreco.getText()));

				contProd.addProd();
			}
		});
		contentPaneCadProd.add(btAdicionar);

		JLabel lbCodigo = new JLabel("C\u00F3digo:");
		sl_contentPaneCadProd.putConstraint(SpringLayout.NORTH, lbCodigo, 10, SpringLayout.NORTH, contentPaneCadProd);
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, lbCodigo, 5, SpringLayout.WEST, contentPaneCadProd);
		contentPaneCadProd.add(lbCodigo);

		tfCodigo = new JTextField();
		sl_contentPaneCadProd.putConstraint(SpringLayout.NORTH, tfCodigo, 6, SpringLayout.SOUTH, lbCodigo);
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, tfCodigo, 5, SpringLayout.WEST, contentPaneCadProd);
		contentPaneCadProd.add(tfCodigo);
		tfCodigo.setColumns(10);

		tfDescricao = new JTextField();
		sl_contentPaneCadProd.putConstraint(SpringLayout.NORTH, tfDescricao, 0, SpringLayout.NORTH, tfCodigo);
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, tfDescricao, 6, SpringLayout.EAST, tfCodigo);
		sl_contentPaneCadProd.putConstraint(SpringLayout.EAST, tfDescricao, -102, SpringLayout.EAST,
				contentPaneCadProd);
		contentPaneCadProd.add(tfDescricao);
		tfDescricao.setColumns(10);

		lbDescricao = new JLabel("Descri\u00E7\u00E3o:");
		sl_contentPaneCadProd.putConstraint(SpringLayout.NORTH, lbDescricao, 0, SpringLayout.NORTH, lbCodigo);
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, lbDescricao, 0, SpringLayout.WEST, tfDescricao);
		contentPaneCadProd.add(lbDescricao);

		lbPreco = new JLabel("Pre\u00E7o:");
		sl_contentPaneCadProd.putConstraint(SpringLayout.NORTH, lbPreco, 0, SpringLayout.NORTH, lbCodigo);
		contentPaneCadProd.add(lbPreco);

		tfPreco = new JTextField();
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, tfPreco, 6, SpringLayout.EAST, tfDescricao);
		sl_contentPaneCadProd.putConstraint(SpringLayout.EAST, tfPreco, -5, SpringLayout.EAST, contentPaneCadProd);
		sl_contentPaneCadProd.putConstraint(SpringLayout.WEST, lbPreco, 0, SpringLayout.WEST, tfPreco);
		sl_contentPaneCadProd.putConstraint(SpringLayout.NORTH, tfPreco, 0, SpringLayout.NORTH, tfCodigo);
		contentPaneCadProd.add(tfPreco);
		tfPreco.setColumns(10);
	}
}
