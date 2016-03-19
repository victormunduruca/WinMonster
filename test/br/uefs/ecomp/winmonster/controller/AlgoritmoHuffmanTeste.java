package br.uefs.ecomp.winmonster.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.winmonster.controller.AdministradorController;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;
import br.uefs.ecomp.winmonster.util.AlgoritmoHuffman;
import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.No;

public class AlgoritmoHuffmanTeste {
	
	private AlgoritmoHuffman algoritimoHuffman;

	@Before
	public void setUp() throws Exception {
		AdministradorController.zerarSingleton();
		algoritimoHuffman = AlgoritmoHuffman.getInstance();
	}
	
	@Test
	public void testeCriarArvoreSucesso(){
		No no1 = new No();
		no1.setFrequencia(1);
		no1.setSimbolo('o');
		No no2 = new No();
		no2.setFrequencia(2);
		no2.setSimbolo('i');
		No no3 = new No();
		no3.setFrequencia(4);
		no3.setSimbolo('a');
		
		Fila fila = new Fila();
		fila.inserirPrioridade(no1);
		fila.inserirPrioridade(no2);
		fila.inserirPrioridade(no3);
		
		No raiz = null;
		try{
			raiz = algoritimoHuffman.arvore(fila);
		}catch(FilaNulaException e){
			fail();
		}
		
		assertEquals(7, raiz.getFrequencia());
		assertEquals(4, raiz.getFilhoDaDireita().getFrequencia());
		assertEquals(3, raiz.getFilhoDaEsquerda().getFrequencia());
		No atual = raiz.getFilhoDaEsquerda();
		assertEquals(1, atual.getFilhoDaEsquerda().getFrequencia());
		assertEquals(2, atual.getFilhoDaDireita().getFrequencia());
	}
}
