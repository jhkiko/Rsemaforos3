package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

import model.Carro;

public class CorridaThread extends Thread{
	private Semaphore [] escuderia;
	private Semaphore corrida;
	private Carro [] carros;
	private static int finalizados = 0;
	private int id;
	private int pista;
	
	public CorridaThread(Semaphore [] escuderia, Semaphore corrida, Carro [] carros, int id) {
		this.escuderia = escuderia;
		this.corrida = corrida;
		this.carros = carros;
		this.id = id;
		this.pista = 300;
	}
	
	@Override
	public void run() {
		while(!acabou()) {
			try {
				this.escuderia[carros[id].getEscuderia() - 1].acquire();
				this.corrida.acquire();
				correr();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				this.corrida.release();
				this.escuderia[this.carros[id].getEscuderia() - 1].release();
			}
		}
		if(acabouTotal()) {
			podio();
		}
	}
	
	private void correr() {
		double time = System.nanoTime();
		int aux = pista;
		Random random = new Random();
		while(aux > 0) {
			aux -= (random.nextInt(60) + 20);
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		time = System.nanoTime() - time;
		time = time/Math.pow(10, 9);
		System.out.println("O carro " + this.carros[id].getId() + " da escuderia "
				+ this.carros[id].getEscuderia() + " correm em " + time + "s.");
		System.out.println();
		if(this.carros[id].getVoltas() == 0) {
			this.carros[id].setTime(time);
		}else if(this.carros[id].getTime() > time) {
			this.carros[id].setTime(time);
		}
		this.carros[id].setVoltas(this.carros[id].getVoltas() + 1);
	}
	
	private boolean acabou() {
		if(this.carros[id].getVoltas() == 3) {
			finalizados ++;
			return true;
		}else {
			return false;
		}
	}
	
	private boolean acabouTotal() {
		return finalizados == 14;
	}
	
	private void podio() {
		for (int i = 0; i < this.carros.length; i++) {
			for (int j = i+1; j < this.carros.length; j++) {
				if(this.carros[i].getTime() > this.carros[j].getTime()) {
					Carro aux = this.carros[i];
					this.carros[i] = this.carros[j];
					this.carros[j] = aux;
				}
			}
		}
		int pos = 1;
		for(Carro var : this.carros) {
			System.out.println(pos + "º lugar - Carro " + var.getId() + " da escuderia " + var.getEscuderia()
			+ " com o tempo minimo de " + var.getTime() + "s.");
			pos++;
		}
	}
}
