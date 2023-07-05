package com.mohak.gaming.canvas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.mohak.gaming.sprites.OppPlayer;
import com.mohak.gaming.sprites.Player;
import com.mohak.gaming.utils.GameConstant;

public class Board extends JPanel implements GameConstant{
	BufferedImage imageBg;
	private Player player;
	private OppPlayer oppPlayer;
	
	public Board() throws Exception {
		player = new Player();
		oppPlayer = new OppPlayer();
		loadbackgroundimage();
		setFocusable(true);//this focus on the board
		bindEvents();
	}
	private void bindEvents() {
		KeyListener listener = new KeyListener() {//listener is used to detect the keys during game

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Typed "+e.getKeyCode()+" "+e.getKeyChar());
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// left Player
				if(e.getKeyCode() == KeyEvent.VK_A) {
					player.setSpeed(-SPEED);
					player.move();
					//System.out.println("X left "+player.getX());//for getting new x value
					repaint();//for painting the new coordinates of image 
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					player.setSpeed(SPEED);
					player.move();
					//System.out.println("X right "+player.getX());//for getting new x value
					repaint();//for painting the new coordinates of image
				}
				//right player
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					oppPlayer.setSpeed(-SPEED);
					oppPlayer.move();
					//System.out.println("X left "+oppPlayer.getX());//for getting new x value
					repaint();//for painting the new coordinates of image 
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					oppPlayer.setSpeed(SPEED);
					oppPlayer.move();
					//System.out.println("X right "+oppPlayer.getX());//for getting new x value
					repaint();//for painting the new coordinates of image
				}
				System.out.println("Press "+e.getKeyCode()+" "+e.getKeyChar());
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Release "+e.getKeyCode()+" "+e.getKeyChar());
				
			}
			
		};
		this.addKeyListener(listener);
	}
	@Override
	public void paintComponent(Graphics pen) {//paintcomponent is used to paint/render anything on the image
		printBackgroundImage(pen);
		player.drawPlayer(pen);
		oppPlayer.drawPlayer(pen);
		
	}
	private void printBackgroundImage(Graphics pen) {
		pen.drawImage(imageBg,0,0,GWIDTH,GHEIGHT,null);//null is used for image observer because our image size is fixed
	}
	
		private void loadbackgroundimage() {
		try {
			imageBg = ImageIO.read(Board.class.getResource(BG_IMG));//image source
			}
			catch (Exception ex) {
				System.out.println("Background image failed");
				System.exit(0);
			}
	}
}
