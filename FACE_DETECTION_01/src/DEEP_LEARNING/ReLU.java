package DEEP_LEARNING;

import java.util.ArrayList;

public class ReLU {
	private ConvolutionImage convData;

	public ArrayList<int[][]> ReLUData;

	public ReLU(ConvolutionImage convData) {
		this.convData = convData;
		buildReLUData();
	}

	private void buildReLUData() {
		// TODO Auto-generated method stub

	}

	public final ArrayList<int[][]> getReLUData() {
		return ReLUData;
	}

	public final void setReLUData(ArrayList<int[][]> reluData) {
		ReLUData = reluData;
	}

}
