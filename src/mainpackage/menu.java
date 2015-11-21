package mainpackage;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.Random;
public class menu extends BasicGameState{
	
	int mouseX2;
	int mouseY2;
	
	int[] starX = new int[100];
	int[] starY = new int[100];
	boolean menuStart = false;
	Random randomGenerator = new Random();
	
	public menu(int state){
	}
    private UnicodeFont fUnicodeFont;

	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Font font = new Font("Serif", Font.PLAIN, 20);
        fUnicodeFont = new UnicodeFont(font, 23, true, false);

		fUnicodeFont.getEffects().add(new ColorEffect());
        fUnicodeFont.addAsciiGlyphs();
        fUnicodeFont.loadGlyphs();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg,Graphics g) throws SlickException{		
		Color tileColor = new Color(30,248,117);
		
		for(int x =0; x < 100; x++){
			if(starX[x] < 0 || starY[x] < - 5){
				if(menuStart == false){
				starX[x] = randomGenerator.nextInt(1300);
				starY[x] = randomGenerator.nextInt(1200);
				if(x == 99){
					menuStart = true;
				}
				}else{
				starX[x] = 1250 + randomGenerator.nextInt(100);
				starY[x] = 0 + randomGenerator.nextInt(2000);
				}
				 
			}else{
				starX[x] -=2;
				starY[x] -=1;
			}
			g.setColor(Color.white);
			g.fillRect(starX[x], starY[x], 2, 2);
		}
		
		g.setColor(tileColor);	
		g.fillRect(565, 200, 150, 75);
		g.fillRect(565, 350, 150, 75);
		
		fUnicodeFont.drawString(580, 230, "New Game",Color.black);
		fUnicodeFont.drawString(580, 380, "Load Game",Color.black);
		
		g.drawString("" + startup.getWorldUnitsX(mouseX2) , 50, 50);
		g.drawString("" + startup.getWorldUnitsY(mouseY2) , 50, 100);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = gc.getHeight()-Mouse.getY();		
		
		mouseX2 = mouseX;
		mouseY2 = mouseY;
		if(mouseX >= startup.toPixelsX(110) && mouseX <= startup.toPixelsX(140)){
			if(mouseY >= startup.toPixelsY(70) && mouseY <= startup.toPixelsY(97)){
				if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			gc.exit();
		}
		
		if(input.isKeyPressed(Input.KEY_R)){
			if(gc.getHeight() == 720 && gc.getWidth() == 1280){
				startup.resize(960, 540, false);
			}else if(gc.getWidth() >930 && gc.getWidth() < 961){
				startup.resize(640, 360, false);
			}else if(gc.getWidth() > 600 && gc.getWidth() < 650){
				startup.resize(gc.getScreenWidth(), gc.getScreenHeight(), true);
			}else if(gc.getWidth() == gc.getScreenWidth()){
				startup.resize(1280, 720, false);
			}
		}
		
	}
	
	
	public int getID(){
		return 0;
	}
}
