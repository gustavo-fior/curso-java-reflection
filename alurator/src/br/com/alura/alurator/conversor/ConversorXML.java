package br.com.alura.alurator.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {

	public String converte(Object objeto) {

		try {

			Class<?> classeDoObjeto = objeto.getClass();

			StringBuilder xmlBuilder = new StringBuilder();

			// Checando se o objeto passado eh um conjunto
			if (objeto instanceof Collection) {
				Collection<?> colecao = (Collection<?>) objeto;

				// Abrindo a tag raiz da colecao
				xmlBuilder.append("<lista>");

				// Adicionando cada objeto da lista ao XML
				colecao.forEach(obj -> {
					String objetoConvertidoEmXML = converte(obj);
					xmlBuilder.append(objetoConvertidoEmXML);
				});

				// Fechando a tag raiz da colecao
				xmlBuilder.append("</lista>");
			} else {

				// Recuperando a anotacao
				NomeTagXML anotacaoDaClasse = classeDoObjeto.getDeclaredAnnotation(NomeTagXML.class);

				// Adicionando nome da classe ao XML
				String nomeDaClasse = anotacaoDaClasse == null ? classeDoObjeto.getName() : anotacaoDaClasse.value();
				xmlBuilder.append("<" + nomeDaClasse + ">");

				for (Field campo : classeDoObjeto.getDeclaredFields()) {

					campo.setAccessible(true);

					// Recuperando a anotacao
					NomeTagXML anotacaoDoCampo = campo.getDeclaredAnnotation(NomeTagXML.class);
					
					// Adicionando nome do campo ao XML
					String nomeDoCampo = anotacaoDoCampo == null ? campo.getName() : anotacaoDoCampo.value();
					
					xmlBuilder.append("<" + nomeDoCampo + ">");
					xmlBuilder.append(campo.get(objeto));
					xmlBuilder.append("</" + nomeDoCampo + ">");

				}

				xmlBuilder.append("</" + nomeDaClasse + ">");
			}

			return xmlBuilder.toString();

		} catch (IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();
			throw new RuntimeException("Erro na geracao do XML");
		}

	}

}
