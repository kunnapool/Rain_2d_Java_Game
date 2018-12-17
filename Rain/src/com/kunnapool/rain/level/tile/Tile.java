package com.kunnapool.rain.level.tile;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	public int size;
	
	
	public static Tile grass=new GrassTile(Sprite.grass);
	public static Tile flower=new FlowerTile(Sprite.flower);
	public static Tile rock=new RockTile(Sprite.rock);
	public static Tile voidTile=new Tile(Sprite.voidSprite);
	
	
	
	public Tile(Sprite sprite)
	{
		this.sprite=sprite;
		this.size=sprite.SIZE;
	}
	
	/**
	 * Render tile onto the screen
	 * @param x coordinate of where to render
	 * @param y coordinate of where to render
	 * @param screen
	 */
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x<<4, y<<4, this);
	}
	
	protected boolean solid()
	{
		return false;
	}

}
