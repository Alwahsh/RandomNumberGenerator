import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


public class ExponentialFrame extends JFrame
{

	private JPanel contentPane;

	private ExponentialGenerator rand = new ExponentialGenerator();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ExponentialFrame frame = new ExponentialFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExponentialFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 197, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lamda");
		lblNewLabel.setBounds(60, 12, 70, 15);
		contentPane.add(lblNewLabel);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(12, 168, 169, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnGenerateRandom = new JButton("Generate Random");
		btnGenerateRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setText(String.format("%.10f", rand.getNextRandom()));
			}
		});
		btnGenerateRandom.setBounds(12, 83, 169, 25);
		contentPane.add(btnGenerateRandom);
		
		JButton btnPlotHistogram = new JButton("Plot Histogram");
		btnPlotHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset dataset = rand.getData();
				JFreeChart chart = ChartFactory.createBarChart("Exponential Generator Histogram", "Ranges", "Numbers", dataset);
				ChartFrame frame = new ChartFrame("Exponential Generator Histogram", chart);
				frame.setVisible(true);
				frame.setSize(1900, 1000);
			}
		});
		btnPlotHistogram.setBounds(12, 120, 169, 25);
		contentPane.add(btnPlotHistogram);
		
		final JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				rand.setLamda(Double.valueOf(spinner.getValue().toString()));
			}
		});
		spinner.setBounds(60, 39, 70, 20);
		contentPane.add(spinner);
	}
}
