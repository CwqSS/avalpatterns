package br.ifba.edu.inf011.ui;

import java.util.Stack;

import br.ifba.edu.inf011.command.Command;

public class CommandHistory {
	private Stack<Command> redoStack;
	private Stack<Command> undoStack;
	
	public CommandHistory() {
		redoStack = new Stack<Command>();
		undoStack = new Stack<Command>();
	}
	
	public void add(Command cmd) {
		undoStack.push(cmd);
	}
	
	public void redo() {
		var cmd= redoStack.pop();
		cmd.execute();
		undoStack.push(cmd);
	}
	
	public void undo() {
		var cmd= undoStack.pop();
		cmd.undo();
		redoStack.push(cmd);
	}
}
