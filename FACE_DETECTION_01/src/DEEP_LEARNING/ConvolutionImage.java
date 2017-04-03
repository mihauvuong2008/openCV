package DEEP_LEARNING;

import java.util.ArrayList;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ConvolutionImage {
	final Mat BaseImage;
	private double[][] featureDetector = new double[3][3];
	ArrayList<Mat> ConvolutionData = new ArrayList<>();

	final double Width;
	final double Height;
	private double[][] Data;

	public ConvolutionImage(Mat BaseImage) {
		this.BaseImage = new Mat();
		Size sz = new Size(128, 128);
		Imgproc.resize(BaseImage, this.BaseImage, sz);
		Width = this.BaseImage.width();
		Height = this.BaseImage.height();
		// ConvolutionDataBuild();
	}

	private void initFeatureDetector(int x, int y) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (BaseImage.get(x + i, y + j) != null) {
					featureDetector[i][j] = (BaseImage.get(x + i, y + j)[0] + BaseImage.get(x + i, y + j)[1]
							+ BaseImage.get(x + i, y + j)[2]) / 3;
				}
			}
		}
	}

	private void ConvolutionDataBuild() {
		Size sz = new Size(Width - 2, Height - 2);
		for (int x = 0; x < Width; x++) {
			for (int y = 0; y < Height; y++) {
				initFeatureDetector(x, y);
				Mat Data = new Mat();
				Data.create(sz, CvType.CV_8UC1);
				for (int i = 0; i < Width - 2; i++) {
					for (int j = 0; j < Height - 2; j++) {
						Data.put(i, j, getFilteringData(i, j));
					}
				}
				ConvolutionData.add(Data);
			}
		}
	}

	private double[] getFilteringData(int i, int j) {
		double[] data = new double[3];
		double Sum = 0;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (BaseImage.get(i + x, j + y) != null) {
					Sum += (BaseImage.get(i + x, j + y)[0] + BaseImage.get(i + x, j + y)[1]
							+ BaseImage.get(i + x, j + y)[2]) / 3 * featureDetector[x][y];
				}
			}
		}
		Sum = Sum / (9 * 255);
		data[0] = Sum;
		data[1] = Sum;
		data[2] = Sum;
		return data;
	}

	public Mat getFilterImage(int i) {
		if (ConvolutionData.size() > 0)
			return ConvolutionData.get(0);
		else
			return null;
	}

	public final ArrayList<Mat> getConvolutionData() {
		return ConvolutionData;
	}

	public void setFilter(double[][] loadArray) {
		featureDetector = loadArray;
	}

	public Mat getSingleConvolutionData() {
		Size sz = new Size(Width - 2, Height - 2);
		Mat Data = new Mat();
		Data.create(sz, CvType.CV_8UC1);
		for (int i = 0; i < Width - 2; i++) {
			for (int j = 0; j < Height - 2; j++) {
				Data.put(i, j, getFilteringData(i, j));
			}
		}
		return Data;
	}

}
