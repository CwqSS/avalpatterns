package br.ifba.edu.inf011.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.CreateCommand;
import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.model.FWDocumentException;
import br.ifba.edu.inf011.model.documentos.Privacidade;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;
import br.ifba.edu.inf011.strategy.CurriculoAutenticadorStrategy;

public class MyGerenciadorDocumentoUI extends AbstractGerenciadorDocumentosUI{
	
	 public MyGerenciadorDocumentoUI(DocumentOperatorFactory factory) {
		super(factory);
	}

	protected JPanelOperacoes montarMenuOperacoes() {
		JPanelOperacoes comandos = new JPanelOperacoes();
		comandos.addOperacao("➕ Criar Publico", e -> this.criarDocumento(Privacidade.PUBLICO));
		comandos.addOperacao("➕ Criar Privado", e -> this.criarDocumento(Privacidade.SIGILOSO));
		comandos.addOperacao("💾 Salvar", e-> this.salvarConteudo());
		comandos.addOperacao("🔑 Proteger", e->this.protegerDocumento());
		comandos.addOperacao("✍️ Assinar", e->this.assinarDocumento());
		comandos.addOperacao("⏰ Urgente", e->this.tornarUrgente());
		return comandos;
	}
	
	@Override
	protected Map<String, AutenticadorStrategy> gerarDicionarioDeAutenticacao() {
		var dict = new HashMap<String, AutenticadorStrategy>(super.gerarDicionarioDeAutenticacao());
		dict.put("Curriculo", new CurriculoAutenticadorStrategy());
		return dict;
	}
	
	protected void salvarConteudo() {
        try {
            this.controller.salvarDocumento(this.atual, this.areaEdicao.getConteudo());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + e.getMessage());
        }
    }	
	
	protected void protegerDocumento() {
		try {
			this.controller.protegerDocumento(this.atual);
			this.refreshUI();
		} catch (FWDocumentException e) {
			JOptionPane.showMessageDialog(this, "Erro ao proteger: " + e.getMessage());
		}
	}

	protected void assinarDocumento() {
		try {
			this.controller.assinarDocumento(this.atual);
			this.refreshUI();
		} catch (FWDocumentException e) {
			JOptionPane.showMessageDialog(this, "Erro ao assinar: " + e.getMessage());
		}		
	}
	
	protected void tornarUrgente() {
		try {
			this.controller.tornarUrgente(this.atual);
			this.refreshUI();
		} catch (FWDocumentException e) {
			JOptionPane.showMessageDialog(this, "Erro ao tornar urgente: " + e.getMessage());
		}		
	}	

	private void criarDocumento(Privacidade privacidade) {
		String chaveSelecionada = this.barraSuperior.getSelected();
		AutenticadorStrategy strategy = this.tipos.get(chaveSelecionada);
		Command command = new CreateCommand(this, privacidade, strategy, this.barraDocs);
		this.executeCommand(command);
    }	
	

}
