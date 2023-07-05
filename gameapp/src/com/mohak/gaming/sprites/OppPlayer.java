package com.mohak.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mohak.gaming.utils.GameConstant;

public class OppPlayer extends CommonPlayer implements GameConstant{
	public OppPlayer() throws IOException {
		x = GWIDTH - 300;
		h = w = 200;
		y = FLOOR - h;
		speed = -(SPEED);//moving player right to left
		image = ImageIO.read(OppPlayer.class.getResource(OPP_PLAYER_IMG));
	}

	@Override
	public BufferedImage walk() {
		// TODO Auto-generated method stub
		return image.getSubimage(43, 45, 59, 81);
	}

}
