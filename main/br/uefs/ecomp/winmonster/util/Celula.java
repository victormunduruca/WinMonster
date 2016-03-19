package br.uefs.ecomp.winMonster.util;

public class Celula{
	
	/**@author Diego Louren�o*/
	
	private Object objeto;
	private Celula proximo;
	
	public Celula(){
	}
	/**M�todo que recupera um objeto.
	 * @return Object*/
	public Object getObjeto() {
		return objeto;
	}
	/**M�todo que atribui um valor ao atributo objeto.
	 * @param objeto
	 * @return void*/
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	/**M�todo que recupera uma refer�ncia.
	 * @return Celula*/
	public Celula getProximo() {
		return proximo;
	}
	/**M�todo que atribui uma refer�ncia para o atributo proximo.
	 * @param proximo
	 * @return void*/
	public void setProximo(Celula proximo) {
		this.proximo = proximo;
	}

}
