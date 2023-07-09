package com.mohak.gaming.canvas;

import java.awt.Color;
import java.awt.Font;
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
import com.mohak.gaming.sprites.Power;
import com.mohak.gaming.sprites.RyuPlayer;
import com.mohak.gaming.utils.GameConstants;



public class Board extends JPanel implements GameConstants {
	BufferedImage imageBg;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	private Power ryuFullPower;
	private Power kenFullPower;
	private Timer timer;
	private boolean gameOver;
	
	private void gameLoop() {
		timer = new Timer(GAME_LOOP,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				if(gameOver) {
					timer.stop();//when game is over
				}
				ryuPlayer.fall();//this function always run
				kenPlayer.fall();//this function always run
				collision();
				isGameOver();
				
			}
		});
		timer.start();
	}
	private void loadPower() {
		ryuFullPower = new Power(30,"RYU");
		kenFullPower = new Power(GWIDTH - 550,"KEN");
	}
	
	private void printFullPower(Graphics g) {
		ryuFullPower.printRectangle(g);
		kenFullPower.printRectangle(g);
	}
	
	private boolean isCollide() {//for checking weather the players collide or not
		int xDistance = Math.abs(ryuPlayer.getX() - kenPlayer.getX());
		int yDistance = Math.abs(ryuPlayer.getY() - kenPlayer.getY());
		int maxH = Math.max(ryuPlayer.getH(), kenPlayer.getH());
		int maxW = Math.max(ryuPlayer.getW(), kenPlayer.getW());
		return xDistance <=maxW-25 && yDistance <= maxH-25;//-25 so that blank spaces are removed in game
	}
	
	private void collision() {
		if(isCollide()) {
			if(ryuPlayer.isAttacking()) {
				kenPlayer.setCurrentMove(DAMAGE);
				kenFullPower.setHealth();
			}
			else if (kenPlayer.isAttacking()) {
				ryuPlayer.setCurrentMove(DAMAGE);
				ryuFullPower.setHealth();
			}
			else if(ryuPlayer.isAttacking() && kenPlayer.isAttacking()) {
				ryuPlayer.setCurrentMove(DAMAGE);
				kenPlayer.setCurrentMove(DAMAGE);
			}
			kenPlayer.setCollide(true);
			ryuPlayer.setCollide(true);
			//System.out.println("horaha");//for checking collision 
			ryuPlayer.setSpeed(0);
		}
		else {
			ryuPlayer.setCollide(false);
			ryuPlayer.setSpeed(SPEED);
		}
	}
	
	private void isGameOver() {
		if(ryuFullPower.getHealth()<=0 || kenFullPower.getHealth()<=0) {
			gameOver = true;
		}
	}
	
	private void printGameOver(Graphics pen) {
		if(gameOver) {
			pen.setColor(Color.RED);
		pen.setFont(new Font ("times", Font.BOLD, 40));
		pen.drawString("GAME OVER", GWIDTH/2-100, GHEIGHT/2-100);
		}
	}
	
	public Board() throws IOException  {
		
		loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		loadPower();
		
		
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
					ryuPlayer.setCollide(false);//so that player can move backward also
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
					kenPlayer.setCollide(false);//so that player can move backward also
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
		printFullPower(pen);
		printGameOver(pen);
		
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
