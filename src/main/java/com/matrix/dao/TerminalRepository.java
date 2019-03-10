package com.matrix.dao;

import com.matrix.models.Terminal;

public interface TerminalRepository {

	public Terminal getTerminal(String id);
	
	public boolean saveTerminal(Terminal terminal);
	
	public Terminal updateTerminal(Terminal terminal);
	
	public boolean deleteTerminal(String id);

}
