package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Field;

import br.com.alura.alurator.playground.modelo.Produto;

public class TestaManipulacaoDeAtributos {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Object produto = new Produto("Produto 1", 20.0, "Marca 1");

		Class<?> classDoProduto = produto.getClass();
		
		// Tentando recuperar atributo privado da superclasse
		System.out.println(classDoProduto.getField("id").get(produto));

		for (Field campo : classDoProduto.getDeclaredFields()) {
			campo.setAccessible(true);
			System.out.println(campo.getName() + ": " + campo.get(produto));
		}

	}

}
