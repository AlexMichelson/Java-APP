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
import br.com.model.Produto;

public class CadastroProduto extends JFrame {

	private JPanel contentPane;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JButton btCacelar = new JButton("Cancelar");
		btCacelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btCacelar, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btCacelar, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btCacelar, 205, SpringLayout.WEST, contentPane);
		contentPane.add(btCacelar);

		JButton btAdicionar = new JButton("Adicionar");
		sl_contentPane.putConstraint(SpringLayout.WEST, btAdicionar, 102, SpringLayout.EAST, btCacelar);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btAdicionar, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btAdicionar, -5, SpringLayout.EAST, contentPane);
		btAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto prod = new Produto(tfCodigo.getText(), tfDescricao.getText(),
						Double.parseDouble(tfPreco.getText()));

				ControllerProduto c = new ControllerProduto();

				c.addProd(prod);
			}
		});
		contentPane.add(btAdicionar);

		JLabel lbCodigo = new JLabel("C\u00F3digo:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lbCodigo, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lbCodigo, 5, SpringLayout.WEST, contentPane);
		contentPane.add(lbCodigo);

		tfCodigo = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfCodigo, 6, SpringLayout.SOUTH, lbCodigo);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfCodigo, 5, SpringLayout.WEST, contentPane);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);

		tfDescricao = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfDescricao, 0, SpringLayout.NORTH, tfCodigo);
		sl_contentPane.putConstraint(SpringLayout.WEST, tfDescricao, 6, SpringLayout.EAST, tfCodigo);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfDescricao, -102, SpringLayout.EAST, contentPane);
		contentPane.add(tfDescricao);
		tfDescricao.setColumns(10);

		lbDescricao = new JLabel("Descri\u00E7\u00E3o:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lbDescricao, 0, SpringLayout.NORTH, lbCodigo);
		sl_contentPane.putConstraint(SpringLayout.WEST, lbDescricao, 0, SpringLayout.WEST, tfDescricao);
		contentPane.add(lbDescricao);

		lbPreco = new JLabel("Pre\u00E7o:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lbPreco, 0, SpringLayout.NORTH, lbCodigo);
		contentPane.add(lbPreco);

		tfPreco = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, tfPreco, 6, SpringLayout.EAST, tfDescricao);
		sl_contentPane.putConstraint(SpringLayout.EAST, tfPreco, -5, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lbPreco, 0, SpringLayout.WEST, tfPreco);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tfPreco, 0, SpringLayout.NORTH, tfCodigo);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
	}
}
