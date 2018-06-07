package br.com.returnvoid.returnengine.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Game{
	private boolean running = false;
	private Thread threadTps, threadFps;
	protected JFrame window;
	private GameSpeedTracker speedTracker;

	public Game(int tps, int maxFps, JFrame window) {
		this.speedTracker = new GameSpeedTracker(tps,maxFps);
		this.window = window;
		//this.window.setUndecorated(true);
		//this.window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.window.setIgnoreRepaint(true);
		this.window.setLocation(100, 100);
		//this.window.setVisible(true);
		
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
		    g.setColor(Color.green);
		    g.fillRect(0, 0, window.getWidth(), window.getHeight());
		    //g.fillRect(0, 0, 50, 100);
		    this.onRender(g);
		    g.setColor(Color.white);
	        g.fillRect(0, 30, 60, 30);
	        g.setColor(Color.black);
	        g.setFont(new Font("", Font.BOLD, 12));
	        g.drawString(this.speedTracker.getTps() + " tps", 10, 42);
	        g.drawString(this.speedTracker.getFps() + " fps", 10, 54);
	        
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
		public final double NANO_SECOND = 1e9;
		
		private int max_fps;		
		private double startTimeFps;
		private long sleepTimeFps;
		private double loopTimeFps;
		private double expectedLoopTimeFps;
		
		private int expectedTps;		
		private double startTimeTps;
		private long sleepTimeTps;
		private double loopTimeTps;
		private double expectedLoopTimeTps;
		
		
		public GameSpeedTracker(int expectedTps, int max_fps) {
			this.expectedTps = expectedTps;
			this.max_fps = max_fps;
		}
		
		public int getTps() {				
			return (int)(1/((this.loopTimeTps + this.sleepTimeTps*1e6)/this.NANO_SECOND));
		}
		
		public int getFps() {			
			return (int)(1/((this.loopTimeFps + this.sleepTimeFps*1e6)/this.NANO_SECOND));
		}
		
		public void startFps() {
			this.startTimeFps = System.nanoTime();				
		}
		
		public void startTps() {
			this.startTimeTps = System.nanoTime();
		}
		
		public void stopFps() {
			this.loopTimeFps = (System.nanoTime() - this.startTimeFps);
		}
		
		public void stopTps() {
			this.loopTimeTps = (System.nanoTime() - this.startTimeTps);
		}
		
		public void ensureTps() {
			this.expectedLoopTimeTps = (this.NANO_SECOND / this.expectedTps);
			this.sleepTimeTps = (long)((this.expectedLoopTimeTps - this.loopTimeTps)/1e6);				
			try {
				Thread.sleep(this.sleepTimeTps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void ensureFps() {
			this.expectedLoopTimeFps = (this.NANO_SECOND / this.max_fps);	
			this.sleepTimeFps = (long)((this.expectedLoopTimeFps - this.loopTimeFps)/1e6);					
			try {
				if(this.sleepTimeFps > 0)					
					Thread.sleep(this.sleepTimeFps);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}
}



