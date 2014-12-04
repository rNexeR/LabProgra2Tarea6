package lab.progra2.prueba;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ClasePrincipal extends ApplicationAdapter implements InputProcessor{
	Stage stage;
	Stage menu;
	Stage over;
	Stage ins;
	int frame = 0;
	
	Enemigo enemy;
	Jugador jugador;
	Boton play;
	Boton instru;
	Boton exit;
	Boton instrucc;
	Image img;
	
	char state;
	
	@Override
	public void create () {		
		stage=new Stage();
		menu = new Stage();
		over = new Stage();
		Gdx.input.setInputProcessor(this);
		
		createMenu();
		
		ins = new Stage();
		instrucc = new Boton(new Texture("Instrucciones.png"));
		instrucc.setSize(650, 480);
		ins.addActor(instrucc);
		
		over.addActor(new Over());
		
		jugador = new Jugador();
		img = new Image(new Texture("bgp.png"));
		img.setSize(650, 480);
		stage.addActor(img);
		stage.addActor(jugador);
		enemy = new Enemigo(jugador);
		stage.addActor(enemy);
		stage.addActor(new Plataforma());
		state = 'M';
	}

	void createMenu(){
		//Boton de PLAY
		play = new Boton(new Texture("btnjugar.png"));
		play.setPosition(400, 240);
		play.setSize(150, 70);
		
		//Boton de INSTRUCCIONES
		instru = new Boton(new Texture("btninstruc.png"));
		instru.setPosition(400, 160);
		instru.setSize(150, 70);
		
		//Boton de SALIDA
		exit = new Boton(new Texture("btnsalir.png"));
		exit.setPosition(400, 80);
		exit.setSize(150, 70);
		
		//IMAGEN DE FONDO
		img = new Image(new Texture("menup.png"));
		img.setSize(650, 480);
		menu.addActor(img);
		
		//RESPLANDOR
		img = new Image(new Texture("destello.png"));
		img.setSize(650, 480);
		menu.addActor(img);
		
		//AGREGAR EL JUGADOR DE MUESTRA
		Jugador m = new Jugador();
		m.setY(120);
		m.piso = 120;
		menu.addActor(m);
		
		//AGREGAR BOMBA DE MUESTRA
		Muestra muestra = new Muestra(2);
		menu.addActor(muestra);
		muestra = new Muestra(-2);
		menu.addActor(muestra);
		
		//Agregando los Botones
		menu.addActor(play);
		menu.addActor(instru);
		menu.addActor(exit);
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0f, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (state == 'P'){
			Gdx.input.setInputProcessor(this);
			stage.draw();
			stage.act();
			if (enemy.colision(jugador)){
				state = 'L';
			}
		}else if (state == 'L'){
			Gdx.input.setInputProcessor(over);
			over.draw();
			over.act();
			if (Gdx.input.isTouched()){
				create();
			}
		}else if(state == 'M'){
			Gdx.input.setInputProcessor(menu);
			menu.draw();
			menu.act();
			if (play.wasTouch())
				state = 'P';
			else if (instru.wasTouch()){
				System.out.println("Instrucciones de Juego");
				state = 'I';
			}
			else if (exit.wasTouch())
				System.exit(0);
		}else if (state == 'I'){
			Gdx.input.setInputProcessor(ins);
			ins.draw();
			ins.act();
			if (instrucc.wasTouch()){
				System.out.println("Termino Instru");
				create();
				state = 'M';
			}
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		jugador.saltar();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		jugador.saltar();
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}








