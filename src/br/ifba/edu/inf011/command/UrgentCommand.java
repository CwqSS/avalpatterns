package br.ifba.edu.inf011.command;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.ui.AbstractGerenciadorDocumentosUI;

public class UrgentCommand implements Command {

	private AbstractGerenciadorDocumentosUI context;
	private GerenciadorDocumentoModel controller;
	private Documento atual;
	
	public UrgentCommand(AbstractGerenciadorDocumentosUI context, GerenciadorDocumentoModel controller, Documento atual) {
		this.context = context;
		this.controller = controller;
		this.atual = atual;
	}

	@Override
	public Boolean execute() {
		try {
			this.controller.tornarUrgente(this.atual);
			return Boolean.TRUE;
		} catch (FWDocumentException e) {
			JOptionPane.showMessageDialog(context, "Erro ao tornar urgente: " + e.getMessage());
			return Boolean.FALSE;
		}		
	}

}
