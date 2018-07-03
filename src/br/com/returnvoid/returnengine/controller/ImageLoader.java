package br.com.returnvoid.returnengine.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
/*
 * Essa classe é responsável por carregar imagens dos recursos e mantê-las
 * em cache, no exemplo foi usada apenas no EasterEGG, já que era a unica
 * entidade que utiliza imagens durante a execução
 * 
 * Salvando as imagens em cache ocorre uma diminuição do uso de memória,
 * já que, no caso de várias entidades usarem a mesma imagem, haveria apenas
 * um carregamento a partir do disco e uma instância de BufferedImage
 * contendo a imagem.
 * 
 * Nota: essa classe é um Singleton
 */
public class ImageLoader {
	// HashMap para o cache
	private HashMap<String, BufferedImage> cache = new HashMap<String, BufferedImage>();
	// Instancia do Singleton
	private static ImageLoader instance;
	private ImageLoader() {
	}
	
	public BufferedImage load(String image) {
		// Se estiver em cache é mais facil
		if(cache.containsKey(image)) {
			return cache.get(image);
		}
		// Caso contrário carregue do disco
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource("/resources/"+image));
		    cache.put(image, img);
		} catch (IOException e) {
			System.err.println("Não foi possível carregar imagem do disco");
		}
		return img;
	}
	public static ImageLoader getInstance() {
		if(instance == null)
			instance = new ImageLoader();
		return instance;
	}
}
