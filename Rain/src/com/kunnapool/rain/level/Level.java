package com.kunnapool.rain.level;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.graphics.Sprite;
import com.kunnapool.rain.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int tilesInt[]; //the map, basically. Stores what kind of tiles is where in the map
	protected int tiles[];
	
	public static Level spawn=new SpawnLevel("/levels/spawn.png");
	
	
	public Level(int width, int height)
	{
		this.width=width;
		this.height=height;
		
		generateLevel();
	}
	
	
	public Level(String path)
	{
		loadLevel(path);
		generateLevel();
	}
	
	
	protected void generateLevel()
	{
		
	}
	
	protected void loadLevel(String path)
	{
		
	}
	
	
	public void update()
	{
		
	}
	
	
	// Grass 0xFF00FF00
	// Flower 0xFFFFFF00
	// Rock 0xFF7F7F00
	public Tile getTile(int x, int y)
	{
		if (x<0 || y<0 || x>=width || y>=height) return Tile.voidTile;
		if (tiles[x+y*width]==Tile.col_spawn_floor) return Tile.spawn_floor;
		if (tiles[x+y*width]==Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if (tiles[x+y*width]==Tile.col_spawn_wall2) return Tile.spawn_wall2;
		if (tiles[x+y*width]==Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles[x+y*width]==Tile.col_spawn_water) return Tile.spawn_water;
		if (tiles[x+y*width]==Tile.col_spawn_hedge) return Tile.spawn_hedge;
		return Tile.voidTile;
	}
	
	private void time()
	{
		
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
				getTile(x,y).render(x, y, screen);
			}
		}
		
		
		
		
		
		
	}
	
	
}
