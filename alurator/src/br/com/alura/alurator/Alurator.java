package br.com.alura.alurator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.ioc.ContainerIoc;
import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.ManipuladorDeObjeto;
import br.com.alura.alurator.reflexao.Reflexao;

public class Alurator {

	private String pacoteBase;
	
	private ContainerIoc containerIoc;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
		this.containerIoc = new ContainerIoc();
	}

	public Object executa(String url) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		Request request = new Request(url);
		
		String nomeControle = request.getNomeController();
		String nomeMetodo = request.getNomeMetodo();
		Map<String, Object> parametros = request.getUrlParametros();
		
		
		// Pegando a class do controle
		Class<?> classeControle = new Reflexao().getClasse(pacoteBase + nomeControle);
		
		// Pegando sua instancia do controle IOC
		Object instanciaDoControle = containerIoc.getInstancia(classeControle);
		
		Object retornoDoMetodo = new ManipuladorDeObjeto(instanciaDoControle)
											.getMetodo(nomeMetodo, parametros)
											.comTratamentoDeExcecao((metodo, excecao) -> {
												System.out.println("Erro no método " + metodo.getName() + 
														" da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
												
							                    throw new RuntimeException("ERRO!");
											})
											.invoca();
		
		retornoDoMetodo = new ConversorXML().converte(retornoDoMetodo);

		return retornoDoMetodo;
	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {

		containerIoc.registrar(tipoFonte, tipoDestino);
		
	}
}
