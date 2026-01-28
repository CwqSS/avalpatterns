package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;

public class ConfidencialAutenticadorStrategy implements AutenticadorStrategy {

	@Override
	public void autenticar(Documento documento) {
		String numero = "DOC-" + System.currentTimeMillis();
		documento.setNumero(numero);
	}

}
