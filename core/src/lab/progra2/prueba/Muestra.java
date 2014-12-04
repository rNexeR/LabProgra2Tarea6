package lab.progra2.prueba;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Muestra extends Image{
	int x = 0;
	public Muestra(int x){
		super(new Texture("bomba01.png"));
		this.x = x;
		setSize(50, 50);
		setPosition(5, 0);
	}
	@Override
	public void act(float delta) {
		if (x > 0){
			if (getX() >=650)
				setX(0);
		}else
			if (getX() <=1)
				setX(650);
		
		setX(getX()+x);
		super.act(delta);
	}
}
