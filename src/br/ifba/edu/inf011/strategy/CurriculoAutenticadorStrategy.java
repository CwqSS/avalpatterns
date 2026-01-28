package br.ifba.edu.inf011.strategy;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;

public class CurriculoAutenticadorStrategy implements AutenticadorStrategy {

	@Override
	public void autenticar(Documento documento) {
		String numero = "CURRICULO-" + LocalDate.now().getYear() + "-" + documento.hashCode();
		documento.setNumero(numero);
	}

}
