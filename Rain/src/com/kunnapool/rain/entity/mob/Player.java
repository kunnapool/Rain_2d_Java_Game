package com.kunnapool.rain.entity.mob;

import com.kunnapool.rain.graphics.Screen;
import com.kunnapool.rain.graphics.Sprite;
import com.kunnapool.rain.input.Keyboard;

public class Player extends Mob{
	
	public Keyboard input;
	private Sprite sprite;
	
	private int anim=0;
	private boolean walking=false;
	
	public Player(Keyboard input)
	{
		this.input=input;
	}
	
	
	
	public Player(int x, int y, Keyboard input)
	{
		this.x=x;
		this.y=y;
		this.input=input;
	}
	
	/**
	 * Incrementally updating the player's position
	 * xa and or ya is the change in the value
	 * the move() function then just changes the value
	 * by 1
	 */
	public void update()
	{
		if (anim<7500)
			anim++;
		else
			anim=0;
		
		int xa=0, ya=0;
		if(input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;
		
		if (xa!=0 || ya!=0)
		{
			
			move(xa, ya);
			walking=true;
		}
		else
		{
			walking=false;
		}
			
	}
	
	public void render(Screen screen)
	{
		int flip=0;
		int xx=x-16;
		int yy=y-16;
		
		if(dir==0)
		{
			sprite=sprite.player_forward;
			if (walking) 
			{
				if(anim%20>10)
					sprite=Sprite.player_forward_1;
				else
					sprite=Sprite.player_forward_2;
			}
		}
		if(dir==1)
		{
			sprite=sprite.player_side;
			if (walking) 
			{
				if(anim%20>10)
					sprite=sprite.player_side_1;
				else
					sprite=sprite.player_side_2;
			}
		}
		if(dir==2)
		{
			sprite=sprite.player_back;
			if (walking) 
			{
				if(anim%20>10)
					sprite=sprite.player_back_1;
				else
					sprite=sprite.player_back_2;
			}
		}
		if(dir==3)
		{
			sprite=sprite.player_side;
			if (walking) 
			{
				if(anim%20>10)
					sprite=sprite.player_side_1;
				else
					sprite=sprite.player_side_2;
			}
			flip=1;
		}

			

		
		screen.renderPlayer(xx, yy, sprite, flip);
	}

}
