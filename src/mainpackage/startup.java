package mainpackage;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class startup extends StateBasedGame{
	
	public startup(String gamename) {
		super(gamename);
		this.addState(new menu(menu));
		this.addState(new play(play));
		this.addState(new shop(shop));

	}
	
	private static AppGameContainer appgc;
	
	static float sizeWidth = 1280;
	static float sizeHeight = 720;
	public static final String gamename = "Expedition";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int shop = 2;
	public static final int DEFAULT_WIDTH = 1280;
	public static final int DEFAULT_HEIGHT = 720;

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(shop).init(gc, this);
		this.enterState(menu);
		
	}
	
	public static void main(String[] args) throws LWJGLException{
		try{
			appgc = new AppGameContainer(new ScalableGame(new startup(gamename), DEFAULT_WIDTH, DEFAULT_HEIGHT, false));
			appgc.setDisplayMode(1280, 720, false);
			appgc.setShowFPS(true);
			appgc.setTargetFrameRate(60);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
		
	}
	
	public static void resize(int width, int height, boolean fullScreen){
		try{
			sizeWidth = width;
			sizeHeight = height;
		appgc.setDisplayMode(width, height, fullScreen);
		}catch(SlickException e){
			
		}
	}
	
	public static float toPixelsX(float worldUnits){
		
		return (sizeWidth/250)*worldUnits;
	}
	
	public static float toPixelsY(float worldUnits){
		
		return (sizeHeight/250)*worldUnits;
	}
	
	public static float getWorldUnitsX(int x){
		
		return (250*x)/sizeWidth;
	}
	
	public static float getWorldUnitsY(int x){
		
		return (250*x)/sizeHeight;
	}
}
