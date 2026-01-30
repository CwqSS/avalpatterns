package br.ifba.edu.inf011.command;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.ui.AbstractGerenciadorDocumentosUI;
import br.ifba.edu.inf011.ui.MyGerenciadorDocumentoUI;

public class ProtectCommand extends LoggerCommand {

	private AbstractGerenciadorDocumentosUI context;
	private GerenciadorDocumentoModel controller;
	private Documento atual;
	private Documento protegido;
	
	public ProtectCommand(AbstractGerenciadorDocumentosUI context, GerenciadorDocumentoModel controller, Documento atual) {
		this.context = context;
		this.controller = controller;
		this.atual = atual;
	}

	@Override
	public Boolean execute() {
		try {
			this.protegido = this.controller.protegerDocumento(this.atual);
			super.armazenar(this, Boolean.TRUE);
			return Boolean.TRUE;
		} catch (FWDocumentException e) {
			JOptionPane.showMessageDialog(context, "Erro ao proteger: " + e.getMessage());
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean undo() {
		this.controller.atualizarRepositorio(protegido, atual);
		super.armazenar(this, Boolean.FALSE);
		return Boolean.TRUE;
	}

}
