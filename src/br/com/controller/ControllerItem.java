package br.com.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.model.Item;

public class ControllerItem extends Item {

	private DB db = new DB();

	public ControllerItem() {

	}

	public void addItem() {
		String sQuery = "INSERT INTO public.\"Item\"(\"Documento\", \"Produto\") VALUES (?, ?)";

		try {
			PreparedStatement psItem = db.getCon().prepareStatement(sQuery);
			psItem.setInt(1, getNumDocumento());
			psItem.setString(2, getCodProduto());
			psItem.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
