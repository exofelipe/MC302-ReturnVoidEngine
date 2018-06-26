package br.com.returnvoid.returnengine.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	private HashMap<String, BufferedImage> cache = new HashMap<String, BufferedImage>();
	private static ImageLoader instance;
	private ImageLoader() {
	}
	public BufferedImage load(String image) {
		if(cache.containsKey(image)) {
			return cache.get(image);
		}
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource("/resources/"+image));
		    cache.put(image, img);
		} catch (IOException e) {
			System.err.println("Coudln");
		}
		return img;
	}
	public static ImageLoader getInstance() {
		if(instance == null)
			instance = new ImageLoader();
		return instance;
	}
}
