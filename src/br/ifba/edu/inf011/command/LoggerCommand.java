package br.ifba.edu.inf011.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class LoggerCommand implements Command{
	
	protected void armazenar(Command c, Boolean isExecute) {
		File myFile = new File("LOG.txt");
		try {
			if (myFile.createNewFile()) 
		        System.out.println("File created: " + myFile.getName());
		} catch (IOException  e) {
			System.out.println("Erro ao criar arquivo: " + e.getMessage());
		}
		
		try {
			FileWriter writer = new FileWriter("LOG.txt", true);
			String op = isExecute ? "EXEC" : "UNDO";
			writer.append(op + " " + c.toString() + '\n');
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao escrever em arquivo: " + e.getMessage());
			e.printStackTrace();
		}		
	}
	
}
