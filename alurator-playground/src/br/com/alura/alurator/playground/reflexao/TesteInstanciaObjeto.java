package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.Controle;
import br.com.alura.alurator.playground.controle.SubControle;

public class TesteInstanciaObjeto {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		// Extraindo uma classe 
		Class<Controle> controleClasse1 = Controle.class;

		// Class pode inferir apenas as classes que estenderem de controle 
		SubControle subControle = new SubControle();
		Class<? extends Controle> controleClasse2 = subControle.getClass();
		
		// Class pode inferir qualquer tipo de classe
		Class<?> controleClasse3 = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		// Retorna uma instancia de object (fazer cast para Controle)
		// Como o extraimos direto como Object o Java ja sabe
		Controle objectControle = controleClasse1.newInstance();
		
		// Java nao consegue garantir que sera uma instancia de Controle
		Object outroObjectControle = controleClasse3.newInstance();
		
		System.out.println(objectControle instanceof Controle);
		System.out.println(outroObjectControle instanceof Controle);
		
	}

}
