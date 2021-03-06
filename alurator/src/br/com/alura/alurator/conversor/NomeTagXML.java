package br.com.alura.alurator.conversor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Anotacao nao tem um palavra chave reservada, pois 
// por baixo dos panos, ela eh uma interface especial
// e eh um subinterface da interface annotation

// Nao pode extender nem implementar nada

@Retention(RetentionPolicy.RUNTIME) // Quando a notacao vai ser levada em consideracao
@Target({ ElementType.TYPE, ElementType.FIELD }) //Onde essa anotacao pode ser usada - por ser em mais de um lugar passamos um array
public @interface NomeTagXML {

	// Apenas um atributo na anotacao = value
	public String value();

}
