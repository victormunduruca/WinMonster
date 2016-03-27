package br.uefs.ecomp.winmonster.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

import br.uefs.ecomp.winmonster.controller.AdministradorController;

public class DescompactarAction implements ActionListener {
	
	AdministradorController controllerAdm;
	
	public DescompactarAction(){
		AdministradorController.zerarSingleton();
		controllerAdm = AdministradorController.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser(); //cria um novo selecionador de arquivos
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //configura o selecionador para s� receber arquivos
		fc.setDialogTitle(" Selecionar Arquivo "); //define o t�tulo da janela de sele��o
		int resposta = fc.showOpenDialog(null); //abre a janela de sele��o e guarda a a��o do usu�rio em resposta
		if (resposta == JFileChooser.APPROVE_OPTION) { 
			File arquivo = fc.getSelectedFile();

		}
		
	}
}