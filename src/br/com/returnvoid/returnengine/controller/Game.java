package br.com.returnvoid.returnengine.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import br.com.returnvoid.returnengine.view.Window;


public class Game{
	private boolean running = false;
	private Thread threadTps, threadFps;
	private Window window;
	private GameSpeedTracker speedTracker;

	public Game() {
		this.speedTracker = new GameSpeedTracker(30,60);
		this.window = new Window(new Dimension(800, 600));
		this.threadTps = new Thread(new Runnable() {
			@Override
			public void run() {
				runTPS();
			}
		});
		
		this.threadFps = new Thread(new Runnable() {
			@Override
			public void run() {
				runFPS();
			}
		});
	}
	
	public void stop() {
		this.running = false;
	}
	
	public void runTPS() {
		while(this.isRunning()) {
			this.speedTracker.start();
			
			this.speedTracker.stop();
		}
	}
	
	public void runFPS() {
		while(this.isRunning()) {
			System.out.println("FPS running");
			Random r = new Random();
			try {
				this.window.getContentPane().setBackground(new Color(
						r.nextInt(255), r.nextInt(255), r.nextInt(255)));
				this.window.repaint();
				this.window.revalidate();
				Thread.sleep(1 * 200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void run() {
		this.running = true;
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
		
		public void start() {
			// TODO inicia cronometro
		}
		
		public void stop() {
			// TODO para cronometro
		}
		
		public void ensureTps() {
			// TODO Thread.sleep para o que faltar do cronometro até o tps
		}
		
		public void ensureFps() {
			// TODO se o loop estiver muito rapido dorme um pouco
		}
	}
}



