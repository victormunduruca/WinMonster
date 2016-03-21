package br.uefs.ecomp.winmonster.view;

import java.io.File;
import java.io.IOException;

import br.uefs.ecomp.winmonster.controller.AdministradorController;
import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;
import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.MeuIterador;
import br.uefs.ecomp.winmonster.util.No;

public class Principal {
	
	public static void main(String [] args){
		
		AdministradorController controllerAdm = AdministradorController.getInstance();
		
		File arquivo = new File("C:/frase.txt");
		String texto = null;
		
		try {
			texto = controllerAdm.lerArquivo(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		Fila fila = null;
		fila = controllerAdm.getHuff().contaFrequencias(texto);
		
		MeuIterador i = (MeuIterador) fila.iterador();
		while(i.temProximo()){
			No no = (No) i.obterProximo();
			System.out.println("Char: " + no.getSimbolo());
			System.out.println("Frequencia: " + no.getFrequencia());
		}
		
		System.out.println("\n¡rvore");
		
		No no = null;
		try {
			no = controllerAdm.getHuff().arvore(fila);
		} catch (FilaNulaException e2) {
			e2.printStackTrace();
		}
		
		percorreArvore(no);
		System.out.println("\n");
		
		try {
			controllerAdm.getHuff().geradorArquivo("NomeQualquer");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			controllerAdm.getHuff().mapeamento(no);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArvoreNulaException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void percorreArvore(No raiz){
		if(raiz != null){
			percorreArvore(raiz.getFilhoDaEsquerda());
			System.out.println(raiz.getFrequencia());
			percorreArvore(raiz.getFilhoDaDireita());
		}
	}
	
}
