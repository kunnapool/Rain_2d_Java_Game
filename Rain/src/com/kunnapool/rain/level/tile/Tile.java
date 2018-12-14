package com.kunnapool.rain.level.tile;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	public int size;
	
	
	public static Tile grass=new GrassTile(Sprite.grass);
	public static Tile voidTile=new Tile(Sprite.voidSprite);
	
	
	
	public Tile(Sprite sprite)
	{
		this.sprite=sprite;
		this.size=sprite.SIZE;
	}
	
	public void render(int x, int y, Screen screen)
	{
		
	}
	
	protected boolean solid()
	{
		return false;
	}

}
