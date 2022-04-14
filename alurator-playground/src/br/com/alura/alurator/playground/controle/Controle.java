package br.com.alura.alurator.playground.controle;

import java.util.List;

public class Controle {
	private List<String> lista = List.of("item 1", "item 2", "item 3");

	public Controle() {
	}

	public Controle(String s) {

	}

	private Controle(String s, String t) {

	}

	public List<String> getLista() {
		return lista;
	}

	private void metodoControle1() {
	}

	public void metodoControle2(String s1) {
		System.out.println("Executando Controle.metodoControle2(String s1)");
		System.out.println("Parametro obitdo com s1: " + s1);
	}
	
	public void metodoControle2(String s1, String s2) {
		System.out.println("Executando Controle.metodoControle2(String s1, String s2)");
		System.out.println("Parametro obitdo com s1: " + s1);
		System.out.println("Parametro obitdo com s2: " + s2);
	}
	
	public void metodoControle2(String s1, Integer i1) {
		System.out.println("Executando Controle.metodoControle2(String s1, Integer i1)");
		System.out.println("Parametro obitdo com s1: " + s1);
		System.out.println("Parametro obitdo com i1: " + i1);
	}
}
