import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UniformFrame extends JFrame
{

	private JPanel contentPane;
	private boolean newSeed = false;
	private UniformGenerator rand = new UniformGenerator();

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
					UniformFrame frame = new UniformFrame();
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
	public UniformFrame()
	{
		setTitle("Uniform Generator");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 341, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeed = new JLabel("Seed");
		lblSeed.setBounds(12, 12, 70, 15);
		contentPane.add(lblSeed);
		
		final JSpinner seed = new JSpinner();
		seed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				newSeed = true;
			}
		});
		seed.setBounds(12, 39, 70, 20);
		contentPane.add(seed);
		
		JLabel lblA = new JLabel("A");
		lblA.setBounds(94, 12, 70, 15);
		contentPane.add(lblA);
		
		final JSpinner a = new JSpinner();
		a.setBounds(94, 39, 70, 20);
		contentPane.add(a);
		
		JLabel lblC = new JLabel("C");
		lblC.setBounds(176, 12, 70, 15);
		contentPane.add(lblC);
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(176, 39, 70, 20);
		contentPane.add(spinner);
		
		JLabel lblM = new JLabel("M");
		lblM.setBounds(258, 12, 70, 15);
		contentPane.add(lblM);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if ((int)spinner_1.getValue() == 0)
					rand.resetAll();
				else
					rand.setAll((int)a.getValue(), (int)spinner.getValue(), (int)spinner_1.getValue());
			}
		});
		spinner_1.setBounds(258, 39, 70, 20);
		contentPane.add(spinner_1);
		
		final JLabel shownValue = new JLabel("");
		shownValue.setBounds(129, 192, 70, 15);
		contentPane.add(shownValue);
		
		JButton btnNewButton = new JButton("Generate Random Number");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (newSeed) {
					rand.setSeed((int)seed.getValue());
					newSeed = false;
				}
				float val = rand.getNextRandom();
				shownValue.setText(String.format("%.7f",val));
			}
		});
		btnNewButton.setBounds(12, 81, 316, 25);
		contentPane.add(btnNewButton);
		
		JButton btnCalculatePeriodAnd = new JButton("Calculate Period and Gap sizes");
		btnCalculatePeriodAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GapSizes gs = rand.getGapSizes();
				JOptionPane.showMessageDialog(new JFrame(),
						"Period: " + String.valueOf(rand.testPeriodNoSeedChange()) +
						" Gap Sizes, Min: " + String.format("%.15f",gs.getMinGap()) +
						" Max: " + String.format("%.15f", gs.getMaxGap()) + 
						" Average: " + String.format("%.15f", gs.getAverageGap())
						);
			}
		});
		btnCalculatePeriodAnd.setBounds(12, 118, 316, 25);
		contentPane.add(btnCalculatePeriodAnd);
		
		JButton btnDrawHistogram = new JButton("Plot Histogram");
		btnDrawHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset dataset = Plotter.getData(rand);
				JFreeChart chart = ChartFactory.createBarChart("Unifrom Generator Histogram", "Ranges", "Numbers", dataset);
				ChartFrame frame = new ChartFrame("Unifrom Generator Histogram", chart);
				frame.setVisible(true);
				frame.setSize(1900, 1000);
			}
		});
		btnDrawHistogram.setBounds(12, 155, 316, 25);
		contentPane.add(btnDrawHistogram);
	}
}
