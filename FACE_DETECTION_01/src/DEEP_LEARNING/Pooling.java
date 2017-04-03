package DEEP_LEARNING;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Pooling {
	private final int PoolingSize = 2;
	private final int PoolingStep = 2;
	private ReLU ReLUData;
	private ReLU PoolingData;
	private Mat SRC;
	private Mat Data;

	public Pooling(ReLU ReLUData) {
		this.ReLUData = ReLUData;
		buildPoolingData();
	}

	public Pooling(Mat sRC) {
		this.SRC = sRC;
	}

	private void buildPoolingData() {
		Data = new Mat();
		Data.create(SRC.size(), CvType.CV_8UC1);

	}

	public final ReLU getPoolingData() {
		return PoolingData;
	}

	public static Mat getImage() {
		return null;
	}
}
