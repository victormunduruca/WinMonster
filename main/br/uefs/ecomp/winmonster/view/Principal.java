package br.uefs.ecomp.winmonster.view;

import java.io.File;
import java.io.IOException;

import br.uefs.ecomp.winmonster.controller.AdministradorController;
import br.uefs.ecomp.winmonster.controller.AlgoritmoHuffmanTeste;
import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;
import br.uefs.ecomp.winmonster.util.AlgoritmoHuffman;
import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.Lista;
import br.uefs.ecomp.winmonster.util.MeuIterador;
import br.uefs.ecomp.winmonster.util.No;
import br.uefs.ecomp.winmonster.util.NoMapa;

public class Principal {
	
	/**public static void main(String [] args){
		
		AdministradorController controllerAdm = AdministradorController.getInstance();
		
		File arquivo = new File("C:/frase.txt");
		
		Fila fila = null;
		try {
			fila = controllerAdm.getHuff().contaFrequencias(arquivo);
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		MeuIterador i = (MeuIterador) fila.iterador();
		while(i.temProximo()){
			No no = (No) i.obterProximo();
			System.out.println("Char: " + no.getSimbolo());
			System.out.println("Frequencia: " + no.getFrequencia());
		}
		
		System.out.println("\n�rvore");
		
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
		
		try{
			controllerAdm.getHuff().escreverMapa();
		}catch(IOException e){
			
		}
		
	}*/
	public static void main(String[] args) {
		Lista lista = new Lista();
		NoMapa no1 = new NoMapa("10", 'O');
		NoMapa no2 = new NoMapa("11", 'I');
		NoMapa no3 = new NoMapa("0", 'U');

		lista.inserirFinal(no1);
		lista.inserirFinal(no2);
		lista.inserirFinal(no3);
		
		AlgoritmoHuffman huff = AlgoritmoHuffman.getInstance();
		No raiz = new No();
		huff.decodificar(lista.getPrimeiro(), raiz);
		percorreArvore(raiz);
 	
	}
	
	public static void percorreArvore(No raiz){
		if(raiz != null){
			percorreArvore(raiz.getFilhoDaEsquerda());
			if(raiz.eFolha()) {
				System.out.println(raiz.getSimbolo());
			}
			percorreArvore(raiz.getFilhoDaDireita());
		}
	}
	
}
