package com.kunnapool.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.kunnapool.rain.entity.mob.Player;
import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.input.Keyboard;
import com.kunnapool.rain.level.Level;
import com.kunnapool.rain.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	
	/**
	 *  ????
	 */
	private static final long serialVersionUID = 1L;
	
	public static int width= 300;
	public static int height= width/16 * 9;
	public static int scale = 3;
	private String title="Rain";
	private boolean running = false;
	
	
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	
	
	private Player player;
	
	

	/* Read image and convert into a 'Raster'-- Integer representation of an image as pixels */
	private BufferedImage image= new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels= ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	
	
	/**
	 * Default constructor
	 * Initializes the screen, frame and keyboard
	 */
	public Game()
	{
		Dimension size = new Dimension(width*scale, height*scale); //Dimensions of the screen
		setPreferredSize(size); //size of the canvas
		
		screen=new Screen(width, height);
		frame=new JFrame();
		key=new Keyboard();
		level=Level.spawn;
		TileCoordinate player_spawn=new TileCoordinate(19, 62);
		player=new Player(player_spawn.x(), player_spawn.y(), key);
		player.init(level.spawn);
		
		
		addKeyListener(key); //canvas listens to keys
	}
	
	
	/**
	 * Starts the game thread -- makes a new thread, and starts it
	 * Pass the frame into the new thread
	 */
	public synchronized void start()
	{
		
		running=true;
		
		thread=new Thread(this,"Display"); //thread contains this class
		
		thread.start();
	}
	
	
	/**
	 * Close the thread
	 */
	public synchronized void stop()
	{
		running=false;
		try
		{
			thread.join();
		} catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Called by thread.start() method, runs the game
	 * Controls render and update
	 */
	public void run()
	{
		requestFocus(); //focus on the window thread to detect key presses
		
		long lastTime = System.nanoTime();
		long timer=System.currentTimeMillis();
		final double ns = (double)Math.pow(10, 9) / 60.0; //1 sec/60
		long delta = 0;
		int frames=0;
		int updates=0;
		
		/* infinite game loop */
		while(running)
		{
			
			long now = System.nanoTime();
			delta+=(now-lastTime); //how long has it been  
			lastTime=now;
			
			/* Do stuff 60 times a second -- apporx. */
			while(delta>=ns)
			{
				/* update the logic */
				update();
				updates++;
				delta=0;
				
			}
			
			/* draw */
			render();
			frames++; //num of frames rendered
			
			/* once per second */
			if(System.currentTimeMillis() - timer >1000)
			{
				timer+=1000;
				frame.setTitle(title+" | "+updates+ " ups, "+frames+" fps");
				frames=0;
				updates=0;
			}
		}
		stop();
	}
	
	
//	int x=0, y=0;
	
	/* Update logic, usually 60 times a second */
	public void update()
	{
		key.update();
		player.update();
	}
	
	
	/* Draw everything on the screen */
	public void render()
	{
		/* buffer to hold what to draw, before drawing */
		BufferStrategy bs=getBufferStrategy(); //Canvas' method
		
		/* hasn't been created yet */
		if (bs==null)
		{
			createBufferStrategy(3); //better speed
			return;
		}
		
		screen.clear();
		
		int xScroll=player.x-screen.width/2;
		int yScroll=player.y-screen.height/2;
		
		/* x,y act as offset, however rendering still starts at 0,0 */
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		/* copy what was rendered by screen.render() */
		for(int i=0;i<pixels.length;i++)
			pixels[i]=screen.pixels[i];
		
		
		
		Graphics g=bs.getDrawGraphics(); //get graphics from buffer
		g.drawImage(image, 0,0, getWidth(), getHeight(), null);
		
		g.drawString("("+player.x+" ,"+player.y+")",0,16);
		
		g.dispose(); //like free
		bs.show();
		
	}
	
	
	public static void main(String args[])
	{
		System.out.println("Starting...");
		
		/* create new game */
		Game game = new Game();
		
		game.frame.setResizable(false);
//		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack(); //resize to preferred size
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when 'red x' is pressed
		game.frame.setLocationRelativeTo(null); //center the screen
		game.frame.setVisible(true);
		
		game.start();
		
		System.out.println("Stopping...");
		

	}
	
	

}
