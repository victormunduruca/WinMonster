package br.uefs.ecomp.winmonster.util;

public class MeuIterador implements Iterador{
	
	private Celula atual;
	private Celula inicio;
	
	public MeuIterador(Object primeiroNO){
		this.atual = (Celula) primeiroNO;
		this.inicio = (Celula) primeiroNO;
		//As duas refer�ncias "atual" e "inicio" recebem a refer�ncia do inicio da lista
	}
	/**M�todo que verifica se o pr�ximo elemento � null.
	 * @return boolean*/
	@Override
	public boolean temProximo() {
		return atual != null;
		/*Garanto que existe uma proxima celula da lista at� que a refer�ncia
		 * atual seja igual a null*/
	}
	/**M�todo que retona o objeto da celula atual e referencia a pr�xima celula.
	 * @return Object*/
	@Override
	public Object obterProximo() {
		Object objetoAtual = atual.getObjeto();// Recebo o objeto da celula atual
		atual = atual.getProximo();// Posiciono a refer�ncia atual na pr�xima celula
		return objetoAtual;//retorno o objeto
	}
	/**M�todo que reseta a posi��o da refer�ncia. 
	 * @return void*/
	public void resetar(){
		//M�todo que serve para resetar a posi��o da refer�ncia, vontando sempre para o inicio
		atual = inicio;
	}
	
}
