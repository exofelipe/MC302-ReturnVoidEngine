package br.com.returnvoid.returnengine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EasterEgg implements KeyListener{
	private String triggerSequence;
	private Runnable runnable;
	
	public EasterEgg(String triggerSequence, Runnable runnable) {
		this.triggerSequence = triggerSequence;
		this.runnable = runnable;
	}
	//TODO cada vez que uma tecla for apertada vá verificando se está na sequencia
	//Caso complete a sequencia, execute o runnable
	//Caso tecla fora da sequencia, resete
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
