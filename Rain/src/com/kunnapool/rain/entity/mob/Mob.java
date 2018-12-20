package com.kunnapool.rain.entity.mob;

import com.kunnapool.rain.entity.Entity;
import com.kunnapool.rain.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving=false;
	
	
	/**
	 * Moves by 1
	 * @param xa change in x
	 * @param ya change in y
	 */
	public void move(int xa, int ya)
	{
		if(xa!=0 && ya!=0)
		{
			move(xa, 0);
			move(0, ya);
			return;
		}
		
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
		boolean solid = false;
		for(int c=0;c<4;c++)
		{
			int xt = ((x+xa) +c % 2 * 12 - 7 )>>4;
			int yt = ((y+ya) +c / 2 * 12 + 3 )>>4;
			if ( level.getTile( xt, yt).solid() ) solid = true;

		}
		return solid;
	}
	
	public void render()
	{
		
	}
	

}
