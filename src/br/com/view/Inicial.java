package br.com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class Inicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicial frame = new Inicial();
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
	public Inicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 99);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JButton btnCadastroProduto = new JButton("Cadastro Produto");
		btnCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto frame = new CadastroProduto();
				frame.setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCadastroProduto, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCadastroProduto, 10, SpringLayout.WEST, contentPane);
		contentPane.add(btnCadastroProduto);

		JButton btnVenda = new JButton("Venda");
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venda frame = new Venda();
				frame.setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnVenda, 0, SpringLayout.NORTH, btnCadastroProduto);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnVenda, 6, SpringLayout.EAST, btnCadastroProduto);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnVenda, 130, SpringLayout.EAST, btnCadastroProduto);
		contentPane.add(btnVenda);

		JButton btnDocumento = new JButton("Documento");
		btnDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Documentos frame = new Documentos();
				frame.setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDocumento, 0, SpringLayout.NORTH, btnCadastroProduto);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDocumento, 6, SpringLayout.EAST, btnVenda);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnDocumento, 107, SpringLayout.EAST, btnVenda);
		contentPane.add(btnDocumento);
	}
}
