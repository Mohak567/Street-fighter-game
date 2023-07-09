package com.mohak.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KenPlayer extends Sprite {
	private BufferedImage walkImages [] = new BufferedImage[6];
	private BufferedImage kickImages [] = new BufferedImage[8];
	private BufferedImage punchImages [] = new BufferedImage[7];
	BufferedImage damageEffectImages [] = new BufferedImage[5];
	
	public KenPlayer() throws IOException {
		x = GWIDTH - 400;//because it is on right side
		h = 200;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		imageIndex = 0;
		currentMove = WALK;
		image = ImageIO.read(RyuPlayer.class.getResource(KEN_IMAGE));
		loadWalkImages();
		loadKickImages();
		loadPunchImages();
		loadDamageEffect();
	}
	//jump logic
	public void jump() {
		if(!isJump) {//this stops the jump to initiate multiple times
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
	public void loadDamageEffect() {
		damageEffectImages[0]  = image.getSubimage(1362, 3274, 68, 93);
		damageEffectImages[1]  = image.getSubimage(1439, 3273, 82, 94);
		damageEffectImages[2]  = image.getSubimage(1540, 3278, 74, 89);
		damageEffectImages[3]  = image.getSubimage(1629, 3278, 70, 89);
		damageEffectImages[4]  = image.getSubimage(1710, 3277, 63, 89);
	}
	
	private void loadWalkImages() {
		walkImages[0]  = image.getSubimage(1688, 686, 68, 90);
		walkImages[1]  = image.getSubimage(1754, 686, 68, 90);
		walkImages[2]  = image.getSubimage(1824, 686, 68, 90);
		walkImages[3]  = image.getSubimage(1893, 681, 68, 96);
		walkImages[4]  = image.getSubimage(1961, 682, 68, 96);
		walkImages[5]  = image.getSubimage(2033, 682, 64, 96);
	}
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(1041, 2317, 65, 106);
		kickImages[1] = image.getSubimage(1133, 2323, 103, 103);
		kickImages[2] = image.getSubimage(1248, 2319, 121, 103);
		kickImages[3] = image.getSubimage(1387, 2316, 107, 106);
		kickImages[4] = image.getSubimage(1526, 2302, 70, 120);
		kickImages[5] = image.getSubimage(1611, 2302, 70, 120);
		kickImages[6] = image.getSubimage(1695, 2301, 70, 121);
		kickImages[7] = image.getSubimage(1779, 2301, 70, 121);
	}
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(1450, 1147, 62, 96);
		punchImages[1] = image.getSubimage(1519, 1147, 62, 96);
		punchImages[2] = image.getSubimage(1592, 1147, 75, 96);
		punchImages[3] = image.getSubimage(1666, 1147, 110, 96);
		punchImages[4] = image.getSubimage(1865, 1147, 67, 96);
		punchImages[5] = image.getSubimage(1931, 1147, 95, 96);
		punchImages[6] = image.getSubimage(2030, 1147, 67, 96);
	}
	private BufferedImage printWalk() {
		isAttacking = false;//for stopping the damage effect images
		if(imageIndex>5) {
			imageIndex=0;
		}
		BufferedImage img = walkImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	} 
	private BufferedImage printKick() {
		if(imageIndex>7) {
			imageIndex=0;
			currentMove = WALK;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = kickImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printPunch() {
		if(imageIndex>6) {
			imageIndex=0;
			currentMove = WALK;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = punchImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	
	public BufferedImage printDamageImages() {
		if(imageIndex>=4) {
			imageIndex = 0;
			currentMove = WALK;
		}
		BufferedImage img =  damageEffectImages[imageIndex];
		imageIndex++;
		return img;
			}
	
	@Override
	public BufferedImage defaultImage() {
		//BufferedImage subImage = image.getSubimage(1756,685,62,94);//single image
		if(currentMove == DAMAGE) {
			return printDamageImages();//check
		}
		else if(currentMove == KICK) {
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
