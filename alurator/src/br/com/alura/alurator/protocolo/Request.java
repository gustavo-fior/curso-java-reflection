package br.com.alura.alurator.protocolo;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String nomeControle;
	private String nomeMetodo;
	
	private Map<String, Object> urlParametros;

	public Request(String url) {

		// /classe/metodo
		// /produto / lista
		//
		// /classe/metodo?param1=valor&param2=valor
		// /produto/filtra ? nome=valor&marca=valor
		
		String semPrimeiraBarra = url.replaceFirst("/", "");
		String[] partesUrl = semPrimeiraBarra.split("[?]"); // Para a regex interpretar como um char
		
		String[] controleEMetodo = partesUrl[0].split("/");

		this.nomeControle = controleEMetodo[0].substring(0, 1).toUpperCase() + controleEMetodo[0].substring(1) + "Controller";
		this.nomeMetodo = controleEMetodo[1];
		
		// Maior que 1 significa que recebemos parametros
		urlParametros = partesUrl.length > 1 
				? new QueryParamsBuilder().comParamentros(partesUrl[1]).build()
				: new HashMap<String, Object>();

	}

	public String getNomeController() {
		return this.nomeControle;
	}

	public String getNomeMetodo() {
		return this.nomeMetodo;
	}
	
	public Map<String, Object> getUrlParametros() {
		return urlParametros;
	}
}
