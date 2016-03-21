package br.uefs.ecomp.winmonster.util;

public class NoMapa {
	
	private String sequencia;
	private char simbolo;
	
	public NoMapa(String sequencia, char simbolo){
		this.sequencia = sequencia;
		this.simbolo = simbolo;
	}

	public String getSequencia() {
		return sequencia;
	}

	public char getSimbolo() {
		return simbolo;
	}

}
