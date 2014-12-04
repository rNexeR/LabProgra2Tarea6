package lab.progra2.prueba;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Plataforma extends Image
{
	int velocidad = 300;
	public Plataforma()
	{
		super(new Texture("tierra.png"));
		this.setX(500);
		this.setY(-200);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		this.setX(this.getX()-delta*velocidad);
	}
}
