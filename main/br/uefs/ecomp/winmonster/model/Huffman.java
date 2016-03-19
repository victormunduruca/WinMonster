package br.uefs.ecomp.winmonster.model;

import java.util.HashMap;
import java.util.Map;

import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.Iterador;
import br.uefs.ecomp.winmonster.util.No;
import br.uefs.ecomp.winmonster.util.NoMapa;

public class Huffman {
	
	public Huffman() {
		
	}
	public  Fila contaFrequencias(String letras) {
		/*Map<Character, NoMapa> conta = new HashMap<>();
		for(char ch : letras) {
			if(!conta.containsKey(ch)) {
				conta.put(ch, new NoMapa(ch));
			}
			conta.get(ch).adicionaFrequencia();
		} */
		Fila fila = new Fila();
		int i;
		for(i = 0; i < letras.length(); i++) {
			char ch = letras.charAt(i);
			No no = recuperarNo(fila, ch);
			
			if(no == null){
				No novoNo = new No();
				novoNo.setSimbolo(ch);
				fila.inserirFinal(novoNo);
			} else {
				no.setFrequencia(no.getFrequencia() + 1);
			}
		}
		
		Fila filaOrdenada = new Fila();
		while(!fila.estaVazia()) {
			filaOrdenada.inserirFinal(fila.removerInicio());
		}
		return filaOrdenada;
	}
	public No recuperarNo(Fila fila, char simbolo) {
		Iterador iteradorFila = fila.iterador();
		
		while(iteradorFila.temProximo()) {
			No noAtual =  (No) iteradorFila.obterProximo();
			if(noAtual.getSimbolo() == simbolo) {
				return noAtual;
			}
		}
		return null;
	}
	public No criarArvore(Fila fila) {
		
		
		while(true) {
			No no1 = (No) fila.removerInicio();
			No no2 = (No) fila.removerInicio();
			
			No pai = new No(no1, no2);
			
			if(fila.estaVazia()) {
				return pai;
			}
			
			fila.inserirFinal(pai);
		}
		
	}
	
	
}
