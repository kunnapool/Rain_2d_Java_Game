package com.kunnapool.rain.graphics;

import java.util.Random;


public class Screen {
	
	private int width;
	private int height;
	
	private static final int MAP_SIZE = 16;
	
	
	public int[] tiles=new int[MAP_SIZE*MAP_SIZE];
	
	public int[] pixels;
	
	private Random random =new Random();
	
	public Screen(int width, int height)
	{
		this.width=width;
		this.height=height;
		
		pixels =new int[width*height];
		
		for(int i=0;i<MAP_SIZE*MAP_SIZE;i++)
			tiles[i]=random.nextInt(0xffffff); //any colour
		
	}
	
	public void clear()
	{
		for(int i=0;i<pixels.length;i++)
			pixels[i]=0;
	}
	
	public void render(int xoffset, int yoffset)
	{		
		for (int y=0;y<height;y++)
		{
			int yy=y+yoffset;
			for (int x=0;x<width;x++)
			{
				int xx=x+xoffset;
				int tileIndex=((xx/16)& MAP_SIZE-1) + ((yy/16)& MAP_SIZE-1)* MAP_SIZE; //size of tile is 32 pixels
				pixels[x+y*width]=tiles[tileIndex];
			}
		}
	}
}

