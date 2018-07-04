package br.com.returnvoid.returnengine.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/*
 * Classe Principal da engine
 * 
 * Note que essa classe é abstrata, subclasses devem herdar o construtor
 * e declarar os métodos onLoop e onRender, responsáveis pela lógica específica
 * do jogo e da lógica gráfica específica do jogo
 */
public abstract class Game{
	private boolean running = false;
	// Threads para controle dos loops lógico e gráfico
	private Thread threadTps, threadFps;
	// Janela onde será desenhado o jogo
	protected JFrame window;
	private GameSpeedTracker speedTracker;

	public Game(int tps, int maxFps, JFrame window) {
		this.speedTracker = new GameSpeedTracker(tps,maxFps);
		this.window = window;
		this.window.setIgnoreRepaint(true); // Como os elementos serão desenhados "na mão" isso pode atrapalhar o controle gráfico
		
		// Construindo thread com o loop lógico declarado a baixo
		this.threadTps = new Thread(new Runnable() {
			@Override
			public void run() {
				runTps();
			}
		});
		
		// Construindo thread com o loop gráfico, declarado a baixo
		this.threadFps = new Thread(new Runnable() {
			@Override
			public void run() {
				runFps();
			}
		});
	}
	
	public void stop() {
		// Condição de parada do loop, ambos devem ser finalizados quando essa flag for alterada
		this.running = false;
	}
	
	public void runTps() {
		while(this.isRunning()) {
			this.speedTracker.startTps();			
			this.onLoop();
			this.speedTracker.stopTps();			
			this.speedTracker.ensureTps();			
		}
	}
	
	public void runFps() {
		while(this.isRunning()) {
			this.speedTracker.startFps();
			BufferStrategy bufferStrategy = window.getBufferStrategy();
			Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		    g.setColor(Color.BLACK);
		    g.fillRect(0, 0, window.getWidth(), window.getHeight());
		    this.onRender(g);
		    g.setColor(Color.white);
	        g.fillRect(0, 30, 60, 30);
	        g.setColor(Color.black);
	        g.setFont(new Font("", Font.BOLD, 12));
	        g.drawString(this.speedTracker.getTps() + " tps", 10, 42);
	        g.drawString(this.speedTracker.getFps() + " fps", 10, 54);
	        
	        g.dispose();
	        try {
	        	bufferStrategy.show();
			}catch (java.lang.IllegalStateException e) {
				//Essa exceção pode ser ignorada, ocorre quando finaliza-se o jogo
				//Enquanto essa thread ocorre
			}	
			this.speedTracker.stopFps();
			this.speedTracker.ensureFps();			
		}
	}
	protected abstract void onLoop();
	protected abstract void onRender(Graphics2D g);

	public void run() {
		this.running = true;
		this.window.setVisible(true);
		this.window.createBufferStrategy(2);
		
		this.threadFps.start();
		this.threadTps.start();
	}

	public boolean isRunning() {
		return running;
	}
	
	
	private class GameSpeedTracker {
		public final double MILI_SECOND = 1e3;
		
		private int max_fps;		
		private double startTimeFps;
		private double sleepTimeFps;
		private double loopTimeFps;
		private double expectedLoopTimeFps;
		
		private int expectedTps;		
		private double startTimeTps;
		private double sleepTimeTps;
		private double loopTimeTps;
		private double expectedLoopTimeTps;
		
		
		public GameSpeedTracker(int expectedTps, int max_fps) {
			this.expectedTps = expectedTps;
			this.max_fps = max_fps;
		}
		
		public int getTps() {			
			return (int)(this.MILI_SECOND/((this.loopTimeTps + this.sleepTimeTps)));
		}
		
		public int getFps() {			
			return (int)(this.MILI_SECOND/((this.loopTimeFps + this.sleepTimeFps)));
		}
		
		public void startFps() {
			// Salvando o tempo de inicio do loop FPS
			this.startTimeFps = System.currentTimeMillis();				
		}
		
		public void startTps() {
			// Salvando o tempo de inicio do loop TPS
			this.startTimeTps = System.currentTimeMillis();
		}
		
		public void stopFps() {
			// Obtendo o tempo de execução do loop FPS
			this.loopTimeFps = (System.currentTimeMillis() - this.startTimeFps);
		}
		
		public void stopTps() {
			// Obtendo o tempo de execução do loop TPS
			this.loopTimeTps = (System.currentTimeMillis() - this.startTimeTps);
		}
		
		public void ensureTps() {
			// Dado o tempo de execução do loop TPS, calcula a diferença entre com o esperado e "pausa" 
			// a thread o valor faltante
			this.expectedLoopTimeTps = (this.MILI_SECOND / this.expectedTps);
			this.sleepTimeTps = (this.expectedLoopTimeTps - this.loopTimeTps);	
			try {
				if(this.sleepTimeTps > 0)		
					Thread.sleep((long)Math.floor(this.sleepTimeTps));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void ensureFps() {
			// Dado o tempo de execução do loop FPS, calcula a diferença entre com o esperado e "pausa" 
			// a thread o valor faltante
			this.expectedLoopTimeFps = (this.MILI_SECOND / this.max_fps);	
			this.sleepTimeFps =  (this.expectedLoopTimeFps - this.loopTimeFps);					
			try {
				if(this.sleepTimeFps > 0)					
					Thread.sleep((long)Math.floor(this.sleepTimeFps));				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		
	}
}



