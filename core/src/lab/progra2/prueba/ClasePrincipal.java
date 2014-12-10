package lab.progra2.prueba;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ClasePrincipal extends ApplicationAdapter implements InputProcessor{
	Stage stage;
	Stage menu;
	Stage win;
	Stage over;
	Stage ins;
	Stage lvls;
	int frame = 0;
	int lvl = 0;
	int jump = 0;
	
	Enemigo enemy;
	Jugador jugador;
	Boton play;
	Boton instru;
	Boton exit;
	Boton instrucc;
	Boton lvl1, lvl2, lvl3;
	Boton pause;
	Image img;
	
	Music mmenu;
	Music mplay;
	
	char state;
	
	@Override
	public void create () {		
		stage=new Stage();
		menu = new Stage();
		win = new Stage();
		over = new Stage();
		lvls = new Stage();
		Gdx.input.setInputProcessor(this);
		
		mplay = Gdx.audio.newMusic(Gdx.files.getFileHandle("play.mp3", FileType.Internal));
		mplay.setLooping(true);
		mplay.stop();
		
		mmenu = Gdx.audio.newMusic(Gdx.files.getFileHandle("menu.mp3", FileType.Internal));
		mmenu.setLooping(true);
		mmenu.play();
		
		createMenu();
		createInstrucciones();
		createGameOver();
		createLvls();
		createWin();
		
		state = 'M';
	}
	
	void createGame(int mov){
		createJugador();
		createEnemigo(mov);
		createPlataforma();
	}
	
	void createLvls(){
		img = new Image(new Texture("lvls.png"));
		img.setSize(650, 480);
		lvls.addActor(img);
		lvl1 = new BotonAnimado(new Texture("lvl1.png"));
		lvls.addActor(lvl1);
		lvl2 = new BotonAnimado(new Texture("lvl2.png"));
		lvls.addActor(lvl2);
		lvl3 = new BotonAnimado(new Texture("lvl3.png"));
		lvls.addActor(lvl3);
	}
	
	void createEnemigo(int mov){
		enemy = new Enemigo(jugador, -mov);
		stage.addActor(enemy);
	}
	
	void createPlataforma(){
		stage.addActor(new Plataforma(0));
		stage.addActor(new Plataforma(534));
	}
	
	void createNewPlataforma(){
		if (lvl == 300){
			stage.addActor(new Plataforma(250));
			stage.addActor(new Plataforma(550));
		}else if (lvl == 400){
			stage.addActor(new Plataforma(500));
			stage.addActor(new Plataforma(600));
		}else if (lvl == 500)
			stage.addActor(new Plataforma(634));
	}
	
	void createJugador(){
		jugador = new Jugador();
		img = new Image(new Texture("bgp.png"));
		img.setSize(650, 480);
		stage.addActor(img);
		stage.addActor(jugador);
	}
	
	void createGameOver(){
		over.addActor(new Over());
	}
	
	void createWin(){
		win.addActor(new Win());
	}
	
	void createInstrucciones(){
		ins = new Stage();
		instrucc = new Boton(new Texture("Instrucciones.png"));
		instrucc.setSize(650, 480);
		ins.addActor(instrucc);
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
		
		if (state == 'P'){//Play
			mmenu.stop();
			mplay.play();
			Gdx.input.setInputProcessor(this);
			stage.draw();
			stage.act();
			if (enemy.getX()<=0){
				createNewPlataforma();
				createEnemigo(lvl);
				jump++;
			}
			if (enemy.colision(jugador)){
				state = 'L';
			}
			if (jump>15){
				state = 'W';
			}
		}else if(state == 'W'){
			Gdx.input.setInputProcessor(win);
			win.draw();
			win.act();
			if (Gdx.input.isTouched()){
				jump = 0;
				mplay.stop();
				create();
			}
		}else if(state == 'V'){
			Gdx.input.setInputProcessor(lvls);
			lvls.draw();
			lvls.act();
			if (lvl1.wasTouch()){
				lvl = 300;
				createGame(lvl);
				state = 'P';
			}else if (lvl2.wasTouch()){
				lvl = 400;
				createGame(lvl);
				state = 'P';
			}else if (lvl3.wasTouch()){
				lvl = 500;
				createGame(lvl);
				state = 'P';
			}
		}else if (state == 'L'){//GameOver
			Gdx.input.setInputProcessor(over);
			over.draw();
			over.act();
			if (Gdx.input.isTouched()){
				mplay.stop();
				create();
			}
		}else if(state == 'M'){//Menu
			mmenu.play();
			mplay.stop();
			Gdx.input.setInputProcessor(menu);
			menu.draw();
			menu.act();
			if (play.wasTouch())
				state = 'V';
			else if (instru.wasTouch()){
				System.out.println("Instrucciones de Juego");
				state = 'I';
			}
			else if (exit.wasTouch())
				System.exit(0);
		}else if (state == 'I'){//Instrucciones
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
		//jugador.saltar();
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








