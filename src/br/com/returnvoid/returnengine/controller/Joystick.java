package br.com.returnvoid.returnengine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


/*
 * Essa classe é um agregador de KeyListeners, permitindo que ações
 * sejam atribuidas à botões e agrupadas de forma mais simples
 */
public class Joystick implements KeyListener{
	// HashMap mapeando as teclas (Int) em KeyListeners
	private HashMap<Integer, KeyListener> buttons = new HashMap<Integer, KeyListener>();
	public final static int UP=38, DOWN = 40, W = 87, S = 83;
	public void addButton(int key, KeyListener listener) {
		this.buttons.put(key, listener);		
	}
	public void removeButton(int key) {
		this.buttons.remove(key);
	}
	// Para as ações KeyTyped, KeyPressed e KeyReleased as ações são delegadas aos respectivos listeners
	@Override
	public void keyTyped(KeyEvent e) {
		if(this.buttons.containsKey(e.getKeyCode())) {
			this.buttons.get(e.getKeyCode()).keyTyped(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(this.buttons.containsKey(e.getKeyCode())) {
			this.buttons.get(e.getKeyCode()).keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(this.buttons.containsKey(e.getKeyCode())) {
			this.buttons.get(e.getKeyCode()).keyReleased(e);
		}
	}
}
