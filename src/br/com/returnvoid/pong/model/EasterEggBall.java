package br.com.returnvoid.pong.model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import br.com.returnvoid.returnengine.controller.ImageLoader;

public class EasterEggBall extends Ball {
	private static String[] images = new String[] {
			"easteregg.png",
			"easteregg2.jpg",
			"easteregg3.jpg",
			"easteregg4.png",
	};
	private BufferedImage img;
	private static Random random = new Random();
	private final Dimension dimension= new Dimension(40, 40);
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawImage(this.img, this.getCoordinate().x, this.getCoordinate().y, 
				dimension.height, dimension.width, null);
	}
	public EasterEggBall() {
		BufferedImage img = ImageLoader.getInstance().load(images[random.nextInt(images.length)]);
		
		BufferedImage destination = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = destination.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK); // The color here doesn't really matter
        graphics.fillOval(0, 0, img.getHeight(), img.getWidth());
        graphics.setComposite(AlphaComposite.SrcIn); // Only paint inside the oval from now on
        graphics.drawImage(img, 0, 0, null);
		
		this.img = destination;
		this.setDimension(dimension);
	}
}
