package br.uefs.ecomp.winmonster.util;

public class Celula{
	
	/**@author Diego Lourenço*/
	
	private Object objeto;
	private Celula proximo;
	
	public Celula(){
	}
	/**Método que recupera um objeto.
	 * @return Object*/
	public Object getObjeto() {
		return objeto;
	}
	/**Método que atribui um valor ao atributo objeto.
	 * @param objeto
	 * @return void*/
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	/**Método que recupera uma referência.
	 * @return Celula*/
	public Celula getProximo() {
		return proximo;
	}
	/**Método que atribui uma referência para o atributo proximo.
	 * @param proximo
	 * @return void*/
	public void setProximo(Celula proximo) {
		this.proximo = proximo;
	}

}
