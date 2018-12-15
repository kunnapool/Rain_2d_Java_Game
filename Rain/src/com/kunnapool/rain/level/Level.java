package com.kunnapool.rain.level;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int tiles[]; //the map, basically. Stores what kind of tiles is where in the map
	
	
	
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
	
	/**
	 * Get the tile at (x,y) in the map
	 * @param x coordinate
	 * @param y coordinate
	 * @return Tile at (x,y)
	 */
	public Tile getTile(int x, int y)
	{
		if (x<0 || y<0 || x>=width || y>=height)
			return Tile.voidTile;
		/* see which tile is there on (x,y) in the map */
		if(tiles[x+y*width]==0)
			return Tile.grass;
		
		return Tile.voidTile;
	}
	
	/**
	 * Start at (0,0)
	 * Take the current position of the player and draw the map accordingly
	 * Player is left-cornered
	 * @param xScroll x of player pixel wise
	 * @param yScroll y of player pixel wise
	 * @param screen
	 */
	public void render(int xScroll, int yScroll, Screen screen)
	{
		/* current position of where player is */
		screen.setOffset(xScroll, yScroll);
		
		/* Corner Pins in tile precision */
		int x0= xScroll >> 4; // ÷16
		int x1= (xScroll + screen.width + 16) >>4;
		int y0= yScroll >> 4; // ÷16
		int y1= (yScroll + screen.height + 16) >>4;
		
		
		/* Go through all the tiles and render them*/
		for (int y=y0;y<y1;y++)
		{
			for(int x=x0;x<x1;x++)
			{
				Tile t=getTile(x,y);
				t.render(x,y,screen);
			}
		}
		
		
		
		
		
		
	}
	
	
}
