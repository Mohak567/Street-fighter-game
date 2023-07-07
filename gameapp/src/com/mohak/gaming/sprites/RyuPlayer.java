package com.mohak.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mohak.gaming.utils.GameConstants;

public class RyuPlayer extends Player  {
	private BufferedImage walkImages [] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[6]; 
	private BufferedImage punchImages[] = new BufferedImage[6];
	//private BufferedImage jumpImages[] = new BufferedImage[6];
	public RyuPlayer() throws IOException {
		x = 100;
		h = 200;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		image = ImageIO.read(RyuPlayer.class.getResource(RYU_IMAGE));
		loadWalkImages();
		loadKickImages();
		loadPunchImages();
		//loadJumpImages();
	}
	
	public void jump() {
		if(!isJump) {
		isJump = true;
		force = -20;
		y = y + force;
		}
	}
	
	public void fall() {
		if(y>=(FLOOR-h)) {
			isJump = false;
			return ;
		}
		y = y + force;
		force = force + GRAVITY;
	}
	
	private void loadWalkImages() {
		walkImages[0]  = image.getSubimage(111, 127, 69, 95);
		walkImages[1]  = image.getSubimage(187, 127, 69, 95);
		walkImages[2]  = image.getSubimage(269, 127, 69, 95);
		walkImages[3]  = image.getSubimage(350, 127, 66, 95);
		walkImages[4]  = image.getSubimage(427, 124, 66, 98);
		walkImages[5]  = image.getSubimage(505, 123, 66, 99);
	}
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(40, 1046, 69, 96);
		kickImages[1] = image.getSubimage(120, 1045, 69, 96);
		kickImages[2] = image.getSubimage(199, 1050, 121, 93);
		kickImages[3] = image.getSubimage(327, 1045, 71, 99);
		kickImages[4] = image.getSubimage(405, 1044, 70, 99);
		kickImages[5] = image.getSubimage(480, 1047, 97, 103);
	}
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(24,819,70,106);
		punchImages[1] = image.getSubimage(105,816,72,104);
		punchImages[2] = image.getSubimage(187,817,115,103);
		punchImages[3] = image.getSubimage(310,819,79,107);
		punchImages[4] = image.getSubimage(401,817,108,105);
		punchImages[5] = image.getSubimage(518,816,76,105);
	}
	
	/*private void loadJumpImages() {
		jumpImages[0] = image.getSubimage(114, 459, 65, 111);
		jumpImages[1] = image.getSubimage(193, 459, 65, 92);
		jumpImages[2] = image.getSubimage(273, 460, 60, 75);
		jumpImages[3] = image.getSubimage(346, 467, 64, 69);
		jumpImages[4] = image.getSubimage(426, 464, 64, 86);
		jumpImages[5] = image.getSubimage(505, 452, 64, 118);
		
	}*/
	
	private BufferedImage printWalk() {
		if(imageIndex>5) {
			imageIndex=0;
		}
		BufferedImage img = walkImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printKick() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = WALK;
		}
		BufferedImage img = kickImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printPunch() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = WALK;
		}
		BufferedImage img = punchImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	/*private BufferedImage printJump() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = WALK;
		}
		BufferedImage img = jumpImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}*/
	
	
	@Override
	public BufferedImage defaultImage() {
		 if(currentMove == KICK) {
			return printKick();
		}
		 else if (currentMove == PUNCH) {
			 return printPunch();
		 }
		 /*else if (currentMove == JUMP) {
			 return printJump();
		 }*/
		 else {
			 return printWalk();
		 }
	}
	
	

}
