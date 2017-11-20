package util;

/**
 * Simple extended exception for our project's purposes. 
 * @author alp
 *
 */
public class RainyHillsException extends Exception
{

	public RainyHillsException(String message)
	{
		super(message);
		System.out.println(message);
	}

	
	
}
