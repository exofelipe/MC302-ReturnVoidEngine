package br.com.returnvoid.pong.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.PUBLIC_MEMBER;

import br.com.returnvoid.pong.controller.PongGame;
import br.com.returnvoid.pong.model.Player;

public class MainScreen extends JFrame{
	private JPanel pnlLogo, pnlPlayers;
	private JTextField player1Name, player2Name;
	private JButton btnStart, btnClose;
	public MainScreen() {
		this.setTitle("JAVA PONG - MC302 - ReturnVoid");
		this.setLayout(new GridLayout(2, 1));
		
		pnlLogo = new JPanel();
		pnlLogo.setBackground(Color.RED);
		pnlLogo.setForeground(Color.RED);
		pnlLogo.setLayout(new GridLayout(1, 1));
		pnlLogo.add(new JLabel("JAVA PONG (isso aqui tem que virar um logo)"));
		
		pnlPlayers = new JPanel();
		pnlPlayers.setLayout(new GridLayout(3,2));
		
		pnlPlayers.add(new JLabel("Nome Jogador 1"));
		pnlPlayers.add(new JLabel("Nome Jogador 2"));
		
		player1Name = new JTextField();
		pnlPlayers.add(player1Name);
		player2Name = new JTextField();
		pnlPlayers.add(player2Name);
		
		btnStart = new JButton("JOGAR");
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
