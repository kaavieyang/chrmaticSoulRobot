package com.kaavie.chrmaticSoulRobot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

public class GenerateImg {

	private static String imgPath="./src/main/resources/img/";
	
	public static void main(String[] args) throws AWTException, IOException {
		final Robot rb = new Robot();
		Click click = new Click();
		//363 180 61 61
		//178 470  157 44
		//151 358  119 32
		//3-1-2   117 335   173 349
		//3-1-3  108 336  164 348 
		//反选角色  176 185   20 20 
		BufferedImage	fuben313Image=Click.capturePartScreen(rb,108,336,56,12);
		File fuben313file =new File(imgPath+"logo/fuben313.png");
		if(!fuben313file.exists()){
			fuben313file.getParentFile().mkdir();
		}
		ImageIO.write(fuben313Image, "png", fuben313file);
//		BufferedImage	roleImage=Click.capturePartScreen(rb,176,185,20,20);
//		File file =new File(imgPath+"logo/roleUnchoose.png");
//		if(!file.exists()){
//			file.getParentFile().mkdir();
//		}
//		ImageIO.write(roleImage, "png", file);
//		BufferedImage	figthEndImage=Click.capturePartScreen(rb, 221, 553, 116, 27);
//		File figthEndfile =new File(imgPath+"logo/figthEnd.png");
//		if(!figthEndfile.exists()){
//			figthEndfile.getParentFile().mkdir();
//		}
//		ImageIO.write(figthEndImage, "png", figthEndfile);
//		BufferedImage	androidImage=Click.capturePartScreen(rb,220,366,40,40);
//		File androidfile =new File(imgPath+"logo/android.png");
//		if(!androidfile.exists()){
//			androidfile.getParentFile().mkdir();
//		}
//		ImageIO.write(androidImage, "png", androidfile);
	}
}
