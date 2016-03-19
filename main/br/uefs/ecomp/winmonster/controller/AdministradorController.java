package br.uefs.ecomp.winMonster.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import br.uefs.ecomp.winMonster.util.AlgoritmoHuffman;

public class AdministradorController {

	AlgoritmoHuffman huff = new AlgoritmoHuffman();

	public AdministradorController(){
		
	}
	
	public AlgoritmoHuffman getHuff(){
		return huff;
	}
	
	public String lerArquivo(File arquivo) throws IOException {
		FileReader file = new FileReader(arquivo);
		BufferedReader leitura = new BufferedReader(file);
		String texto = leitura.readLine();
		while(leitura.ready()){
			texto = texto + "\n" + leitura.readLine();
		}
		leitura.close();
		file.close();
		return texto;
	}
	
	public void diegod() {
		System.out.println("DIEGOD");
	}
}
