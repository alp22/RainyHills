package util;

/**
 * The aim here is to make simple validations for input strings, and return them as an Integer array
 * @author alp
 *
 */
public class SimpleValidationAndConversion
{

	/**
	 * Validates and converts a string to an Integer array
	 * @param inputString
	 * @return
	 * @throws RainyHillsException
	 */
	public Integer[] validateAndConvert(String inputString) throws RainyHillsException
	{
		if (inputString==null)
		{
			throw new RainyHillsException("Input String is null");
		}
		if (inputString.length()==0)
		{
			throw new RainyHillsException("Input String has zero length");
		}
		
		if (inputString.indexOf(",")<0)
		{
			throw new RainyHillsException("Delimiter must be a comma");
		}
		String[] array=inputString.split(",",inputString.length());
		Integer[] result=new Integer[array.length];
		for (int i = 0; i < array.length; i++)
		{
			try
			{
			Integer val = Integer.parseInt(array[i]);
			result[i]=val;
			}
			catch(Exception e)
			{
				throw new RainyHillsException("Character at index "+i+" cannot be converted to an integer value" + e.getMessage());
			}
			
			
		}
		return result;
	
		
	}
	
	
}
