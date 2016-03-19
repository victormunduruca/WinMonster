package br.uefs.ecomp.winmonster.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;

public class AlgoritmoHuffman {
	
	private String arestas = "";
	private File arquivo;

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
	
	/*Gero um arquivo de mapa com  mesmo nome do arquivo original para facilitar
	 *  na hora da busca do mapa na descompactação.
	 */
	public void geradorArquivo(String nome) throws IOException{
		arquivo = new File("mapas/" + nome + ".txt");
		arquivo.createNewFile();
	}
	
	public Fila contaFrequencias(String letras) {
		Fila fila = new Fila();
		for(int i = 0; i < letras.length(); i++) {
			char ch = letras.charAt(i);
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
	
	public void mapeamento(No no) throws IOException{
		if(no != null){
			if(no.getFilhoDaEsquerda() == null && no.getFilhoDaDireita() == null){
				FileWriter file = new FileWriter(arquivo, true);
				BufferedWriter escrever = new BufferedWriter(file);
				escrever.write(no.getSimbolo() + arestas);
				escrever.newLine();
				escrever.close();
				file.close();
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
}
