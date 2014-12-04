package lab.progra2.prueba;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Boton extends Image{
	private BotonInput btn;
	
	Boton(Texture t){
		super(t);
		btn = new BotonInput(this);
		addListener(btn);
	}
	
	boolean wasTouch(){
		return btn.getTouch();
	}
}
