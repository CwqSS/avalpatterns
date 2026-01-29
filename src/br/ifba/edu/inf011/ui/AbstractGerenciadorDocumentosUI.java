package br.ifba.edu.inf011.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ifba.edu.inf011.af.DocumentOperatorFactory;
import br.ifba.edu.inf011.command.Command;
import br.ifba.edu.inf011.model.GerenciadorDocumentoModel;
import br.ifba.edu.inf011.model.documentos.Documento;
import br.ifba.edu.inf011.strategy.AutenticadorStrategy;
import br.ifba.edu.inf011.strategy.ConfidencialAutenticadorStrategy;
import br.ifba.edu.inf011.strategy.CriminalAutenticadorStrategy;
import br.ifba.edu.inf011.strategy.ExportacaoAutenticadorStrategy;
import br.ifba.edu.inf011.strategy.PessoalAutenticadorStrategy;

public abstract class AbstractGerenciadorDocumentosUI extends JFrame implements ListSelectionListener{
    
	protected GerenciadorDocumentoModel controller;
	protected JPanelBarraSuperior<String> barraSuperior;
	protected JPanelAreaEdicao areaEdicao;
	protected JPanelListaDocumentos<String> barraDocs;
	
	protected CommandHistory commandsHistory = new CommandHistory();
	
	protected Map<String, AutenticadorStrategy> tipos;
	
    protected Documento atual;
    protected DefaultListModel<String> listDocs;
    

    public AbstractGerenciadorDocumentosUI(DocumentOperatorFactory factory) {
    	tipos = gerarDicionarioDeAutenticacao();
        this.controller = new GerenciadorDocumentoModel(factory);
    	this.listDocs = new DefaultListModel<String>();
    	this.barraSuperior = new JPanelBarraSuperior<String>(tipos.keySet().toArray(new String[0]));
    	this.areaEdicao = new JPanelAreaEdicao();
    	this.barraDocs = new JPanelListaDocumentos<String>(this.listDocs, this);
    	this.montarAparencia();
    }
    
    protected abstract JPanelOperacoes montarMenuOperacoes();    

    protected Map<String, AutenticadorStrategy> gerarDicionarioDeAutenticacao() {
    	return 	Map.ofEntries(
			Map.entry("Criminal", new CriminalAutenticadorStrategy()),
			Map.entry("Pessoal", new PessoalAutenticadorStrategy()),
			Map.entry("Exportação", new ExportacaoAutenticadorStrategy()),
			Map.entry("Confidencial", new ConfidencialAutenticadorStrategy())
    	);
    }
    
	public void montarAparencia() {
    	// Configuração da Janela
    	this.setTitle("Sistema de Gestão de Documentos - INF011");
    	this.setSize(800, 500);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setLayout(new BorderLayout());
    	
        //Layout
    	this.add(this.barraSuperior, BorderLayout.NORTH);
    	this.add(this.areaEdicao, BorderLayout.CENTER);
        this.add(this.barraDocs, BorderLayout.WEST);
        this.add(this.montarMenuOperacoes(), BorderLayout.EAST);    	
    	
    }


    protected void refreshUI() {
        try {
        	this.areaEdicao.atualizar(this.atual.getConteudo());
        } catch (Exception e) {
        	this.areaEdicao.atualizar("");
        	JOptionPane.showMessageDialog(this, "Erro ao Carregar : " + e.getMessage());
        }    	
    }

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			update();
        }
	}

	private void update() {
		int index = this.barraDocs.getIndiceDocSelecionado();
        if (index != -1) {
            this.atual = controller.getRepositorio().get(index);
            controller.setDocumentoAtual(atual);
            this.refreshUI();
        }
	}
	
	protected void executeCommand(Command command) {
		if(command.execute()) {
			commandsHistory.add(command);
			update();
		}
	}
	
	public void setAtual(Documento doc) {
		atual = doc;
	}
	
	public GerenciadorDocumentoModel getController() {
		return controller;
	}
	
	protected void redo() {
		commandsHistory.redo();
		update();
	}
	
	protected void undo() {
		commandsHistory.undo();
		update();
	}
	
	protected void clearHistory() {
		commandsHistory.clear();
	}
}