package br.com.returnvoid.pong.model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import br.com.returnvoid.returnengine.controller.ImageLoader;

/*
 * Subclasse de bola usada no EasterEGG, herda todas as características de bola
 * se alterando apenas o método para desenhar na tela e as dimensões
 */
public class EasterEggBall extends Ball {
	// Array com as imagens do easteregg (armazenadas nos resources do executável)
	private static String[] images = new String[] {
			"easteregg.png",
			"easteregg2.jpg",
			"easteregg3.jpg",
			"easteregg4.png",
	};
	private BufferedImage img;
	private static Random random = new Random();
	private static final Dimension EASTEREGG_BALL_DIMENSION= new Dimension(40, 40);
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawImage(this.img, this.getCoordinate().x, this.getCoordinate().y, 
				EASTEREGG_BALL_DIMENSION.height, EASTEREGG_BALL_DIMENSION.width, null);
	}
	public EasterEggBall() {
		// Sorteando uma imagem do array e carregando com nosso singleton
		BufferedImage img = ImageLoader.getInstance().load(images[random.nextInt(images.length)]);
		
		// Além disso vamos recortar a imagem em um círculo
		BufferedImage destination = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = destination.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK); // A cor não importa muito, é apenas para fazer o AntiAlias
        graphics.fillOval(0, 0, img.getHeight(), img.getWidth());
        graphics.setComposite(AlphaComposite.SrcIn); // Desenhe apenas a parte de dentro do círculo
        graphics.drawImage(img, 0, 0, null);
		
		this.img = destination;
		this.setDimension(EASTEREGG_BALL_DIMENSION);
	}
}
