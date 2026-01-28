package br.ifba.edu.inf011.strategy;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;

public class ExportacaoAutenticadorStrategy implements AutenticadorStrategy {

	@Override
	public void autenticar(Documento documento) {
		String numero;
		if (documento.getPrivacidade() == Privacidade.SIGILOSO) {
            numero = "SECURE-" + documento.hashCode();
        } else {
            numero = "PUB-" + documento.hashCode();
        }
		documento.setNumero(numero);
	}

}
