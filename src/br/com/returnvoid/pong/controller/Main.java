package br.com.returnvoid.pong.controller;

import java.awt.Dimension;

import javax.swing.JFrame;

import br.com.returnvoid.pong.view.MainScreen;
import br.com.returnvoid.returnengine.controller.Joystick;

public class Main {

	public static void main(String[] args) {
		/* JFrame gameWindow = new JFrame();
		gameWindow.setSize(800, 600);
		PongGame game = new PongGame(gameWindow);
		game.run(); */
		
		MainScreen sc = new MainScreen();
		sc.setVisible(true);
	}
}
