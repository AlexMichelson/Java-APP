package br.com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FramePrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
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
	public FramePrincipal() {

		// setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmCadastroProduto = new JMenuItem("Cadastro Produto");
		mntmCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto frameCadProd = new CadastroProduto();
				frameCadProd.setVisible(true);
			}
		});

		mntmCadastroProduto.addMouseListener(new MouseAdapter() {
		});
		mnMenu.add(mntmCadastroProduto);

		JMenuItem mntmVenda = new JMenuItem("Venda");
		mntmVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venda frameVenda = new Venda();
				frameVenda.setVisible(true);
			}
		});
		mnMenu.add(mntmVenda);

		JMenuItem mntmDoctoVenda = new JMenuItem("Documentos Venda");
		mntmDoctoVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venda frameVenda = new Venda();
				frameVenda.setVisible(true);
			}
		});
		mnMenu.add(mntmDoctoVenda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
