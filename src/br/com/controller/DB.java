package br.com.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private String url;
	private String usuario;
	private String senha;
	private Connection con;

	public Connection getCon() {
		return con;
	}

	public DB() {
		url = "jdbc:postgresql://localhost:5432/postgres";
		usuario = "postgres";
		senha = "123";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão Realizada");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Conexão Falhou");
			// TODO: handle exception
		}

	}

}
