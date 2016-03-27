package br.uefs.ecomp.winmonster.view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Splash extends JWindow {

	public void showSplash(){
		JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        
        // Configura a posi��o e o tamanho da janela
        int largura = 500;
        int altura = 400;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-largura)/2;
        int y = (screen.height-altura)/2;
        setBounds(x,y,largura,altura);
        
        // Constr�i o splash screen
        JLabel label = new JLabel(new ImageIcon("imagem\\img.png"));
        content.add(label, BorderLayout.CENTER);
   
        // Torna vis�vel
        setVisible(true);
        
        // Espera ate que os recursos estejam carregados
        try { 
        	Thread.sleep(2000); 
        } catch (Exception e){
        	e.printStackTrace();
        }        
        setVisible(false);   
	}

}
