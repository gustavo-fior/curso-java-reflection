package br.com.alura.alurator.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorDeObjeto {

	private Object objetoManipulado;

	public ManipuladorDeObjeto(Object objetoManipulado) {
		this.objetoManipulado = objetoManipulado;
	}

	public ManipuladorDeMetodo getMetodo(String nomeMetodoASerExecutado, Map<String, Object> paramsRecebidosNaUrl) {

		// Criando stream de metodos
		Stream<Method> streamDosMetodosDeclarados = Stream.of(objetoManipulado.getClass().getDeclaredMethods());
		
		// Filtrando pela condicao do lambda
		// Achando o primeiro e se nao achar lancar a exception
		Method metodoEncontrado = 
				streamDosMetodosDeclarados.filter(
						metodo -> metodo.getName().equals(nomeMetodoASerExecutado) 							  																				// Metodo tem o mesmo nome
													&& metodo.getParameterCount() == paramsRecebidosNaUrl.values().size()  																// Metodo tem o mesmo n de params
													&& Stream.of(metodo.getParameters())
														.allMatch(parametroDoMetodoDaClasse -> {							  															// Nome do parametro esta nas chaves do
															
															System.out.println(parametroDoMetodoDaClasse.getName());
															return paramsRecebidosNaUrl.keySet().contains(parametroDoMetodoDaClasse.getName()) 											// map de params passado
															&& paramsRecebidosNaUrl.get(parametroDoMetodoDaClasse.getName()).getClass().equals(parametroDoMetodoDaClasse.getType()); 	// Tipo do parametro eh igual 
																																	  													// ao que esta no map
													})
												 )
													.findFirst()
													.orElseThrow(() -> new RuntimeException("Metodo nao encontrado"));
		
		return new ManipuladorDeMetodo(metodoEncontrado, objetoManipulado, paramsRecebidosNaUrl);
	}

}
