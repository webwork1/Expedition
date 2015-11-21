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
public class play extends BasicGameState{
	public play(int state){
	}
    private UnicodeFont fUnicodeFont;
	
	static Random randomGenerator = new Random();
	
	int mouseX2;
	int mouseY2;
	
	static double[] starX = new double[100];
	static double[] starY = new double[100];
	
	//EXAUST VARIABLES
	static double[] exhaustX = new double[100];
	static double[] exhaustY = new double[100];
	static boolean[] exaustOn = new boolean[100];
	static int[] exaustTime = new int[100];
	static boolean playStart = false;
	
	static boolean[] exaustStation = new boolean[exhaustX.length];
	static double[] exaustStationX = new double[exhaustX.length];
	static double[] exaustStationY = new double[exhaustX.length];
	static int[] exaustStationDegreeX = new int[exhaustX.length];
	static int[] exaustStationDegreeY = new int[exhaustX.length];
	
	//is space bar pressed?
	static boolean spacebar = false;
	static double shipSpeed = 0;
	static int shipMaxSpeed = 20;
	
	//IMAGES
    Image ship;
    Image plusButton;
    Image minusButton;   
    Image velocityScale;
    Image velocityScaleFront;
    
	//map scale
	static int mapScale = 2;
	
    //cordinate system:
    static double cordinateX = 0;
    static double cordinateY = 0;
    
    //finding real rotation
    int shipRotation = 360;
    double shipRealRotationX = 0;
    static double shipRealRotation = 0;
    
    //startup script
    static boolean begin = false;
    static boolean generateSizes = true;
    
    //planet coordinates
    
    //MAKE LESS THAN X TO NOT HAVE FLICKER
    static double[] planetX = new double[25];
    static double[] planetY = new double[planetX.length];
    static double[] planetColorG = new double[planetX.length];
    static double[] planetColorR = new double[planetX.length];
    static double[] planetColorB = new double[planetX.length];
    static double[] planetMass = new double[planetX.length];
    static double[] planetSize = new double[planetX.length];
    static double[] planetMiddleX = new double[planetX.length];
    static double[] planetMiddleY = new double[planetX.length];
    static boolean[] isPlanetOrbiting = new boolean[planetX.length];
    
    static int planetCounter = 0;
    
    //moon coordinates
    static double[] moonX = new double[planetX.length];
    static double[] moonY = new double[moonX.length];
    static double[] moonColorG = new double[moonX.length];
    static double[] moonColorR = new double[moonX.length];
    static double[] moonColorB = new double[moonX.length];
    static double[] moonMass = new double[moonX.length];
    static double[] moonSize = new double[moonX.length];
    static double[] moonMiddleX = new double[moonX.length];
    static double[] moonMiddleY = new double[moonX.length];
    static double[] moonOrbitingDistance =new double[moonX.length];
    static double[] moonVelocity = new double[moonX.length];
    static int[] moonPlanetNumber = new int[moonX.length];
    static double[] moonRotation = new double[moonX.length];
    static int moonCounter = 0;
    
    //sun coordinates
    static double[] sunX = new double[planetX.length];
    static double[] sunY = new double[sunX.length];
    static double[] sunColorG = new double[sunX.length];
    static double[] sunColorR = new double[sunX.length];
    static double[] sunColorB = new double[sunX.length];
    static double[] sunMass = new double[sunX.length];
    static double[] sunSize = new double[sunX.length];
    static double[] sunMiddleX = new double[sunX.length];
    static double[] sunMiddleY = new double[sunX.length];
    
    static double[] planetOrbitingDistance =new double[moonX.length];
    static double[]	planetVelocity = new double[moonX.length];
    static int[] planetSunNumber = new int[moonX.length];
    static double[] planetRotation = new double[moonX.length];
    
    static int sunCounter = 0;
    
    //MUST BE >= 2
    static int sunAmount = 2;
    
    //NET FORCE ON SHIP
    static double shipNetForceX = 0;
    static double shipNetForceY = 0;
    
    static //SHIP X CONSTANT
    int shipXConstant = 623;
    static int shipYConstant = 340;
    
     //MINIMAP VARIABLES
    int[] miniMapPlanetX = new int[planetX.length];
    int[] miniMapPlanetY = new int[planetX.length];
    
    int[] miniMapMoonX = new int[planetX.length];
    int[] miniMapMoonY = new int[planetX.length];
    
    int[] miniMapSunX = new int[sunX.length];
    int[] miniMapSunY = new int[sunX.length];
    
     //planet counter
    static int objectCounter = 0;
    static boolean objectSuccess = false;
    
    //planet spacing
    static int objectSpacing = 800;
    
    //setting variables
    static boolean settingVariables = true;
    
     //area of level;
    static int areaWidth = 10000;
    static int areaHeigth = 10000;
    
	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Font font = new Font("Serif", Font.PLAIN, 20);
        fUnicodeFont = new UnicodeFont(font, 25, true, false);

		fUnicodeFont.getEffects().add(new ColorEffect());
        fUnicodeFont.addAsciiGlyphs();
        fUnicodeFont.loadGlyphs();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg,Graphics g) throws SlickException{
		
		//SETTING VARIABLES
		
		if(settingVariables){
	    for(int x = 0; x < planetX.length; x++){
	        isPlanetOrbiting[x] = false;
	        }
	    settingVariables = false;
		}
		
		//CREATING PLANETS && LOADING SCREEN

		if(begin == false){
			
			if(generateSizes){
			generateSizes();
			generateSizes = false;
			}
			g.setColor(Color.gray);
			g.fillRect(465, 335, 350, 50);
			g.setColor(Color.green);
			
		   // drawing green progress bar
		    g.fillRect(465, 335, (350*objectCounter)/planetX.length, 50);
			
		    //drawing percentage
		    g.setColor(Color.black);
		    g.drawString((100*objectCounter)/planetX.length + "%", 600, 346);
		    
		    //planet creator
		    if(objectCounter == planetX.length-1){
		    	begin = true;
		    }else{
		    	if(objectSuccess){
		    		objectSuccess = false;
		    		objectCounter++;
		    	}
		    	generatePlanets(objectCounter);
		    }
		}
		
		if(begin){
			
			g.setColor(Color.black);
			g.fillRect(0, 0, 1280, 720);
			//border color
			Color borderColor = new Color(166,166,166);
			
			//setting real rotation
			if(shipRotation < 0){
			shipRealRotationX = 360 - (Math.abs(shipRotation - ((int) Math.floor((shipRotation/360))*360)));
			}else{
			shipRealRotationX = (Math.abs(shipRotation - ((int) Math.floor((shipRotation/360))*360)));
			}
			
			if(shipRealRotationX > 0 && shipRealRotationX <= 90){
				shipRealRotation = 90 - shipRealRotationX;
			}
			if(shipRealRotationX > 90 && shipRealRotation < 360){
				shipRealRotation = 450 - shipRealRotationX;
			}
			
			//DRAWING STARS
			drawingStars();
			g.setColor(Color.white);
					
			for(int x = 0; x < starX.length; x++){
			g.fillRect((int) starX[x],(int) starY[x], 2, 2);
			}
			
			//DRAWING EXAUST
			exaust();
			for(int x = 0; x < exhaustX.length;x++){
			g.setColor(Color.yellow);
			if(exaustTime[x] > 0){
			g.fillRect((int)exhaustX[x],(int) exhaustY[x], 2, 2);
			}
			}
			//CORDINATE SYSTEM
			
			if(shipRealRotation >= 90 && shipRealRotation <= 270){
				cordinateX -= Math.abs(shipSpeed*Math.cos(Math.toRadians((shipRealRotation))));
			}else{
				cordinateX += Math.abs(shipSpeed*Math.cos(Math.toRadians((shipRealRotation))));
			}
			if(shipRealRotation >= 180 && shipRealRotation < 360){
				cordinateY -= Math.abs(shipSpeed*Math.sin(Math.toRadians((shipRealRotation))));
			}else{
				cordinateY += Math.abs(shipSpeed*Math.sin(Math.toRadians((shipRealRotation))));
			}
			//DRAWING PLANET 
			for(int x = 0; x < planetCounter; x++){
			Color tileColor = new Color((int) planetColorR[x],(int) planetColorG[x],(int) planetColorB[x]);
			g.setColor(tileColor);
			g.fillOval((float)systemX((int) planetX[x]), (float) systemY((int) planetY[x]), (float) planetSize[x],(float)  planetSize[x]);
			}
			
			//DRAWING MOON
			for(int x = 0; x < moonCounter; x++){
				if(moonX[x] != 0){
			Color moonColor = new Color((int) moonColorR[x],(int) moonColorG[x],(int) moonColorB[x]);
			g.setColor(moonColor);
			g.fillOval((float)systemX((int) moonX[x]), (float) systemY((int) moonY[x]), (float) moonSize[x],(float)  moonSize[x]);
				}
			}
			
			//DRAWING SUN 
			for(int x = 0; x < sunCounter; x++){
			Color sunColor = new Color((int) sunColorR[x],(int) sunColorG[x],(int) sunColorB[x]);
			g.setColor(sunColor);
			g.fillOval((float)systemX((int) sunX[x]), (float) systemY((int) sunY[x]), (float) sunSize[x],(float)  sunSize[x]);
			}
			
			//UPDATING PLANETS
			updatingPlanets();
			
			//MINI-MAP BACKGROUND
			g.setColor(Color.green);
			g.setColor(Color.black);
			g.fillRect(1060, 560, 210, 160);
			
			//Map Scale Differences
			int totalX = 0;
			int totalY = 0;
		
		//Because mapScale changes values..
		switch(mapScale){
		case 1:
			totalX = -11;
			break;
		case 2:
			totalX = 41;
			totalY = 43;
			break;
		case 3:
			totalX = 57;
			totalY = 57;
			break;
		case 4:
			totalX = 66;
			totalY = 59;
			break;			
		case 5:
			totalX = 71;
			totalY = 63;
			break;
		}		
		
		for(int x = 0; x < planetCounter; x ++){
			
			Color tileColor = new Color((int) planetColorR[x],(int) planetColorG[x],(int) planetColorB[x]);
			g.setColor(tileColor);
			
		miniMapPlanetX[x] = (int) ((int) 1070 + totalX + ((systemX( + (int) (planetX[x]))*(210))/1280/mapScale));
		miniMapPlanetY[x] = (int) ((int)  (560 +  totalY +((float) ((systemY((int) planetY[x]))*160)/720)/mapScale));
		
		//DRAWING MINI-MAP PLANETS
		if(miniMapPlanetX[x] > 1050 && miniMapPlanetX[x] < 1270){
			if(miniMapPlanetY[x] > 550 && miniMapPlanetY[x] < 710){
		g.fillOval(miniMapPlanetX[x], miniMapPlanetY[x], (float) (planetSize[x]*210)/1280/mapScale,(float) (planetSize[x]*210)/1280/mapScale);
			}
		}
		}
		
		
		for(int x = 0; x < moonCounter; x ++){
			
			Color moonColor = new Color((int) moonColorR[x],(int) moonColorG[x],(int) moonColorB[x]);
			g.setColor(moonColor);
			
			miniMapMoonX[x] = (int) ((int) 1070 + totalX + ((systemX( + (int) (moonX[x]))*(210))/1280/mapScale));
			miniMapMoonY[x] = (int) ((int)  (560 +  totalY +((float) ((systemY((int) moonY[x]))*160)/720)/mapScale));
			
			
		//DRAWING MINI-MAP MOONS
		if(miniMapMoonX[x] > 1050 && miniMapMoonX[x] < 1270){
			if(miniMapMoonY[x] > 550 && miniMapMoonY[x] < 710){
		g.fillOval(miniMapMoonX[x], miniMapMoonY[x], (float) (moonSize[x]*210)/1280/mapScale,(float) (moonSize[x]*210)/1280/mapScale);
			}
		}
		}
		
		for(int x = 0; x < sunCounter; x ++){
			
			Color sunColor = new Color((int) sunColorR[x],(int) sunColorG[x],(int) sunColorB[x]);
			g.setColor(sunColor);
			
		miniMapSunX[x] = (int) ((int) 1070 + totalX + ((systemX( + (int) (sunX[x]))*(210))/1280/mapScale));
		miniMapSunY[x] = (int) ((int)  (560 +  totalY +((float) ((systemY((int) sunY[x]))*160)/720)/mapScale));
		
		//DRAWING MINI-MAP PLANETS
		if(miniMapSunX[x] > 1050 && miniMapSunX[x] < 1270){
			if(miniMapSunY[x] > 550 && miniMapSunY[x] < 710){
		g.fillOval(miniMapSunX[x], miniMapSunY[x], (float) (sunSize[x]*210)/1280/mapScale,(float) (sunSize[x]*210)/1280/mapScale);
			}
		}
		}
		
		//MINIMAP DOT
		g.setColor(Color.red);
		g.fillRect(1160, 635, 3, 3);
		
		//MINI-MAP COLOR
		g.setColor(borderColor);	
		g.fillRect(1270, 550, 10, 160);
		g.fillRect(1050, 710, 230, 10);
		g.fillRect(1050, 550, 10, 160);
		g.fillRect(1050, 550, 230, 10);
		
		
		//GRAVITY EFFECT ON CORDINATES
		cordinateX +=shipNetForceX;
		cordinateY +=shipNetForceY;
		
		//FRICTION OF SHIP
		if(shipSpeed > 0){
		shipSpeed -= .003;
		}
		
		ship = new Image("res/ship.png");
		velocityScale = new Image("res/velocityScale.png");
		velocityScaleFront = new Image("res/velocityScaleFront.png");
		ship.setRotation(shipRotation);
		g.drawImage(ship, 600, 300);
		
		//drawing velocity scale
		g.drawImage(velocityScale, 1051, 480);
		
		Color velocityColor = new Color(40,(int) 221,57);
		g.setColor(velocityColor);
		g.fillRect(1051, 480,(float) ((float) (shipSpeed*1280)/shipMaxSpeed/5.6), 60);
		
		g.drawImage(velocityScaleFront, 1051, 480);
		
		//testing mouse input
		//fUnicodeFont.drawString(590, 155, "test",Color.black);
		g.setColor(Color.white);
		g.drawString("" + startup.getWorldUnitsX(mouseX2) , 50, 50);
		g.drawString("" + startup.getWorldUnitsY(mouseY2) , 50, 100);
		
		//drawing map arrows
		plusButton = new Image("res/plusButton.png");
		minusButton = new Image("res/minusButton.png");
		g.drawImage(plusButton,1015, 580);
		g.drawImage(minusButton,1015, 640);
		
		//DRAWING CORDS
		g.setColor(Color.orange);
		g.drawString("x : " + (int) cordinateX + "y : " + (int) cordinateY, 300, 50);
		
		//DRAWING BORDER OF LEVEL
		
		Color levelColor = new Color(240,230,71,128);
		g.setColor(levelColor);
		
		/*g.fillRect(systemX((-areaWidth/2) - 1500), systemY((-areaHeigth/2) - 1500), 1000, areaHeigth + 2999);
		g.fillRect(systemX((areaWidth/2) + 1500), systemY((-areaHeigth/2) - 1500), 1000, areaHeigth + 2999);
		g.fillRect(systemX((-areaWidth/2)-1500), systemY((areaHeigth/2) + 1500), areaWidth + 3999, 1000);
		g.fillRect(systemX((-areaWidth/2) - 499) , systemY((-areaHeigth/2) - 1500), areaWidth + 1998, 1000);
		*/
		}

	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = gc.getHeight()-Mouse.getY();
		
		mouseY2 = mouseY;
		mouseX2 = mouseX;
		
		//MINIMAP ZOOM
		if(mouseX >= startup.toPixelsX(198) && mouseX <= startup.toPixelsX(204)){
			if(mouseY >= startup.toPixelsY(203) && mouseY <= startup.toPixelsY(212)){
				if(input.isMousePressed(0)){
					if(mapScale > 1){
				mapScale--;
					}
				}
			}
		}
		
		//MINIMAP ZOOM
		if(mouseX >= startup.toPixelsX(198) && mouseX <= startup.toPixelsX(204)){
			if(mouseY >= startup.toPixelsY(223) && mouseY <= startup.toPixelsY(233)){
				if(input.isMousePressed(0)){
					if(mapScale < 5){
				mapScale++;
					}
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			gc.exit();
		}
		
		//ROTATING SHIP
		
		if(input.isKeyDown(Input.KEY_A)|| input.isKeyDown(Input.KEY_LEFT)){
			shipRotation-=3;
		}
		
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
			shipRotation+=3;
		}
		
		//making ship move
		if(input.isKeyDown(Input.KEY_SPACE)){
			if(shipSpeed < shipMaxSpeed){
			shipSpeed+=.09;
			spacebar = true;
			}
		}
		
		if(input.isKeyDown(Input.KEY_W)){
			if(shipSpeed > 0){
			shipSpeed -=.08;
			}
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
	
	//CORDINATE SYSTEM
	public static int systemX(int x){
		
		x-= cordinateX;
		return x;
	}
	
	public static int systemY(int y){
		y+= cordinateY;
		return y;
	}
	
	public int getID(){
		return 1;
	}
	
	public static void updatingPlanets(){
		
		//UPDATING SUNS
		for(int x = 0; x < sunX.length; x ++){
			if(sunX[x] >  shipXConstant - 2000 && sunX[x] < shipXConstant + 2000 && sunY[x] > shipYConstant + 2000 && sunY[x] < shipYConstant -2000){
				
			}else{
			
			double v = 0;
			double y = 0;
			double r = 0;
			
			double a = 0;
			double b = 0;
			double c = 0;
			if(x == 0){
				shipNetForceX = 0;
				shipNetForceY  = 0;
				v = 0;
				y = 0;
				r = 0;
				a = 0;
				b = 0;
				c = 0;
			}
			
			sunMiddleX[x] = systemX((int) sunX[x]) + (sunSize[x]/2);
			sunMiddleY[x] = (float) systemY((int) sunY[x]) + (float) (sunSize[x]/2);
			
			//600 Because ship is not actually moving, just rotating!
			//X GRAVITY
			if(sunMiddleX[x] > shipXConstant && sunMiddleY[x] > shipYConstant){
				v = (Math.sqrt(((sunMiddleX[x] - shipXConstant)*(sunMiddleX[x] - shipXConstant)) + ((sunMiddleY[x] - shipYConstant)*(sunMiddleY[x] - shipYConstant))));
				y = (sunMiddleX[x] - shipXConstant);
				r = ((v)/(sunSize[x]))*3;
				shipNetForceX += ((sunMass[x]*(y/v))/(r*r));
			}
			else if(sunMiddleX[x] > shipXConstant && sunMiddleY[x] < shipYConstant){
				v = (Math.sqrt(((sunMiddleX[x] - shipXConstant)*(sunMiddleX[x] - shipXConstant)) + ((sunMiddleY[x] - shipYConstant)*(sunMiddleY[x] - shipYConstant))));
				y = (sunMiddleX[x] - shipXConstant);
				r = ((v)/(sunSize[x]))*3;
				shipNetForceX += ((sunMass[x]*(y/v))/(r*r));
			}
			else if(sunMiddleX[x] < shipXConstant && sunMiddleY[x] > shipYConstant){
				v = (Math.sqrt(((sunMiddleX[x] - shipXConstant)*(sunMiddleX[x] - shipXConstant)) + ((sunMiddleY[x] - shipYConstant)*(sunMiddleY[x] - shipYConstant))));
				y = (sunMiddleX[x] - shipXConstant);
				r = ((v)/(sunSize[x]))*3;
				shipNetForceX += ((sunMass[x]*(y/v))/(r*r));
			}
			else if(sunMiddleX[x] < shipXConstant && sunMiddleY[x] < shipYConstant){
				v = (Math.sqrt(((sunMiddleX[x] - shipXConstant)*(sunMiddleX[x] - shipXConstant)) + ((sunMiddleY[x] - shipYConstant)*(sunMiddleY[x] - shipYConstant))));
				y = (sunMiddleX[x] - shipXConstant);
				r = ((v)/(sunSize[x]))*3;
				shipNetForceX += ((sunMass[x]*(y/v))/(r*r));
			}
			
			//Y GRAVITY
				a = (Math.sqrt(((sunMiddleX[x] - shipXConstant)*(sunMiddleX[x] - shipXConstant)) + ((sunMiddleY[x] - shipYConstant)*(sunMiddleY[x] - shipYConstant))));
				b = (sunMiddleY[x] - shipYConstant);
				c = ((a)/(sunSize[x]))*3;
				shipNetForceY -= ((sunMass[x]*(b/a))/(c*c));
			
			v = 0;
			y = 0;
			r = 0;
			a = 0;
			b = 0;
			c = 0;
		}
		}
		
		
		//UPDATING PLANETS
		for(int x = 0; x < planetCounter; x ++){
			if(planetX[x] >  shipXConstant - 2000 && planetX[x] < shipXConstant + 3000 && planetY[x] > shipYConstant + 2000 && planetY[x] < shipYConstant -2000){
				
			}else{
			
			double v = 0;
			double y = 0;
			double r = 0;
			
			double a = 0;
			double b = 0;
			double c = 0;
			if(x == 0){
				v = 0;
				y = 0;
				r = 0;
				a = 0;
				b = 0;
				c = 0;
			}
			
			planetMiddleX[x] = systemX((int) planetX[x]) + (planetSize[x]/2);
			planetMiddleY[x] = (float) systemY((int) planetY[x]) + (float) (planetSize[x]/2);
			
			//600 Because ship is not actually moving, just rotating!
			//X GRAVITY
			if(planetMiddleX[x] > shipXConstant && planetMiddleY[x] > shipYConstant){
				v = (Math.sqrt(((planetMiddleX[x] - shipXConstant)*(planetMiddleX[x] - shipXConstant)) + ((planetMiddleY[x] - shipYConstant)*(planetMiddleY[x] - shipYConstant))));
				y = (planetMiddleX[x] - shipXConstant);
				r = ((v)/(planetSize[x]))*3;
				shipNetForceX += ((planetMass[x]*(y/v))/(r*r));
			}
			else if(planetMiddleX[x] > shipXConstant && planetMiddleY[x] < shipYConstant){
				v = (Math.sqrt(((planetMiddleX[x] - shipXConstant)*(planetMiddleX[x] - shipXConstant)) + ((planetMiddleY[x] - shipYConstant)*(planetMiddleY[x] - shipYConstant))));
				y = (planetMiddleX[x] - shipXConstant);
				r = ((v)/(planetSize[x]))*3;
				shipNetForceX += ((planetMass[x]*(y/v))/(r*r));
			}
			else if(planetMiddleX[x] < shipXConstant && planetMiddleY[x] > shipYConstant){
				v = (Math.sqrt(((planetMiddleX[x] - shipXConstant)*(planetMiddleX[x] - shipXConstant)) + ((planetMiddleY[x] - shipYConstant)*(planetMiddleY[x] - shipYConstant))));
				y = (planetMiddleX[x] - shipXConstant);
				r = ((v)/(planetSize[x]))*3;
				shipNetForceX += ((planetMass[x]*(y/v))/(r*r));
			}
			else if(planetMiddleX[x] < shipXConstant && planetMiddleY[x] < shipYConstant){
				v = (Math.sqrt(((planetMiddleX[x] - shipXConstant)*(planetMiddleX[x] - shipXConstant)) + ((planetMiddleY[x] - shipYConstant)*(planetMiddleY[x] - shipYConstant))));
				y = (planetMiddleX[x] - shipXConstant);
				r = ((v)/(planetSize[x]))*3;
				shipNetForceX += ((planetMass[x]*(y/v))/(r*r));
			}
			
			//Y GRAVITY
				a = (Math.sqrt(((planetMiddleX[x] - shipXConstant)*(planetMiddleX[x] - shipXConstant)) + ((planetMiddleY[x] - shipYConstant)*(planetMiddleY[x] - shipYConstant))));
				b = (planetMiddleY[x] - shipYConstant);
				c = ((a)/(planetSize[x]))*3;
				shipNetForceY -= ((planetMass[x]*(b/a))/(c*c));
			
				//ORBITING PLANETS
				if(isPlanetOrbiting[x]){

					if(planetRotation[x] <= 360){
						planetRotation[x] += planetVelocity[x]/5;
					}else{
						planetRotation[x] = 0;
					}
					
					//X DIRECTION
					planetX[x] = sunX[planetSunNumber[x]] + (sunSize[planetSunNumber[x]]/2) - (planetSize[x]/2) + planetOrbitingDistance[x]*(Math.cos(Math.toRadians(planetRotation[x])));
					
					//Y DIRECTION			
					planetY[x] = sunY[planetSunNumber[x]] + (sunSize[planetSunNumber[x]]/2) -(planetSize[x]/2) + planetOrbitingDistance[x]*(Math.sin(Math.toRadians(planetRotation[x])));
					
				}
			v = 0;
			y = 0;
			r = 0;
			a = 0;
			b = 0;
			c = 0;
		}
		}
		
		//UPDATING MOONS
		for(int x = 0; x < moonCounter; x ++){
			
			if(moonX[x] >  shipXConstant - 2000 && moonX[x] < shipXConstant + 3000 && moonY[x] > shipYConstant + 2000 && moonY[x] < shipYConstant -2000){
				
			}else{
			
			double v = 0;
			double y = 0;
			double r = 0;
			
			double a = 0;
			double b = 0;
			double c = 0;
			
			if(x == 0){
				v = 0;
				y = 0;
				r = 0;
				a = 0;
				b = 0;
				c = 0;
			}
			
			moonMiddleX[x] = systemX((int) moonX[x]) + (moonSize[x]/2);
			moonMiddleY[x] = (float) systemY((int) moonY[x]) + (float) (moonSize[x]/2);
			
			//600 Because ship is not actually moving, just rotating!
			//X GRAVITY
			if(moonMiddleX[x] > shipXConstant && moonMiddleY[x] > shipYConstant){
				v = (Math.sqrt(((moonMiddleX[x] - shipXConstant)*(moonMiddleX[x] - shipXConstant)) + ((moonMiddleY[x] - shipYConstant)*(moonMiddleY[x] - shipYConstant))));
				y = (moonMiddleX[x] - shipXConstant);
				r = ((v)/(moonSize[x]))*3;
				shipNetForceX += ((moonMass[x]*(y/v))/(r*r));
			}
			else if(moonMiddleX[x] > shipXConstant && moonMiddleY[x] < shipYConstant){
				v = (Math.sqrt(((moonMiddleX[x] - shipXConstant)*(moonMiddleX[x] - shipXConstant)) + ((moonMiddleY[x] - shipYConstant)*(moonMiddleY[x] - shipYConstant))));
				y = (moonMiddleX[x] - shipXConstant);
				r = ((v)/(moonSize[x]))*3;
				shipNetForceX += ((moonMass[x]*(y/v))/(r*r));
			}
			else if(moonMiddleX[x] < shipXConstant && moonMiddleY[x] > shipYConstant){
				v = (Math.sqrt(((moonMiddleX[x] - shipXConstant)*(moonMiddleX[x] - shipXConstant)) + ((moonMiddleY[x] - shipYConstant)*(moonMiddleY[x] - shipYConstant))));
				y = (moonMiddleX[x] - shipXConstant);
				r = ((v)/(moonSize[x]))*3;
				shipNetForceX += ((moonMass[x]*(y/v))/(r*r));
			}
			else if(moonMiddleX[x] < shipXConstant && moonMiddleY[x] < shipYConstant){
				v = (Math.sqrt(((moonMiddleX[x] - shipXConstant)*(moonMiddleX[x] - shipXConstant)) + ((moonMiddleY[x] - shipYConstant)*(moonMiddleY[x] - shipYConstant))));
				y = (moonMiddleX[x] - shipXConstant);
				r = ((v)/(moonSize[x]))*3;
				shipNetForceX += ((moonMass[x]*(y/v))/(r*r));
			}
			
			//Y GRAVITY
				a = (Math.sqrt(((moonMiddleX[x] - shipXConstant)*(moonMiddleX[x] - shipXConstant)) + ((moonMiddleY[x] - shipYConstant)*(moonMiddleY[x] - shipYConstant))));
				b = (moonMiddleY[x] - shipYConstant);
				c = ((a)/(moonSize[x]))*3;
				shipNetForceY -= ((moonMass[x]*(b/a))/(c*c));
				
				
				
				
				//MOVING MOON					
				
				if(moonRotation[x] <= 360){
					moonRotation[x] += moonVelocity[x]/5;
				}else{
					moonRotation[x] = 0;
				}
				
				//X DIRECTION
				moonX[x] = planetX[moonPlanetNumber[x]] + (planetSize[moonPlanetNumber[x]]/2) - (moonSize[x]/2) + moonOrbitingDistance[x]*(Math.cos(Math.toRadians(moonRotation[x])));
				
				//Y DIRECTION			
				moonY[x] = planetY[moonPlanetNumber[x]] + (planetSize[moonPlanetNumber[x]]/2) -(moonSize[x]/2) + moonOrbitingDistance[x]*(Math.sin(Math.toRadians(moonRotation[x])));
				
					
					
			v = 0;
			y = 0;
			r = 0;
			a = 0;
			b = 0;
			c = 0;
		}
			
			
		}
	}
	
	//DRAWING EXAUST
	public static void exaust(){	
		
		for(int x = 1; x < 100; x++){
			if(spacebar && exaustOn[x] == false){
				if(randomGenerator.nextInt(20) == 10){
					exaustStation[x] = true;
					if(shipRealRotation >= 0 && shipRealRotation <= 45){
						exhaustX[x] = 610 + randomGenerator.nextInt(7);
						exhaustY[x] = 333 + randomGenerator.nextInt(7);
					}
					if(shipRealRotation > 45 && shipRealRotation <= 90){
						exhaustX[x] = 616 + randomGenerator.nextInt(7);
						exhaustY[x] = 335 + randomGenerator.nextInt(7);
					}
					if(shipRealRotation >90 && shipRealRotation <= 135){
						exhaustX[x] = 625 + randomGenerator.nextInt(7);
						exhaustY[x] = 342 + randomGenerator.nextInt(7);
					}
					if(shipRealRotation > 135 && shipRealRotation <= 180){
						exhaustX[x] = 643 + randomGenerator.nextInt(7);
						exhaustY[x] = 335 + randomGenerator.nextInt(7);
					}
					if(shipRealRotation > 180 && shipRealRotation <= 225){
						exhaustX[x] = 638 + randomGenerator.nextInt(7);
						exhaustY[x] = 320 + randomGenerator.nextInt(7);
					}
					if(shipRealRotation > 225 && shipRealRotation <= 270){
						exhaustX[x] = 623 + randomGenerator.nextInt(7);
						exhaustY[x] = 323 + randomGenerator.nextInt(7);
					}
					if(shipRealRotation > 270 && shipRealRotation <= 315){
						exhaustX[x] = 616 + randomGenerator.nextInt(7);
						exhaustY[x] = 317 + randomGenerator.nextInt(7);
					}
					if(shipRealRotation > 315 && shipRealRotation <= 360){
						exhaustX[x] = 612 + randomGenerator.nextInt(7);
						exhaustY[x] = 323 + randomGenerator.nextInt(7);
					}
					
				exaustOn[x] = true;
				spacebar = false;
				exaustTime[x] = 20 + randomGenerator.nextInt(5);
				
				}
			}
			exaustTime[x]--;
			
			if(exaustStation[x]){
		if(shipRealRotation >= 90 && shipRealRotation <= 270){
			exaustStationX[x] = Math.abs(shipSpeed*Math.cos(Math.toRadians((shipRealRotation))));
			exaustStationDegreeX[x] = 0;
		}else{
			exaustStationX[x] = Math.abs(shipSpeed*Math.cos(Math.toRadians((shipRealRotation))));
			exaustStationDegreeX[x] = 1;
		}
		if(shipRealRotation >= 180 && shipRealRotation < 360){
			exaustStationY[x] = Math.abs(shipSpeed*Math.sin(Math.toRadians((shipRealRotation))));
			exaustStationDegreeY[x] = 0;
		}else{
			exaustStationY[x] = Math.abs(shipSpeed*Math.sin(Math.toRadians((shipRealRotation))));
			exaustStationDegreeY[x] = 1;
		}
		exaustStation[x] = false;
			}else{

				if(exaustStationDegreeX[x] == 0){
					exhaustX[x] += exaustStationX[x];
				}else{
					exhaustX[x] -= exaustStationX[x];
				}
				if(exaustStationDegreeY[x] == 0){
					exhaustY[x] -= exaustStationY[x];
				}else{
					exhaustY[x] += exaustStationY[x];
				}
			}
			
			
		exaustOn[x] = false;
		}
	}
	
	//DRAWING STARS
	public static void drawingStars(){
		for(int x = 0; x < 100; x++){
			if(playStart == false){
				starX[x] = -1500 + randomGenerator.nextInt(3500);
				starY[x] = -1000 + randomGenerator.nextInt(3500);
				if(x == 99){
					playStart = true;
				}
			}else{
			if(starX[x] < -1500 || starX[x] > 2500 || starY[x] < -1000 || starY[x] > 2000){
				
				if(starY[x] < -1000){
					starX[x] = -1500 + randomGenerator.nextInt(3500);
					starY[x] = 900 + randomGenerator.nextInt(500);
					}
					if(starY[x] > 2000){
						starX[x] = -1500 + randomGenerator.nextInt(3500);
						starY[x] = -2 - randomGenerator.nextInt(990);
					}
					if(starX[x] < -1500){
						starX[x] = 1290 + randomGenerator.nextInt(500);
						starY[x] = -1000 + randomGenerator.nextInt(3000);
					}
					if(starX[x] > 2500){
						starX[x] = 0 - randomGenerator.nextInt(1290);
						starY[x] = -1000 + randomGenerator.nextInt(3000);
					}

				}else{
					
					if(shipRealRotation >= 90 && shipRealRotation <= 270){
						starX[x] += Math.abs(shipSpeed*Math.cos(Math.toRadians((shipRealRotation))))/10;
					}else{
						starX[x] -= Math.abs(shipSpeed*Math.cos(Math.toRadians((shipRealRotation))))/10;
					}
					if(shipRealRotation >= 180 && shipRealRotation < 360){
						starY[x] -= Math.abs(shipSpeed*Math.sin(Math.toRadians((shipRealRotation))))/10;
					}else{
						starY[x] += Math.abs(shipSpeed*Math.sin(Math.toRadians((shipRealRotation))))/10;
					}
				}
			}
		}
	}
	
	
	//CREATES BODY --
	public static void generateSizes(){
		
		for(int x = 0; x < planetX.length; x++){
		planetSize[x] = 80 + randomGenerator.nextInt(250);
		}
		
		for(int x = 0; x < moonX.length; x++){
		moonSize[x] = 30 + randomGenerator.nextInt(30);
		moonOrbitingDistance[x] = 200 + randomGenerator.nextInt(300);
		}
		
		for(int x = 0; x < sunX.length; x++){
			sunSize[x] = 500 + randomGenerator.nextInt(800);
			planetOrbitingDistance[x] = 500 + randomGenerator.nextInt(500);
			}
	}
	
	
	
	
	//CREATES PLANETS
	public static void generatePlanets(int x){
		
			//spawning variables
			int planetCounterSunTotal;
			int planetCounterPlanetTotal;
			
			int moonCounterPlanetTotal;
			int moonCounterSunTotal;
			
			int sunCounterSunTotal;
			int sunCounterPlanetTotal;
			
			int sunCounterSunSystemTotal;
			int sunCounterPlanetSystemTotal;
			
			int sunCounterSunSystemTotal2;
			int sunCounterPlanetSystemTotal2;
			
			int sunCounterSunSystemTotal3;
			int sunCounterPlanetSystemTotal3;
			
			int sunCounterSunSystemTotal4;
			int sunCounterPlanetSystemTotal4;
			
			int sunCounterSunSystemTotal5;
			int sunCounterPlanetSystemTotal5;
			
			int randomX = 0;
			int randomY = 0;			 
			int createMoonR = 0;
			int createMoonR2 = 0;		
			
			int createPlanetR1 = randomGenerator.nextInt(2);
			int createPlanetR2 = randomGenerator.nextInt(2);
			int createPlanetR3 = randomGenerator.nextInt(2);
			int createPlanetR4 = randomGenerator.nextInt(2);
			
			int checkSunAmount = randomGenerator.nextInt(sunAmount);
			
			//PLANET CREATED IS Z, OTHER PLANETS ARE Y, Z IS COUNTER
			 for(int z = x; z <= x; z++){
				 
					planetCounterSunTotal = 0;
					planetCounterPlanetTotal = 0;
					
					moonCounterPlanetTotal = 0;
					moonCounterSunTotal = 0;
					
					sunCounterSunTotal = 0;
					sunCounterPlanetTotal = 0;
					
					sunCounterSunSystemTotal = 0;
					sunCounterPlanetSystemTotal = 0;
					
					sunCounterSunSystemTotal2 = 0;
					sunCounterPlanetSystemTotal2 = 0;
					
					sunCounterSunSystemTotal3 = 0;
					sunCounterPlanetSystemTotal3 = 0;
					
					sunCounterSunSystemTotal4 = 0;
					sunCounterPlanetSystemTotal4 = 0;
					
					sunCounterSunSystemTotal5 = 0;
					sunCounterPlanetSystemTotal5 = 0;
					
					randomX = -((areaWidth/2)+500) + randomGenerator.nextInt(areaWidth - 1000);
					randomY = -((areaHeigth/2)+500) + randomGenerator.nextInt(areaHeigth - 1000);
					
					createMoonR = randomGenerator.nextInt(2);
					createMoonR2 = randomGenerator.nextInt(3);
					
					
					
					//finding if new planet has other planets in a certain radius of it, not if it is outside of new planet
			for(int y = 0; y <= x; y++){
				
				//CHECKING PLANETS VS. PLANETS
				if((randomX < (planetX[y] + planetSize[y]) + objectSpacing && (randomX + planetSize[planetCounter]) > (planetX[y] -planetSize[y] - objectSpacing) && 
						((randomY - planetSize[planetCounter]) < (planetY[y] + planetSize[y] + objectSpacing) && randomY > (planetY[y] - planetSize[y]) - objectSpacing))){
				planetCounterPlanetTotal --;	
			}else{
				planetCounterPlanetTotal ++;
			}
				
				//CHECKING PLANETS VS. SUN
				if((randomX < (sunX[y] + sunSize[y]) + objectSpacing && (randomX + planetSize[planetCounter]) > (sunX[y] -sunSize[y] - objectSpacing) && 
						((randomY - planetSize[planetCounter]) < (sunY[y] + sunSize[y] + objectSpacing) && randomY > (sunY[y] - sunSize[y]) - objectSpacing))){
				planetCounterSunTotal --;		
			}else{
				planetCounterSunTotal ++;
			}
				
				
				
				
				//CHECKING MOONS VS. PLANETS
				if(randomX < (planetX[y] + planetSize[y]) + objectSpacing + moonOrbitingDistance[y] + moonSize[y] && (randomX + planetSize[planetCounter]) > (planetX[y] -planetSize[y] - objectSpacing - moonOrbitingDistance[y] - moonSize[y]) && 
						((randomY - planetSize[planetCounter]) < (planetY[y] + planetSize[y] + objectSpacing + moonOrbitingDistance[y] + moonSize[y]) && randomY > (planetY[y] - planetSize[y]) - objectSpacing - moonOrbitingDistance[y] - moonSize[y])){
					moonCounterPlanetTotal --;
				}else{
					moonCounterPlanetTotal ++;
				}
				
				//CHECKING MOONS VS. SUN
				if(randomX < (sunX[y] + sunSize[y]) + objectSpacing + moonOrbitingDistance[y] + moonSize[y] && (randomX + planetSize[planetCounter]) > (sunX[y] -sunSize[y] - objectSpacing - moonOrbitingDistance[y] - moonSize[y]) && 
						((randomY - planetSize[planetCounter]) < (sunY[y] + sunSize[y] + objectSpacing + moonOrbitingDistance[y] + moonSize[y]) && randomY > (sunY[y] - sunSize[y]) - objectSpacing - moonOrbitingDistance[y] - moonSize[y])){
					moonCounterSunTotal --;
				}else{
					moonCounterSunTotal ++;
				}
				
						
				
				//CHECKING SUN VS. SUN
				if((randomX < (sunX[y] + sunSize[y]) + objectSpacing + 300&& (randomX + sunSize[sunCounter]) > (sunX[y] -sunSize[y] - objectSpacing - 300) && 
						((randomY - sunSize[sunCounter]) < (sunY[y] + sunSize[y] + objectSpacing + 300) && randomY > (sunY[y] - sunSize[y]) - objectSpacing - 300))){	
					sunCounterSunTotal --;
				}else{
					sunCounterSunTotal ++;
				}
				
				//CHECKING SUN VS. PLANET
				if((randomX < (planetX[y] + planetSize[y]) + objectSpacing + 300 && (randomX + sunSize[sunCounter]) > (planetX[y] -planetSize[y] - objectSpacing - 300) && 
						((randomY - sunSize[sunCounter]) < (planetY[y] + planetSize[y] + objectSpacing + 300) && randomY > (planetY[y] - planetSize[y]) - objectSpacing - 300))){
				sunCounterPlanetTotal--;
			}else{
				sunCounterPlanetTotal ++;
			}
				
				
				//CHECKING SUN VS. SUN FOR SYSTEM
				if((randomX < (sunX[y] + sunSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (sunX[y] -sunSize[y] - objectSpacing - planetOrbitingDistance[y]) - 300&& 
						((randomY - sunSize[sunCounter]) < (sunY[y] + sunSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (sunY[y] - sunSize[y]) - objectSpacing - planetOrbitingDistance[y]-300))){	
					sunCounterSunSystemTotal --;
				}else{
					sunCounterSunSystemTotal ++;
				}
				
				//CHECKING SUN VS. PLANET FOR SYSTEM
				if((randomX < (planetX[y] + planetSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300&& (randomX + sunSize[sunCounter]) > (planetX[y] -planetSize[y] - objectSpacing- planetOrbitingDistance[y] - 300) && 
						((randomY - sunSize[sunCounter]) < (planetY[y] + planetSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (planetY[y] - planetSize[y]) - objectSpacing - planetOrbitingDistance[y] - 300))){
				sunCounterPlanetSystemTotal--;
			}else{
				sunCounterPlanetSystemTotal ++;
			}
			
			}
			
			//CREATING SUN
			
			//xy prevents plant and sun from spawning at same location
			int xy = 0;
			xy = 0;
			
			//TESTING FOR SYSTEM
			if(planetCounter < planetX.length && sunCounter < sunX.length && moonCounter < moonX.length){
			if(checkSunAmount == 1){
			if(sunCounterSunSystemTotal > x && sunCounterPlanetSystemTotal > x && planetCounter <= planetX.length){
				xy = 1;
				if((randomX > -300 && randomX < 1400) && (randomY < 900 && randomY > -900)){
					 z--;
				 }else{
					 
					 xy = 2;
						sunX[sunCounter] = randomX;
						sunY[sunCounter] = randomY;
						
					    sunMass[sunCounter] = 15 + randomGenerator.nextInt(10);
			    	    
					    sunColorR[sunCounter]=  200 + randomGenerator.nextInt(52);
					    sunColorG[sunCounter]= 200 + randomGenerator.nextInt(52);
					    sunColorB[sunCounter]= 5 + randomGenerator.nextInt(25); 
					    
					    if(z == x){
						    objectSuccess = true;
						    }						    
					    
					    
					    
						//CREATING FIRST PLANET FOR SUN SYSTEM
					    
						//CHECKING SUN VS. SUN FOR SYSTEM
					    
					    for(int y = 0; y <= x; y++){
						if((randomX < (sunX[y] + sunSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (sunX[y] -sunSize[y] - objectSpacing - planetOrbitingDistance[y]) - 300&& 
								((randomY - sunSize[sunCounter]) < (sunY[y] + sunSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (sunY[y] - sunSize[y]) - objectSpacing - planetOrbitingDistance[y]-300))){	
							sunCounterSunSystemTotal2 --;
						}else{
							sunCounterSunSystemTotal2 ++;
						}
						
						//CHECKING SUN VS. PLANET FOR SYSTEM
						if((randomX < (planetX[y] + planetSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (planetX[y] -planetSize[y] - objectSpacing- planetOrbitingDistance[y] - 300) && 
								((randomY - sunSize[sunCounter]) < (planetY[y] + planetSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (planetY[y] - planetSize[y]) - objectSpacing - planetOrbitingDistance[y] - 300))){
						sunCounterPlanetSystemTotal2--;
					}else{
						sunCounterPlanetSystemTotal2 ++;
					}
					    }
					    
					    if(sunCounterSunSystemTotal2 >= x-1 && sunCounterPlanetSystemTotal2 >= x-1 && createPlanetR1 == 1){
					    	
					    sunMiddleX[x] = systemX((int) sunX[sunCounter]) + (sunSize[sunCounter]/2);
						sunMiddleY[x] = (float) systemY((int) sunY[sunCounter]);				
						
						planetX[planetCounter]= sunMiddleX[sunCounter] - planetOrbitingDistance[planetCounter] + (planetSize[planetCounter]/2);
						planetY[planetCounter]= sunMiddleY[sunCounter] - planetOrbitingDistance[planetCounter] + (planetSize[planetCounter]/2);
						
						planetMass[planetCounter] = 7 + randomGenerator.nextInt(8);
			    	    
					    isPlanetOrbiting[planetCounter] = true;
					    
					    planetColorR[planetCounter]=  60 + randomGenerator.nextInt(135);
		 			    planetColorG[planetCounter]= 170 + randomGenerator.nextInt(54);
					    planetColorB[planetCounter]= 118 + randomGenerator.nextInt(12); 
					    planetSunNumber[planetCounter] = sunCounter;
					    
					    planetVelocity[planetCounter] = 2 + randomGenerator.nextInt(2);
					    planetCounter++;
					    
					    if(z == x){
						    objectSuccess = true;
						    }	
					    }
					    
						  //CREATING SECOND PLANET FOR SUN SYSTEM
					    
					    if(planetCounter >= 1){
					    planetOrbitingDistance[planetCounter] = planetOrbitingDistance[planetCounter -1 ] + planetSize[planetCounter] + 300;
						
					    
						//CHECKING SUN VS. SUN FOR SYSTEM
					    
					    for(int y = 0; y <= x; y++){
						if((randomX < (sunX[y] + sunSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (sunX[y] -sunSize[y] - objectSpacing - planetOrbitingDistance[y]) - 300&& 
								((randomY - sunSize[sunCounter]) < (sunY[y] + sunSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (sunY[y] - sunSize[y]) - objectSpacing - planetOrbitingDistance[y]-300))){	
							sunCounterSunSystemTotal3 --;
						}else{
							sunCounterSunSystemTotal3 ++;
						}
						
						//CHECKING SUN VS. PLANET FOR SYSTEM
						if((randomX < (planetX[y] + planetSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (planetX[y] -planetSize[y] - objectSpacing- planetOrbitingDistance[y] - 300) && 
								((randomY - sunSize[sunCounter]) < (planetY[y] + planetSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (planetY[y] - planetSize[y]) - objectSpacing - planetOrbitingDistance[y] - 300))){
						sunCounterPlanetSystemTotal3--;
					}else{
						sunCounterPlanetSystemTotal3 ++;
					}
					    }
					    

					    if(sunCounterSunSystemTotal3 >= x-1 && sunCounterPlanetSystemTotal3 >= x-1 && createPlanetR2 == 1){
					  	
								sunMiddleX[x] = systemX((int) sunX[sunCounter]) + (sunSize[sunCounter]/2);
								sunMiddleY[x] = (float) systemY((int) sunY[sunCounter]);
								
								planetX[planetCounter]= sunMiddleX[z] + planetOrbitingDistance[planetCounter] - (planetSize[planetCounter]/2);
								planetY[planetCounter]= sunMiddleY[z] + planetOrbitingDistance[planetCounter] - (planetSize[planetCounter]/2);
								
								planetMass[planetCounter] = 7 + randomGenerator.nextInt(8);   	    
							    
								isPlanetOrbiting[planetCounter] = true;
								
							    planetColorR[planetCounter]=  60 + randomGenerator.nextInt(135);
				 			    planetColorG[planetCounter]= 170 + randomGenerator.nextInt(54);
							    planetColorB[planetCounter]= 118 + randomGenerator.nextInt(12); 
							    planetSunNumber[planetCounter] = sunCounter;
							    
							    planetRotation[planetCounter] = 30 + randomGenerator.nextInt(270);
							    
							    planetVelocity[planetCounter] = 2 + randomGenerator.nextInt(2);
							    planetCounter++;
							    
							    if(z == x){
								    objectSuccess = true;
								    }	
					    }
								
							
							
					  //CREATING THIRD PLANET FOR SUN SYSTEM
					    planetOrbitingDistance[planetCounter] = planetOrbitingDistance[planetCounter -1 ] + planetSize[planetCounter] + 300;
					    
					    for(int y = 0; y <= x; y++){
							if((randomX < (sunX[y] + sunSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (sunX[y] -sunSize[y] - objectSpacing - planetOrbitingDistance[y]) - 300&& 
									((randomY - sunSize[sunCounter]) < (sunY[y] + sunSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (sunY[y] - sunSize[y]) - objectSpacing - planetOrbitingDistance[y]-300))){	
								sunCounterSunSystemTotal4 --;
							}else{
								sunCounterSunSystemTotal4 ++;
							}
							
							//CHECKING SUN VS. PLANET FOR SYSTEM
							if((randomX < (planetX[y] + planetSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (planetX[y] -planetSize[y] - objectSpacing- planetOrbitingDistance[y] - 300) && 
									((randomY - sunSize[sunCounter]) < (planetY[y] + planetSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (planetY[y] - planetSize[y]) - objectSpacing - planetOrbitingDistance[y] - 300))){
							sunCounterPlanetSystemTotal4 --;
						}else{
							sunCounterPlanetSystemTotal4 ++;
						}
						    }
						    

						    if(sunCounterSunSystemTotal4 >= x-1  && sunCounterPlanetSystemTotal4 >= x-1 && createPlanetR3 == 1){
								
									sunMiddleX[x] = systemX((int) sunX[sunCounter]) + (sunSize[sunCounter]/2);
									sunMiddleY[x] = (float) systemY((int) sunY[sunCounter]);
									
									planetX[planetCounter]= sunMiddleX[z] + planetOrbitingDistance[planetCounter] - (planetSize[planetCounter]/2);
									planetY[planetCounter]= sunMiddleY[z] + planetOrbitingDistance[planetCounter] - (planetSize[planetCounter]/2);
									
									planetMass[planetCounter] = 7 + randomGenerator.nextInt(8);   	    
								    
									isPlanetOrbiting[planetCounter] = true;
									
								    planetColorR[planetCounter]=  60 + randomGenerator.nextInt(135);
					 			    planetColorG[planetCounter]= 170 + randomGenerator.nextInt(54);
								    planetColorB[planetCounter]= 118 + randomGenerator.nextInt(12); 
								    planetSunNumber[planetCounter] = sunCounter;
								    
								    planetRotation[planetCounter] = 30 + randomGenerator.nextInt(270);
								    
								    planetVelocity[planetCounter] = 2 + randomGenerator.nextInt(2);
								    planetCounter++;
								    
								    if(z == x){
									    objectSuccess = true;
									    }	
						    }
						    
						    
							  //CREATING FOURTH PLANET FOR SUN SYSTEM
						    planetOrbitingDistance[planetCounter] = planetOrbitingDistance[planetCounter -1 ] + planetSize[planetCounter] + 300;
						    
						    for(int y = 0; y <= x; y++){
								if((randomX < (sunX[y] + sunSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (sunX[y] -sunSize[y] - objectSpacing - planetOrbitingDistance[y]) - 300&& 
										((randomY - sunSize[sunCounter]) < (sunY[y] + sunSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (sunY[y] - sunSize[y]) - objectSpacing - planetOrbitingDistance[y]-300))){	
									sunCounterSunSystemTotal5 --;
								}else{
									sunCounterSunSystemTotal5 ++;
								}
								
								//CHECKING SUN VS. PLANET FOR SYSTEM
								if((randomX < (planetX[y] + planetSize[y]) + objectSpacing + planetOrbitingDistance[y] + 300 && (randomX + sunSize[sunCounter]) > (planetX[y] -planetSize[y] - objectSpacing- planetOrbitingDistance[y] - 300) && 
										((randomY - sunSize[sunCounter]) < (planetY[y] + planetSize[y] + objectSpacing + planetOrbitingDistance[y] + 300) && randomY > (planetY[y] - planetSize[y]) - objectSpacing - planetOrbitingDistance[y] - 300))){
								sunCounterPlanetSystemTotal5 --;
							}else{
								sunCounterPlanetSystemTotal5 ++;
							}
							    }
							    

							    if(sunCounterSunSystemTotal5 >= x-1  && sunCounterPlanetSystemTotal5 >= x-1 && createPlanetR4 == 1){									
							    	
										sunMiddleX[x] = systemX((int) sunX[sunCounter]) + (sunSize[sunCounter]/2);
										sunMiddleY[x] = (float) systemY((int) sunY[sunCounter]);
										
										planetX[planetCounter]= sunMiddleX[z] + planetOrbitingDistance[planetCounter] - (planetSize[planetCounter]/2);
										planetY[planetCounter]= sunMiddleY[z] + planetOrbitingDistance[planetCounter] - (planetSize[planetCounter]/2);
										
										planetMass[planetCounter] = 7 + randomGenerator.nextInt(8);   	    
									    
										isPlanetOrbiting[planetCounter] = true;
										
									    planetColorR[planetCounter]=  60 + randomGenerator.nextInt(135);
						 			    planetColorG[planetCounter]= 170 + randomGenerator.nextInt(54);
									    planetColorB[planetCounter]= 118 + randomGenerator.nextInt(12); 
									    planetSunNumber[planetCounter] = sunCounter;
									    
									    planetRotation[planetCounter] = 30 + randomGenerator.nextInt(270);
									    
									    planetVelocity[planetCounter] = 2 + randomGenerator.nextInt(2);
									    planetCounter++;
									    
									    if(z == x){
										    objectSuccess = true;
										    }	
							    }
					    }
							    if(sunCounter < 74){
								    sunCounter++;
								   }	    							
				 }
				
				
				
			}else{
				//REGULAR SUN
			if(sunCounterSunTotal > x && sunCounterPlanetTotal > x){
				xy = 1;
				 if((randomX > -300 && randomX < 1400) && (randomY < 900 && randomY > -900)){
					 z--;
				 }else{
				
				
					 
				sunX[sunCounter] = randomX;
				sunY[sunCounter] = randomY;
				
			    sunMass[sunCounter] = 20 + randomGenerator.nextInt(10);
	    	    
			    sunColorR[sunCounter]=  200 + randomGenerator.nextInt(52);
			    sunColorG[sunCounter]= 200 + randomGenerator.nextInt(52);
			    sunColorB[sunCounter]= 5 + randomGenerator.nextInt(25); 
			   if(sunCounter < planetX.length){
			    sunCounter++;
			   }
			    if(z == x){
				    objectSuccess = true;
				    }	
			    
				 }

			}
			}
			}
			
			//CREATING PLANET
			if(xy == 0){
				
			if(planetCounterSunTotal > x && planetCounterPlanetTotal > x){
				 if((randomX > -300 && randomX < 1400) && (randomY < 900 && randomY > -900)){
					 z--;
				 }else{
				
				planetX[planetCounter] = randomX;
				planetY[planetCounter] = randomY;
				
				planetMass[planetCounter] = 7 + randomGenerator.nextInt(8);
	    	    
			    planetColorR[planetCounter]=  60 + randomGenerator.nextInt(135);
 			    planetColorG[planetCounter]= 170 + randomGenerator.nextInt(54);
			    planetColorB[planetCounter]= 118 + randomGenerator.nextInt(12); 
			   
			    planetCounter++;
			    
			    if(z == x){
				    objectSuccess = true;
				    }	
				 }
			}else{
					 z--;
			}
			 
			creatingMoon(x, moonCounterPlanetTotal,moonCounterSunTotal, randomX, randomY,createMoonR,createMoonR2, xy);
			}						
			 }else{
				 objectSuccess = true;
			 }
			 }
	}
	
	public static void creatingMoon(int x, int moonCounterPlanetTotal, int moonCounterSunTotal, int randomX, int randomY, int createMoonR, int createMoonR2, int xy){
		if((moonCounterSunTotal > x && moonCounterPlanetTotal > x && (createMoonR == 1)) || ((xy == 2 && createMoonR == 1))){
			if((randomX > -300 && randomX < 1400) && (randomY < 900 && randomY > -900)){
			}else{
		int e = 0;
		
		//CREATING FIRST MOON
		if(xy == 0){
		planetMiddleX[x] = systemX((int) planetX[planetCounter]) + (planetSize[planetCounter]/2);
		planetMiddleY[x] = (float) systemY((int) planetY[planetCounter]);
		}
		
		moonX[moonCounter]= planetMiddleX[planetCounter] - moonOrbitingDistance[moonCounter] + (moonSize[moonCounter]/2);
		moonY[moonCounter]= planetMiddleY[planetCounter] - moonOrbitingDistance[moonCounter] + (moonSize[moonCounter]/2);
	    moonMass[moonCounter] = 1 + randomGenerator.nextInt(2);
	    
	    e =  150 + randomGenerator.nextInt(75);
	    
	    moonColorR[moonCounter] = e; 
	    moonColorG[moonCounter] = e;
	    moonColorB[moonCounter] = e;
	    moonPlanetNumber[moonCounter] = planetCounter;
	    
	    moonVelocity[moonCounter] = 3 + randomGenerator.nextInt(10);
	    moonCounter++;
	    
	    //CREATING SECOND MOON
		if(createMoonR2 == 1 && moonOrbitingDistance[moonCounter] + moonSize[moonCounter] < moonOrbitingDistance[moonCounter-1] - moonSize[moonCounter-1]){
		planetMiddleX[x] = systemX((int) planetX[planetCounter]) + (planetSize[planetCounter]/2);
		planetMiddleY[x] = (float) systemY((int) planetY[planetCounter]);					
		
		moonX[moonCounter]= planetMiddleX[planetCounter] - moonOrbitingDistance[moonCounter] + (moonSize[moonCounter]/2);
		moonY[moonCounter]= planetMiddleY[planetCounter] - moonOrbitingDistance[moonCounter] + (moonSize[moonCounter]/2);
	    moonMass[moonCounter] = 1 + randomGenerator.nextInt(2);
	    
	    e =  150 + randomGenerator.nextInt(75);
	    
	    moonColorR[moonCounter] = e; 
	    moonColorG[moonCounter] = e;
	    moonColorB[moonCounter] = e;
	    moonPlanetNumber[moonCounter] = planetCounter;
	    
	    moonRotation[moonCounter] = 30 + randomGenerator.nextInt(270);
	    
	    moonVelocity[moonCounter] = 3 + randomGenerator.nextInt(5);
	    moonCounter++;
		    
		}

			}
		}
	}
	
}
