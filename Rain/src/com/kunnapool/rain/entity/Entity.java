package com.kunnapool.rain.entity;

import java.util.Random;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.level.Level;

public abstract class Entity {
	
	public int x,y;
	private boolean removed = false;
	protected Level level;
	protected final Random random=new Random();
	
	
	public void update(int xa, int ya)
	{
		
		
	}
	
	public void render(Screen screen)
	{
		
	}
	
	public void init(Level level)
	{
		this.level=level;
	}
	
	public void remove()
	{
		removed=true;
	}
	
	public boolean isRemoved()
	{
		return removed;
	}

}
