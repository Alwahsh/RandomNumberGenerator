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


public class GeometricFrame extends JFrame
{

	private JPanel contentPane;

	private GeometricGenerator rand = new GeometricGenerator();
	private JTextField textField;
	
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
					GeometricFrame frame = new GeometricFrame();
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
	public GeometricFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 205, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblP = new JLabel("P");
		lblP.setBounds(71, 12, 70, 15);
		contentPane.add(lblP);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(54, 166, 70, 15);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("Generate Random");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rand.setP(Double.valueOf(textField.getText()));
				lblNewLabel.setText(String.format("%.10f",rand.getNextRandom()));
			}
		});
		button.setBounds(12, 71, 169, 25);
		contentPane.add(button);
		
		JButton btnPlotHistogram = new JButton("Plot Histogram");
		btnPlotHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rand.setP(Double.valueOf(textField.getText()));
				DefaultCategoryDataset dataset = rand.getData();
				JFreeChart chart = ChartFactory.createBarChart("Geomtric Generator Histogram", "Ranges", "Numbers", dataset);
				ChartFrame frame = new ChartFrame("Geometric Generator Histogram", chart);
				frame.setVisible(true);
				frame.setSize(1900, 1000);
			}
		});
		btnPlotHistogram.setBounds(12, 108, 169, 25);
		contentPane.add(btnPlotHistogram);
		
		textField = new JTextField();
		textField.setText("0.5");
		textField.setBounds(63, 40, 61, 19);
		contentPane.add(textField);
		textField.setColumns(10);
	}

}
