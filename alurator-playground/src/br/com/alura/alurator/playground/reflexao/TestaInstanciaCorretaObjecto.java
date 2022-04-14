package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import br.com.alura.alurator.playground.controle.SubControle;

public class TestaInstanciaCorretaObjecto {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<SubControle> subControleClasse1 = SubControle.class;

		Class<?> subControleClasse2 = Class.forName("br.com.alura.alurator.playground.controle.SubControle");

		Class<?> ControleClasse1 = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		
		

		// Pegando o construtor padrao da classe SubControle, pois nao passamos nenhum
		// paramentro para o metodo getConstructor
		Constructor<SubControle> constructorPublicoSubControle = subControleClasse1.getConstructor();
		
		
		

		// Pegando o construtor privado da classe SubControle
		Constructor<SubControle> constructorPrivadoSubControle = subControleClasse1.getDeclaredConstructor(String.class);

		// Retornaria uma IllegalAccessException pois eh privado
		constructorPrivadoSubControle.setAccessible(true);
		
		// Pegando uma instancia do objeto a partir do construtor privado
		SubControle subControle = constructorPrivadoSubControle.newInstance("oi");
		
	}

}
