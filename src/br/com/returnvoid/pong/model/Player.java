package br.com.returnvoid.pong.model;

/*
 * Classe base para guardar informações basicas do jogador.
 * No exemplo, nome e pontos
 */

public class Player {
	private String name;
	private int points;
	
	public Player(String name) {
		this.setName(name);
		this.points = 0;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return this.points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
