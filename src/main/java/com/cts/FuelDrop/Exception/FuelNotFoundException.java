package com.cts.FuelDrop.Exception;
public class FuelNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuelNotFoundException(String message) {
        super(message);
    }
}