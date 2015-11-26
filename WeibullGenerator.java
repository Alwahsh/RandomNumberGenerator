import org.jfree.data.category.DefaultCategoryDataset;


public class WeibullGenerator implements RandomNumberGenerator
{
	UniformGenerator uni = new UniformGenerator();
	double alpha = 1;
	double beta = 1;
	
	public void setAlpha(double alpha)
	{
		this.alpha = alpha;
	}
	
	public void setBeta(double beta)
	{
		this.beta = beta;
	}
	
	@Override
	public float getNextRandom()
	{
		double val = uni.getNextRandom();
		double res = alpha*(Math.pow(-Math.log(1-val),1.0/beta));
		return (float)res;
	}
	
	public DefaultCategoryDataset getData() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int numbers[] = new int[200];
		float randoms[] = new float[Plotter.NUMS_GENERATED];
		float max = 0;
		float min = 100000;
		for (int i = 0; i < Plotter.NUMS_GENERATED; i++) {
			float cur = getNextRandom();
			if (cur > max)
				max = cur;
			if (cur < min)
				min = cur;
			randoms[i] = cur;
		}
		float step = (float) ((max-min+1)/20.0);
		for (int i = 0; i < Plotter.NUMS_GENERATED; i++) {
			int ind = (int) (randoms[i]/step);
			numbers[ind]++;
		}
		float cnt = 0;
		int ind = 0;
		while(cnt <= max-10e-6) {
			dataset.setValue(numbers[ind++], "Number", String.format("%.2f", cnt));
			cnt+= step;
		}
		return dataset;
	}
}
