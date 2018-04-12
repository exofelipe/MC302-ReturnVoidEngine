package br.com.returnvoid.returnengine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends JFrame{
	public Window(Dimension dimension) {
		this.setSize(dimension);
		this.setTitle("Game");
		this.getContentPane().setBackground(new Color(0, 0, 0));	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void draw(BufferedImage image, Point point) {
		//TODO desenhar grafico em um frame diferente do exibido
	}
	
	public void show() {
		//TODO exibir o frame "desenhado" e jogar fora o que esta sendo exibido
	}
	
}
