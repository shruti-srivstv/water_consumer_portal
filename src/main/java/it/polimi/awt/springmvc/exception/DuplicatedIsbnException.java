package it.polimi.awt.springmvc.exception;

public class DuplicatedIsbnException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedIsbnException(){
		super("The book has an already existent isbn");
	}

}
