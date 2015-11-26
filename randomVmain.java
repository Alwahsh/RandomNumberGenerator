import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import org.jfree.chart.ChartFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class randomVmain
{

	private JFrame frmRandomv;

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
					randomVmain window = new randomVmain();
					window.frmRandomv.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public randomVmain()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmRandomv = new JFrame();
		frmRandomv.setTitle("RandomV");
		frmRandomv.setBounds(100, 100, 248, 235);
		frmRandomv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRandomv.getContentPane().setLayout(null);
		
		JButton btnUniformGenerator = new JButton("Uniform Generator");
		btnUniformGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UniformFrame uni_frame = new UniformFrame();
				uni_frame.setVisible(true);
			}
		});
		btnUniformGenerator.setBounds(12, 12, 208, 25);
		frmRandomv.getContentPane().add(btnUniformGenerator);
		
		JButton btnGaussianGenerator = new JButton("Gaussian Generator");
		btnGaussianGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GaussianFrame gauss_frame = new GaussianFrame();
				gauss_frame.setVisible(true);
			}
		});
		btnGaussianGenerator.setBounds(12, 52, 208, 25);
		frmRandomv.getContentPane().add(btnGaussianGenerator);
		
		JButton btnExponentialGenerator = new JButton("Exponential Generator");
		btnExponentialGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExponentialFrame exp_frame = new ExponentialFrame();
				exp_frame.setVisible(true);;
			}
		});
		btnExponentialGenerator.setBounds(12, 89, 208, 25);
		frmRandomv.getContentPane().add(btnExponentialGenerator);
		
		JButton btnWeibullGenerator = new JButton("Weibull Generator");
		btnWeibullGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WeibullFrame wei_frame = new WeibullFrame();
				wei_frame.setVisible(true);
			}
		});
		btnWeibullGenerator.setBounds(12, 126, 208, 25);
		frmRandomv.getContentPane().add(btnWeibullGenerator);
		
		JButton btnGeometricGenerator = new JButton("Geometric Generator");
		btnGeometricGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeometricFrame geo_frame = new GeometricFrame();
				geo_frame.setVisible(true);
			}
		});
		btnGeometricGenerator.setBounds(12, 163, 208, 25);
		frmRandomv.getContentPane().add(btnGeometricGenerator);
	}
}
