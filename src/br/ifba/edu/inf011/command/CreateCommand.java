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
	private Documento doc;
	private String barraDocId;
	
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
        	this.doc = app.getController().criarDocumento(strategy, privacidade);
        	this.barraDocId = "[" + doc.getNumero() + "]";
        	barraDocs.addDoc(barraDocId);
        	app.setAtual(doc);
        } catch (FWDocumentException e) {
        	result = Boolean.FALSE;
            JOptionPane.showMessageDialog(app, "Erro: " + e.getMessage());
        }
		
		return result;
	}

	@Override
	public Boolean undo() {
		Boolean result = Boolean.TRUE;
		
		try {
        	var repo = app.getController().getRepositorio();
        	repo.remove(doc);
        	barraDocs.removeDoc(barraDocId);
        	app.setAtual(null);
        } catch (Exception e) {
        	result = Boolean.FALSE;
            JOptionPane.showMessageDialog(app, "Erro: " + e.getMessage());
        }
		
		return result;
	}

}
