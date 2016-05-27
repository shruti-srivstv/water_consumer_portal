package it.polimi.awt.waterconsumer.exception;

public class DuplicatedUsernameException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedUsernameException(){
		super("The user has already registered");
	}

}
