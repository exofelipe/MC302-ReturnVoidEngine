package br.com.returnvoid.returnengine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;

public class Joystick implements KeyListener{
	private HashMap<Integer, KeyListener> buttons = new HashMap<Integer, KeyListener>();
	
	public void addButton(int key, KeyListener listener) {
		this.buttons.put(key, listener);		
	}
	public void removeButton(int key) {
		this.buttons.remove(key);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(this.buttons.containsKey(e.getKeyCode())) {
			this.buttons.get(e.getKeyCode()).keyTyped(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("pressed "+e.getKeyCode());
		if(this.buttons.containsKey(e.getKeyCode())) {
			this.buttons.get(e.getKeyCode()).keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("released "+e.getKeyCode());
		if(this.buttons.containsKey(e.getKeyCode())) {
			this.buttons.get(e.getKeyCode()).keyReleased(e);
		}
	}
}
