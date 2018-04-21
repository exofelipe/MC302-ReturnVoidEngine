package br.com.returnvoid.returnengine.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Game{
	private boolean running = false;
	private Thread threadTps, threadFps;
	private JFrame window;
	private GameSpeedTracker speedTracker;

	public Game(int tps, int maxFps, JFrame window) {
		this.speedTracker = new GameSpeedTracker(tps,maxFps);
		this.window = window;
		//this.window.setUndecorated(true);
		//this.window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.window.setIgnoreRepaint(true);
		this.window.setLocation(100, 100);
		this.window.setVisible(true);
		
		this.threadTps = new Thread(new Runnable() {
			@Override
			public void run() {
				runTps();
			}
		});
		
		this.threadFps = new Thread(new Runnable() {
			@Override
			public void run() {
				runFps();
			}
		});
	}
	
	public void stop() {
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
		    g.setColor(Color.black);
		    g.fillRect(0, 0, window.getWidth(), window.getHeight());
		    //g.fillRect(0, 0, 50, 100);
		    this.onRender(g);
		    g.setColor(Color.white);
	        g.fillRect(0, 30, 40, 30);
	        g.setColor(Color.black);
	        g.setFont(new Font("", Font.BOLD, 12));
	        g.drawString(this.speedTracker.getTps() + " tps", 1, 42);
	        g.drawString(this.speedTracker.getFps() + " fps", 1, 54);
	        
	        g.dispose();
	        bufferStrategy.show();
	        
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
		private int tps;
		private int max_fps;
		public GameSpeedTracker(int tps, int max_fps) {
			this.tps = tps;
			this.max_fps = max_fps;
		}
		
		public int getTps() {
			return 0; //TODO calcular
		}
		
		public int getFps() {
			return 0; //TODO calcular
		}
		
		public void startFps() {
			// TODO inicia cronometro
		}
		
		public void startTps() {
			// TODO inicia cronometro
		}
		
		public void stopFps() {
			// TODO para cronometro
		}
		
		public void stopTps() {
			// TODO para cronometro
		}
		
		public void ensureTps() {
			// TODO Thread.sleep para o que faltar do cronometro até o tps
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void ensureFps() {
			// TODO se o loop estiver muito rapido dorme um pouco
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



