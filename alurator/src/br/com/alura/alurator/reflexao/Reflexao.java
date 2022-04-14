package br.com.alura.alurator.reflexao;

public class Reflexao {

	public ManipuladorDeClasse refleteClasse(String fullyQualifiedName) {
		
		try {
			Class<?> classeController = Class.forName(fullyQualifiedName);
			return new ManipuladorDeClasse(classeController);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

}
