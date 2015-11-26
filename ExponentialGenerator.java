import org.jfree.data.category.DefaultCategoryDataset;


public class ExponentialGenerator implements RandomNumberGenerator
{
	UniformGenerator uni = new UniformGenerator();
	double lamda = 1;
	
	public void setLamda(double lamda)
	{
		if (Math.abs(lamda-0) < 10e-6)
			this.lamda = 1;
		else
			this.lamda = lamda;			
	}
	
	@Override
	public float getNextRandom()
	{
		double val = uni.getNextRandom();
		double res = -1.0/lamda;
		res*= Math.log(val);
		return (float)res;
	}
	
	
	public int mapNumbertoIndex(float num) {
		if (num >= 5.0)
			return 49;
		int res = 0;
		float cnt = 0;
		while(true) {
			cnt+= 0.3;
			if (num < cnt)
				return res;
			res++;
		}
	}
	
	public DefaultCategoryDataset getData() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int numbers[] = new int[500];
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
		while(cnt < max) {
			dataset.setValue(numbers[ind++], "Number", String.format("%.2f", cnt));
			cnt+= step;
		}
		return dataset;
	}


}
