package lab.progra2.prueba;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Enemigo extends PersonajeAnimado
{
	Jugador jugador;
	Enemigo(Jugador jugador)
	{
		super(loadImages());
		setX(800);
		velocidad_x=-100;
		this.jugador=jugador;
	}
	
	static ArrayList<Image> loadImages()
	{
		ArrayList<Image> images;
		images = new ArrayList<Image>();
		images.add(new Image(new Texture("bomba01.png")));
		images.add(new Image(new Texture("bomba02.png")));
		images.add(new Image(new Texture("bomba03.png")));
		return images;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(colision(jugador))
		{
			System.out.println("Colision!");
		}
	}
}
