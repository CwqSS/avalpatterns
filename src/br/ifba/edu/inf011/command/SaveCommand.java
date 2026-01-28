package br.ifba.edu.inf011.command;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.ui.AbstractGerenciadorDocumentosUI;
import br.ifba.edu.inf011.ui.JPanelAreaEdicao;

public class SaveCommand implements Command {

	private AbstractGerenciadorDocumentosUI context;
	private GerenciadorDocumentoModel controller;
	private Documento atual;
	private JPanelAreaEdicao areaEdicao;
	
	public SaveCommand(AbstractGerenciadorDocumentosUI context, GerenciadorDocumentoModel controller, Documento atual, JPanelAreaEdicao areaEdicao) {
		this.context = context;
		this.controller = controller;
		this.atual = atual;
		this.areaEdicao = areaEdicao;
	}
	
	@Override
	public Boolean execute() {
		try {
            controller.salvarDocumento(atual, areaEdicao.getConteudo());
            return Boolean.TRUE;
        } catch (Exception exception) {
        	JOptionPane.showMessageDialog(context, "Erro ao Salvar: " + exception.getMessage());
        	return Boolean.FALSE;
        }
	}

	@Override
	public Boolean undo() {
		// TODO Auto-generated method stub
		return null;
	}

}
