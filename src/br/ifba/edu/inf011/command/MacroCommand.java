package br.ifba.edu.inf011.command;

import java.util.List;

public interface MacroCommand extends Command {
	public List<Command> getChildren();
	public void add(Command command);
	public void remove(Command command);
}
