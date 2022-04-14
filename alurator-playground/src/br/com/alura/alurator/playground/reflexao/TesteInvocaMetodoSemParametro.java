package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TesteInvocaMetodoSemParametro {

	public static void main(String[] args) throws Exception {
		
		Class<?> subControleClasse = Class.forName("br.com.alura.alurator.playground.controle.SubControle");
		
		Constructor<?> construtorPadrao = subControleClasse.getDeclaredConstructor();
		construtorPadrao.setAccessible(true);
		
		Object subControle = construtorPadrao.newInstance();
		
		// Metodos publicos e de superclasses
		System.out.println("Metodos publicos e de superclasses");
		for (Method m : subControleClasse.getMethods()) {
			System.out.println(m);
		}
		
		System.out.println();
		
		// Metodos publicos e privados so desta classe
		System.out.println("Metodos publicos e privados so desta classe");
		for (Method m : subControleClasse.getDeclaredMethods()) {
			System.out.println(m);
		}
		
		System.out.println();
		System.out.println("Executando metodo privado da classe SubControle");
		
		Method metodoPrivado = subControleClasse.getDeclaredMethod("subControle2");
		metodoPrivado.setAccessible(true);
		Object retorno = metodoPrivado.invoke(subControle);
		
		System.out.println();
		System.out.println(retorno);
		
	}
	
}
