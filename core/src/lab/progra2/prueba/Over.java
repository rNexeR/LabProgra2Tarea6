package lab.progra2.prueba;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Over extends PersonajeAnimado{

	public Over() {
		super(getImages());
		// TODO Auto-generated constructor stub
		piso = 0;
	}

	private static ArrayList<Image> getImages() {
		ArrayList<Image> images;
		Image img;
		images = new ArrayList<Image>();
		img = new Image(new Texture("gameover1.png"));
		img.setSize(650, 480);
		img.setY(0);
		images.add(img);
		
		img = new Image(new Texture("gameover2.png"));
		img.setSize(650, 480);
		img.setY(0);
		images.add(img);
		return images;
	}

}
