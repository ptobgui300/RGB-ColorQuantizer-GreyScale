
public class ImageHandler {

	private MImage one;

	public ImageHandler(MImage one) {
		this.one = one;
	}

	// task1
	public void grayScale() {

		int[] rgb = new int[3];
		int gray = 0;

		for (int i = 0; i < one.getW(); i++) {
			for (int j = 0; j < one.getH(); j++) {

				one.getPixel(i, j, rgb);

				gray = (int) Math.round((0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2]));

				rgb[0] = gray;
				rgb[1] = gray;
				rgb[2] = gray;

				one.setPixel(i, j, rgb);
			}
		}

	}

	// task2
	public void UCQ() {

		int[] rgb = new int[3];

		for (int i = 0; i < one.getW(); i++) {
			for (int j = 0; j < one.getH(); j++) {

				one.getPixel(i, j, rgb);

				rgb[0] = (rgb[0] / 32) * 32 + 16;
				rgb[1] = (rgb[1] / 32) * 32 + 16;
				rgb[2] = (rgb[2] / 64) * 64 + 32;

				one.setPixel(i, j, rgb);
			}
		}

	}

	public void indexValue() {

		int[] rgb = new int[3];
		int gray = 0;

		for (int i = 0; i < one.getW(); i++) {
			for (int j = 0; j < one.getH(); j++) {
				one.getPixel(i, j, rgb);			
				
				/*
				gray = (int) Math.round((0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2]));

				rgb[0] = (gray /32) *32 +16;
				rgb[1] = gray/32*32 +16;
				rgb[2] = (gray/64)*64 +32;*/
				
				/*
				rgb[0] = (rgb[0] / 32) * 32 + 16;
				rgb[1] = (rgb[1] / 32) * 32 + 16;
				rgb[2] = (rgb[2] / 64) * 64 + 32;
				
				gray = (int) Math.round((0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2]));
				
				rgb[0] = gray;
				rgb[1] = gray;
				rgb[2] = gray;*/

				

				one.setPixel(i, j, rgb);
			}
		}
	}

	public void generateLookUpTable() {

		System.out.println("LUT by UCQ");
		System.out.println("Index " + "  R   " + " G  " + "  B");
		System.out.println("____________________________");

		int rgb[] = new int[3];
		int bin[] = new int[3];

		int r = 0, b = 0, g = 0;

		int red = 0;
		int green = 0;
		int blue = 0;

		r = rgb[0] / 32;
		b = rgb[1] / 32;
		g = rgb[2] / 64;

		for (int i = 0; i <= 255; i++) {

			bin = rgbValue8Bit(i);

			red = 32 * bin[0] + 16;
			green = 32 * bin[1] + 16;
			blue = 64 * bin[2] + 32;

			System.out.println(i + "      " + red + "   " + green + "   " + blue);

		}

	}

	public int[] rgbValue8Bit(int index) {

		int bin[] = new int[3];
		// RED

		if (index / (int) Math.pow(2, 7) > 0) {
			bin[0] += (int) Math.pow(2, 2);
			index -= (int) Math.pow(2, 7);
		}
		if (index / (int) Math.pow(2, 6) > 0) {
			bin[0] += (int) Math.pow(2, 1);
			index -= (int) Math.pow(2, 6);
		}
		if (index / (int) Math.pow(2, 5) > 0) {
			bin[0] += (int) Math.pow(2, 0);
			index -= (int) Math.pow(2, 5);
		}

		// Green

		if (index / (int) Math.pow(2, 4) > 0) {
			bin[1] += (int) Math.pow(2, 2);
			index -= (int) Math.pow(2, 4);
		}
		if (index / (int) Math.pow(2, 3) > 0) {
			bin[1] += (int) Math.pow(2, 1);
			index -= (int) Math.pow(2, 3);
		}
		if (index / (int) Math.pow(2, 2) > 0) {
			bin[1] += (int) Math.pow(2, 0);
			index -= (int) Math.pow(2, 2);
		}

		// BLUE
		if (index / (int) Math.pow(2, 1) > 0) {
			bin[2] += (int) Math.pow(2, 1);
			index -= (int) Math.pow(2, 1);
		}
		if (index == 1) {
			bin[2] += (int) Math.pow(2, 0);
			index -= (int) Math.pow(2, 0);
		}

		return bin;
	}

}
