package com.mohak.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KenPlayer extends Player {
	
	public KenPlayer() throws IOException {
		x = GWIDTH - 400;
		h = 200;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		image = ImageIO.read(RyuPlayer.class.getResource(KEN_IMAGE));
	}
	
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage subImage = image.getSubimage(1756,685,62,94);
		return subImage;
	}
	
}
