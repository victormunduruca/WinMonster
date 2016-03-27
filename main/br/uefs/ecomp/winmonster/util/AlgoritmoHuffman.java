package br.uefs.ecomp.winmonster.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;

public class AlgoritmoHuffman {

	private String arestas = "";
	private File arquivo;

	private Lista folhas = new Lista();

	private static AlgoritmoHuffman instanciaAdm;

	private AlgoritmoHuffman() {

	}

	public static AlgoritmoHuffman getInstance(){
		if(instanciaAdm == null)
			instanciaAdm = new AlgoritmoHuffman();
		return instanciaAdm;
	}

	public static void zerarSingleton() {
		instanciaAdm = null;
	}

	public void geradorArquivo(String nome) throws IOException{
		arquivo = new File("mapas/" + nome + ".txt");
		arquivo.createNewFile();
	}
	
	public Fila contaFrequencias(File arquivo) throws IOException{
		Fila fila = new Fila();
		FileReader file = new FileReader(arquivo);
		BufferedReader leitura = new BufferedReader(file);
		while(leitura.ready()){
			String texto = leitura.readLine() + "\n";
			for(int i = 0; i < texto.length(); i++) {
				char ch = texto.charAt(i);
				No no = recuperarNo(fila, ch);
				if(no == null){
					No novoNo = new No();
					novoNo.setSimbolo(ch);
					fila.inserirFinal(novoNo);
				} 
				else{
					no.setFrequencia(no.getFrequencia() + 1);
				}
			}
		}
		leitura.close();
		file.close();
		Fila filaOrdenada = new Fila();
		while(!fila.estaVazia()) {
			filaOrdenada.inserirPrioridade(fila.removerInicio());
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

	public No arvore(Fila fila) {
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


	public void mapeamento(No no) throws IOException, ArvoreNulaException{
		if(no == null)
			throw new ArvoreNulaException();
		if(no.getFilhoDaEsquerda() == null && no.getFilhoDaDireita() == null){
			NoMapa folha = new NoMapa(arestas, no.getSimbolo());
			folhas.inserirFinal(folha);
			return;
		}
		arestas = arestas + 0;
		mapeamento(no.getFilhoDaEsquerda());
		arestas = arestas.substring(0, arestas.length() - 1);

		arestas = arestas + 1;
		mapeamento(no.getFilhoDaDireita());
		arestas = arestas.substring(0, arestas.length() - 1);

	}

	public void escreverMapa() throws IOException{
		FileWriter file = new FileWriter(arquivo, true);
		BufferedWriter escrever = new BufferedWriter(file);
			
		MeuIterador i = (MeuIterador) folhas.iterador();
		NoMapa noAtual = null;
		while(i.temProximo()){
			noAtual = (NoMapa) i.obterProximo();
			escrever.write(noAtual.getSimbolo() + noAtual.getSequencia());
			escrever.newLine();
		}
		escrever.close();
		file.close();
	}
	
	public Lista getLista(){
		return folhas;
	}
	
	public void decodificarArvore(Celula mapa, No arvore) {
		if(mapa == null) {
			return;
		}
		NoMapa noMapa = (NoMapa) mapa.getObjeto();
		if(noMapa.getSequencia().equals("")) {
			arvore.setSimbolo(noMapa.getSimbolo());
			mapa = mapa.getProximo();
			return;
		}
		if(noMapa.getSequencia().charAt(0) == '0') {
			No novoNo = new No();
			arvore.setFilhoDaEsquerda(novoNo);
			noMapa.setSequencia(noMapa.getSequencia().substring(1));
			decodificarArvore(mapa, arvore.getFilhoDaEsquerda());
		} else if(noMapa.getSequencia().charAt(0) == '1') {
			No novoNo = new No();
			arvore.setFilhoDaEsquerda(novoNo);
			noMapa.setSequencia(noMapa.getSequencia().substring(1));
			decodificarArvore(mapa, arvore.getFilhoDaDireita());
		}
	}
	public String decodificarTexto(Lista mapa , String textoCod) {
		int i ;
		String aux = "";
		String textoDecod = "";
		for(i = 0; i < textoCod. length(); i++ ) {
			String txtBuscado = buscarCod(mapa , textoCod .charAt( i) + "");
			if(txtBuscado == null) {
				aux = aux + textoCod .charAt( i);
			} else {
				textoDecod = textoDecod + txtBuscado ;
				aux = "";
			}     
		}
		return textoDecod ;
	}
	public String buscarCod( Lista mapa , String sequencia) {
		Iterador iteradorMapa = mapa.iterador ();
		while(iteradorMapa .temProximo()) {
			NoMapa noMapa = (NoMapa) iteradorMapa. obterProximo();
			if(noMapa .getSequencia() .equals( sequencia)) {
				return "" + noMapa.getSimbolo ();
			}
		}
		return null ;
	}
	
	public String codificarTexto(Lista mapa , String texto) {
		String textoCod = "";
		for(int i = 0; i < texto. length(); i++ ) {
			String sequenciaBuscada = buscar(mapa , texto .charAt( i));
			if(sequenciaBuscada != null) {
				textoCod = textoCod + sequenciaBuscada;
			} 
		}
		return textoCod ;
	}
	public String buscar(Lista mapa , char simbolo) {
		Iterador iteradorMapa = mapa.iterador ();
		while(iteradorMapa .temProximo()) {
			NoMapa noMapa = (NoMapa) iteradorMapa. obterProximo();
			if(noMapa.getSimbolo() ==  simbolo) {
				return noMapa.getSequencia();
			}
		}
		return null ;
	}
	public String hash(String texto){
		String novomd5 = "";
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("MD5");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));
		novomd5 = hash.toString(16);
		return novomd5;
	}
	
}
