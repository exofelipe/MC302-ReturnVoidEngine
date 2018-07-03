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


/*
 * Classe que herda atributos da biblioteca JFrame para o uso de métodos da mesma.
 * EndScreen é a tela que é exibida após o termino de uma partida.
 * Diferentemente da MainScreen, a EndScreen possui três JPanels, um para a exibição do titulo "scoreboard",
 * outra que contém a pontuação dos jogadores e qual deles foi vitorioso (exibidos em dois JLabels) e
 * um ultimo onde foram colocados os botões de Exit, PlayAgain e Menu. 
 * 
 */

@SuppressWarnings("serial")
public class EndScreen extends JFrame{
	private JPanel pnlScoreLogo, pnlPlayers, pnlButtons;
	private JLabel player1Score, player2Score;
	private JButton btnPlayAgain, btnExit, btnMenu;
	//Notar que a classe EndScreen recebe como parametro os dois jogadores da última partida,
	//dessa forma podendo utilizar os resultados já gerados para imprimir nosso placar.
	public EndScreen(Player p1, Player p2) {
		
		ImageIcon score = new ImageIcon(getClass().getResource("/resources/scoreboard.png"));
		ImageIcon menu = new ImageIcon(getClass().getResource("/resources/menu.png"));
		ImageIcon buttonPlayAgain = new ImageIcon(getClass().getResource("/resources/again.png"));
		ImageIcon buttonExit = new ImageIcon(getClass().getResource("/resources/exitButton.png"));
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
		
		player1Score = new JLabel(p1.getName() + " "+(p1.getPoints()>p2.getPoints()? "WON": "LOST")+"! ["+p1.getPoints()+" points]");
		player2Score = new JLabel(p2.getName() + " "+(p1.getPoints()<p2.getPoints()? "WON": "LOST")+"! ["+p2.getPoints()+" points]");
		
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
		//Criando novo PongGame e iniciando novo jogo com novos jogadores
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
		//Criando novo MainScreen
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
				EndScreen.this.dispose();
			}			
		});
		pnlButtons.add(btnExit);		
		
		this.add(pnlScoreLogo);
		this.add(pnlPlayers);
		this.add(pnlButtons);
		
		this.setSize(800, 600);
	}
}
