package br.com.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.model.Produto;

public class ControllerProduto extends Produto {

	private DB db = new DB();
	private Statement st;

	public ControllerProduto() {

	}

	public ControllerProduto(String codigo, String descricao, Double preco) {
		super(codigo, descricao, preco);
	}

	public void addProd() {

		if (getCodigo() != null) {

			try {
				st = db.getCon().createStatement();
				st.executeQuery("Select count(*) From \"Produto\" Where \"Codigo\" = ' " + getCodigo() + " ' ");

				ResultSet rs = st.getResultSet();

				st.execute("Insert Into public.\"Produto\" (\"Codigo\", \"Descricao\", \"Preco\") Values('"
						+ getCodigo() + "','" + getDescricao() + "','" + getPreco() + "')");

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			JOptionPane.showInternalMessageDialog(null, "Infome o código do produto!");
		}
	}

	public List<Produto> SelectProduto(String codProd) {

		List<Produto> produto = new ArrayList<>();
		try {
			String sql = "Select * From \"Produto\" Where \"Codigo\" = ? ";
			PreparedStatement preparedStatement = db.getCon().prepareStatement(sql);
			preparedStatement.setString(1, codProd);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Produto p = new Produto();
				p.setIdItem(resultSet.getInt("ID"));
				p.setCodigo(resultSet.getString("Codigo"));
				p.setDescricao(resultSet.getString("Descricao"));
				p.setPreco(resultSet.getDouble("Preco"));
				produto.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return produto;
	}

}
