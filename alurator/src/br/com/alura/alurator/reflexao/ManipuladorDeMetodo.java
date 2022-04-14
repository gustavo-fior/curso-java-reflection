package br.com.alura.alurator.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorDeMetodo {

	private Method metodoASerExecutado;
	private Object objetoManipulado;
	private Map<String, Object> paramsRecebidosNaUrl;
	private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

	public ManipuladorDeMetodo(Method metodoASerExecutado, Object objetoManipulado,
			Map<String, Object> paramsRecebidosNaUrl) {
		this.metodoASerExecutado = metodoASerExecutado;
		this.objetoManipulado = objetoManipulado;
		this.paramsRecebidosNaUrl = paramsRecebidosNaUrl;
	}

	public Object invoca() {

		List<Object> parametrosFinais = new ArrayList<Object>();

		// Iterando sobre os argumentos do metodo escolhido pelo usuario e pegando os
		// que tem o mesmo nome que os parametros passados na requisicao e adicionando
		// na lista de parametros finais a ser passada a invocacao do metodo
		// -------------------------------------------------------------------
		// Nao esquecer que, por padrao, o java nao consegue recuperar o nome
		// original do metodo, entao precisamos mudar as propriedades do projeto

		Stream.of(metodoASerExecutado.getParameters()).forEach(
				parametroDoMetodo -> parametrosFinais.add(paramsRecebidosNaUrl.get(parametroDoMetodo.getName())));

		try {
			Object retorno = metodoASerExecutado.invoke(objetoManipulado, parametrosFinais.toArray());
			return retorno;

		} catch (IllegalAccessException | IllegalArgumentException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} catch (InvocationTargetException e) {
			
			// tratamento especial e customizado da exceção.
			if (tratamentoExcecao != null) {
				return tratamentoExcecao.apply(metodoASerExecutado, e);
			}

			e.printStackTrace();
			throw new RuntimeException("Erro no método!", e.getTargetException());
		}

	}

	public ManipuladorDeMetodo comTratamentoDeExcecao(
			BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
		this.tratamentoExcecao = tratamentoExcecao;
		return this;
	}

}
