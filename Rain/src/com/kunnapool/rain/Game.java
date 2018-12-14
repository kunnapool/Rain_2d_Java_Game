package com.kunnapool.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.input.Keyboard;

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
	int x=0, y=0;
	
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	
	
	
	

	
	private BufferedImage image= new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels= ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	
	
	public Game()
	{
		Dimension size = new Dimension(width*scale, height*scale); //900 pixels wide
		setPreferredSize(size); //size of the canvas
		
		screen=new Screen(width, height);
		frame=new JFrame();
		key=new Keyboard();
		
		addKeyListener(key); //canvas listens to keys
	}
	
	
	
	public synchronized void start()
	{
		
		running=true;
		
		thread=new Thread(this,"Display"); //thread contains this class
		
		thread.start();
	}
	
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
	
	/* called by start() method */
	public void run()
	{
		
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
			delta+=(now-lastTime); 
			lastTime=now;
			
			
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
				System.out.println(updates+ " ups, "+frames+" fps,"+delta+" delta");
				frame.setTitle(title+" | "+updates+ " ups, "+frames+" fps");
				frames=0;
				updates=0;
			}
		}
		stop();
	}
	
	public void update()
	{
		key.update();
		
		if (key.up)
			x++;
		
		if (key.down)
			x--;
	}
	
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
		screen.render(x,y);
		
		for(int i=0;i<pixels.length;i++)
			pixels[i]=screen.pixels[i];
		
		
		Graphics g=bs.getDrawGraphics(); //get graphics from buffer
		g.drawImage(image, 0,0, getWidth(), getHeight(), null);
		
		
		g.dispose(); //like free
		bs.show();
		
	}
	
	
	public static void main(String args[])
	{
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
		

	}
	
	

}
