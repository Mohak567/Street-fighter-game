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
		walkImages[0]  = image.getSubimage(60, 236,77,98);
		walkImages[1]  = image.getSubimage(142, 235,77,98);
		walkImages[2]  = image.getSubimage(225,236,60,98);
		walkImages[3]  = image.getSubimage(304, 233,58,98);
		walkImages[4]  = image.getSubimage(377, 234,59,99);
		walkImages[5]  = image.getSubimage(453, 239,65,96);
	}
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(38, 1040,73,105);
		kickImages[1] = image.getSubimage(123, 1039,65,106);
		kickImages[2] = image.getSubimage(199, 1037,118,110);
		kickImages[3] = image.getSubimage(327, 1045,71,99);
		kickImages[4] = image.getSubimage(405,1044,70,99);
		kickImages[5] = image.getSubimage(480, 1047,97,103);
	}
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(24,819,70,106);
		punchImages[1] = image.getSubimage(105,816,72,104);
		punchImages[2] = image.getSubimage(187,817,115,103);
		punchImages[3] = image.getSubimage(310,819,79,107);
		punchImages[4] = image.getSubimage(401,817,108,105);
		punchImages[5] = image.getSubimage(518,816,76,105);
	}
	
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
	
	
	@Override
	public BufferedImage defaultImage() {
		 if(currentMove == KICK) {
			return printKick();
		}
		 else if (currentMove == PUNCH) {
			 return printPunch();
		 }
		 else {
			 return printWalk();
		 }
	}
	
	

}
