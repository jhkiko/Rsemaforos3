package view;

import java.util.concurrent.Semaphore;

import controller.CorridaThread;
import model.Carro;

public class Main {

	public static void main(String[] args) {
		Semaphore [] escuderia = new Semaphore [7];
		Semaphore corrida = new Semaphore(5);
		Carro [] carros = new Carro [14];
		int idEscuderia = 1;
		for (int i = 0; i < carros.length; i++) {
			if(idEscuderia == 8) {
				idEscuderia = 1;
			}
			carros[i] = new Carro(i + 1, idEscuderia);
			idEscuderia ++;
		}
		for (int i = 0; i < escuderia.length; i++) {
			escuderia[i] = new Semaphore(1);
		}
		for(int i = 0; i<14; i++) {
			CorridaThread t = new CorridaThread(escuderia, corrida, carros, i);
			t.start();
		}
	}

}
