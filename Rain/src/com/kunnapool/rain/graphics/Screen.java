package com.kunnapool.rain.graphics;

import java.util.Random;

import com.kunnapool.rain.entity.mob.Player;
import com.kunnapool.rain.level.tile.Tile;


public class Screen {
	
	public int width;
	public int height;
	private static final int MAP_SIZE = 16;
	
	public int[] tiles=new int[MAP_SIZE*MAP_SIZE];
	public int[] pixels;
	
	private Random random =new Random();
	
	public int xoffset, yoffset;
	
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
	
//	/**
//	 * Draw onto the screen 
//	 */
//	public void render(int xoffset, int yoffset)
//	{	
//		for (int y=0;y<height;y++)
//		{
//			int yp= y+ yoffset ;
//			
//			/* if out of bounds -- skip, and hence black */
//			if(yp<0 || yp>=height)
//				continue;
//			
//			for (int x=0;x<width;x++)
//			{
//				int xp= x + xoffset; 
//				
//				if(xp<0 || xp>=width) //same
//						continue;
//				
//				pixels[ xp + yp *width]=Sprite.grass.pixels[ (x & 15) + (y & 15) * Sprite.grass.SIZE]; //fill screen with grass tiles
//			}
//		}
//	}

	
	/**
	 * Render 1 tile onto the screen
	 * @param xp x coordinate of where on the screen
	 * @param yp y coordinate of where on the screen
	 * @param Tile to be drawn
	 */
	public void renderTile(int xp, int yp, Tile tile)
	{
		/* adjustment for movement */
		xp-= xoffset;
		yp-= yoffset;
		
		for(int y=0;y<tile.sprite.SIZE;y++)
		{
			int ya=y+yp; //absolute position of the tile on the screen
			
			for(int x=0;x<tile.sprite.SIZE;x++)
			{
				int xa=x+xp;
				
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
					
				if (xa<0)
					xa=0;
				
				pixels[xa+ya*width]=tile.sprite.pixels[x+y*tile.sprite.SIZE];
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite)
	{
		/* adjustment for movement */
		xp-= xoffset;
		yp-= yoffset;
		
		for(int y=0;y<16;y++)
		{
			int ya=y+yp; //absolute position of the tile on the screen
			
			for(int x=0;x<16;x++)
			{
				int xa=x+xp;
				
				if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
					
				if (xa<0)
					xa=0;
				int col=sprite.pixels[x+y*16];
				if (col!=-327425)
					pixels[xa+ya*width]=col;
					
			}
		}
	}
	
	
	/**
	 * Sets offset for player movement
	 * @param xoffset
	 * @param yoffset
	 */
	public void setOffset(int xoffset,int yoffset)
	{
		this.xoffset=xoffset;
		this.yoffset=yoffset;
	}

}

