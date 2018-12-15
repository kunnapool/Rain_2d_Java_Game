package com.kunnapool.rain.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int pixels[];
	private SpriteSheet sheet;
	
	
	
	public static Sprite grass = new Sprite(16,0,0,SpriteSheet.tiles);
	public static Sprite voidSprite= new Sprite(16, 0x0099ff);
	
	public static Sprite player0= new Sprite(16,0,10,SpriteSheet.tiles);
	public static Sprite player1= new Sprite(16,1,10,SpriteSheet.tiles);
	public static Sprite player2= new Sprite(16,0,11,SpriteSheet.tiles);
	public static Sprite player3= new Sprite(16,1,11,SpriteSheet.tiles);
			
	public Sprite(int size, int colour)
	{
		this.SIZE=size;
		pixels=new int[SIZE*SIZE];
		setColour(colour);
	}
	
	public void setColour(int colour)
	{
		for(int i=0;i<SIZE*SIZE;i++)
			pixels[i]=colour;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		this.SIZE=size;
		this.x=x*size; //location of sprites
		this.y=y*size;
		this.sheet=sheet;
		
		pixels=new int[size*size];
		load();
	}
	
	
	private void load()
	{
		for (int y=0;y<SIZE;y++)
		{
			for (int x=0;x<SIZE;x++)
			{
				/* extract and place sprite */
				pixels[x+y*SIZE]=sheet.pixels[(x+this.x)+(y+this.y)* sheet.SIZE];
			}
		}
	}
	
	
}
