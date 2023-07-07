package com.mohak.gaming.canvas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.mohak.gaming.sprites.KenPlayer;
import com.mohak.gaming.sprites.RyuPlayer;
import com.mohak.gaming.utils.GameConstants;



public class Board extends JPanel implements GameConstants {
	BufferedImage imageBg;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	
	private Timer timer;
	
	private void gameLoop() {
		timer = new Timer(GAME_LOOP,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				ryuPlayer.fall();//this function always run
				kenPlayer.fall();//this function always run
				
			}
		});
		timer.start();
	}
	
	
	public Board() throws IOException  {
		
		loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		
		
	}
	
	private void bindEvents() {//listener is used to detect the keys during game
		this.addKeyListener(new KeyAdapter() {
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				ryuPlayer.setSpeed(0);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				//ryu left
				if(e.getKeyCode() == KeyEvent.VK_A) {
					ryuPlayer.setSpeed(-SPEED);
					//System.out.println("X left "+player.getX());//for getting new x value
					ryuPlayer.move();
					//repaint();//for painting the new coordinates of image
				}
				//ryu jump
				else if (e.getKeyCode() == KeyEvent.VK_W) {
				ryuPlayer.jump();
				}
				//ryu right
				else if(e.getKeyCode() == KeyEvent.VK_D) {
					ryuPlayer.setSpeed(SPEED);
					ryuPlayer.move();
					//repaint();//for painting the new coordinates of image
				}
				//ryu Kick
				else if (e.getKeyCode()== KeyEvent.VK_Z) {
					ryuPlayer.setCurrentMove(KICK);
				}
				//ryu Punch
				else if (e.getKeyCode()== KeyEvent.VK_X) {
					ryuPlayer.setCurrentMove(PUNCH);
				}
				/*//ryu jump
				else if (e.getKeyCode()== KeyEvent.VK_W) {
					ryuPlayer.setCurrentMove(JUMP);
				}*/
				// Ken 
				//ken jump
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					kenPlayer.jump();
					}
				//ken left move
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					kenPlayer.setSpeed(-SPEED);
					kenPlayer.move();
					//repaint()//for painting the new coordinates of image
				}
				//ken kick
				else if (e.getKeyCode()== KeyEvent.VK_NUMPAD8) {
					kenPlayer.setCurrentMove(KICK);
				}
				//ken punch
				else if (e.getKeyCode()== KeyEvent.VK_NUMPAD7) {
					kenPlayer.setCurrentMove(PUNCH);
				}
				//ken right move
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					kenPlayer.setSpeed(SPEED);
					kenPlayer.move();
					//repaint();//for painting the new coordinates of image
				}
				
				
			}
		});
	}
	
	
	
	@Override
	public void paintComponent(Graphics pen) {//paintcomponent is used to paint/render anything on the image
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuPlayer.printPlayer(pen);
		kenPlayer.printPlayer(pen);
		
		
		
	}

	
	private void printBackgroundImage(Graphics pen) {
		pen.drawImage(imageBg,0,0, 1400,900, null);//null is used for image observer because our image size is fixed
	}
	
	
	
	private void loadBackgroundImage() {
		try {
			imageBg = ImageIO.read(Board.class.getResource(BG_IMG));//image source
			}
			catch(Exception ex) {
				System.out.println("Background Image Loading Fail...");
				System.exit(0);
			
			}
	}
}
