package br.com.returnvoid.returnengine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
 * Classe implementada com o objetivo de "ouvir" o teclado, esperando uma
 * sequência de teclas (implementada como uma String) ser digitada, e então
 * disparando um Runnable em uma Thread.
 * 
 * Na prática executa uma função de forma assíncrona caso a frase seja digitada
 */
public class EasterEgg implements KeyListener{
	private String triggerSequence, typedSequence;
	private Runnable runnable;
	
	public EasterEgg(String triggerSequence, Runnable runnable) {
		this.triggerSequence = triggerSequence;
		this.runnable = runnable;
		
		this.typedSequence = "";
	}
	/*
	 * Cada vez que uma tecla é apertada a sequencia de EasterEgg é monitorada.
	 * Caso complete, o objeto Runnable é disparado em uma nova Thread
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("Typed "+ typedSequence+ Character.toLowerCase(e.getKeyChar()));
		if(triggerSequence.startsWith(typedSequence + Character.toLowerCase(e.getKeyChar()))) {
			typedSequence += e.getKeyChar();
			//System.out.println(typedSequence);
			if(triggerSequence.equals(typedSequence)) {
				new Thread(this.runnable).start();
				typedSequence = "";
			}
		}else {
			this.typedSequence = ""; //Restart
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
