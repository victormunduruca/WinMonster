package br.uefs.ecomp.winmonster.view;

import java.io.File;
import java.io.IOException;

import br.uefs.ecomp.winmonster.controller.AdministradorController;
import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;
import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.Iterador;
import br.uefs.ecomp.winmonster.util.Lista;
import br.uefs.ecomp.winmonster.util.MeuIterador;
import br.uefs.ecomp.winmonster.util.No;
import br.uefs.ecomp.winmonster.util.NoMapa;

public class Principal {
	
	public static void main(String [] args){
		
		AdministradorController controllerAdm = AdministradorController.getInstance();
		
//		GUI gui = new GUI();
//		gui.InterfaceGrafica();

		File arquivo = new File("C:/Users/Victor/pequeno.txt");
		
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
			controllerAdm.getHuff().mapeamento(no);
			Lista mapa = controllerAdm.getHuff().escreverMapa();
			Iterador it = (Iterador) mapa.iterador();
//			System.out.println("MAPA: ");
//			while(it.temProximo()) {
//				NoMapa n = (NoMapa) it.obterProximo();
//				System.out.println("Char: " +n.getSimbolo());
//				System.out.println("Sequencia: " +n.getSequencia());
//			}
			
			String cod = controllerAdm.getHuff().codificarTexto(mapa, controllerAdm.getHuff().pegarStringArquivo(arquivo));
			
			//System.out.println("Texto codificado: " +cod);
			
			//String decod = controllerAdm.getHuff().decodificarTexto(mapa, cod);
			System.out.println("Oxente");
			//System.out.println("Texto decodificado: " +decod);
			controllerAdm.getHuff().compactar(no, cod);
		//	System.out.println("Texto lido: " +controllerAdm.getHuff().pegarStringArquivo(arquivo));
			controllerAdm.getHuff().descompactar("C:/Users/Victor/bin.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArvoreNulaException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
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
