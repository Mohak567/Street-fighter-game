package com.mohak.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.mohak.gaming.utils.GameConstant;

public class Player extends CommonPlayer implements GameConstant{
	
	public Player() throws Exception {
		x = 100;
		h = w = 200;
		y = FLOOR - h;
		speed = SPEED;//moving player left to right
		image = ImageIO.read(Player.class.getResource(PLAYER_IMG));
	}
	@Override
	public BufferedImage walk() {
		return image.getSubimage(64, 237, 72, 95);
	}
	
	
}
