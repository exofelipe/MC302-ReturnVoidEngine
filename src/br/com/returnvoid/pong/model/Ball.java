package br.com.returnvoid.pong.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import br.com.returnvoid.returnengine.model.Entity;

/*
 * Implementação de bola, subclasse de entidade
 */
public class Ball extends Entity{
	private static final Dimension BALL_DIMENSION = new Dimension(10, 10);
	private Random random = new Random();
	public Ball() {
		this.randomSpeed();
		this.setDimension(BALL_DIMENSION);
	}
	
	// Método para atribuir velocidade aleatória para a bola, note que um mínimo é necessário para não atrapalhar o jogo
	public void randomSpeed() {
		this.vx = ((random.nextDouble() * 0.4) + 0.2 )*(random.nextBoolean()? -1: 1);
		this.vy = ((random.nextDouble() * 0.3) + 0.1 )*(random.nextBoolean()? -1: 1);
	}

	// Verificando a colisão através da intersecção das fronteiras
	@Override
	public boolean checkColision(Entity entity) {
		return this.getBounds().intersects(entity.getBounds());
	}
	
	// Desenhando entidade na tela. (Círculo branco)
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
	    g.fillOval(this.getCoordinate().x, this.getCoordinate().y, 
	    		this.getDimension().width, this.getDimension().height);
	}


}
