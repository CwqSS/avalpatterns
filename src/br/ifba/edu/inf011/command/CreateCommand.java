package br.ifba.edu.inf011.command;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;
import br.ifba.edu.inf011.ui.AbstractGerenciadorDocumentosUI;
import br.ifba.edu.inf011.ui.JPanelListaDocumentos;

public class CreateCommand extends LoggerCommand {

	private AbstractGerenciadorDocumentosUI app;
	private Privacidade privacidade;
	private JPanelListaDocumentos<String> barraDocs;
	private Documento doc;
	private String barraDocId;
	
	public CreateCommand(
			AbstractGerenciadorDocumentosUI app,
			Privacidade privacidade,
			JPanelListaDocumentos<String> barraDocs
		) {
		this.app = app;
		this.privacidade = privacidade;
		this.barraDocs = barraDocs;
	}
	
	@Override
	public Boolean execute() {
		Boolean result = Boolean.TRUE;
		
		if(doc != null) {
			app.getController().getRepositorio().add(doc);
			barraDocs.addDoc(barraDocId);
			app.setAtual(doc);
			super.armazenar(this, Boolean.TRUE);
			return Boolean.TRUE;
		}
		
		try {
        	this.doc = app.getController().criarDocumento(privacidade);
        	this.barraDocId = "[" + doc.getNumero() + "]";
        	barraDocs.addDoc(barraDocId);
        	app.setAtual(doc);
        	super.armazenar(this, Boolean.TRUE);
        } catch (Exception e) {
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
        	super.armazenar(this, Boolean.FALSE);
        } catch (Exception e) {
        	result = Boolean.FALSE;
            JOptionPane.showMessageDialog(app, "Erro: " + e.getMessage());
        }
		
		return result;
	}

}
