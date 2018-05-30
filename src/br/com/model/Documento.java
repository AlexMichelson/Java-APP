package br.com.model;

import br.com.controller.DB;

public class Documento extends DB {
	
	String numero;
	double total;
	boolean confirmado;
	
	public Documento() {
		
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}	

}


