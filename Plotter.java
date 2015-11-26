import org.jfree.data.category.DefaultCategoryDataset;


public class Plotter
{
	public static final int NUMS_GENERATED = 10000000;
	
	public static int mapNumbertoIndex(float num) {
		if (num >= 1.0)
			return 19;
		int res = 0;
		float cnt = 0;
		while(true) {
			cnt+= 0.05;
			if (num < cnt)
				return res;
			res++;
		}
	}
	
	public static DefaultCategoryDataset getData(RandomNumberGenerator rand) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int numbers[] = new int[20];
		for (int i = 0; i < NUMS_GENERATED; i++) {
			float cur = rand.getNextRandom();
			numbers[mapNumbertoIndex(cur)]++;
		}
		float cnt = 0;
		int ind = 0;
		while(cnt < 1-10e-3) {
			dataset.setValue(numbers[ind++], "Number", String.format("%.2f", cnt+=0.05));
		}
		return dataset;
	}
}
