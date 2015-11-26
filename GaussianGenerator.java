import org.jfree.data.category.DefaultCategoryDataset;


public class GaussianGenerator implements RandomNumberGenerator
{
	UniformGenerator uni = new UniformGenerator();
	double mean;
	double variance;
	
	public GaussianGenerator() {
		mean = 0;
		variance = 1;
	}
	
	public void setParams(int m, int v) {
		mean = (double) m;
		variance = (double) v;
	}
	
	public void setMean(double mean)
	{
		this.mean = mean;
	}
	
	public void setVariance(double variance)
	{
		this.variance = variance;
	}
	
	@Override
	public float getNextRandom()
	{
		double sigma = Math.sqrt(variance);
		double val = uni.getNextRandom();
		double res = Math.pow(val,0.135)-Math.pow(1-val,0.135);
		res/= 0.1975;
		res = sigma*res + mean;
		return (float)res;
	}
	
	public DefaultCategoryDataset getData() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int numbers[] = new int[255];
		for (int i = 0; i < Plotter.NUMS_GENERATED; i++) {
			float cur = getNextRandom();
			numbers[mapNumbertoIndex(cur)]++;
		}
		double sigma = Math.sqrt(variance);
		double cnt = mean - 3*sigma;
		double slot = 6*sigma/20.0;
		double ending = mean+3*sigma;
		int ind = 0;
		while(cnt < ending) {
			dataset.setValue(numbers[ind++], "Number", String.format("%.2f", cnt));
			cnt+= slot;
		}
		return dataset;
	}

	private int mapNumbertoIndex(float cur)
	{
		double sigma = Math.sqrt(variance);
		double begin = mean - 3*sigma;
		double ending = mean+3*sigma;
		double slot = 6*sigma/20.0;
		int cnt = 0;
		for (double i = begin; i < ending; i+= slot) {
			if (cur < i)
				return cnt;
			cnt++;
		}
		return cnt;
	}
	
}
