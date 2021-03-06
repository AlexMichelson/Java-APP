package br.com.model;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Produto {

	int idItem;
	String codigo;
	String descricao;
	double preco;

	public Produto() {

	}

	public Produto(String codigo, String descricao, Double preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	
	public void Add(Statement st) {
		try {
			st.execute("Insert Into public.\"Produto\" (\"Codigo\", \"Descricao\", \"Preco\") Values('" + getCodigo()
					+ "','" + getDescricao() + "','" + getPreco() + "')");
			JOptionPane.showMessageDialog(null, "Produto cadastrado!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
