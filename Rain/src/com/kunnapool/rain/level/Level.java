package com.kunnapool.rain.level;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int tiles[];
	
	
	public Level()
	{
		
	}
	
	
	public Level(int width, int height)
	{
		this.width=width;
		this.height=height;
		tiles=new int[width*height];
		
		generateLevel();
	}
	
	
	public Level(String path)
	{
		loadLevel(path);
	}
	
	
	protected void generateLevel()
	{
		
	}
	
	private void loadLevel(String path)
	{
		
	}
	
	public void update()
	{
		
	}
	
	private void time()
	{
		
	}
	
	public Tile getTile(int x, int y)
	{
		/* see which tile is there on (x,y) in the map */
		if(tiles[x+y*width]==0)
			return Tile.grass;
		
		return Tile.voidTile;
	}
	
	
	public void render(int xScroll, int yScroll, Screen screen)
	{
		
		/* Corner Pins */
		int x0= xScroll >> 4; // ÷16
		int x1= (xScroll + screen.width) >>4;
		int y0= yScroll >> 4; // ÷16
		int y1= (yScroll + screen.height) >>4;
		
		
		
		
		
		
	}
	
	
}