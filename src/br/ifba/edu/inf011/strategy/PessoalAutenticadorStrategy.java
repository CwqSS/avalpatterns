package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class PessoalAutenticadorStrategy implements AutenticadorStrategy {

	@Override
	public void autenticar(Documento documento) {
		String numero = "PES-" + LocalDate.now().getDayOfYear() + "-" + documento.getProprietario().hashCode();
		documento.setNumero(numero);
	}

}
