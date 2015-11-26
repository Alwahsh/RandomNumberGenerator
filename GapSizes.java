
public class GapSizes
{
	private float minGap;
	private float maxGap;
	private float averageGap;
	
	public GapSizes(float min, float max, float average) {
		minGap = min;
		maxGap = max;
		averageGap = average;
	}
	
	public float getAverageGap()
	{
		return averageGap;
	}
	
	public float getMaxGap()
	{
		return maxGap;
	}
	
	public float getMinGap()
	{
		return minGap;
	}
}
