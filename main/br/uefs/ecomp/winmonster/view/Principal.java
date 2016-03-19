package br.uefs.ecomp.winMonster.view;

import java.io.File;
import java.io.IOException;

import br.uefs.ecomp.winMonster.controller.AdministradorController;
import br.uefs.ecomp.winMonster.util.Fila;
import br.uefs.ecomp.winMonster.util.MeuIterador;
import br.uefs.ecomp.winMonster.util.No;

public class Principal {
	
	public static void main(String [] args){
		
		AdministradorController controllerAdm = new AdministradorController();
		
		File arquivo = new File("C:/frase.txt");
		String texto = null;
		
		try {
			texto = controllerAdm.lerArquivo(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		Fila fila = controllerAdm.getHuff().contaFrequencias(texto);
		MeuIterador i = (MeuIterador) fila.iterador();
		while(i.temProximo()){
			No no = (No) i.obterProximo();
			System.out.println("Char: " + no.getSimbolo());
			System.out.println("Frequencia: " + no.getFrequencia());
		}
		
		System.out.println("\n¡rvore");
		
		No no = controllerAdm.getHuff().huffman(fila);
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
