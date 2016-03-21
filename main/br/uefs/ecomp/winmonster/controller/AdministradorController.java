package br.uefs.ecomp.winmonster.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import br.uefs.ecomp.winmonster.util.AlgoritmoHuffman;

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
		if(instanciaAdm == null)//Se for a primeira vez a ser usada, ent�o � criada a uma instancia.
			instanciaAdm = new AdministradorController();
		return instanciaAdm;//Retorna a refer�ncia do mesmo objeto AdministradorController.
	}

	public AlgoritmoHuffman getHuff(){
		return algoritmoHuffman;
	}

	/**
	 * M�todo que reseta a refer�ncia "instanciaAdm" permitindo criar uma inst�ncia da classe AdministradorController.
	 */
	public static void zerarSingleton() {
		instanciaAdm = null;
	}
}
