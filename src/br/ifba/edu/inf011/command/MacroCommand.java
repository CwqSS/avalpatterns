package br.ifba.edu.inf011.command;

import java.util.ArrayList;
import java.util.List;

public class MacroCommand extends LoggerCommand {
	
	private List<Command> cmds = new ArrayList<Command>();
	
	public void add(Command command) {
		this.cmds.add(command);
	}
	public void remove(Command command) {
		this.cmds.remove(command);
	}
	@Override
	public Boolean execute() {
		for(Command cmd : this.cmds) {
			if(!cmd.execute()) {
				return Boolean.FALSE;
			}
			super.armazenar(cmd, Boolean.TRUE);
		}
		return Boolean.TRUE;
	}
	@Override
	public Boolean undo() {
		for(Command cmd : this.cmds.reversed()) {
			if(!cmd.undo()) {
				return Boolean.FALSE;
			}
			super.armazenar(cmd, Boolean.FALSE);
		}
		return null;
	}
}
