package lab.progra2.prueba;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Enemigo extends PersonajeAnimado
{
	Jugador jugador;
	Enemigo(Jugador jugador, int velx)
	{
		super(loadImages());
		setX(800);
		velocidad_x=velx;
		this.jugador=jugador;
		if (velx == -300){
			setWidth(100);
			setHeight(100);
		}else if (velx == -400){
			setWidth(125);
			setHeight(125);
		}else if (velx == -500){
			setWidth(150);
			setHeight(150);
		}
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
	}
}
