package br.com.returnvoid.pong.controller;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame gameWindow = new JFrame();
		gameWindow.setSize(800, 600);
		gameWindow.setVisible(true);
		PongGame game = new PongGame(gameWindow);
		game.run();
	}

}
