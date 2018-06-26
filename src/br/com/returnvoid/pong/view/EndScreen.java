package br.com.returnvoid.pong.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.returnvoid.pong.controller.PongGame;
import br.com.returnvoid.pong.model.Player;

public class EndScreen extends JFrame{
	private JPanel pnlScoreLogo, pnlPlayers, pnlButtons, pnlMenu;
	private JLabel player1Score, player2Score;
	private JButton btnPlayAgain, btnExit, btnMenu;
	public EndScreen(Player p1, Player p2) {
		
		ImageIcon score = new ImageIcon(getClass().getResource("scoreboard.png"));
		ImageIcon menu = new ImageIcon(getClass().getResource("menu.png"));
		ImageIcon buttonPlayAgain = new ImageIcon(getClass().getResource("again.png"));
		ImageIcon buttonExit = new ImageIcon(getClass().getResource("exitButton.png"));
		this.setTitle("JAVA PONG - MC302 - ReturnVoid");
		this.setLayout(new GridLayout(3, 1));
		
		pnlScoreLogo = new JPanel();
		pnlScoreLogo.setBackground(Color.BLACK);
		pnlScoreLogo.setForeground(Color.BLACK);
		pnlScoreLogo.setLayout(new GridLayout(1, 1));
		pnlScoreLogo.add(new JLabel(score));
		
		pnlPlayers = new JPanel();
		pnlPlayers.setLayout(new GridLayout(2,1));
		pnlPlayers.setBackground(Color.BLACK);
		
		pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridLayout(1,3));
		pnlButtons.setBackground(Color.BLACK);
		
		if(p1.getPoints()==10) {
			player1Score = new JLabel(p1.getNome() + " WON! ["+p1.getPoints()+" points]");
			player2Score = new JLabel(p2.getNome() + " LOST! ["+p2.getPoints()+" points]");
		}else {
			player1Score = new JLabel(p1.getNome() + " LOST! ["+p1.getPoints()+" points]");
			player2Score = new JLabel(p2.getNome() + " WON! ["+p2.getPoints()+" points]");
		}
		//customizando JTextField
		player1Score.setBackground(Color.BLACK);
		player1Score.setBorder(null);
		player1Score.setForeground(Color.WHITE);
		player1Score.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		player1Score.setHorizontalAlignment(JTextField.CENTER);
		
		player2Score.setBackground(Color.BLACK);
		player2Score.setBorder(null);
		player2Score.setForeground(Color.WHITE);
		player2Score.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		player2Score.setHorizontalAlignment(JTextField.CENTER);
		
		pnlPlayers.add(player1Score);
		pnlPlayers.add(player2Score);		
		
		btnPlayAgain = new JButton();
		btnPlayAgain.setIcon(buttonPlayAgain);
		btnPlayAgain.setBackground(Color.BLACK);
		btnPlayAgain.setBorderPainted(false);		
		btnPlayAgain.setFocusPainted(false);
		btnPlayAgain.setContentAreaFilled(false);
		btnPlayAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				EndScreen.this.dispose();				
				JFrame gameWindow = new JFrame();
				
				PongGame game = new PongGame(gameWindow, p1, p2);
				game.run();
				
				gameWindow.setVisible(true);
				gameWindow.setSize(800, 600);
				gameWindow.repaint();
			}
			
		});
		
		pnlButtons.add(btnPlayAgain);
		
		btnMenu = new JButton();
		btnMenu.setIcon(menu);
		btnMenu.setBackground(Color.BLACK);
		btnMenu.setBorderPainted(false);		
		btnMenu.setFocusPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				EndScreen.this.dispose();				
				MainScreen frame = new MainScreen();
				frame.setSize(new Dimension(800, 600));
				frame.setVisible(true);
			}
			
		});
		pnlButtons.add(btnMenu);
		
		btnExit = new JButton();
		btnExit.setIcon(buttonExit);
		btnExit.setBackground(Color.BLACK);
		btnExit.setBorderPainted(false);		
		btnExit.setFocusPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}			
		});
		pnlButtons.add(btnExit);		
		
		this.add(pnlScoreLogo);
		this.add(pnlPlayers);
		this.add(pnlButtons);
		
		this.setSize(800, 600);
	}
}
