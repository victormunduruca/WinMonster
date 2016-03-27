package br.uefs.ecomp.winmonster.view;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private JButton button1;
	private JButton button2;
	private Splash splash;
	
	public GUI(){
		super("WinMonster");
	}
	
	public void InterfaceGrafica(){
		
		//Configurações da janela do programa
		this.setLayout(null);
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//inicialização dos componentes da janela
		button1 = new JButton("Compactar");
		button2 = new JButton("Descompactar");
		splash = new Splash();
		
		//configuração da posição dos componentes
		button1.setBounds(80, 30, 130, 60);
		button2.setBounds(80, 90, 130, 60);
		
		button1.addActionListener(new CompactarAction());
		button2.addActionListener(new DescompactarAction());
		
		splash.showSplash();
		
		add(button1);
		add(button2);
		
		setVisible(true);
	}
	
}
