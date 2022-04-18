package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoc {

	// Map para guardar um tipo fonte e um tipo destino para o container criar
	// quando achar a fonte
	private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();

	// Metodo para pegarmos instancia sem cria-las diretamente nas classes
	public Object getInstancia(Class<?> tipoFonte) {

		Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);

		// Caso a classe precisando de instancia nao possa ser intanciada
		// instanciamos a classe referente a sua chave no mapa
		if (tipoDestino != null) {
			return getInstancia(tipoDestino);
		}

		// Recuperando todos os construtores
		Stream<Constructor<?>> construtores = Stream.of(tipoFonte.getDeclaredConstructors());

		// Recuperando construtor padrao
		Optional<Constructor<?>> construtorPadrao = construtores
				.filter(construtor -> construtor.getParameterCount() == 0).findFirst();

		try {

			// Caso tenha um construtor padrao
			if (construtorPadrao.isPresent()) {
				Object instancia = construtorPadrao.get().newInstance();
				return instancia;

			} else {

				// Pegando o primeiro construtor da lista
				Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];

				// Lista de parametros a ser populada
				List<Object> parametrosDoConstrutor = new ArrayList<>();

				// Para cada parametro deste construtor
				for (Parameter parametro : construtor.getParameters()) {

					// Adicionando instancias dos parametros dentro da lista com recursao
					Class<?> tipoDoParametro = parametro.getType();
					parametrosDoConstrutor.add(getInstancia(tipoDoParametro));
				}

				return construtor.newInstance(parametrosDoConstrutor.toArray());

			}

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {

			throw new RuntimeException(e);

		}

	}

	// Fazendo a validacao de compatiblidade com o generics
	public <T, K extends T> void registrar(Class<?> tipoFonte, Class<?> tipoDestino) {
		mapaDeTipos.put(tipoFonte, tipoDestino);
	}

}
