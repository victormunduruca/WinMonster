package br.uefs.ecomp.winmonster.util;

public class NoMapa {
	
	private String sequencia;

	

	private char simbolo;
	
	

	public NoMapa(String sequencia, char simbolo){
		this.sequencia = sequencia;
		this.simbolo = simbolo;
	}
	
	/**
	 * @param sequencia the sequencia to set
	 */
	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}
	public String getSequencia() {
		return sequencia;
	}

	public char getSimbolo() {
		return simbolo;
	}
	/**
	 * @param simbolo the simbolo to set
	 */
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

}
