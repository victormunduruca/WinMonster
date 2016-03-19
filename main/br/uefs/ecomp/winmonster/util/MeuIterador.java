package br.uefs.ecomp.winmonster.util;

public class MeuIterador implements Iterador{
	
	private Celula atual;
	private Celula inicio;
	
	public MeuIterador(Object primeiroNO){
		this.atual = (Celula) primeiroNO;
		this.inicio = (Celula) primeiroNO;
		//As duas referências "atual" e "inicio" recebem a referência do inicio da lista
	}
	/**Método que verifica se o próximo elemento é null.
	 * @return boolean*/
	@Override
	public boolean temProximo() {
		return atual != null;
		/*Garanto que existe uma proxima celula da lista até que a referência
		 * atual seja igual a null*/
	}
	/**Método que retona o objeto da celula atual e referencia a próxima celula.
	 * @return Object*/
	@Override
	public Object obterProximo() {
		Object objetoAtual = atual.getObjeto();// Recebo o objeto da celula atual
		atual = atual.getProximo();// Posiciono a referência atual na próxima celula
		return objetoAtual;//retorno o objeto
	}
	/**Método que reseta a posição da referência. 
	 * @return void*/
	public void resetar(){
		//Método que serve para resetar a posição da referência, vontando sempre para o inicio
		atual = inicio;
	}
	
}
