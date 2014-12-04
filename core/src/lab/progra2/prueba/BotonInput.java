package lab.progra2.prueba;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class BotonInput extends InputListener{
	private boolean touch;
	
	BotonInput(Boton b){
		super();
		touch = false;
	}
	
	boolean getTouch(){
		return touch;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		touch = true;
		return super.touchDown(event, x, y, pointer, button);
	}
}
