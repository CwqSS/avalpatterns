package br.ifba.edu.inf011.command;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.ui.AbstractGerenciadorDocumentosUI;
import br.ifba.edu.inf011.ui.MyGerenciadorDocumentoUI;

public class SignCommand extends LoggerCommand {

	private AbstractGerenciadorDocumentosUI context;
	private GerenciadorDocumentoModel controller;
	private Documento assinado;
	private Documento doc;
	
	public SignCommand(AbstractGerenciadorDocumentosUI context, GerenciadorDocumentoModel controller) {
		this.context = context;
		this.controller = controller;
		this.doc = this.controller.getDocumentoAtual();
	}

	@Override
	public Boolean execute() {
		try {
			this.assinado = this.controller.assinarDocumento(doc);
			super.armazenar(this, Boolean.TRUE);
			return Boolean.TRUE;
		} catch (FWDocumentException e) {
			JOptionPane.showMessageDialog(context, "Erro ao assinar: " + e.getMessage());
			return Boolean.FALSE;
		}	
	}

	@Override
	public Boolean undo() {
		this.controller.atualizarRepositorio(assinado, doc);
		super.armazenar(this, Boolean.FALSE);
		return Boolean.TRUE;
	}

}
