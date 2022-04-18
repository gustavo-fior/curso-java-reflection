package br.com.alura.alurator.reflexao;

public class Reflexao {

	public ManipuladorDeClasse refleteClasse(String fullyQualifiedName) {
		Class<?> classeController = getClasse(fullyQualifiedName);
		return new ManipuladorDeClasse(classeController);
	}

	public Class<?> getClasse(String fullyQualifiedName) {

		try {
			Class<?> classeController = Class.forName(fullyQualifiedName);
			return classeController;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
