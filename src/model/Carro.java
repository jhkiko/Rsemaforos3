package model;

public class Carro {
	private int id;
	private int escuderia;
	private double time;
	private int voltas;
	
	public Carro(int id, int escuderia) {
		this.id = id;
		this.escuderia = escuderia;
		this.time = 0;
		this.voltas = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEscuderia() {
		return escuderia;
	}

	public void setEscuderia(int escuderia) {
		this.escuderia = escuderia;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getVoltas() {
		return voltas;
	}

	public void setVoltas(int voltas) {
		this.voltas = voltas;
	}
	
	
}
