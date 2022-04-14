package br.com.alura.alurator.reflexao;

import java.lang.reflect.Constructor;

public class ManipuladorDeClasse {

	private Class<?> classeManipulada;

	public ManipuladorDeClasse(Class<?> classeManipulada) {
		this.classeManipulada = classeManipulada;
	}

	private ManipuladorConstrutor getConstrutorPadrao() {

		try {
			Constructor<?> construtorPadrao = classeManipulada.getDeclaredConstructor();
			return new ManipuladorConstrutor(construtorPadrao);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ManipuladorDeObjeto criaInstancia() {
		
		Object instanciaDaClasseManipulada = getConstrutorPadrao().invoca();
		return new ManipuladorDeObjeto(instanciaDaClasseManipulada);
		
	}

}
