package com.mohak.gaming.canvas;

import javax.swing.JFrame;

import com.mohak.gaming.utils.GameConstant;

public class GameFrame extends JFrame implements GameConstant{
	public GameFrame() throws Exception {
		setResizable(false);
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(GWIDTH,GHEIGHT);
		setLocationRelativeTo(null);
		Board board = new Board();//board is called
		add(board);//Board is now added in frame
		setVisible(true);
		
	}

	public static void main(String[] args) {
		try {
			GameFrame obj =new GameFrame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
