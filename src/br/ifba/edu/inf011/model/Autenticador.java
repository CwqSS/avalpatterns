package br.ifba.edu.inf011.model;

import java.time.LocalDate;

import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
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
	
	public void autenticar(Integer tipo, Documento documento) {
		String numero;
		if(tipo == 0)
			numero = "CRI-" + LocalDate.now().getYear() + "-" + documento.hashCode();
		else if(tipo == 1)
			numero = "PES-" + LocalDate.now().getDayOfYear() + "-" + documento.getProprietario().hashCode();
		else if (tipo == 2) {
            if (documento.getPrivacidade() == Privacidade.SIGILOSO) {
                numero = "SECURE-" + documento.hashCode();
            } else {
                numero = "PUB-" + documento.hashCode();
            }
        }else
			numero = "DOC-" + System.currentTimeMillis(); 
		documento.setNumero(numero);
	}

}
