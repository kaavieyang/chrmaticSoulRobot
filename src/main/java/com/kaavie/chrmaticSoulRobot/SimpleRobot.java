package com.kaavie.chrmaticSoulRobot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class SimpleRobot implements Runnable {
	private static String imgPath = "./src/main/resources/img/";

	public static Logger logger = Logger.getLogger(SimpleRobot.class);
	public static Integer startXIndex = 0;
	public static Integer endXIndex = 0;
	public static Integer screenWidth = 500;
	public static Integer screenHeight = 726;
	public static Integer screenTitleHeigh = 5;
	public static Integer osxHeight = 22;
	public BufferedImage	fubenImage;
	public BufferedImage	roleImage;
	public BufferedImage	endImage;
	public BufferedImage androidImage;
	Robot rb = null;

	public SimpleRobot() throws AWTException, FileNotFoundException, IOException {
		this.rb = new Robot();
		this.fubenImage= ImageIO.read(new FileInputStream(imgPath + "logo/fuben313.png"));
		this.roleImage= ImageIO.read(new FileInputStream(imgPath + "logo/roleUnchoose.png"));
		this.endImage= ImageIO.read(new FileInputStream(imgPath + "logo/figthEnd.png"));
		this.androidImage= ImageIO.read(new FileInputStream(imgPath + "logo/android.png"));
		
	}
	// 1-2-3 title 205 292
	// 到第一个角色 325 252
	// 好友选项卡 218 209
	// 开始战斗 155 542
	// 直接入场 87 368
	// 普通攻击 87 450
	// 技能框 55 55
	// 技能框之间的间隙 10
	// 怪物位置 84 262
	// 宽度 141-84
	// 第二个怪物 237 262 这个在球的上边

	// 第三个怪物 336 273
	// 点击球的大小 60 ＊ 60

	// boss 1 ：100 274
	// boss 2:277 292
	// 100 160 280 340
	// 确定按钮 154 496 大小 119 30

	public static void main(String[] args) throws AWTException, IOException {
		SimpleRobot simpleRobot;
		try {
			simpleRobot = new SimpleRobot();
			simpleRobot.run();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 使用技能
	 */
	public void useSkill() {

	}

	/**
	 * 选取角色
	 */
	public void chooseRole() {

	}

	/**
	 * 开始战斗
	 */
	public void startFight() {

	}

	/**
	 * 结束战斗
	 */
	public void endFight() {

	}

	/**
	 * 获取当前界面
	 * 如果返回的是0的话，表示程序已经发生错误，返回到了android界面
	 * 如果返回的是－1的话，表示还在加载中，需要等待
	 * 如果返回的是对应数字的话，表示正常，可以下一步操作
	 * 
	 * 
	 * 
	 * 1判断是否是副本选择界面
	 * 2判断是否是任务反选界面
	 * 3判断是否是结束界面
	 */
	public Integer nowWindows(int beforeWindows){
		//首先判断是否已经到android界面了
		BufferedImage	androidImage=Click.capturePartScreen(rb,220,366,40,40);
		Double androidCompareNum = ImageEqules.getSimilarityMethodMutiColor(androidImage, this.androidImage);
		logger.debug("the android compare num is :" + androidCompareNum);
		if (androidCompareNum > 0.9d) {
			return 0;
		}
		
		switch(beforeWindows){
		case 1://判断是否在副本选择
			BufferedImage	fubenImage=Click.capturePartScreen(rb,108,336,56,12);
			Double fubenCompareNum = ImageEqules.getSimilarityMethodMutiColor(fubenImage, this.fubenImage);

			logger.debug("the fuben compare num is :" + fubenCompareNum);
			if (fubenCompareNum > 0.9d) {
				return 1;
			}
			break;
		case 2://判断是否在反选角色
			BufferedImage	roleImage=Click.capturePartScreen(rb,176,185,20,20);
			Double roleCompareNum = ImageEqules.getSimilarityMethodMutiColor(roleImage, this.roleImage);

			logger.debug("the role compare num is :" + roleCompareNum);
			if (roleCompareNum > 0.9d) {
				return 2;
			}
			break;
		case 3://判断战斗是否结束
			BufferedImage endImage = Click.capturePartScreen(rb, 221, 553, 116, 27);
			Double endCompareNum = ImageEqules.getSimilarityMethodMutiColor(endImage, this.endImage);

			logger.debug("the end fight compare num is :" + endCompareNum);
			if (endCompareNum > 0.9d) {
				return 3;
			}
			break;
		
			
	
		}
		return -1;
	}
	
	public void autoStart() throws InterruptedException, IOException {
		int i = 0;
		while(true){
		do{
			Thread.sleep(1000);
		}while(this.nowWindows(1)==-1);
		
		if(this.nowWindows(1)==0){
			//已经返回到android界面了
			logger.debug("android发生错误，已经返回到界面");
			break;
		}
			
			Long startTime = System.currentTimeMillis();
			logger.debug("start game " + i);
			//推荐好友  416 288 434 306
			
			
			// 1-2-3
			// Click.clickLMouse(rb, 362, 368, 100);
			// Click.clickLMouse(rb, 362, 368, 100);
			// 3-1-1
			// Click.clickLMouse(rb, 189, 230, 100);
			// Click.clickLMouse(rb, 189, 230, 100);
			// 3-1-2
			//Click.clickLMouse(rb, 148, 275, 100);
			//Click.clickLMouse(rb, 148, 275, 100);
			//3-1-3
			Click.clickLMouse(rb, 149, 333, 100);
			Click.clickLMouse(rb, 149, 333, 100);
			
			do{
				Thread.sleep(1000);
			}while(this.nowWindows(2)==-1);
			
			if(this.nowWindows(2)==0){
				//已经返回到android界面了
				logger.debug("android发生错误，已经返回到界面");
				break;
			}

			// 反选
			Click.clickLMouse(rb, 184, 195, 100);
			Click.clickLMouse(rb, 257, 195, 100);
			Click.clickLMouse(rb, 331, 195, 100);

			Click.clickLMouse(rb, 408, 333, 100);
			Click.clickLMouse(rb, 361, 285, 100);
			Thread.sleep(1000);
			// 职业选择排序
			Click.clickLMouse(rb, 367, 572, 100);
			Click.clickLMouse(rb, 367, 572, 100);
			Thread.sleep(1000);
			// 选择第三个人
			Click.clickLMouse(rb, 408, 496, 100);
			// 技能1 220 507 227 507
//			BufferedImage skill1 = Click.capturePartScreen(rb, 207, 465, 51, 51);
//			File skill1file = new File(imgPath + "logo/" + UUID.randomUUID().toString() + ".png");
//			if (!skill1file.exists()) {
//				skill1file.getParentFile().mkdir();
//			}
//			ImageIO.write(skill1, "png", skill1file);
//			BufferedImage skill2 = Click.capturePartScreen(rb, 267, 465, 51, 51);
//			File skill2file = new File(imgPath + "logo/" + UUID.randomUUID().toString() + ".png");
//			if (!skill2file.exists()) {
//				skill2file.getParentFile().mkdir();
//			}
//			ImageIO.write(skill2, "png", skill2file);
//			BufferedImage skill3 = Click.capturePartScreen(rb, 326, 466, 51, 51);
//			File skill3file = new File(imgPath + "logo/" + UUID.randomUUID().toString() + ".png");
//			if (!skill3file.exists()) {
//				skill3file.getParentFile().mkdir();
//			}
//			ImageIO.write(skill3, "png", skill3file);
			Thread.sleep(1000);
			// 开始战斗
			Click.clickLMouse(rb, 284, 612, 100);
			Thread.sleep(1000);
			// 尚未组队，直接入场
			Click.clickLMouse(rb, 216, 436, 100);
			Thread.sleep(10000);

			// 三个怪物的时候执行这个
			do {

				Thread.sleep(3000);
				threeFigth();
				twoFight();
			}while( this.nowWindows(3)==-1);
			
			if(this.nowWindows(3)==0){
				//已经返回到android界面了
				logger.debug("android发生错误，已经返回到界面");
			}
			// 确定
			Click.clickLMouse(rb, 276, 566, 100);
			Long endTime = System.currentTimeMillis();
			logger.debug("this play waste :" + (endTime - startTime) / 1000 + "秒");
			i++;
		}
		logger.debug("this play waste :" + new Date() + "秒");
		// Click.clickLMouse(rb, 475,41, 100);
		// Thread.sleep(3000);
		// Click.clickLMouse(rb, 197,235, 100);
}

	/**
	 * 判断战斗是否结束
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean isEnd() throws IOException {

		// 221 553
		// 337 580

		BufferedImage fullScreenImage = Click.capturePartScreen(rb, 221, 553, 116, 27);
		BufferedImage localImagebuffer = ImageIO.read(new FileInputStream(imgPath + "logo/figthEnd.png"));
		Double compareNum = ImageEqules.getSimilarityMethodMutiColor(localImagebuffer, fullScreenImage);
		if (compareNum > 0.9d) {
			return false;
		}
		return true;
	}

	public void autoFight() throws InterruptedException, IOException {
		

	}

	public void threeFigth() throws InterruptedException {
		Click.clickLMouse(rb, 158, 325, 100);
		Click.clickLMouse(rb, 291, 339, 100);
		Click.clickLMouse(rb, 416, 334, 100);
	}

	public void twoFight() throws InterruptedException {
		// 两个怪物的时候执行这个
		Click.clickLMouse(rb, 196, 320, 100);
		Click.clickLMouse(rb, 367, 337, 100);
	}

	@Override
	public void run() {
		try {
			this.autoStart();
		} catch (InterruptedException | IOException e) {
			logger.debug("SimpleRobot throw exception ,the Exception is " + e.getMessage());

		}

	}

}
