package br.ifba.edu.inf011.command;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;
import br.ifba.edu.inf011.ui.AbstractGerenciadorDocumentosUI;
import br.ifba.edu.inf011.ui.JPanelListaDocumentos;

public class CreateCommand implements Command {

	private AbstractGerenciadorDocumentosUI app;
	private Privacidade privacidade;
	private AutenticadorStrategy strategy;
	private JPanelListaDocumentos<String> barraDocs;
	
	public CreateCommand(
			AbstractGerenciadorDocumentosUI app,
			Privacidade privacidade,
			AutenticadorStrategy strategy,
			JPanelListaDocumentos<String> barraDocs
		) {
		this.app = app;
		this.privacidade = privacidade;
		this.strategy = strategy;
		this.barraDocs = barraDocs;
	}
	
	@Override
	public Boolean execute() {
		Boolean result = Boolean.TRUE;
		
		try {
        	Documento doc = app.getController().criarDocumento(strategy, privacidade);
        	barraDocs.addDoc("[" + doc.getNumero() + "]");
        	app.setAtual(doc);
        } catch (FWDocumentException e) {
        	result = Boolean.FALSE;
            JOptionPane.showMessageDialog(app, "Erro: " + e.getMessage());
        }
		
		return result;
	}

}
