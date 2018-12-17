package com.kunnapool.rain.level.tile;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.graphics.Sprite;
import com.kunnapool.rain.level.tile.spawn_level.SpawnFloorTile;
import com.kunnapool.rain.level.tile.spawn_level.SpawnGrassTile;
import com.kunnapool.rain.level.tile.spawn_level.SpawnHedgeTile;
import com.kunnapool.rain.level.tile.spawn_level.SpawnWallTile;
import com.kunnapool.rain.level.tile.spawn_level.SpawnWaterTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	public int size;
	
	
	public static Tile grass=new GrassTile(Sprite.grass);
	public static Tile flower=new FlowerTile(Sprite.flower);
	public static Tile rock=new RockTile(Sprite.rock);
	public static Tile voidTile=new Tile(Sprite.voidSprite);
	
	public static Tile spawn_grass=new SpawnGrassTile(Sprite.spwan_grass);
	public static Tile spawn_hedge=new SpawnHedgeTile(Sprite.spwan_hedge);
	public static Tile spawn_water=new SpawnWaterTile(Sprite.spwan_water);
	public static Tile spawn_wall1=new SpawnWallTile(Sprite.spwan_wall1);
	public static Tile spawn_wall2=new SpawnWallTile(Sprite.spwan_wall2);
	public static Tile spawn_floor=new SpawnFloorTile(Sprite.spwan_floor);
	
	
	public final static int col_spawn_grass=0xff00ff00;
	public final static int col_spawn_hedge=0;
	public final static int col_spawn_water=0;
	public final static int col_spawn_wall1=0xff808080;
	public final static int col_spawn_wall2=0xff303030;
	public final static int col_spawn_floor=0xff724715;
	
	
	
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
	
	public boolean solid()
	{
		return false;
	}

}
