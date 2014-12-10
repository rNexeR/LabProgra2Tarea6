package lab.progra2.prueba;

import com.badlogic.gdx.graphics.Texture;

public class BotonAnimado extends Boton{
	private int x = 2, y = 2;
	private float px, py;
	
	BotonAnimado(Texture t) {
		super(t);
		px = (float)Math.random()*580;
		py = (float)Math.random()*430;
		System.out.println(px+"-"+py);
		//img.setPosition(px, py);
		setPosition(px, py);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		//System.out.println("moviendo");
		//img.setX(getX()+x);
		setX(getX()+x);
		//img.setY(getY()+y);
		setY(getY()+y);
		if (getY()>430 || getY()<0)
			y = -y;
		if (getX()>580 || getX()<0)
			x = -x;
	}

}
