package br.ifba.edu.inf011.command;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.ui.AbstractGerenciadorDocumentosUI;
import br.ifba.edu.inf011.ui.JPanelAreaEdicao;

public class SaveCommand implements Command {

	private AbstractGerenciadorDocumentosUI context;
	private GerenciadorDocumentoModel controller;
	private Documento doc;
	private String last;
	private String conteudo;
	
	public SaveCommand(AbstractGerenciadorDocumentosUI context, GerenciadorDocumentoModel controller, Documento atual, String conteudo) {
		this.context = context;
		this.controller = controller;
		this.doc = atual;
		this.conteudo = conteudo;
	}
	
	@Override
	public Boolean execute() {
		try {
			this.last = controller.getDocumentoAtual().getConteudo();
            controller.salvarDocumento(controller.getDocumentoAtual(), conteudo);
            return Boolean.TRUE;
        } catch (Exception exception) {
        	JOptionPane.showMessageDialog(context, "Erro ao Salvar: " + exception.getMessage());
        	return Boolean.FALSE;
        }
	}

	@Override
	public Boolean undo() {
		try {
			controller.salvarDocumento(doc, last);
			return Boolean.TRUE;
		} catch (Exception exception) {
        	JOptionPane.showMessageDialog(context, "Erro ao Salvar: " + exception.getMessage());
        	return Boolean.FALSE;
        }
	}

}
