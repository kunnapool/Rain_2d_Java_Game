package com.kunnapool.rain.entity.mob;

import com.kunnapool.rain.entity.Entity;
import com.kunnapool.rain.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving=false;
	
	
	/**
	 * Changes how to mob moves by 1
	 * @param xa change in x
	 * @param ya change in y
	 */
	public void move(int xa, int ya)
	{
		/* Direction */
		if (xa>0)
			dir=1; //east
		else if (xa<0)
			dir=3; //west
		else if (ya>0)
			dir=2; //south
		else if (ya<0)
			dir=0; //north
			
		if(!collision(xa, ya))
		{
			x+=xa;
			y+=ya;
		}

	}
	
	public void update()
	{
		
	}
	
	private boolean collision(int xa, int ya)
	{
		return level.getTile( (x+xa)>>4, (y+ya)>>4).solid();
	}
	
	public void render()
	{
		
	}
	

}
