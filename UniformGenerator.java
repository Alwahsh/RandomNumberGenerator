import java.awt.MouseInfo;
import java.util.Arrays;

import org.jfree.chart.plot.Plot;


public class UniformGenerator implements RandomNumberGenerator
{
	private int seed;
	private int a,c,m;
	
	// Default constructor with parameters that ensure full period.
	public UniformGenerator() {
		resetAll();
	}
	
	
	public void resetAll()
	{
		m = (1<<30);
		a = (int) System.currentTimeMillis();
		a&= ((-1) >> 1); // Make sure resulting number is a positive number.
		// Check performed to make sure a is not equal to 0.
		if (a <= 0)
			a = 1;
		// if a is equal to 0, this might go to a negative number which we want to avoid.
		while((a-1)%4 != 0)
			a--;
		c = m-1; // c & m are relatively prime.
		setRandomSeed();
	}

	public void setAll(int customA, int customC, int customM) {
		a = customA;
		c = customC;
		m = customM;
		setRandomSeed();
	}
	
	// If user wants to enter his own attributes.
	public UniformGenerator(int customA, int customC, int customM) {
		setAll(customA, customC, customM);
	}
	
	public void setRandomSeed() {
		seed = (int) System.currentTimeMillis();
		seed+= MouseInfo.getPointerInfo().getLocation().getX();
		seed&= ((-1) >> 1);
	}
	
	// If user wants to set his own seed.
	public void setSeed(int customSeed) {
		seed = customSeed;
	}
	
	// Get a random number.
	public float getNextRandom() {
		seed = (int) (((long) a * (long) seed + (long) c) % (long) m);
		return (float) ((double)seed/(double)m);
	}

	
	// Test the period length.
	public int testPeriod() {
		int res = 0;
		getNextRandom();
		int startValue = seed; // we need to check when we reach it again.
		while(true) {
			getNextRandom();
			res++;
			if (seed == startValue)
				return res;
			// if we don't reach the start value after m trials, it means
			// we've entered a cycle in the middle with some other set of numbers.
			// Let's count the period of those.
			if (res > m) {
				startValue = seed;
				res = 0;
			}
		}
	}
	
	// Test the period length without changing the seed value.
	public int testPeriodNoSeedChange() {
		int curSeed = seed;
		int res = testPeriod();
		seed = curSeed;
		return res;
	}
	
	public GapSizes getGapSizes() {
		float min = 1;
		float max = 0;
		float average = 0;
		float arr[] = new float[Plotter.NUMS_GENERATED];
		for (int i = 0; i < Plotter.NUMS_GENERATED; i++)
			arr[i] = getNextRandom();
		Arrays.sort(arr);
		for (int i = 1; i < Plotter.NUMS_GENERATED; i++) {
			float diff =  arr[i]-arr[i-1];
			average+= diff;
			max = Math.max(max, diff);
			min = Math.min(min, diff);
		}
		average/= (float)Plotter.NUMS_GENERATED;
		return new GapSizes(min, max, average);
	}
	
	public GapSizes getGapSizesSilent() {
		int curSeed = seed;
		GapSizes res = getGapSizes();
		seed = curSeed;
		return res;
	}
	
}
