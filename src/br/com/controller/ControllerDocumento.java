package br.com.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.model.Documento;

public class ControllerDocumento {

	private DB db = new DB();
	private Statement st;

	public String SelectProcimoNumDocto() {

		try {
			String sql = "Select count(*) From \"Documento\"";
			Statement Statement = db.getCon().createStatement();
			ResultSet resultSet = Statement.executeQuery(sql);

			if (resultSet.next()) {

				return Integer.toString(resultSet.getShort(1) + 1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void addDocto(Documento docto) {
		try {
			st = db.getCon().createStatement();
			st.execute("Insert Into public.\"Documento\" (\"Total\", \"Confirmado\") Values('" + docto.getTotal()
					+ "','" + docto.getConfirmado() + "')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Documento> SelectDocumento() {

		List<Documento> docto = new ArrayList<>();
		try {
			String sql = "Select * From \"Documento\" Where \"Confirmado\" = 't'";
			PreparedStatement preparedStatement = db.getCon().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Documento doc = new Documento();
				doc.setNumero(resultSet.getString("Numero"));
				doc.setTotal(Double.parseDouble(resultSet.getString("Total")));
				docto.add(doc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return docto;
	}

}
