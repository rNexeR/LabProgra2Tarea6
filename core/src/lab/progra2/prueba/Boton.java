package lab.progra2.prueba;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Boton extends Image{
	protected BotonInput btn;
	protected Image img;
	
	Boton(Texture t){
		super(t);
		img = new Image(t);
		btn = new BotonInput(this);
		addListener(btn);
	}
	
	boolean wasTouch(){
		return btn.getTouch();
	}
}
