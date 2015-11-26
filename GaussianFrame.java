import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


public class GaussianFrame extends JFrame
{

	private JPanel contentPane;

	GaussianGenerator rand = new GaussianGenerator();
	
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
					GaussianFrame frame = new GaussianFrame();
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
	public GaussianFrame()
	{
		setTitle("Gaussian Generator");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 327, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				rand.setMean((double) spinner.getValue());
			}
		});
		spinner.setBounds(65, 38, 70, 20);
		contentPane.add(spinner);
		
		JLabel lblMean = new JLabel("Mean");
		lblMean.setBounds(65, 12, 70, 15);
		contentPane.add(lblMean);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				rand.setVariance((double) spinner_1.getValue());
			}
		});
		spinner_1.setBounds(184, 38, 70, 20);
		contentPane.add(spinner_1);
		
		JLabel lblVariance = new JLabel("Variance");
		lblVariance.setBounds(184, 12, 70, 15);
		contentPane.add(lblVariance);
		
		final JLabel label = new JLabel("");
		label.setBounds(123, 157, 70, 15);
		contentPane.add(label);
		
		JButton button = new JButton("Generate Random Number");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float val = rand.getNextRandom();
				label.setText(String.format("%.7f",val));
			}
		});
		button.setBounds(47, 83, 229, 25);
		contentPane.add(button);
		
		JButton btnPlotHistogram = new JButton("Plot Histogram");
		btnPlotHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset dataset = rand.getData();
				JFreeChart chart = ChartFactory.createBarChart("Gaussian Generator Histogram", "Ranges", "Numbers", dataset);
				ChartFrame frame = new ChartFrame("Gaussian Generator Histogram", chart);
				frame.setVisible(true);
				frame.setSize(1900, 1000);
			}
		});
		btnPlotHistogram.setBounds(47, 120, 229, 25);
		contentPane.add(btnPlotHistogram);
	}

}
