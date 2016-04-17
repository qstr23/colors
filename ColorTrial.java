package colors;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

public class ColorTrial {

	/*
	 * 2 * Part of the Java Image Processing Cookbook, please see 3 *
	 * http://www.lac.inpe.br/~rafael.santos/JIPCookbook.jsp 4 * for information
	 * on usage and distribution. 5 * Rafael Santos (rafael.santos@lac.inpe.br)
	 * 6
	 */
	int[][][] colorLineOld = new int[][][] { { { 237, 10, 63 }, { 204, 71, 75 }, { 204, 51, 54 }, { 225, 44, 44 },
			{ 185, 78, 72 }, { 179, 59, 36 }, { 204, 85, 61 }, { 230, 115, 92 }, { 255, 136, 100 }, { 242, 198, 73 },
			{ 252, 214, 103 }, { 252, 232, 131 }, { 181, 179, 92 }, { 255, 255, 159 }, { 190, 230, 75 },
			{ 58, 166, 85 }, { 108, 166, 124 }, { 1, 120, 111 }, { 46, 180, 230 }, { 0, 70, 140 }, { 140, 144, 200 },
			{ 112, 112, 204 }, { 63, 38, 191 }, { 115, 46, 108 }, { 246, 83, 166 }, { 255, 166, 201 }, { 233, 116, 81 },
			{ 102, 66, 40 }, { 255, 203, 164 }, { 128, 85, 51 }, { 102, 82, 51 }, { 230, 188, 92 }, { 146, 146, 110 },
			{ 201, 192, 187 }, { 218, 138, 103 }, { 0, 0, 0 }, { 115, 106, 98 }, { 255, 255, 255 } },
			{ { 217, 33, 33 }, { 229, 144, 115 }, { 236, 177, 118 }, { 242, 186, 73 }, { 255, 235, 0 },
					{ 250, 250, 55 }, { 217, 230, 80 }, { 172, 191, 96 }, { 94, 140, 49 }, { 77, 140, 87 },
					{ 141, 217, 204 }, { 48, 191, 191 }, { 126, 212, 230 }, { 71, 171, 204 }, { 172, 172, 230 },
					{ 139, 114, 190 }, { 115, 51, 128 }, { 217, 130, 181 }, { 166, 58, 121 }, { 165, 83, 83 },
					{ 139, 134, 128 }, { 0, 0, 0 } },
			{ { 237, 10, 63 }, { 195, 33, 72 }, { 185, 78, 72 }, { 255, 104, 31 }, { 255, 136, 51 }, { 236, 177, 118 },
					{ 255, 174, 66 }, { 242, 198, 73 }, { 252, 214, 103 }, { 251, 232, 112 }, { 181, 179, 92 },
					{ 255, 255, 159 }, { 197, 225, 122 }, { 95, 167, 119 }, { 147, 223, 184 }, { 1, 163, 104 },
					{ 149, 224, 232 }, { 108, 218, 231 }, { 0, 149, 183 }, { 71, 171, 204 }, { 46, 180, 230 },
					{ 51, 154, 204 }, { 0, 70, 140 }, { 0, 102, 255 }, { 69, 112, 230 }, { 140, 144, 200 },
					{ 118, 110, 200 }, { 100, 86, 183 }, { 143, 71, 179 }, { 191, 143, 204 }, { 230, 103, 206 },
					{ 226, 156, 210 }, { 217, 108, 190 }, { 235, 176, 215 }, { 187, 51, 133 }, { 246, 83, 166 },
					{ 255, 166, 201 }, { 230, 46, 107 }, { 255, 145, 164 }, { 202, 52, 53 }, { 233, 116, 81 },
					{ 175, 89, 62 }, { 255, 203, 164 }, { 102, 82, 51 }, { 201, 192, 187 }, { 0, 0, 0 },
					{ 139, 134, 128 }, { 255, 255, 255 } },
			{ { 237, 10, 63 }, { 195, 33, 72 }, { 198, 45, 66 }, { 185, 78, 72 }, { 255, 63, 52 }, { 254, 111, 94 },
					{ 255, 112, 52 }, { 255, 104, 31 }, { 255, 136, 51 }, { 255, 174, 66 }, { 242, 198, 73 },
					{ 248, 213, 104 }, { 252, 214, 103 }, { 251, 232, 112 }, { 241, 231, 136 }, { 236, 235, 189 },
					{ 181, 179, 92 }, { 255, 255, 159 }, { 197, 225, 122 }, { 95, 167, 119 }, { 147, 223, 184 },
					{ 1, 163, 104 }, { 1, 120, 111 }, { 143, 216, 216 }, { 149, 224, 232 }, { 118, 215, 234 },
					{ 0, 149, 183 }, { 147, 204, 234 }, { 40, 135, 200 }, { 0, 102, 204 }, { 0, 70, 140 },
					{ 0, 102, 255 }, { 169, 178, 195 }, { 195, 205, 230 }, { 118, 110, 200 }, { 100, 86, 183 },
					{ 131, 89, 163 }, { 230, 103, 206 }, { 142, 49, 121 }, { 226, 156, 210 }, { 235, 176, 215 },
					{ 200, 80, 155 }, { 187, 51, 133 }, { 251, 174, 210 }, { 255, 166, 201 }, { 247, 70, 138 },
					{ 255, 145, 164 }, { 202, 52, 53 }, { 254, 186, 173 }, { 233, 116, 81 }, { 175, 89, 62 },
					{ 158, 91, 64 }, { 210, 125, 70 }, { 217, 154, 108 }, { 255, 203, 164 }, { 253, 213, 177 },
					{ 102, 82, 51 }, { 230, 190, 138 }, { 201, 192, 187 }, { 218, 138, 103 }, { 0, 0, 0 },
					{ 139, 134, 128 }, { 200, 200, 205 }, { 255, 255, 255 } },
			{ { 253, 14, 53 }, { 254, 76, 64 }, { 255, 153, 128 }, { 255, 185, 123 }, { 231, 114, 0 },
					{ 251, 231, 178 }, { 254, 216, 93 }, { 255, 255, 153 }, { 175, 227, 19 }, { 123, 160, 91 },
					{ 157, 224, 147 }, { 99, 183, 108 }, { 51, 204, 153 }, { 26, 179, 133 }, { 41, 171, 135 },
					{ 0, 204, 153 }, { 0, 117, 94 }, { 0, 204, 204 }, { 0, 128, 128 }, { 45, 56, 58 }, { 0, 157, 196 },
					{ 2, 164, 211 }, { 21, 96, 189 }, { 122, 137, 184 }, { 79, 105, 198 }, { 141, 144, 161 },
					{ 153, 153, 204 }, { 101, 45, 193 }, { 107, 63, 160 }, { 201, 160, 220 }, { 128, 55, 144 },
					{ 214, 174, 221 }, { 193, 84, 193 }, { 252, 116, 253 }, { 165, 11, 94 }, { 97, 64, 81 },
					{ 218, 50, 135 }, { 255, 51, 153 }, { 255, 183, 213 }, { 227, 11, 92 }, { 253, 215, 228 },
					{ 219, 80, 121 }, { 252, 128, 165 }, { 240, 145, 169 }, { 247, 163, 142 }, { 135, 66, 31 },
					{ 146, 111, 91 }, { 222, 166, 129 }, { 237, 201, 175 }, { 238, 217, 196 }, { 131, 112, 80 },
					{ 217, 214, 207 }, { 200, 138, 101 } }

	};

	public void makePattern() {
		String line = null;

		// final long startTime = System.currentTimeMillis();
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("colors.csv");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				colorList.add(new colors((line.split(","))));

				// names[i] = new pokenames(line);
				// i++;
				// System.out.println(line);

			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		for (int h = 0; h < height; h++)
			for (int w = 0; w < width; w++) {
				// image.setRGB(w, h, asdf.getRGB());

				raster.setPixel(w, h,
						colorLineOld[w / (width / colorLineOld.length)][clamp(0,
								colorLineOld[w / (width / colorLineOld.length)].length - 1,
								h / (height / colorLineOld[w / (width / colorLineOld.length)].length))]);

			}
	}

	/*
	 * String hexColor = String.format("#%06X", (0xFFFFFF & intColor)); where
	 * intcolor is color we want
	 * 
	 * OR
	 * 
	 * int red = Color.red(intColor); int green = Color.green(intColor); int
	 * blue = Color.blue(intColor);
	 */

	public void makeNewPattern() {
		/*
		 * List<Integer> firstColor = new ArrayList<Integer>();
		 * 
		 * for(int i=0;i<colorList.size();i++){ if(colorList.get(i).start==1903)
		 * firstColor.add(i);
		 * 
		 * }
		 */
		drawRectangle(300, 400, -100, -200, 0xffabacad);
		drawRectangle(600, 700, 200, 150, color(240, 167, 98));
		drawLine(24, 25, 100, 300, 0xff12ab54);
		// drawWideLine(2,400,400,600,400,0xff45a4b9);
		drawCircle(200, 500, 800, 0xffabacad);
		drawEllipse(700,300,100,300,0x45abf4);
		drawCircle(50,700,300,0xff00F000);
		/*
		 * for (int h = 0; h < height; h++) //
		 * System.out.println(String.format("%06X", (0xFFFFFF & image.getRGB(0,
		 * h)))); for (int w = 0; w < width; w++) { image.setRGB(w, h,
		 * colorList.get(firstColor.get(w%firstColor.size())).getsRGB().getRGB()
		 * ); }
		 */
	}

	public int color(int r, int g, int b) {

		return (0xFF0000 | clamp(0, 255, r) | clamp(0, 255, g) | clamp(0, 255, b));
	}

	public void drawCircle(int radius, int x0, int y0, int colorHex) {
///////uses bresenham midpoint circle algorithm
		int x = radius;
		  int y = 0;
		  int xMovement = 1 - x;   // Decision criterion divided by 2 evaluated at x=r, y=0

		  while( y <= x )
		  {
		    image.setRGB( x + x0,  y + y0,colorHex); // Octant 1
		    image.setRGB( y + x0,  x + y0,colorHex); // Octant 2
		    image.setRGB(-x + x0,  y + y0,colorHex); // Octant 4
		    image.setRGB(-y + x0,  x + y0,colorHex); // Octant 3
		    image.setRGB(-x + x0, -y + y0,colorHex); // Octant 5
		    image.setRGB(-y + x0, -x + y0,colorHex); // Octant 6
		    image.setRGB( x + x0, -y + y0,colorHex); // Octant 7
		    image.setRGB( y + x0, -x + y0,colorHex); // Octant 8
		    y++;
		    if (xMovement<=0)
		    {
		      xMovement += 2 * y + 1;   // Change in decision criterion for y -> y+1
		    }
		    else
		    {
		      x--;
		      xMovement += 2 * (y - x) + 1;   // Change for y -> y+1, x -> x-1
		    }
		  }
	}
	
	public void drawEllipse(int xc, int yc, int width, int height,int colorHex){
		
		    int a2 = width * width;
		    int b2 = height * height;
		    int fa2 = 4 * a2, fb2 = 4 * b2;
		    int x, y, sigma;

		    /* first half */
		    for (x = 0, y = height, sigma = 2*b2+a2*(1-2*height); b2*x <= a2*y; x++)
		    {
		        image.setRGB (xc + x, yc + y,colorHex);
		        image.setRGB (xc - x, yc + y,colorHex);
		        image.setRGB (xc + x, yc - y,colorHex);
		        image.setRGB (xc - x, yc - y,colorHex);
		        if (sigma >= 0)
		        {
		            sigma += fa2 * (1 - y);
		            y--;
		        }
		        sigma += b2 * ((4 * x) + 6);
		    }

		    /* second half */
		    for (x = width, y = 0, sigma = 2*a2+b2*(1-2*width); a2*y <= b2*x; y++)
		    {
		        image.setRGB (xc + x, yc + y,colorHex);
		        image.setRGB (xc - x, yc + y,colorHex);
		        image.setRGB (xc + x, yc - y,colorHex);
		        image.setRGB (xc - x, yc - y,colorHex);
		        if (sigma >= 0)
		        {
		            sigma += fb2 * (1 - x);
		            x--;
		        }
		        sigma += a2 * ((4 * y) + 6);
		    }
		
	}

	public void drawRectangle(int x, int y, int w, int h, int colorHex) {
		if (w < 0) {
			x += w;
			w = -w;
		}
		if (h < 0) {
			y += h;
			h = -h;
		}
		for (int wid = x; wid < x + w; wid++)
			for (int hei = y; hei < y + h; hei++)
				image.setRGB(wid, hei, colorHex);

	}

	public void drawWideLine(int width, int x0, int y0, int x1, int y1, int colorHex) {

	}

	public void drawLine(int x0, int y0, int x1, int y1, int colorHex) {
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);

		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;

		int err = dx - dy;
		int e2;
		while (true) {
			image.setRGB(x0, y0, colorHex);

			if (x0 == x1 && y0 == y1) {
				break;
			}

			e2 = 2 * err;
			if (e2 > -1 * dy) {
				err = err - dy;
				x0 = x0 + sx;
			}

			if (e2 < dx) {
				err = err + dx;
				y0 = y0 + sy;
			}
		}
	}

	public void savePattern() throws IOException {
		ImageIO.write(image, "PNG", new File("newPattern.png"));
		Desktop.getDesktop().open(new File("newPattern.png"));
	}
	public void countColors(){
		int[] red=new int [256];
		int[] blue=new int[256];
		int[] green=new int[256];
		int temp;
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				temp=image.getRGB(x,y);
				red[temp&0xFF]++;
				green[temp>>8&0xFF]++;
				blue[temp>>16&0xFF]++;
			}
	}
		
		System.out.println(Arrays.toString(red));
		System.out.println(Arrays.toString(blue));
		System.out.println(Arrays.toString(green));
	}

	/**
	 * 17 * This application creates a color pattern image without using JAI
	 * methods or operators. 18 * The pixels in the image will be set
	 * individually by calling the Raster.setPixel method. 19
	 */
	public class colors {
		public String name;
		public Color color;
		public int start;
		public int end;
		public int span;

		colors(String[] x) {
			name = x[0];
			color = new Color(Integer.parseInt(x[1]), Integer.parseInt(x[2]), Integer.parseInt(x[3]));
			start = Integer.parseInt(x[5]);
			end = Integer.parseInt(x[6]);
			span = end - start;
		}

		public int getRGB() {
			return color.getRGB();
		}

		public Color getsRGB() {
			return color;
		}
	}

	int width = 1920; // Dimensions of the image
	int height = 1080;
	// Create a Buffered Image.
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	// We need its raster to set the pixels' values.
	WritableRaster raster = image.getRaster();

	// Create the colors.
	public int clamp(int min, int max, int x) {
		if (x < min)
			return min;
		if (x > max)
			return max;
		return x;
	}

	List<colors> colorList = new ArrayList<>();

	/**
	 * The application starting point.
	 */
	ColorTrial() {
		// URL url = getClass().getResource("colors.csv");
		// File file = new File(url.getPath());
		// System.out.println(file);

	}

	public static void main(String[] args) throws IOException {

		ColorTrial trial = new ColorTrial();
		trial.makeNewPattern();
		trial.savePattern();
	//	trial.countColors();
		// Put the pixels on the raster, choosing a color depending on its
		// coordinates.
		// System.out.println(colors.length);

		// Store the image using the PNG format.

	}

}