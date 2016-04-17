package colors;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ColorChecking {
	/////////// http://stackoverflow.com/questions/19398238/how-to-mix-two-int-colors-correctly
	/////// alpha blending colors
	int width; // Dimensions of the image
	int height;
	public int[] red = new int[256];
	public int[] blue = new int[256];
	public int[] green = new int[256];
	// Create a Buffered Image.
	BufferedImage baseImage;
	BufferedImage Histogram;
	final int histHeight;
	final int histWidth;
	ColorChecking(){
		histHeight=100;
		histWidth=256;
		Histogram = new BufferedImage(histWidth, histHeight, BufferedImage.TYPE_INT_ARGB);
		
	}

	public void openImage(String x) {
		try {
			baseImage = ImageIO.read(new File(x));
		} catch (IOException e) {

			e.printStackTrace();
		}

		width = baseImage.getWidth();
		height = baseImage.getHeight();

		System.out.println(height + " " + width);
	}

	public void countColors() {

		int temp;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				temp = baseImage.getRGB(x, y);
				red[temp & 0xFF]++;
				green[temp >> 8 & 0xFF]++;
				blue[temp >> 16 & 0xFF]++;
			}
		}
		System.out.println(red.length);
		/*
		 * System.out.println(Arrays.toString(red));
		 * System.out.println(Arrays.toString(blue));
		 * System.out.println(Arrays.toString(green));
		 */}

	public void makeHistogram() throws IOException {
		int highest = Math.max(Math.max(Arrays.stream(red).max().getAsInt(), Arrays.stream(blue).max().getAsInt()),
				Arrays.stream(green).max().getAsInt());

		for (int x = 0; x < 256; x++) {
			drawDown(x, map(red[x], 0, highest, histHeight,0), 0xFFFF0000, Histogram);
			drawDown(x, map(green[x], 0, highest,  histHeight,0), 0xA600FF00, Histogram);
			drawDown(x, map(blue[x], 0, highest,  histHeight,0), 0x590000FF, Histogram);
		}

		ImageIO.write(Histogram, "PNG", new File("Histo.png"));
		Desktop.getDesktop().open(new File("Histo.png"));
	}

	public int map(int x, int in_min, int in_max, int out_min, int out_max) {

		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public void drawDown(int x, int y, int colorHex, BufferedImage histo) {
		// draws from y=val down until y=0
		System.out.println(x + "\t" + y + "\t" + (colorHex >> 24 & 0x00FFFFFF));
	//	y = histHeight - y;
		while (y < 100) {
			histo.setRGB(x, y, colorHex);
			y++;
		}
		return;
	}

	public static void main(String[] args) throws IOException {

		ColorChecking trial = new ColorChecking();
		trial.openImage("zelda.jpg");
		trial.countColors();
		trial.makeHistogram();
		// Put the pixels on the raster, choosing a color depending on its
		// coordinates.
		// System.out.println(colors.length);

		// Store the image using the PNG format.

	}
}
