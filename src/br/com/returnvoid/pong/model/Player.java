package br.com.returnvoid.pong.model;

public class Player {
	private String nome;
	private int points;
	
	public Player(String nome) {
		this.setNome(nome);
		this.points = 0;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPoints() {
		return this.points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
