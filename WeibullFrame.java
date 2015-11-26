import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class WeibullFrame extends JFrame
{

	private JPanel contentPane;

	private WeibullGenerator rand = new WeibullGenerator();
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
					WeibullFrame frame = new WeibullFrame();
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
	public WeibullFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlpha = new JLabel("Alpha");
		lblAlpha.setBounds(87, 30, 70, 15);
		contentPane.add(lblAlpha);
		
		JLabel lblBeta = new JLabel("Beta");
		lblBeta.setBounds(214, 30, 70, 15);
		contentPane.add(lblBeta);
		
		final JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				rand.setAlpha(Double.valueOf(spinner.getValue().toString()));
			}
		});
		spinner.setBounds(76, 57, 70, 20);
		contentPane.add(spinner);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				rand.setBeta(Double.valueOf(spinner_1.getValue().toString()));
			}
		});
		spinner_1.setBounds(201, 57, 70, 20);
		contentPane.add(spinner_1);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(87, 179, 137, 15);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("Generate Random Number");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(String.format("%.10f", rand.getNextRandom()));
			}
		});
		button.setBounds(36, 95, 316, 25);
		contentPane.add(button);
		
		JButton btnPlotHistogram = new JButton("Plot Histogram");
		btnPlotHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset dataset = rand.getData();
				JFreeChart chart = ChartFactory.createBarChart("Weibull Generator Histogram", "Ranges", "Numbers", dataset);
				ChartFrame frame = new ChartFrame("Weibull Generator Histogram", chart);
				frame.setVisible(true);
				frame.setSize(1900, 1000);
			}
		});
		btnPlotHistogram.setBounds(36, 132, 316, 25);
		contentPane.add(btnPlotHistogram);
		
	}

}
