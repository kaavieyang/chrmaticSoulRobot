package com.kaavie.chrmaticSoulRobot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

/**
 * @author 作者 :kaavie
 * @version 创建时间：2016年6月12日 下午3:45:26 类说明
 */
public class Click {
	private static Logger logger = Logger.getLogger(Click.class);  


	private static String imgPath="./src/main/resources/img/";
	
	/**
	 * 鼠标单击（左击）,要双击就连续调用
	 * 
	 * @param r
	 * @param x
	 *            x坐标位置
	 * @param y
	 *            y坐标位置
	 * @param delay
	 *            该操作后的延迟时间
	 */
	public static void clickLMouse(Robot r, int x, int y, int delay) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_MASK);
		//r.delay(3000);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		r.delay(delay);
	}

	/**
	 * 捕捉全屏慕
	 * 
	 * @param r
	 * @return
	 */
	public Icon captureFullScreen(Robot r) {
		BufferedImage fullScreenImage = r
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIcon icon = new ImageIcon(fullScreenImage);
		return icon;
	}

	/**
	 * 捕捉屏幕的一个矫形区域
	 * 
	 * @param r
	 * @param x
	 *            x坐标位置
	 * @param y
	 *            y坐标位置
	 * @param width
	 *            矩形的宽
	 * @param height
	 *            矩形的高
	 * @return
	 */
	public static BufferedImage capturePartScreen(Robot r, int x, int y, int width, int height) {
		//r.mouseMove(x, y);
		BufferedImage fullScreenImage = r.createScreenCapture(new Rectangle(x,y,width, height));
		//ImageIcon icon = new ImageIcon(fullScreenImage);
		return fullScreenImage;
	}

	public static void main(String[] args) throws AWTException, FileNotFoundException, IOException {
//		try {
//			final Robot rb = new Robot();
//			Click click = new Click();
//			BufferedImage	fullScreenImage=click.capturePartScreen(rb,0,0,69,69);
//			//BufferedImage imageBuffered=ImageIO.read(new FileInputStream("C://Users//Administrator//Desktop//QQ图片20160612161810.png"));
//			//BufferedImage imagebuffer=ImageIO.read(new FileInputStream("C://Users//Administrator//Desktop//QQ图片20160612161815.png"));
//			BufferedImage imagebuffer=ImageIO.read(new FileInputStream("C://Users//Administrator//Desktop//QQ图片testnocolor.jpg"));
//			BufferedImage imagebufferold=ImageIO.read(new FileInputStream("C://Users//Administrator//Desktop//QQ图片test.jpg"));
//			double  compareNum=	ImageEqules.getSimilarityMethodMutiColor(imagebuffer, imagebufferold);
//			double  grayNum=	ImageEqules.getSimilarityMethodGray(imagebuffer, imagebufferold);
//			System.out.println("multiColorNum is "+compareNum);
//			System.out.println("grayNum is "+grayNum);
//			File file =new File("C://Users//Administrator//Desktop//QQ图片test.png");
//			BufferedImage saveImagebuffer=ImageIO.read(new FileInputStream("C://Users//Administrator//Desktop//QQ图片20160612162158.png"));
//			ImageIO.write(saveImagebuffer, "png", file);
//			System.out.println("end");
//			
//			
//		} catch (AWTException | IOException e) {
//
//			e.printStackTrace();
//		}
		
		final Robot rb = new Robot();
		Click click = new Click();
		//363 180 61 61
		//178 470  157 44
		//151 358  119 32
		
		int xPosition=178;
		int yPosition=470;
		int logox=157;
		int logoy =44;
		
		int logoSize=61;
		int halfLogo=logoSize/2;
		BufferedImage	fullScreenImage=click.capturePartScreen(rb,xPosition,yPosition,logox,logoy);
	
		
		
		//clickLMouse(rb, xPosition+halfLogo, yPosition+halfLogo, 1000);
		//when no focus run twice 
		//178 470  157 44
		//clickLMouse(rb, xPosition+halfLogo, yPosition+halfLogo, 1000);
		File file =new File(imgPath+"logo/reconnection.png");
		if(!file.exists()){
			file.getParentFile().mkdir();
			
		}
		ImageIO.write(fullScreenImage, "png", file);
		File imgfile =new File(imgPath);
		System.out.println(file.getAbsolutePath());
		logger.debug("now the file is :"+file);
		String[] childFile=imgfile.list();
		for(String s:childFile){
			logger.debug("now the childfile is :"+s);
			
		}

	}
	
	
	public void targetZoomOut(String sourcePath){         //将目标图片缩小成256*256并保存
        File file1=new File(sourcePath);             //用file1取得图片名字
        String name=file1.getName();

        try{
        BufferedImage input = ImageIO.read(file1);
        Image big = input.getScaledInstance(256, 256,Image.SCALE_DEFAULT);
        BufferedImage inputbig = new BufferedImage(256, 256,BufferedImage.TYPE_INT_BGR);
        inputbig.getGraphics().drawImage(input, 0, 0, 256, 256, null); //画图


            File file2 =new File("C:/imageSort/targetPIC");            //此目录保存缩小后的关键图
            if(file2.exists()){
                 System.out.println("多级目录已经存在不需要创建！！");
            }else{
              //如果要创建的多级目录不存在才需要创建。
               file2.mkdirs();
             }
            ImageIO.write(inputbig, "jpg", new File("C:/imageSort/targetPIC/"+name));   //将其保存在C:/imageSort/targetPIC/下
        } catch (Exception ex) {ex.printStackTrace();}
    }
}
