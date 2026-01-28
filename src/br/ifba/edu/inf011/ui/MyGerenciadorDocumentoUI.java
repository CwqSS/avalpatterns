package br.ifba.edu.inf011.ui;

import java.util.HashMap;
import java.util.Map;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.command.CreateCommand;
import br.ifba.edu.inf011.command.ProtectCommand;
import br.ifba.edu.inf011.command.SaveCommand;
import br.ifba.edu.inf011.command.SignCommand;
import br.ifba.edu.inf011.command.UrgentCommand;
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
		comandos.addOperacao("💾 Salvar", e -> this.executeCommand(new SaveCommand(this, this.controller, this.atual, this.areaEdicao)));
		comandos.addOperacao("🔑 Proteger", e -> this.executeCommand(new ProtectCommand(this, this.controller, this.atual)));
		comandos.addOperacao("✍️ Assinar", e -> this.executeCommand(new SignCommand(this, this.controller, this.atual)));
		comandos.addOperacao("⏰ Urgente", e -> this.executeCommand(new UrgentCommand(this, this.controller, this.atual)));
		return comandos;
	}
	
	@Override
	protected Map<String, AutenticadorStrategy> gerarDicionarioDeAutenticacao() {
		var dict = new HashMap<String, AutenticadorStrategy>(super.gerarDicionarioDeAutenticacao());
		dict.put("Curriculo", new CurriculoAutenticadorStrategy());
		return dict;
	}

	private void criarDocumento(Privacidade privacidade) {
		String chaveSelecionada = this.barraSuperior.getSelected();
		AutenticadorStrategy strategy = this.tipos.get(chaveSelecionada);
		Command command = new CreateCommand(this, privacidade, strategy, this.barraDocs);
		this.executeCommand(command);
    }	
	
}