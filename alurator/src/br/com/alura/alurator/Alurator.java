package br.com.alura.alurator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.Reflexao;

public class Alurator {

	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}

	public Object executa(String url) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Request request = new Request(url);
		
		String nomeControle = request.getNomeController();
		String nomeMetodo = request.getNomeMetodo();
		Map<String, Object> parametros = request.getUrlParametros();
		
		
		// Criando class com base no full qualified name, depois pegando o construtor
		// padrao e criando uma instancia da classe de controller para criarmos um
		// metodo com base no nome passado
		Object retornoDoMetodo = new Reflexao()
											.refleteClasse(pacoteBase + nomeControle)
											.criaInstancia()
											.getMetodo(nomeMetodo, parametros)
											.comTratamentoDeExcecao((metodo, excecao) -> {
												System.out.println("Erro no m�todo " + metodo.getName() + 
														" da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
												
							                    throw new RuntimeException("ERRO!");
											})
											.invoca();
		
		retornoDoMetodo = new ConversorXML().converte(retornoDoMetodo);

		return retornoDoMetodo;
	}
}
