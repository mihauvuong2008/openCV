package DEEP_LEARNING;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Mat;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.event.ActionEvent;

public class ConvolutionControl extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JPanel panel;
	private JLabel label;
	private JTextField textField_25;
	private JLabel label_1;
	private JTextField textField_26;
	private JPanel panel_1;
	private static Mat SRC;
	private JButton btnSet;
	ConvolutionImage ci;
	private JLabel label_Image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConvolutionControl dialog = new ConvolutionControl();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param sRC
	 */
	public ConvolutionControl() {
		setTitle("MatrixFilter");
		setBounds(100, 100, 357, 456);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			panel_1 = new JPanel();
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.weighty = 10.0;
			gbc_panel_1.gridwidth = 5;
			gbc_panel_1.insets = new Insets(0, 0, 5, 5);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 0;
			contentPanel.add(panel_1, gbc_panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[] { 0, 0 };
			gbl_panel_1.rowHeights = new int[] { 0, 0 };
			gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
			panel_1.setLayout(gbl_panel_1);
			{
				label_Image = new JLabel("");
				GridBagConstraints gbc_label_Image = new GridBagConstraints();
				gbc_label_Image.fill = GridBagConstraints.BOTH;
				gbc_label_Image.gridx = 0;
				gbc_label_Image.gridy = 0;
				panel_1.add(label_Image, gbc_label_Image);
			}
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			contentPanel.add(textField_1, gbc_textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 2;
			gbc_textField_2.gridy = 1;
			contentPanel.add(textField_2, gbc_textField_2);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 5);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 3;
			gbc_textField_3.gridy = 1;
			contentPanel.add(textField_3, gbc_textField_3);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 0);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 4;
			gbc_textField_4.gridy = 1;
			contentPanel.add(textField_4, gbc_textField_4);
		}
		{
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.insets = new Insets(0, 0, 5, 5);
			gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_5.gridx = 0;
			gbc_textField_5.gridy = 2;
			contentPanel.add(textField_5, gbc_textField_5);
		}
		{
			textField_6 = new JTextField();
			textField_6.setText("100");
			textField_6.setColumns(10);
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.insets = new Insets(0, 0, 5, 5);
			gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_6.gridx = 1;
			gbc_textField_6.gridy = 2;
			contentPanel.add(textField_6, gbc_textField_6);
		}
		{
			textField_7 = new JTextField();
			textField_7.setText("100");
			textField_7.setColumns(10);
			GridBagConstraints gbc_textField_7 = new GridBagConstraints();
			gbc_textField_7.insets = new Insets(0, 0, 5, 5);
			gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_7.gridx = 2;
			gbc_textField_7.gridy = 2;
			contentPanel.add(textField_7, gbc_textField_7);
		}
		{
			textField_8 = new JTextField();
			textField_8.setText("100");
			textField_8.setColumns(10);
			GridBagConstraints gbc_textField_8 = new GridBagConstraints();
			gbc_textField_8.insets = new Insets(0, 0, 5, 5);
			gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_8.gridx = 3;
			gbc_textField_8.gridy = 2;
			contentPanel.add(textField_8, gbc_textField_8);
		}
		{
			textField_9 = new JTextField();
			textField_9.setColumns(10);
			GridBagConstraints gbc_textField_9 = new GridBagConstraints();
			gbc_textField_9.insets = new Insets(0, 0, 5, 0);
			gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_9.gridx = 4;
			gbc_textField_9.gridy = 2;
			contentPanel.add(textField_9, gbc_textField_9);
		}
		{
			textField_10 = new JTextField();
			textField_10.setColumns(10);
			GridBagConstraints gbc_textField_10 = new GridBagConstraints();
			gbc_textField_10.insets = new Insets(0, 0, 5, 5);
			gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_10.gridx = 0;
			gbc_textField_10.gridy = 3;
			contentPanel.add(textField_10, gbc_textField_10);
		}
		{
			textField_11 = new JTextField();
			textField_11.setText("100");
			textField_11.setColumns(10);
			GridBagConstraints gbc_textField_11 = new GridBagConstraints();
			gbc_textField_11.insets = new Insets(0, 0, 5, 5);
			gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_11.gridx = 1;
			gbc_textField_11.gridy = 3;
			contentPanel.add(textField_11, gbc_textField_11);
		}
		{
			textField_12 = new JTextField();
			textField_12.setText("100");
			textField_12.setColumns(10);
			GridBagConstraints gbc_textField_12 = new GridBagConstraints();
			gbc_textField_12.insets = new Insets(0, 0, 5, 5);
			gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_12.gridx = 2;
			gbc_textField_12.gridy = 3;
			contentPanel.add(textField_12, gbc_textField_12);
		}
		{
			textField_13 = new JTextField();
			textField_13.setText("100");
			textField_13.setColumns(10);
			GridBagConstraints gbc_textField_13 = new GridBagConstraints();
			gbc_textField_13.insets = new Insets(0, 0, 5, 5);
			gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_13.gridx = 3;
			gbc_textField_13.gridy = 3;
			contentPanel.add(textField_13, gbc_textField_13);
		}
		{
			textField_14 = new JTextField();
			textField_14.setColumns(10);
			GridBagConstraints gbc_textField_14 = new GridBagConstraints();
			gbc_textField_14.insets = new Insets(0, 0, 5, 0);
			gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_14.gridx = 4;
			gbc_textField_14.gridy = 3;
			contentPanel.add(textField_14, gbc_textField_14);
		}
		{
			textField_15 = new JTextField();
			textField_15.setColumns(10);
			GridBagConstraints gbc_textField_15 = new GridBagConstraints();
			gbc_textField_15.insets = new Insets(0, 0, 5, 5);
			gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_15.gridx = 0;
			gbc_textField_15.gridy = 4;
			contentPanel.add(textField_15, gbc_textField_15);
		}
		{
			textField_16 = new JTextField();
			textField_16.setText("100");
			textField_16.setColumns(10);
			GridBagConstraints gbc_textField_16 = new GridBagConstraints();
			gbc_textField_16.insets = new Insets(0, 0, 5, 5);
			gbc_textField_16.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_16.gridx = 1;
			gbc_textField_16.gridy = 4;
			contentPanel.add(textField_16, gbc_textField_16);
		}
		{
			textField_17 = new JTextField();
			textField_17.setText("100");
			textField_17.setColumns(10);
			GridBagConstraints gbc_textField_17 = new GridBagConstraints();
			gbc_textField_17.insets = new Insets(0, 0, 5, 5);
			gbc_textField_17.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_17.gridx = 2;
			gbc_textField_17.gridy = 4;
			contentPanel.add(textField_17, gbc_textField_17);
		}
		{
			textField_18 = new JTextField();
			textField_18.setText("100");
			textField_18.setColumns(10);
			GridBagConstraints gbc_textField_18 = new GridBagConstraints();
			gbc_textField_18.insets = new Insets(0, 0, 5, 5);
			gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_18.gridx = 3;
			gbc_textField_18.gridy = 4;
			contentPanel.add(textField_18, gbc_textField_18);
		}
		{
			textField_19 = new JTextField();
			textField_19.setColumns(10);
			GridBagConstraints gbc_textField_19 = new GridBagConstraints();
			gbc_textField_19.insets = new Insets(0, 0, 5, 0);
			gbc_textField_19.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_19.gridx = 4;
			gbc_textField_19.gridy = 4;
			contentPanel.add(textField_19, gbc_textField_19);
		}
		{
			textField_20 = new JTextField();
			textField_20.setColumns(10);
			GridBagConstraints gbc_textField_20 = new GridBagConstraints();
			gbc_textField_20.insets = new Insets(0, 0, 5, 5);
			gbc_textField_20.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_20.gridx = 0;
			gbc_textField_20.gridy = 5;
			contentPanel.add(textField_20, gbc_textField_20);
		}
		{
			textField_21 = new JTextField();
			textField_21.setColumns(10);
			GridBagConstraints gbc_textField_21 = new GridBagConstraints();
			gbc_textField_21.insets = new Insets(0, 0, 5, 5);
			gbc_textField_21.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_21.gridx = 1;
			gbc_textField_21.gridy = 5;
			contentPanel.add(textField_21, gbc_textField_21);
		}
		{
			textField_22 = new JTextField();
			textField_22.setColumns(10);
			GridBagConstraints gbc_textField_22 = new GridBagConstraints();
			gbc_textField_22.insets = new Insets(0, 0, 5, 5);
			gbc_textField_22.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_22.gridx = 2;
			gbc_textField_22.gridy = 5;
			contentPanel.add(textField_22, gbc_textField_22);
		}
		{
			textField_23 = new JTextField();
			textField_23.setColumns(10);
			GridBagConstraints gbc_textField_23 = new GridBagConstraints();
			gbc_textField_23.insets = new Insets(0, 0, 5, 5);
			gbc_textField_23.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_23.gridx = 3;
			gbc_textField_23.gridy = 5;
			contentPanel.add(textField_23, gbc_textField_23);
		}
		{
			textField_24 = new JTextField();
			textField_24.setColumns(10);
			GridBagConstraints gbc_textField_24 = new GridBagConstraints();
			gbc_textField_24.insets = new Insets(0, 0, 5, 0);
			gbc_textField_24.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_24.gridx = 4;
			gbc_textField_24.gridy = 5;
			contentPanel.add(textField_24, gbc_textField_24);
		}
		{
			panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.weighty = 1.0;
			gbc_panel.gridwidth = 5;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 6;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 40, 86, 35, 86, 0, 0 };
			gbl_panel.rowHeights = new int[] { 20, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				label = new JLabel("Division:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.WEST;
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				textField_25 = new JTextField();
				textField_25.setColumns(10);
				GridBagConstraints gbc_textField_25 = new GridBagConstraints();
				gbc_textField_25.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_25.insets = new Insets(0, 0, 0, 5);
				gbc_textField_25.gridx = 1;
				gbc_textField_25.gridy = 0;
				panel.add(textField_25, gbc_textField_25);
			}
			{
				label_1 = new JLabel("Offset:");
				GridBagConstraints gbc_label_1 = new GridBagConstraints();
				gbc_label_1.anchor = GridBagConstraints.WEST;
				gbc_label_1.insets = new Insets(0, 0, 0, 5);
				gbc_label_1.gridx = 2;
				gbc_label_1.gridy = 0;
				panel.add(label_1, gbc_label_1);
			}
			{
				textField_26 = new JTextField();
				textField_26.setColumns(10);
				GridBagConstraints gbc_textField_26 = new GridBagConstraints();
				gbc_textField_26.insets = new Insets(0, 0, 0, 5);
				gbc_textField_26.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_26.gridx = 3;
				gbc_textField_26.gridy = 0;
				panel.add(textField_26, gbc_textField_26);
			}
			{
				btnSet = new JButton("set");
				btnSet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ci.setFilter(LoadArray());
					}

					private double[][] LoadArray() {
						double[][] result = new double[3][3];
						result[0][0] = Double.valueOf(textField_6.getText());
						result[0][1] = Double.valueOf(textField_7.getText());
						result[0][2] = Double.valueOf(textField_8.getText());
						result[1][0] = Double.valueOf(textField_11.getText());
						result[1][1] = Double.valueOf(textField_12.getText());
						result[1][2] = Double.valueOf(textField_13.getText());
						result[2][0] = Double.valueOf(textField_16.getText());
						result[2][1] = Double.valueOf(textField_17.getText());
						result[2][2] = Double.valueOf(textField_18.getText());
						return result;
					}
				});
				GridBagConstraints gbc_btnSet = new GridBagConstraints();
				gbc_btnSet.gridx = 4;
				gbc_btnSet.gridy = 0;
				panel.add(btnSet, gbc_btnSet);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						Mat mat = ci.getSingleConvolutionData();
						LoadMatToLabel(mat, label_Image);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{

				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public BufferedImage Mat2BufferedImage(Mat m) {
		// Fastest code
		// output can be assigned either to a BufferedImage or to an Image

		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;
	}

	public void setSRC(Mat SRC) {
		this.SRC = SRC;
		ci = new ConvolutionImage(SRC);
	}

	private void LoadMatToLabel(Mat mat, JLabel label) {
		Image img2 = Mat2BufferedImage(mat);
		int w = img2.getWidth(null);
		int h = img2.getHeight(null);
		int width = (int) label.getWidth();
		int height = (int) label.getHeight();
		int hei;
		int wid;
		if (h > height) {
			hei = height;
			wid = hei * width / height;
		} else {
			hei = h;
			wid = hei * w / h;
		}
		// scale it the smooth way
		Image newimg = img2.getScaledInstance(wid, hei, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		label.setIcon(icon);
	}
}
