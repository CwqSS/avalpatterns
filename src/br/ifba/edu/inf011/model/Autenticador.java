package br.ifba.edu.inf011.model;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;

public class Autenticador {
	
	private AutenticadorStrategy strategy;
	
	public Autenticador() {
		this.strategy = null;
	}
	
	public void setStrategy(AutenticadorStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void autenticar(Documento documento) {
		if(strategy == null) {
			throw new RuntimeException("Nenhuma estratégia selecionada!");
		}
		strategy.autenticar(documento);
	}
}
