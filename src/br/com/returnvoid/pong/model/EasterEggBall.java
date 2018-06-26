package br.com.returnvoid.pong.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.com.returnvoid.returnengine.controller.ImageLoader;

public class EasterEggBall extends Ball {
	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		BufferedImage img = ImageLoader.getInstance().load("easteregg.png");
		g.drawImage(img, this.getCoordinate().x, this.getCoordinate().y, 
				20, 20, null);
	}
}
