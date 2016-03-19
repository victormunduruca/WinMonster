package br.uefs.ecomp.winmonster.util;

public class NoMapa {
	private char ch;
	private int frequencia;
	
	public NoMapa(char ch) {
		frequencia = 0;
		this.ch = ch;
	}
	public void adicionaFrequencia() {
		frequencia++;
	}
}
