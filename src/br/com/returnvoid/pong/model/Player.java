package br.com.returnvoid.pong.model;

public class Player {
	private String nome;
	private int points;
	
	public Player(String nome) {
		this.setNome(nome);
		points = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
