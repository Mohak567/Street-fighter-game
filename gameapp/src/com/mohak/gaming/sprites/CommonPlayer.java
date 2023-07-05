package com.mohak.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class CommonPlayer {
	protected int x;
	protected int y;
	protected int w;//width
	protected int h;//height
	protected BufferedImage image;
	protected int speed;
	public abstract BufferedImage walk();
	
//	private  BufferedImage walk() {
//	return image.getSubimage(64, 237, 72, 95);
//}
	
	public void drawPlayer(Graphics pen) {
		pen.drawImage(walk(), x, y, w, h, null);//null is for resizeable
	}
	public void move() {
		x = x + speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
