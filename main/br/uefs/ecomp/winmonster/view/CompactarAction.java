package br.uefs.ecomp.winmonster.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import br.uefs.ecomp.winmonster.controller.AdministradorController;
import br.uefs.ecomp.winmonster.exceptions.ArvoreNulaException;
import br.uefs.ecomp.winmonster.exceptions.FilaNulaException;
import br.uefs.ecomp.winmonster.util.Fila;
import br.uefs.ecomp.winmonster.util.No;

public class CompactarAction implements ActionListener {

	AdministradorController controllerAdm;
	
	public CompactarAction(){
		AdministradorController.zerarSingleton();
		controllerAdm = AdministradorController.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser(); 
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		fc.setDialogTitle(" Selecionar Arquivo "); 
		int resposta = fc.showOpenDialog(null);
		if (resposta == JFileChooser.APPROVE_OPTION) { 
			File arquivo = fc.getSelectedFile();
			try{
				Fila fila = controllerAdm.filaDeFrequencias(arquivo);
				No raiz = controllerAdm.construirArvore(fila);
				controllerAdm.getHuff().mapeamento(raiz);
				
			}catch(IOException e2){
				e2.printStackTrace();
			} catch (FilaNulaException e3) {
				e3.printStackTrace();
			} catch (ArvoreNulaException e4) {
				e4.printStackTrace();
			}
		}
		
	}

	
}
