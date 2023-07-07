package com.mohak.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mohak.gaming.utils.GameConstants;

public abstract class Player implements GameConstants {
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected int speed;
	protected BufferedImage image;
	public abstract BufferedImage defaultImage();
	protected int imageIndex;
	protected int currentMove;
	protected int force;
	protected boolean isJump ;//used to check weather the jump is running or not
	
	
	public int getCurrentMove() {
		return currentMove;
	}

	public void setCurrentMove(int currentMove) {
		this.currentMove = currentMove;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void move() {
		x = x + speed;
	}

	public void printPlayer(Graphics pen) {
		pen.drawImage(defaultImage(),x,y,w,h, null);
	}
}
