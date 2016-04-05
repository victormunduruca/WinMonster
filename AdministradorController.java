package br.uefs.ecomp.winmonster.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;

import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;
import br.uefs.ecomp.winmonster.util.AlgoritmoHuffman;
import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.Iterador;
import br.uefs.ecomp.winmonster.util.Lista;
import br.uefs.ecomp.winmonster.util.No;
import br.uefs.ecomp.winmonster.util.NoMapa;

public class AdministradorController {


	private static AdministradorController instanciaAdm;
	private AlgoritmoHuffman algoritmoHuffman;

	private AdministradorController(){
		AlgoritmoHuffman.zerarSingleton();
		algoritmoHuffman = AlgoritmoHuffman.getInstance();
	}

	/**
	 * Metodo que instancia um objeto do tipo AdministradorController apenas uma vez.
	 * @return AdministradorController
	 */
	public static AdministradorController getInstance(){
		if(instanciaAdm == null)//Se for a primeira vez a ser usada, então é criada a uma instancia.
			instanciaAdm = new AdministradorController();
		return instanciaAdm;//Retorna a referência do mesmo objeto AdministradorController.
	}

	public AlgoritmoHuffman getHuff(){
		return algoritmoHuffman;
	}
	
	public Fila filaDeFrequencias(File arquivo) throws IOException{
		Fila filaOrdenada = algoritmoHuffman.contaFrequencias(arquivo);
		return filaOrdenada;
	}
	
	public No construirArvore(Fila filaOrdenada) throws FilaNulaException{
		if(filaOrdenada == null)
			throw new FilaNulaException();
		No raiz = algoritmoHuffman.arvore(filaOrdenada);
		return raiz;
	}
	
	/**
	 * Método que reseta a referência "instanciaAdm" permitindo criar uma instância da classe AdministradorController.
	 */
	public static void zerarSingleton() {
		instanciaAdm = null;
	}
	
	public String textoOriginal(File arquivo) throws IOException{
		FileReader file = new FileReader(arquivo);
		BufferedReader leitura = new BufferedReader(file);
		String texto = "";
		while(leitura.ready()){
			texto = texto + leitura.readLine() + "\n";
		}
		System.out.println("" +texto);
		leitura.close();
		file.close();
		return texto;
	}
	
	public int funcaoHash(String texto){
		int valor = 0, posicao = 0, soma = 0;
		for(int i=0; i<texto.length(); i++){
			valor = texto.charAt(i);
			posicao = texto.indexOf(texto.charAt(i));
			soma = soma +  (valor * posicao);
		}
		return soma;
	}
	
	public String decodificarTexto(No arvore, String txtCod) {
		No aux = arvore;
		String txtDecod = "";
		for(int i = 0; i < txtCod.length(); i++) {
			if(txtCod.charAt(i) == '0') {
				aux = aux.getFilhoDaEsquerda();
			} else if(txtCod.charAt(i) == '1') {
				aux = aux.getFilhoDaDireita();
			}
			if(aux.eFolha()) {
				txtDecod += aux.getSimbolo();
				aux = arvore;
			}
		}
		return txtDecod;
	}
	
//	public String buscarCod( Lista mapa , String sequencia) {
//		Iterador iteradorMapa = mapa.iterador ();
//		while(iteradorMapa .temProximo()) {
//			NoMapa noMapa = (NoMapa) iteradorMapa. obterProximo();
//			if(noMapa.getSequencia().equals(sequencia)) {
//				return "" + noMapa.getSimbolo ();
//			}
//		}
//		return null ;
//	}
	
//	public String codificarTexto(Lista mapa , String texto) {
//		String textoCod = "";
//		for(int i = 0; i < texto.length(); i++ ) {
//			String sequenciaBuscada = buscar(mapa, texto.charAt(i));
//			if(sequenciaBuscada != null) {
//				textoCod = textoCod + sequenciaBuscada;
//			} 
//		}
//		return textoCod ;
//	}
	public String codificarTexto(Lista mapa , String texto) {
		//String textoCod = "";
		StringBuffer textoCod = new StringBuffer();
		for(int i = 0; i < texto.length(); i++ ) {
			/************************/
			textoCod.append(instanciaAdm.getHuff().getMapa()[i]);
		}
		return textoCod.toString();
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
	
	public void compactar(No raiz, String txtCodificado, String caminho, String nomeArq) throws IOException {
		nomeArq = nomeArq.replace(".txt", ".monster");
		caminho = caminho + nomeArq;		
		File escritaArquivo = new File(caminho);
		FileOutputStream fos = new FileOutputStream(escritaArquivo);
	    ObjectOutputStream escrever = new ObjectOutputStream(fos);
	    escrever.writeObject(raiz);
	    BitSet bits = new BitSet();
	    boolean bitFinal = escreverBitSet(bits, txtCodificado);
	    if(bitFinal) {
	    	escrever.writeBoolean(true);
	    } else {
	    	escrever.writeBoolean(false);
	    }
	    escrever.writeObject(bits);
	    
	    escrever.close();
	    fos.close();
	}
	
	public boolean escreverBitSet(BitSet bits, String texto) {
		for(int i = 0; i < texto.length() ; i++) {
			if(texto.charAt(i) == '0') {
				bits.clear(i);
			} else if(texto.charAt(i) == '1') {
				bits.set(i);
			}
		}
		if(texto.charAt(texto.length() - 1) == '0') {
			bits.set(texto.length());
			return true;
		}
		return false;
	}
	
	public String bitToString(BitSet bits, boolean bitFinal) {
		String strBits = "";
		int tamanho;
		if(bitFinal) {
			tamanho = bits.length() - 1;
		} else {
			tamanho = bits.length();
		}
		for(int i = 0; i < tamanho; i++) {
			if(bits.get(i)) {
				strBits += "1";
			} else {
				strBits += "0";
			}
		}
		
		return strBits;
	}
	
	public No lerMapa(File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
	    ObjectInputStream entrada = new ObjectInputStream(fis);
	    No mapa = (No) entrada.readObject();
	    entrada.close();
	    return mapa;
	}
	
	public String lerTexto(File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
	    ObjectInputStream entrada = new ObjectInputStream(fis);
	    entrada.readObject();
	    boolean bitFinal = (boolean) entrada.readBoolean();
	    BitSet bits = (BitSet) entrada.readObject();
	    String texto = bitToString(bits, bitFinal);
	    entrada.close();
	    return texto;		
	}
	
	public void descompactar(File file, String nomeArq) throws ClassNotFoundException, IOException, ArvoreNulaException {
		No mapa = lerMapa(file);
		String txtCod = lerTexto(file);
		//mapeamento(mapa);
		String txtDecod = decodificarTexto(mapa, txtCod);
		//String novoNomeArq = nomeArq.replace(".monster", ".txt");
		File arquivo = new File(file.getPath().replace(nomeArq, ""), nomeArq + ".txt"); 
		FileWriter fw = new FileWriter(arquivo);  
		BufferedWriter bw = new BufferedWriter(fw);
		String txt = txtDecod.replaceAll("\n", System.lineSeparator());
		bw.write(txt);
		bw.close();
	}
	
}
