package core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A RainyHills Watershed implementation.
 * The terrain heights is given as an integer array, and possible depressions
 * that are filled with water after rains is returned as an integer value.
 * 
 * @author alp
 *
 */
public class WaterShedMethod1 implements IWaterShedMethods
{
	/// Using simple logger
	Logger logger=Logger.getLogger(this.getClass().getName());
	/// For detailed output, if desired
	private boolean detailedOutput=false;
	

	/**
	 * The methodology here is to "fill" every height unit with water, ie. like a cake layer.
	 * So we are running a for loop for the height of the terrain.
	 * And another loop for the "length" of the terrain.
	 * 
	 * To simplify and reduce our loop, first we get the "height elevation difference" from input data, and
	 * then work accordingly.
	 * 
	 * When we encounter a ridge, if the height of terrain at that column is higher than the water level, 
	 * we record it as "ridgefound" if not found already.
	 * 
	 * If the height of the terrain at that column is lower than water level, and if a ridge was
	 * found before, we fill it with 1 unit of water. 
	 * (Since we are using water level 1 by 1, depth becomes irrelevant)
	 * 
	 * If we find a ridge again, and if a previous ridge is found before, and if we have at least 1 unit of 
	 * filled water, we add it to the total water level, set the filled water amount to zero, 
	 * and ridgefound to false so that we can find others in the same elevation, if they exist.
	 * 
	 * Lastly, if we are at the end of our search of elevation columns, with filled water,
	 * and haven't encountered another ridge by then, to give an information we print out water washed away,
	 * if it has a detailed output.  
	 * 
	 *  
	 * 
	 */
	public int getWater(Integer[] terrain)
	{
		logger.log(Level.INFO, "Input values for terrain:" + Arrays.toString(terrain));
		logger.log(Level.INFO, "Detailed Output enabled :" + isDetailed());

		/// Lets sort these guys first.
		/// The aim is to reduce the numbers, using their ranges.
		List<Integer> l = (List) Arrays.asList(terrain);
		int maxHeight = Collections.max(l);
		int minHeight = Collections.min(l);
		/// This is the final result to be returned
		int totalWater = 0;
		/// The possible range of height values in our terrain.
		int heightDiff = maxHeight - minHeight;

		/// So, the waterlevel can only be from height 1 to the height
		/// difference of the terrain.
		for (int waterLevel = 1; waterLevel <= heightDiff; waterLevel++)
		{
			// Starting conditions for that elevation
			int levelWater = 0;
			boolean ridgefound = false;

			if (isDetailed())
			{
				System.out.println("water level:" + waterLevel);
			}

			/// Running now through the terrain
			for (int i = 0; i < terrain.length; i++)
			{

				int relativeHeight = terrain[i] - minHeight;
				/// Checking if we have a ridge from water and terrain height
				/// difference.
				if (relativeHeight >= waterLevel && !ridgefound)
				{
					ridgefound = true;
					if (isDetailed())
					{
						System.out.println("ridge found");
					}
					continue;
				}
				// If elevation is lower than water, we fill it, if we had found
				// a ridge before.
				if (relativeHeight < waterLevel && ridgefound)
				{
					if (isDetailed())
					{
						System.out.println("filling water");
					}
					/// Increase the amount of water in that level.
					levelWater++;
				}
				
				/// If we have a higher elevation, have a ridge before and have
				/// filled water before
				if (relativeHeight >= waterLevel && ridgefound && levelWater > 0)
				{
					if (isDetailed())
					{
						System.out.println("opposing ridge found");
					}
					/// First set the ridgefound to false.
					ridgefound = false;
					if (isDetailed())
					{
						System.out.println(totalWater + "tot");
						System.out.println(levelWater + "lev");
					}
					/// Increase total water in the terrain with the already
					/// filled water.
					totalWater += levelWater;
					/// Set level water to zero, there may be other ridges.
					levelWater = 0;
					continue;
				}
				//// For checking at the end of terrain if filled water at that level washed away
				//// because of a non-existing opposing ridge.
				if (i == terrain.length - 1 && levelWater > 0)
				{
					if (isDetailed())
					{
						System.out.println("No opposing ridge found, water washed away.");
					}
				}

			}

		}
		logger.log(Level.INFO, "Total Water calculated :" + totalWater);
		return totalWater;

	}
	
	/**
	 * For testing purposes of the coder.
	 * No need to mount everything in an application just to see how it works.
	 */
	public static void main(String[] args)
	{
		WaterShedMethod1 v=new WaterShedMethod1();
		v.setDetailed(true);
		Integer[] a={4,1,1,0,2,3};
		//Integer[] a={3,2,4,1,2};
		//Integer[] a={1,2,3,4,5};
		//Integer[] a={5,4,3,2,1};
		System.out.println("total water="+v.getWater(a));
		
	}


	public boolean isDetailed()
	{
		return detailedOutput;
	}


	public void setDetailed(boolean detailed)
	{
		this.detailedOutput = detailed;
	}


	
}
