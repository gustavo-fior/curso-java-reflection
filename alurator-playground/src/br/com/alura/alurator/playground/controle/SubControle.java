package br.com.alura.alurator.playground.controle;

public class SubControle extends Controle{

	public SubControle() {
	}
	
	private SubControle(String s) {
	}
	
	public void subControle1() {
		System.out.println("Executando metodo SubControle.metodoSubControle1()");
	}
	
	private String subControle2() {
		System.out.println("Executando metodo SubControle.metodoSubControle2()");
		
		return "RETORNO do metodo SubControle.metodoSubControle2()";
		
	}
	
}
