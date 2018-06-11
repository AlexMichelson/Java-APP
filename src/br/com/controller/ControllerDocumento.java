package br.com.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.model.Documento;

public class ControllerDocumento extends Documento {

	private DB db = new DB();
	PreparedStatement pStDocto;

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

	public Boolean addDocto() {
		try {
			String sQuery = "Insert Into public.\"Documento\" (\"Numero\", \"Total\", \"Confirmar\") Values('"
					+ getNumero() + "','" + getTotal() + "','" + Boolean.toString(getConfirmado()) + "')";

			pStDocto = db.getCon().prepareStatement(sQuery);
			pStDocto.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Documento> selectDocto(String status) {

		List<Documento> docto = new ArrayList<>();
		try {
			String sql = " SELECT *  FROM \"Documento\" Where \"Confirmar\" = '" + status + "' ";
			PreparedStatement preparedStatement = db.getCon().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Documento doc = new Documento();
				doc.setNumero(resultSet.getInt("Numero"));
				doc.setTotal(Double.parseDouble(resultSet.getString("Total")));
				docto.add(doc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return docto;
	}

	public void updateDocto() {
		String sQuery = "UPDATE public.\"Documento\"\r\n" + "   SET \"Confirmar\"= ? \r\n" + "     , \"Total\"= ? \r\n"
				+ " WHERE \"Numero\" = ?";

		try {
			PreparedStatement pStDocto = db.getCon().prepareStatement(sQuery);
			pStDocto.setBoolean(1, getConfirmado());
			pStDocto.setDouble(2, getTotal());
			pStDocto.setInt(3, getNumero());
			pStDocto.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStatusDocto() {
		String sQuery = "UPDATE public.\"Documento\"\r\n" + "   SET \"Confirmar\"= ? " + " WHERE \"Numero\" = ?";

		try {
			PreparedStatement pStDocto = db.getCon().prepareStatement(sQuery);
			pStDocto.setBoolean(1, getConfirmado());
			pStDocto.setInt(2, getNumero());
			pStDocto.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
