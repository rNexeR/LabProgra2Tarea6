package lab.progra2.prueba;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PersonajeAnimado extends Actor
{
	ArrayList<Image> images;
	int dibujo_actual;
	float velocidad_x;
	float velocidad_y;
	float aceleracion_x;
	float aceleracion_y;
	float frame;
	float velocidad_animacion;
	int piso;

	public PersonajeAnimado(ArrayList<Image>images)
	{
		super();
		this.images = images;
		dibujo_actual=0;
		velocidad_x=0;
		velocidad_y=0;
		aceleracion_x=0;
		aceleracion_y=0;
		frame=0;
		velocidad_animacion=0.1f;
		piso=70;
		setWidth(images.get(0).getWidth());
		setHeight(images.get(0).getHeight());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		frame+=delta;
		if(frame>=velocidad_animacion)
		{
			dibujo_actual++;
			if(dibujo_actual>=images.size())
				dibujo_actual=0;
			frame=0;
		}
		velocidad_x+=aceleracion_x;
		velocidad_y+=aceleracion_y;
		moveBy(velocidad_x*delta, velocidad_y*delta);
		if(getY()<=piso)
			setY(piso);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		images.get(dibujo_actual).setPosition(getX(), getY());
		images.get(dibujo_actual).draw(batch, parentAlpha);
	}
	
	boolean colision(PersonajeAnimado otro_personaje)
	{
		Rectangle r1 = new Rectangle(getX()+getWidth()/4,
							getY()+getHeight()/4, 
							getWidth()/2,
							getHeight()/2);
		Rectangle r2 = new Rectangle(otro_personaje.getX()+otro_personaje.getWidth()/4, 
						otro_personaje.getY()+otro_personaje.getHeight()/4,
						otro_personaje.getWidth()/2,
						otro_personaje.getHeight()/2);
		return r1.overlaps(r2);
	}
}




