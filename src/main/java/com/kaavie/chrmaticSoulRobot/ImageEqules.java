package com.kaavie.chrmaticSoulRobot;

import java.awt.image.BufferedImage;

/**
 * @author 作者 :kaavie
 * @version 创建时间：2016年6月12日 下午3:54:31 类说明
 */
public class ImageEqules {
	private static int[] getRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
		int t = image.getType();
		if (t == BufferedImage.TYPE_INT_ARGB || t == BufferedImage.TYPE_INT_RGB)
			return (int[]) image.getRaster().getDataElements(x, y, width, height, pixels);
		return image.getRGB(x, y, width, height, pixels, 0, width);
	}

	public static float getRateCount(int max, int equals, int value) {
		float idx = ((float) value) * ((float) equals) / ((float) max);
		if (idx >= equals)
			idx = equals - 1;
		return idx;
	}

	/**
	 * single color Histogram filter, get the Eigenvalues of the specified image
	 * .
	 * 
	 * @param image
	 * @param req
	 * @param geq
	 * @param beq
	 * @return float[]
	 */
	private static float[] HistogramFilter(BufferedImage image, int req, int geq, int beq) {
		int width = image.getWidth();
		int height = image.getHeight();

		int[] pixels = new int[width * height];
		float[] histogram = new float[req * geq * beq];
		for (int i = 0; i < histogram.length; i++)
			histogram[i] = 0;

		getRGB(image, 0, 0, width, height, pixels);

		int r, g, b;
		int rp, gp, bp;
		int idx = 0, sidx = 0;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				idx = row * width + col;
				r = (pixels[idx] >> 16) & 0xFF;
				g = (pixels[idx] >> 8) & 0xFF;
				b = (pixels[idx] >> 0) & 0xFF;

				// count the rgb part
				rp = (int) getRateCount(255, req, r);
				gp = (int) getRateCount(255, geq, g);
				bp = (int) getRateCount(255, beq, b);
				sidx = rp + gp * req + bp * req * geq;
				histogram[sidx] += 1;
			}
		}

		float total = width * height;
		for (int i = 0; i < histogram.length; i++)
			histogram[i] = histogram[i] / total;

		return histogram;
	}

	/**
	 * get the similarity of the specified two image . 彩色直方图计算:
	 * 
	 * @param srcImage
	 * @param dstImage
	 */
	public static double getSimilarityMethodMutiColor(BufferedImage srcImage, BufferedImage dstImage) {
		double similarity = 0.0D;
		float[] histogram1 = HistogramFilter(srcImage, 256, 256, 256);
		float[] histogram2 = HistogramFilter(dstImage, 256, 256, 256);

		for (int i = 0; i < histogram1.length; i++) {
			similarity += Math.sqrt(histogram1[i] * histogram2[i]);
		}
		return similarity;
	}

	public static double getSimilarityMethodGray(BufferedImage srcImage, BufferedImage dstImage) {
		double similarity = 0.0D;
		float total1 = srcImage.getWidth() * srcImage.getHeight();
		int[] histogram1 = getHistogram(srcImage);

		float total2 = dstImage.getWidth() * dstImage.getHeight();
		int[] histogram2 = getHistogram(dstImage);

		for (int i = 0; i < histogram1.length; i++) {
			similarity += Math.sqrt(((double) histogram1[i]) / total1 * ((double) histogram2[i]) / total2);
		}
		return similarity;
	}

	private static int[] getHistogram(BufferedImage srcImage) {
		int[] srcPixels = new int[srcImage.getWidth() * srcImage.getHeight()];
		int[] itensity = new int[256];
		// initialzie the itensity
		for (int i = 0; i < itensity.length; i++)
			itensity[i] = 0;
		// fill the srcPixels
		getRGB(srcImage, 0, 0, srcImage.getWidth(), srcImage.getHeight(), srcPixels);

		int index = 0;
		int r, g, b;
		int gray;
		for (int row = 0; row < srcImage.getHeight(); row++) {
			for (int col = 0; col < srcImage.getWidth(); col++) {
				index = row * srcImage.getWidth() + col;
				// a = (srcPixels[index] >> 24) & 0xFF;
				r = (srcPixels[index] >> 16) & 0xFF;
				g = (srcPixels[index] >> 8) & 0xFF;
				b = (srcPixels[index] >> 0) & 0xFF;
				gray = (int) (0.299 * (double) r) + (int) (0.587 * (double) g) + (int) (0.114 * (double) b);
				itensity[gray]++;
			}
		}
		return itensity;
	}

}
