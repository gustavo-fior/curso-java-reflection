package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TesteInvocaMetodoComParametros {

	public static void main(String[] args) throws Exception {
		
		// Pegando a class da classe Controle
		Class<?> classeControle = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		// Pegando o construtor padrao
		Constructor<?> construtorPadrao = classeControle.getDeclaredConstructor();
		
		// Intancia da classe controle
		Object controle = construtorPadrao.newInstance();
		
		// Recuperando metodo da classe controle com parametros
		Method metodo = classeControle.getDeclaredMethod("metodoControle2", String.class, Integer.class);
		
		// Executando metodo com parametros 
		Object retorno = metodo.invoke(controle, "oi", 3);
		
		System.out.println(retorno);
	}
	
}
