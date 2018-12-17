package com.kunnapool.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	
	private String path;
	public final int SIZE;
	
	public int[] pixels;
	
	
	public static SpriteSheet tiles=new SpriteSheet("/textures/sheet.png",256);
	public static SpriteSheet itachi=new SpriteSheet("/textures/itachi.png",256);
	
	
	
	public SpriteSheet(String path, int size)
	{
		this.path=path;
		this.SIZE=size;
		
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	/**
	 * Load sprite sheet from memory
	 */
	private void load()
	{
		try
		{
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			
			int w=image.getWidth();
			int h=image.getHeight();
			
			image.getRGB(0, 0,w,h, pixels, 0, w); //translate image into pixels
			
			
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
