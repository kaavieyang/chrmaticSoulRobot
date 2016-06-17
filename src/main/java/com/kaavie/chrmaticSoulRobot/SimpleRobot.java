package com.kaavie.chrmaticSoulRobot;

import java.awt.AWTException;
import java.awt.Robot;

import org.apache.log4j.Logger;

public class SimpleRobot implements Runnable {
	public static Logger logger=Logger.getLogger(SimpleRobot.class);
	public static Integer startXIndex=0;
	public static Integer endXIndex=0;
	public static Integer screenWidth=500;
	public static Integer screenHeight=726;
	public static Integer screenTitleHeigh=5;
	public static Integer osxHeight=22;
	Robot rb =null;
	public SimpleRobot() throws AWTException {
		this.rb=new Robot();
	}
	//1-2-3 title 205 292 
	//到第一个角色 325 252
	//好友选项卡 218 209
	//开始战斗  155 542 
	//直接入场  87 368 
	//普通攻击  87  450 
    //技能框 55 55
	//技能框之间的间隙  10 
	// 怪物位置 84 262 
	// 宽度 141-84 
	//第二个怪物 237  262   这个在球的上边
 	
	//第三个怪物 336 273 
	//点击球的大小  60 ＊ 60 
	
	
	//boss 1 ：100 274 
	//boss 2:277 292 
	//100 160 280 340 
	//确定按钮  154 496     大小   119 30 
	
	public static void main(String[] args){
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
	public void useSkill(){
			
		
	}
	/**
	 * 选取角色
	 */
	public void chooseRole(){
		
	}
	/**
	 * 开始战斗
	 */
	public void startFight(){
		
	}
	/**
	 * 结束战斗
	 */
	public void endFight(){
		
	}

public void autoStart() throws InterruptedException{
	int i=10;
	while(i!=0){
		Long startTime =System.currentTimeMillis();
		logger.debug("start game 1");
	Click.clickLMouse(rb, 362, 368, 100);
	Click.clickLMouse(rb, 362, 368, 100);
	Thread.sleep(15000);
	//反选
	Click.clickLMouse(rb, 184, 195, 100);
	Click.clickLMouse(rb, 257, 195, 100);
	Click.clickLMouse(rb, 331, 195, 100);
	
	Click.clickLMouse(rb, 408, 333, 100);
	Click.clickLMouse(rb, 361, 285, 100);
	Thread.sleep(1000);
	//职业选择排序
	Click.clickLMouse(rb, 367, 572, 100);
	Click.clickLMouse(rb, 367, 572, 100);
	Thread.sleep(1000);
	//选择第三个人
	Click.clickLMouse(rb, 408, 496, 100);
	Thread.sleep(1000);
	//开始战斗
	Click.clickLMouse(rb, 284, 612, 100);
	Thread.sleep(1000);
	//尚未组队，直接入场
	Click.clickLMouse(rb, 216,436, 100);
	Thread.sleep(10000);
	autoFight();
	 //确定
	Click.clickLMouse(rb, 276,566, 100);
	Thread.sleep(15000);
	Long endTime =System.currentTimeMillis();
	logger.debug("this play waste :"+(endTime-startTime)/1000+"秒");
	}
}

public void autoFight() throws InterruptedException{
	logger.debug("start fight");
	//开始战斗
	//三个怪物的时候执行这个
	threeFigth();
	twoFight();
	Thread.sleep(2000);
	threeFigth();
	twoFight();
	Thread.sleep(2000);
	threeFigth();
	twoFight();
	Thread.sleep(2000);
	threeFigth();
	twoFight();
	Thread.sleep(2000);
	twoFight();
	twoFight();
	Thread.sleep(2000);
	twoFight();
	Thread.sleep(2000);
	logger.debug("end fight");
}

public void threeFigth() throws InterruptedException{
	Click.clickLMouse(rb, 158,325, 100);
	Thread.sleep(1000);
	Click.clickLMouse(rb, 291,339, 100);
	Thread.sleep(1000);
	Click.clickLMouse(rb, 416,334, 100);
	Thread.sleep(1000);
}
public void twoFight() throws InterruptedException{
	//两个怪物的时候执行这个
	Click.clickLMouse(rb, 196,320, 100);
	Thread.sleep(1000);
	Click.clickLMouse(rb, 367,337, 100);
	Thread.sleep(1000);
}

	@Override
	public void run() {
		System.out.println("sadfsf");
		try {
			this.autoStart();
		} catch (InterruptedException e) {
			logger.debug("SimpleRobot throw exception ,the Exception is "+e.getMessage());
			
		}
		
		
	}
	
	
	
	
}
