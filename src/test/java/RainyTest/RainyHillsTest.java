package RainyTest;

import org.junit.Assert;
import org.junit.Test;

import core.WaterShedMethod1;
import util.RainyHillsException;
import util.SimpleValidationAndConversion;

public class RainyHillsTest
{

	WaterShedMethod1 method1=new WaterShedMethod1();
	
	
	@Test
	public void isWorkingCorrectlyFromKnownExample()
	{
		method1.setDetailed(true);
		Integer[] a={4,1,1,0,2,3};
		Assert.assertEquals("Known terrain result does not match", 8, method1.getWater(a));
	}
	
	@Test
	public void staircaseLikeTerrainResultShouldBeZero()
	{
		method1.setDetailed(true);
		Integer[] a={1,2,3,4,5};
		Assert.assertEquals("Staircase-like Terrain Result Should Be Zero", 0, method1.getWater(a));
	}
	
	@Test(expected=RainyHillsException.class)
	public void nullStringTerrainShouldBeCaught() throws RainyHillsException
	{
		String a=null;
		SimpleValidationAndConversion simpleValidationAndConversion=new SimpleValidationAndConversion();
		simpleValidationAndConversion.validateAndConvert(a);
		
	}
	
	@Test(expected=RainyHillsException.class)
	public void zeroLengthStringTerrainShouldBeCaught() throws RainyHillsException
	{
		String a="";
		
		SimpleValidationAndConversion simpleValidationAndConversion=new SimpleValidationAndConversion();
		simpleValidationAndConversion.validateAndConvert(a);
		
	}
	
	@Test(expected=RainyHillsException.class)
	public void delimiterErrorShouldBeCaught() throws RainyHillsException
	{
		String a="324234234";
		
		SimpleValidationAndConversion simpleValidationAndConversion=new SimpleValidationAndConversion();
		simpleValidationAndConversion.validateAndConvert(a);
		
	}
	
}
