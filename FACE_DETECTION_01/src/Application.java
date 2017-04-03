import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import DEEP_LEARNING.ConvolutionControl;
import DEEP_LEARNING.ConvolutionImage;
import DEEP_LEARNING.Pooling;

import java.awt.GridLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;

public class Application extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private ArrayList<MatImage> Data = new ArrayList<>();
	private ArrayList<MatImage> SaveData = new ArrayList<>();
	private ArrayList<Rect> face = new ArrayList<>();
	private JList<MatImage> list_resource;
	private JCheckBox chckbxGrayImage;
	private JList<MatImage> list_Save;
	private JLabel lblNumofItem;
	private JLabel lblFace;
	private JLabel lblNewConvo;
	private JList<MatImage> convolupart;
	private JLabel label_Pooling;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 561);
		setTitle("FACE DETECTION");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 122, 93, 0, 93, 0 };
		gbl_contentPane.rowHeights = new int[] { 29, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnLoadSource = new JButton("Load Source");
		btnLoadSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
				JFileChooser c = new JFileChooser();
				c.setFileFilter(imageFilter);
				c.setMultiSelectionEnabled(true);
				int rVal = c.showOpenDialog(new JPanel());
				File[] filesInDirectory = c.getSelectedFiles();
				for (File f : filesInDirectory) {
					Mat img = Imgcodecs.imread(f.getPath());
					MatImage mi = new MatImage(f.getName(), img);
					Data.add(mi);
				}
				loadList_resource();
			}
		});
		GridBagConstraints gbc_btnLoadSource = new GridBagConstraints();
		gbc_btnLoadSource.weightx = 1.0;
		gbc_btnLoadSource.fill = GridBagConstraints.BOTH;
		gbc_btnLoadSource.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadSource.gridx = 0;
		gbc_btnLoadSource.gridy = 0;
		contentPane.add(btnLoadSource, gbc_btnLoadSource);

		JButton btnStart = new JButton("Detect Face");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_resource.getSelectedValue() != null) {
					CascadeClassifier classifier = loadCascadeClassifier();

					MatOfRect detections = new MatOfRect();
					Mat SRC = ((MatImage) list_resource.getSelectedValue()).getMat();
					Mat DST = new Mat();
					if (chckbxGrayImage.isSelected()) {
						Imgproc.cvtColor(SRC, DST, Imgproc.COLOR_RGB2GRAY);
					} else
						DST = SRC;
					classifier.detectMultiScale(DST, detections);

					// new ListSave
					SaveData = new ArrayList<>();
					DefaultListModel<MatImage> model = new DefaultListModel<>();
					// Draw a bounding box around each face.
					for (Rect rect : detections.toArray()) {
						double anpha = 0.2;
						double beta = anpha;
						int x = (int) (rect.x - rect.width * anpha);
						if (x < 0)
							x = rect.x;
						int y = (int) (rect.y - rect.height * beta);
						if (y < 0)
							y = rect.y;
						int w = (int) (rect.width * (1 + 2 * anpha));
						int h = (int) (rect.height * (1 + 2 * beta));
						Rect newrect = new Rect(x, y, (w + x) < SRC.width() ? w : rect.width,
								(h + y) < SRC.height() ? h : rect.height);
						face.add(newrect);
						model.addElement(new MatImage("Face x: " + rect.x + ", y:" + rect.y, SRC.submat(newrect)));
					}
					list_Save.setModel(model);
					lblNumofItem.setText("Num Of Item: " + list_Save.getModel().getSize());
					displayImage(Mat2BufferedImage(DST));
				}
			}
		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.weightx = 1.0;
		gbc_btnStart.fill = GridBagConstraints.BOTH;
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 0;
		contentPane.add(btnStart, gbc_btnStart);

		JButton btnViewFace = new JButton("View Face");
		btnViewFace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mat SRC = ((MatImage) list_Save.getSelectedValue()).getMat();
				displayImage(Mat2BufferedImage(SRC));
			}
		});
		GridBagConstraints gbc_btnViewFace = new GridBagConstraints();
		gbc_btnViewFace.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewFace.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewFace.gridx = 2;
		gbc_btnViewFace.gridy = 0;
		contentPane.add(btnViewFace, gbc_btnViewFace);

		chckbxGrayImage = new JCheckBox("gray image");
		GridBagConstraints gbc_chckbxGrayImage = new GridBagConstraints();
		gbc_chckbxGrayImage.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxGrayImage.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxGrayImage.gridx = 3;
		gbc_chckbxGrayImage.gridy = 0;
		contentPane.add(chckbxGrayImage, gbc_chckbxGrayImage);

		JButton btnViewImage = new JButton("View Image");
		btnViewImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list_resource.getSelectedValue() != null) {
					CascadeClassifier classifier = loadCascadeClassifier();

					MatOfRect detections = new MatOfRect();
					Mat SRC = ((MatImage) list_resource.getSelectedValue()).getMat();
					Mat DST = new Mat();
					if (chckbxGrayImage.isSelected()) {
						Imgproc.cvtColor(SRC, DST, Imgproc.COLOR_RGB2GRAY);
					} else {
						DST = SRC.clone();
					}
					classifier.detectMultiScale(DST, detections);

					// Draw a bounding box around each face.
					for (Rect rect : detections.toArray()) {
						Imgproc.rectangle(DST, new Point(rect.x, rect.y),
								new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
					}
					displayImage(Mat2BufferedImage(DST));
				}
			}
		});
		GridBagConstraints gbc_btnViewImage = new GridBagConstraints();
		gbc_btnViewImage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewImage.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewImage.gridx = 1;
		gbc_btnViewImage.gridy = 1;
		contentPane.add(btnViewImage, gbc_btnViewImage);

		list_resource = new JList();
		list_resource.setVisibleRowCount(20);
		list_resource.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(list_resource, popupMenu);

		JMenuItem mntmOpen = new JMenuItem("Open");
		popupMenu.add(mntmOpen);

		JMenuItem mntmDelete = new JMenuItem("Delete");
		popupMenu.add(mntmDelete);

		JScrollPane scrollPane = new JScrollPane(list_resource);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		JButton btnCnnTrain = new JButton("CNN training");
		GridBagConstraints gbc_btnCnnTrain = new GridBagConstraints();
		gbc_btnCnnTrain.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCnnTrain.insets = new Insets(0, 0, 5, 0);
		gbc_btnCnnTrain.gridx = 3;
		gbc_btnCnnTrain.gridy = 1;
		contentPane.add(btnCnnTrain, gbc_btnCnnTrain);

		list_Save = new JList();
		list_Save.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list_Save.getSelectedValue() != null) {
					Mat SRC = ((MatImage) list_Save.getSelectedValue()).getMat();
					LoadMatToLabel(SRC, lblFace);
				}
			}
		});

		lblNumofItem = new JLabel("Item: ");
		GridBagConstraints gbc_lblNumofItem = new GridBagConstraints();
		gbc_lblNumofItem.anchor = GridBagConstraints.WEST;
		gbc_lblNumofItem.fill = GridBagConstraints.VERTICAL;
		gbc_lblNumofItem.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumofItem.gridx = 1;
		gbc_lblNumofItem.gridy = 2;
		contentPane.add(lblNumofItem, gbc_lblNumofItem);

		JButton btnViewface = new JButton("Choose Classifier");
		GridBagConstraints gbc_btnViewface = new GridBagConstraints();
		gbc_btnViewface.fill = GridBagConstraints.BOTH;
		gbc_btnViewface.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewface.gridx = 3;
		gbc_btnViewface.gridy = 2;
		contentPane.add(btnViewface, gbc_btnViewface);
		list_Save.setVisibleRowCount(20);
		list_Save.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		JScrollPane scrollPane_1 = new JScrollPane(list_Save);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 5;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 3;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.weighty = 3.0;
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 1, 0 };
		gbl_panel.rowHeights = new int[] { 1, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0 };
		panel.setLayout(gbl_panel);

		lblFace = new JLabel("");
		lblFace.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblFace = new GridBagConstraints();
		gbc_lblFace.anchor = GridBagConstraints.NORTH;
		gbc_lblFace.fill = GridBagConstraints.BOTH;
		gbc_lblFace.gridx = 0;
		gbc_lblFace.gridy = 0;
		panel.add(lblFace, gbc_lblFace);

		JButton btnViewConvolution = new JButton("Convolution");
		btnViewConvolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list_Save.getSelectedValue() != null) {
					Mat SRC = ((MatImage) list_Save.getSelectedValue()).getMat();

					Mat DST = new Mat();
					DST.create(SRC.size(), CvType.CV_8UC1);
					if (chckbxGrayImage.isSelected()) {
						Imgproc.cvtColor(SRC, DST, Imgproc.COLOR_RGB2GRAY);
					} else {
						DST = SRC.clone();
					}
					ConvolutionControl cc = new ConvolutionControl();
					cc.setSRC(SRC);
					cc.setVisible(true);
					ConvolutionImage ci = new ConvolutionImage(SRC);

					if (ci.getFilterImage(0) != null) {
						DefaultListModel<MatImage> model = new DefaultListModel<>();
						for (Mat mat : ci.getConvolutionData()) {
							MatImage matimage = new MatImage("Convol", mat);
							model.addElement(matimage);
						}
						convolupart.setModel(model);
					}
				}
			}
		});
		GridBagConstraints gbc_btnViewConvolution = new GridBagConstraints();
		gbc_btnViewConvolution.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewConvolution.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewConvolution.gridx = 2;
		gbc_btnViewConvolution.gridy = 4;
		contentPane.add(btnViewConvolution, gbc_btnViewConvolution);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 224, 0 };
		gbl_panel_1.rowHeights = new int[] { 127, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		lblNewConvo = new JLabel("");
		lblNewConvo.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblNewConvo = new GridBagConstraints();
		gbc_lblNewConvo.anchor = GridBagConstraints.NORTH;
		gbc_lblNewConvo.fill = GridBagConstraints.BOTH;
		gbc_lblNewConvo.gridx = 0;
		gbc_lblNewConvo.gridy = 0;
		panel_1.add(lblNewConvo, gbc_lblNewConvo);

		JButton btnPooling = new JButton("Pooling");
		btnPooling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (convolupart.getSelectedValue() != null) {
					Mat SRC = ((MatImage) list_Save.getSelectedValue()).getMat();

					Pooling ci = new Pooling(SRC);

					if (Pooling.getImage() != null) {
						LoadMatToLabel(Pooling.getImage(), label_Pooling);
					}
				}
			}
		});
		GridBagConstraints gbc_btnPooling = new GridBagConstraints();
		gbc_btnPooling.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPooling.insets = new Insets(0, 0, 5, 0);
		gbc_btnPooling.gridx = 3;
		gbc_btnPooling.gridy = 4;
		contentPane.add(btnPooling, gbc_btnPooling);

		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.weighty = 5.0;
		gbc_scrollPane_2.gridheight = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 5;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);

		convolupart = new JList();
		convolupart.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (convolupart.getSelectedValue() != null) {
					Mat SRC = ((MatImage) convolupart.getSelectedValue()).getMat();
					LoadMatToLabel(SRC, lblNewConvo);
				}
			}
		});
		convolupart.setVisibleRowCount(20);
		convolupart.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		scrollPane_2.setViewportView(convolupart);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 5;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 1, 0 };
		gbl_panel_2.rowHeights = new int[] { 1, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		label_Pooling = new JLabel("");
		label_Pooling.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_label_Pooling = new GridBagConstraints();
		gbc_label_Pooling.anchor = GridBagConstraints.NORTH;
		gbc_label_Pooling.fill = GridBagConstraints.BOTH;
		gbc_label_Pooling.gridx = 0;
		gbc_label_Pooling.gridy = 0;
		panel_2.add(label_Pooling, gbc_label_Pooling);

	}

	protected void loadList_resource() {
		DefaultListModel<MatImage> model = new DefaultListModel<>();
		for (MatImage m : Data) {
			model.addElement(m);
		}
		list_resource.setModel(model);
	}

	CascadeClassifier loadCascadeClassifier() {
		CascadeClassifier classifier = new CascadeClassifier("./data/haarcascade_frontalface_alt.xml");
		return classifier;
	}

	CascadeClassifier loadEyeCascadeClassifier() {
		CascadeClassifier classifier = new CascadeClassifier("./data/haarcascade_eye.xml");
		return classifier;
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

	public void displayImage(Image img2) {
		// BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		int w = img2.getWidth(null);
		int h = img2.getHeight(null);
		int width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 3;
		int height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2 / 3;
		int hei;
		int wid = 0;
		if (h > height) {
			hei = height;
			wid = hei * width / height;
		} else {
			hei = h;
			wid = hei * w / h;
		}
		frame.setSize(wid + 50, hei + 50);
		// scale it the smooth way
		Image newimg = img2.getScaledInstance(wid, hei, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.getContentPane().add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
