package brbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Bienvenidos al Battle Royale Bot, hecho por Gonza116");
		List<String> participants = addParticipants();
		System.out.println("¡Que empiece el juego!");
		gameOn(participants);
	}

	private static List<String> addParticipants() {
		List<String> participants = new ArrayList<>();
		System.out.println("Ahora tenemos que añadir los participantes. Necesitas al menos cinco personas.");
		Scanner sc = new Scanner(System.in);
		
		String in = "";
		
		while(!in.equals("q")) {
			System.out.println("Nombre del partipante número " + (participants.size() + 1));
			if(participants.size() > 5) System.out.println("(O 'q' para terminar)");
			in = sc.next().trim();
			if(!in.equals("q")) {
				participants.add(in);
			} else {
				if (participants.size() > 5) {
					break;
				} else {
					System.out.println("Nombre no válido");
				}
			}
		}
		
		sc.close();
		return participants;
	}
	
	private static void gameOn(List<String> participants) throws InterruptedException {
	
		
		while(participants.size() > 2) {
			System.out.println("====================================");
			Integer killer = null, victim = null;
			while(killer == victim) {
				killer = dice(participants);
				victim = dice(participants);
			}
			System.out.println(participants.get(killer) + " ha matado a " + participants.get(victim));
			participants.remove((int) victim);
			
			System.out.println("Quedan " + participants.size() + " personas vivas:");
			for (String p: participants) {
				System.out.println("- " + p);
			}
			countdown(10);
		}
		
		System.out.println("====================================");
		
		Integer winner = randomWinner();
		
		if(winner == 1) {
			System.out.println(participants.get(1) + " ha matado a " + participants.get(0));
			participants.remove(0);
		} else {
			System.out.println(participants.get(0) + " ha matado a " + participants.get(1));
			participants.remove(1);
		}
		
		
		
		System.out.println("¡Ha ganado " + participants.get(0) + "!");
		
	}

	private static void countdown(int i) throws InterruptedException {
		Integer seconds = i * 60;
		System.out.print(seconds + " segundos restantes");
		seconds--;
		while(seconds != 0) {
			System.out.print("\r" + seconds + " segundos restantes");
			TimeUnit.SECONDS.sleep(1);
			seconds--;
		}
		
		
	}

	private static Integer dice(List<String> participants) {
		double randomDouble = Math.random();
		randomDouble = randomDouble * (participants.size() - 1);
		int randomInt = (int) randomDouble;
		return randomInt;
	}
	
	private static Integer randomWinner() {
		double randomDouble = Math.random();
		Integer randomInt;
		if(randomDouble >= 0.5) {
			randomInt = 1;
		} else {
			randomInt = 0;
		}
		
		return randomInt;
	}
}
