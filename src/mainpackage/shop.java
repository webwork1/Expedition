package mainpackage;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.Random;

public class shop extends BasicGameState{
	
	//random generator
	static Random randomGenerator = new Random();
	
	//star vars
	static double[] starX = new double[80];
	static double[] starY = new double[80];
	
	static double[] starMediumX = new double[30];
	static double[] starMediumY = new double[30];
	
	//moust variables
	int mouseX2;
	int mouseY2;
	
	//images
	Image backarrowIcon;
	
	//coin value
	static double coinTotal;
	
	//engine boost upgrade variables
	static double boostCost = 10;
	static int boostLevel;
	
	public shop(int state){
	}
	
	//fonts
    private UnicodeFont fUnicodeFont;
    private UnicodeFont fUnicodeFont2;

	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
        Font font = new Font("Serif", Font.PLAIN, 20);
        fUnicodeFont = new UnicodeFont(font, 23, true, false);

		fUnicodeFont.getEffects().add(new ColorEffect());
        fUnicodeFont.addAsciiGlyphs();
        fUnicodeFont.loadGlyphs();
        
        Font font2 = new Font("Serif", Font.PLAIN, 20);
        fUnicodeFont2 = new UnicodeFont(font2, 20, true, false);

		fUnicodeFont2.getEffects().add(new ColorEffect());
        fUnicodeFont2.addAsciiGlyphs();
        fUnicodeFont2.loadGlyphs();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg,Graphics g) throws SlickException{	
		
		Color borderColor = new Color(100,100,100);
		Color whiteColor = new Color(200,200,200);
		Color progressColor = new Color(20,173,237);
		Color wordColor = new Color(42,42,42);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 1280, 720);
		
		for(int x =0; x < starX.length; x++){
			g.setColor(Color.white);
			g.fillRect((int) starX[x],(int) starY[x], 1, 1);
		}
		
		for(int x =0; x < starMediumX.length; x++){
			g.setColor(Color.white);
			g.fillRect((int) starMediumX[x],(int) starMediumY[x], 3, 3);
		}
		
		fUnicodeFont.drawString(580, 30, "Coins : " + (int) coinTotal,Color.white);	
		
		//drawing column descriptions
		fUnicodeFont.drawString(250, 100, "Upgrade",Color.black);	
		
		//DRAWING COLUMN STUFF
		for(int x = 0; x < 5; x++){
			
			//TOP COLUMN STUFF
			
			g.setColor(borderColor);
			g.fillRect(235, 65, 160, 60);
			g.fillRect(445, 65, 260, 60);
			g.fillRect(743, 65, 80, 60);
			g.fillRect(860, 65, 70, 60);
			g.fillRect(965, 65, 70, 60);	
			
			g.setColor(whiteColor);
			g.fillRect(240, 70, 150, 50);
			g.fillRect(450, 70, 250, 50);
			g.fillRect(748, 70, 70, 50);
			g.fillRect(865, 70, 60, 50);
			g.fillRect(970, 70, 60, 50);		
			
			//first column stuff
			g.setColor(borderColor);
			g.fillRect(230, 135 + 100*x, 170, 80);
			
			g.setColor(whiteColor);
			g.fillRect(240, 145 + 100*x, 150, 60);
			
			
			//second column stuff
			g.setColor(borderColor);
			g.fillRect(440, 135 + 100*x, 270, 80);
			
			g.setColor(whiteColor);
			g.fillRect(450, 145 + 100*x, 250, 60);
			
			if(coinTotal < boostCost){
			g.setColor(progressColor);
			g.fillRect(450, 145, (int)((coinTotal/boostCost)*250), 60);
			}else{
				g.setColor(progressColor);
				g.fillRect(450, 145,250, 60);	
			}

			//third column stuff
			g.setColor(borderColor);
			g.fillRect(738, 135 + 100*x, 90, 80);
			
			g.setColor(whiteColor);
			g.fillRect(748, 145 + 100*x, 70, 60);
			
			//fourth column stuff
			g.setColor(borderColor);
			g.fillRect(855, 135 + 100*x, 80, 80);
			
			g.setColor(whiteColor);
			g.fillRect(865, 145 + 100*x, 60, 60);
			
			//fifth column stuff
			g.setColor(borderColor);
			g.fillRect(960, 135 + 100*x, 80, 80);
			g.setColor(whiteColor);
			g.fillRect(970, 145 + 100*x, 60, 60);
		}
		
		
		//TOP ROW WORDS
		fUnicodeFont.drawString(265, 85, "Upgrades", wordColor);	
		fUnicodeFont.drawString(529, 85, "Progress", wordColor);
		fUnicodeFont.drawString(759, 85, "Cost", wordColor);
		fUnicodeFont2.drawString(872, 86, "Level", wordColor);
		fUnicodeFont.drawString(980, 85, "Buy", wordColor);
		
		//1st column words
		fUnicodeFont.drawString(250, 162, "Engine Boost",Color.black);		
		
		//3rd column words
		
		fUnicodeFont.drawString(755, 162,(int) boostCost + "",Color.black);		
		
		//4th column words;
		fUnicodeFont.drawString(887, 162,"" + boostLevel,Color.black);		
		
		//5th column stuff
		if(coinTotal >= boostCost){
		g.setColor(Color.green);
		g.fillRect(970, 145, 60, 60);
		}
		
		//drawing back arrow
		Color settingsBack = new Color(215,215,215);
		g.setColor(settingsBack);		
		
		g.fillRect(20, 650, 50, 50);
		
		backarrowIcon = new Image("res/backarrowIcon.png");
		g.drawImage(backarrowIcon,20, 650);
		
		g.setColor(Color.white);
		g.drawString("" + startup.getWorldUnitsX(mouseX2) , 50, 50);
		g.drawString("" + startup.getWorldUnitsY(mouseY2) , 50, 100);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = gc.getHeight()-Mouse.getY();		
		
		mouseX2 = mouseX;
		mouseY2 = mouseY;
		
		//Going back to play state
		if(mouseX >= startup.toPixelsX(4) && mouseX <= startup.toPixelsX(14)){
			if(mouseY >= startup.toPixelsY(226) && mouseY <= startup.toPixelsY(244)){
				if(input.isMouseButtonDown(0)){
					play.settingPlayVariables((int) coinTotal);
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
		return 2;
	}
	
	public static void setShopValues(int coins){
		
		//drawing stars
		for(int x = 0; x < starX.length; x++){
		starX[x] = play.starX[x];
		starY[x] = play.starY[x];
		}
		
		for(int x = 0; x < starMediumX.length; x++){
		starMediumX[x] = play.starMediumX[x];
		starMediumY[x] = play.starMediumY[x];
		}
		
		//setting coin value
		coinTotal = coins;
	}
}