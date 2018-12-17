package com.kunnapool.rain.level.tile;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite)
	{
		super(sprite);
	}
	
	
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x<<4, y<<4, this);
	}
	
	
	protected boolean solid()
	{
		return true;
	}
}
