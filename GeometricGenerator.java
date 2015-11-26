import org.jfree.data.category.DefaultCategoryDataset;


public class GeometricGenerator implements RandomNumberGenerator
{
	UniformGenerator uni = new UniformGenerator();
	double p = 0.5;
	
	public void setP(double p)
	{
		if (p > 1 || p < 0)
			return;
		this.p = p;
	}
	
	@Override
	public float getNextRandom()
	{
		double val = uni.getNextRandom();
		double res = Math.log(1-val)/Math.log(1-p);
		res-= 1;
		res = Math.ceil(res);
		return (float)res;
	}
	
	
	public int mapNumbertoIndex(float num) {
		if (num >= 10)
			return 9;
		int res = 0;
		float cnt = 0;
		while(true) {
			cnt+= 1;
			if (num < cnt)
				return res;
			res++;
		}
	}
	
	public DefaultCategoryDataset getData() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int numbers[] = new int[20];
		for (int i = 0; i < Plotter.NUMS_GENERATED; i++) {
			float cur = getNextRandom();
			numbers[mapNumbertoIndex(cur)]++;
		}
		float cnt = 0;
		int ind = 0;
		while(cnt < 10-10e-3) {
			dataset.setValue(numbers[ind++], "Number", String.format("%.2f", cnt+=1));
		}
		return dataset;
	}
}
