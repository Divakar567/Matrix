package com.matrix.repos;

import java.util.Map;

import com.matrix.models.Terminal;

public interface TerminalRepository {

	public Terminal getTerminal(String id);
	
	public boolean saveTerminal(Terminal terminal);
	
	public Terminal updateTerminal(String id, Map<String, Object> terminalUpdates);
	
	public boolean deleteTerminal(String id);

}
