package br.uefs.ecomp.winmonster.util;

public class NoMapa {
	
	private int sequencia;
	private char simbolo;
	
	public NoMapa(int sequencia, char simbolo){
		this.sequencia = sequencia;
		this.simbolo = simbolo;
	}

	public int getSequencia() {
		return sequencia;
	}

	public char getSimbolo() {
		return simbolo;
	}

}
