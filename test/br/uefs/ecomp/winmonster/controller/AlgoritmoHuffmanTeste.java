package br.uefs.ecomp.winmonster.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.winmonster.controller.AdministradorController;
import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;
import br.uefs.ecomp.winmonster.util.AlgoritmoHuffman;
import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.Iterador;
import br.uefs.ecomp.winmonster.util.Lista;
import br.uefs.ecomp.winmonster.util.No;
import br.uefs.ecomp.winmonster.util.NoMapa;

public class AlgoritmoHuffmanTeste {
	
	private AlgoritmoHuffman algoritimoHuffman;

	@Before
	public void setUp() throws Exception {
		AdministradorController.zerarSingleton();
		algoritimoHuffman = AlgoritmoHuffman.getInstance();
	}
	
	@Test
	public void testCriarArvoreSucesso(){
		No no1 = new No();
		no1.setFrequencia(1);
		no1.setSimbolo('o');
		No no2 = new No();
		no2.setFrequencia(2);
		no2.setSimbolo('i');
		No no3 = new No();
		no3.setFrequencia(3);
		no3.setSimbolo('a');
		No no4 = new No();
		no4.setFrequencia(4);
		no4.setSimbolo('e');
		
		Fila fila = new Fila();
		fila.inserirPrioridade(no1);
		fila.inserirPrioridade(no2);
		fila.inserirPrioridade(no3);
		fila.inserirPrioridade(no4);
		
		No raiz = null;
		try{
			raiz = algoritimoHuffman.arvore(fila);
		}catch(FilaNulaException e){
			fail();
		}
		
		assertEquals(10, raiz.getFrequencia());
		assertEquals(6, raiz.getFilhoDaDireita().getFrequencia());
		assertEquals(4, raiz.getFilhoDaEsquerda().getFrequencia());
		No atual = raiz.getFilhoDaDireita();
		assertEquals(3, atual.getFilhoDaEsquerda().getFrequencia());
		assertEquals(3, atual.getFilhoDaDireita().getFrequencia());
		No atual2 = atual.getFilhoDaDireita();
		assertEquals(1, atual2.getFilhoDaEsquerda().getFrequencia());
		assertEquals(2, atual2.getFilhoDaDireita().getFrequencia());
	}
	
	@Test
	public void testCriarArvoreComFilaNula(){
		Fila fila = new Fila();
		
		try{
			algoritimoHuffman.arvore(fila);
			fail();
		}catch(FilaNulaException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testCriarMapaSucesso(){
		No no1 = new No();
		no1.setFrequencia(1);
		no1.setSimbolo('o');
		No no2 = new No();
		no2.setFrequencia(2);
		no2.setSimbolo('i');
		No no3 = new No();
		no3.setFrequencia(3);
		no3.setSimbolo('a');
		No no4 = new No();
		no4.setFrequencia(4);
		no4.setSimbolo('e');
		
		Fila fila = new Fila();
		fila.inserirPrioridade(no1);
		fila.inserirPrioridade(no2);
		fila.inserirPrioridade(no3);
		fila.inserirPrioridade(no4);
		
		No raiz = null;
		try{
			raiz = algoritimoHuffman.arvore(fila);
		}catch(FilaNulaException e){
			fail();
		}
		
		Lista lista = null;
		try{
			algoritimoHuffman.mapeamento(raiz);
			lista = algoritimoHuffman.getFolhas();
		}catch(ArvoreNulaException e){
			fail();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		NoMapa folha = null;
		Iterador i = lista.iterador();
		folha = (NoMapa) i.obterProximo();
		assertEquals(0, folha.getSequencia());
		folha = (NoMapa) i.obterProximo();
		assertEquals(10, folha.getSequencia());
		folha = (NoMapa) i.obterProximo();
		assertEquals(110, folha.getSequencia());
		folha = (NoMapa) i.obterProximo();
		assertEquals(111, folha.getSequencia());
	}
}
