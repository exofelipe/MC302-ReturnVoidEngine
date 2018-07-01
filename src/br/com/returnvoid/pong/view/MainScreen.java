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

@SuppressWarnings("serial")
public class MainScreen extends JFrame{
	private JPanel pnlLogo, pnlPlayers;
	private JTextField player1Name, player2Name;
	private JButton btnStart, btnExit;
	public MainScreen() {
		ImageIcon logo = new ImageIcon(getClass().getResource("/resources/logo.png"));
		ImageIcon buttonStart = new ImageIcon(getClass().getResource("/resources/playButton.png"));
		ImageIcon buttonExit = new ImageIcon(getClass().getResource("/resources/exitButton.png"));
		this.setTitle("JAVA PONG - MC302 - ReturnVoid");
		this.setLayout(new GridLayout(2, 1));
		
		pnlLogo = new JPanel();
		pnlLogo.setBackground(Color.BLACK);
		pnlLogo.setForeground(Color.BLACK);
		pnlLogo.setLayout(new GridLayout(1, 1));
		pnlLogo.add(new JLabel(logo));
		pnlPlayers = new JPanel();
		pnlPlayers.setLayout(new GridLayout(2,2));
		pnlPlayers.setBackground(Color.BLACK);
		
		player1Name = new JTextField("Player 01");
		player2Name = new JTextField("Player 02");
		//customizando JTextField
		player1Name.setBackground(Color.BLACK);
		player1Name.setBorder(null);
		player1Name.setForeground(Color.WHITE);
		player1Name.setHorizontalAlignment(JTextField.CENTER);
		player1Name.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		player2Name.setBackground(Color.BLACK);
		player2Name.setBorder(null);
		player2Name.setForeground(Color.WHITE);
		player2Name.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		player2Name.setHorizontalAlignment(JTextField.CENTER);
		
		pnlPlayers.add(player1Name);
		pnlPlayers.add(player2Name);
		
		btnStart = new JButton();
		btnStart.setIcon(buttonStart);
		btnStart.setBackground(Color.BLACK);
		btnStart.setBorderPainted(false);		
		btnStart.setFocusPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Player p1 = new Player(player1Name.getText());
				Player p2 = new Player(player2Name.getText());
				
				MainScreen.this.dispose();
				JFrame gameWindow = new JFrame();
				
				PongGame game = new PongGame(gameWindow, p1, p2);
				game.run();
				
				gameWindow.setVisible(true);
				gameWindow.setSize(800, 600);
				gameWindow.repaint();
			}
			
		});
		pnlPlayers.add(btnStart);
		
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
		pnlPlayers.add(btnExit);
		
		this.add(pnlLogo);
		this.add(pnlPlayers);
		
		this.setSize(800, 600);
	}
	
	public static void main(String args[]) {
		MainScreen frame = new MainScreen();
		frame.setSize(new Dimension(800, 600));
		frame.setVisible(true);
	}
}
