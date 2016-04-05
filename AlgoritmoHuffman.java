package br.uefs.ecomp.winmonster.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;

public class AlgoritmoHuffman {

	private static String arestas = "";

	private static Lista folhas = new Lista();
	private NoMapa[] mapa = new NoMapa[256];
	
	

	public NoMapa[] getMapa() {
		return mapa;
	}

	
	private static AlgoritmoHuffman instanciaAdm;

	private AlgoritmoHuffman() {

	}

	public static AlgoritmoHuffman getInstance(){
		if(instanciaAdm == null)
			instanciaAdm = new AlgoritmoHuffman();
		return instanciaAdm;
	}

	public static void zerarSingleton() {
		arestas = "";
		folhas = new Lista();
		instanciaAdm = null;
	}
	
	public Lista getFolhas(){
		return folhas;
	}
	
	public Fila contaFrequencias(File arquivo) throws IOException{
		No[] vetor = new No[256];
		Fila filaNo = new Fila();
		StringBuffer str = new StringBuffer();
		FileReader file = new FileReader(arquivo);
		BufferedReader leitura = new BufferedReader(file);
		while(leitura.ready()){
			str.append(leitura.readLine()+ "\n");
			for(int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if(vetor[(int) ch] == null) {
					vetor[(int) ch] = new No();
					vetor[(int) ch].setSimbolo(ch);
					filaNo.inserirFinal(vetor[(int) ch]);
				}
				vetor[(int) ch].setFrequencia(vetor[(int) ch].getFrequencia() + 1);
			}
		}
		leitura.close();
		file.close();
		Fila filaOrdenada = new Fila();
		while(!filaNo.estaVazia()) {
			filaOrdenada.inserirPrioridade(filaNo.removerInicio());
		}
		return filaOrdenada;
	}
//	public Fila contaFrequencias(File arquivo) throws IOException{
//		Fila fila = new Fila();
//		StringBuffer str = new StringBuffer();
//		FileReader file = new FileReader(arquivo);
//		BufferedReader leitura = new BufferedReader(file);
//		while(leitura.ready()){
//			str.append(leitura.readLine()+ "\n");
//			for(int i = 0; i < str.length(); i++) {
//				char ch = str.charAt(i);
//				No no = recuperarNo(fila, ch);
//				if(no == null){
//					No novoNo = new No();
//					novoNo.setSimbolo(ch);
//					fila.inserirFinal(novoNo);
//				} 
//				else{
//					no.setFrequencia(no.getFrequencia() + 1);
//				}
//			}
//		}
//		leitura.close();
//		file.close();
//		Fila filaOrdenada = new Fila();
//		while(!fila.estaVazia()) {
//			filaOrdenada.inserirPrioridade(fila.removerInicio());
//		}
//		return filaOrdenada;
//	}
	
//	public Fila contaFrequencias(File arquivo) throws IOException{
//		FileReader file = new FileReader(arquivo);
//		BufferedReader leitura = new BufferedReader(file);
//		while(leitura.ready()){
//			String texto = leitura.readLine() + "\n";
//			for(int i = 0; i < texto.length(); i++) {
//				char ch = texto.charAt(i);
//				hashMap(ch);
//			}
//		}
//		leitura.close();
//		file.close();
//		Fila filaOrdenada = new Fila();
//		while(!folhas.estaVazia()) {
//			filaOrdenada.inserirPrioridade(folhas.removerFinal());
//		}
//		return filaOrdenada;
//	}
//	No[] mapa = new No[120000];
//	
//	public void hashMap(char caractere) {
//		Character ch = new Character(caractere);
//		if(mapa[ch.hashCode()] == null) { 
//			No novoNo = new No();
//			novoNo.setSimbolo(caractere);
//			mapa[ch.hashCode()] = novoNo;
//			folhas.inserirFinal(novoNo);
//		} else {
//			mapa[ch.hashCode()].setFrequencia(mapa[ch.hashCode()].getFrequencia() + 1);
//		}
//	}
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

	public No arvore(Fila fila) throws FilaNulaException{
		if(fila.estaVazia())
			throw new FilaNulaException();
		if(fila.obterTamanho() == 1)
			return (No) fila.removerInicio();
		No noPai = null;
		while(true){
			No no = (No) fila.removerInicio();
			No no2 = (No) fila.removerInicio();
			noPai = new No(no, no2);
			if(fila.estaVazia())
				break;
			fila.inserirPrioridade(noPai);
		}
		return noPai;
	}

//	public void mapeamento(No no) throws IOException, ArvoreNulaException{
//		if(no == null)
//			throw new ArvoreNulaException(); 
//		if(no.getFilhoDaEsquerda() == null && no.getFilhoDaDireita() == null){
//			NoMapa folha = new NoMapa(arestas, no.getSimbolo());
//			folhas.inserirFinal(folha);
//			return;
//		}
//		arestas = arestas + 0;
//		mapeamento(no.getFilhoDaEsquerda());
//		arestas = arestas.substring(0, arestas.length() - 1);
//
//		arestas = arestas + 1;
//		mapeamento(no.getFilhoDaDireita());
//		arestas = arestas.substring(0, arestas.length() - 1);
//
//	}
	public void mapeamento(No no) throws IOException, ArvoreNulaException{
		if(no == null)
			throw new ArvoreNulaException(); 
		if(no.getFilhoDaEsquerda() == null && no.getFilhoDaDireita() == null){
			NoMapa folha = new NoMapa(arestas, no.getSimbolo());
			mapa[(char) no.getSimbolo()] = folha;
			return;
		}
		arestas = arestas + 0;
		mapeamento(no.getFilhoDaEsquerda());
		arestas = arestas.substring(0, arestas.length() - 1);

		arestas = arestas + 1;
		mapeamento(no.getFilhoDaDireita());
		arestas = arestas.substring(0, arestas.length() - 1);

	}
}
