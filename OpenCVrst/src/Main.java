import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class Main {

	public static void main(String[] args) {
		// Load thu vien lien ket C++
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img = Imgcodecs.imread(
				"./data/Festival2.jpg"/* , Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE */);

		// Detect face feature and return position of the detected feature in
		// the original image
		CascadeClassifier classifier = loadCascadeClassifier();
		CascadeClassifier Eyeclassifier = loadEyeCascadeClassifier();

		// lam mo anh
		// int erosion_size = 1;
		// Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new
		// Size(erosion_size + 1, erosion_size + 1),
		// new Point(erosion_size, erosion_size));
		// Imgproc.erode(img, img, element);

		MatOfRect detections = new MatOfRect();
		MatOfRect Eyedetections = new MatOfRect();
		Eyeclassifier.detectMultiScale(img, Eyedetections);
		classifier.detectMultiScale(img, detections);

		System.out.println("detect eye: " + Eyedetections.toArray().length);
		// Draw a bounding box around each face.
		for (Rect rect : detections.toArray()) {
			Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
		}
		for (Rect rect : Eyedetections.toArray()) {
			Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 255));
		}
		displayImage(Mat2BufferedImage(img));

	}

	static CascadeClassifier loadCascadeClassifier() {
		CascadeClassifier classifier = new CascadeClassifier("./data/haarcascade_frontalface_alt.xml");
		return classifier;
	}

	static CascadeClassifier loadCascadeClassifier_FrontAlt2() {
		CascadeClassifier classifier = new CascadeClassifier("./data/haarcascade_frontalcatface_extended.xml");
		return classifier;
	}

	static CascadeClassifier loadEyeCascadeClassifier() {
		CascadeClassifier classifier = new CascadeClassifier("./data/haarcascade_eye.xml");
		return classifier;
	}

	public static BufferedImage Mat2BufferedImage(Mat m) {
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

	public static void displayImage(Image img2) {
		// BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		// int imgw = img2.getWidth(null);
		// int imgh = img2.getHeight(null);
		int width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 3;
		int height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 2 / 3;
		frame.setSize(width + 50, height + 50);
		// scale it the smooth way
		Image newimg = img2.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}