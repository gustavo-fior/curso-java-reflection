package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.anotacao.NomeTagXML;
import br.com.alura.alurator.playground.modelo.Produto;

public class TestaManipulacaoDeAnotacoes {

	public static void main(String[] args) {
		
		Produto produto = new Produto("tenis", 20503, "nike");
		Class<?> classeDoProduto = produto.getClass();
		
		// Se houver a anotacao retorna o valor passado para ela
		classeDoProduto.getDeclaredAnnotation(NomeTagXML.class).value();
	}
	
}
