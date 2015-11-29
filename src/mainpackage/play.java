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
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.Random;

public class play extends BasicGameState{
	public play(int state){
	}
    private UnicodeFont fUnicodeFont;
    private UnicodeFont fUnicodeFont2;
    
	static Random randomGenerator = new Random();
	
	int mouseX2;
	int mouseY2;
	
	static double[] starX = new double[80];
	static double[] starY = new double[80];
	static int starSpeed = 15;
	
	static double[] starMediumX = new double[30];
	static double[] starMediumY = new double[30];
	static int starMediumSpeed = 1;
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
	static int shipMaxSpeed = 10;
	
	//IMAGES
    Image ship;
    Image plusButton;
    Image minusButton;   
    Image velocityScale; 
    Image velocityScaleFront1;
    Image velocityScaleFront2;
    Image settingsIcon;
    Image shopIcon;
    
   static Image[] coin1 = new Image[120];
    
	//map scale
	static int mapScale = 4;
	
    //cordinate system:
    static double cordinateX = 0;
    static double cordinateY = 0;
    
    //finding real rotation
    int shipRotation = 90;
    double shipRealRotationX = 0;
    static double shipRealRotation = 0;
    
    //startup script
    static boolean begin = false;
    static boolean generateSizes = true;
    
    //pause variables
    static boolean pause = false;
    static double shipXSpeedSave;
    static double shipYSpeedSave;
    
    //planet coordinates
    
    static double[] planetX = new double[65];
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
    static int objectSuccess = 0;
    
    //planet spacing
    static int objectSpacing = 800;
    
    //setting variables
    static boolean settingVariables = true;
    
     //area of level;
    static int areaWidth = 20000;
    static int areaHeigth = 20000;
    
    //ship LOCK rotation variables
    static double shipXSpeed = 0;
    static double shipYSpeed = 0;
    static double shipLockRotation = 0;
    static boolean spacebarPressed = false;
    static boolean shipGoing = true;
    
    //how fast the ship speeds up (higher # means lower speedup)
    static int shipSpeedup = 8;
    static double shipTotalSpeed;
    
    //coin variables
    static int[]objectOrbiting = new int[coin1.length];
    static double[]coinX = new double[coin1.length];
    static double[]coinY = new double[coin1.length];
    static double[]coinVelocity = new double[coin1.length];
    static int[]coinDistance = new int[coin1.length];
    static double[]coinRotation = new double[coin1.length];
    static int[]coinObjectNumber = new int[coin1.length];
    static boolean[]coinAlive = new boolean[coin1.length];
    
    static int coinTotal = 0;
    
    //ship health
    static double shipMaxHealth = 200;
    static double shipCurrentHealth = 200;
    static int healthDelay = 10;
    
    private static Shape square = null;
    
    //collision arrays
    private static Circle[] moons = new Circle[moonX.length];
    private static Circle[] planets = new Circle[planetX.length];
    private static Circle[] suns = new Circle[sunX.length];
    private static Circle[] coins = new Circle[coin1.length];
    
    //planet variables
    
	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		for(int x = 0; x < planetX.length; x++){			
			planets[x] = new Circle((int)planetX[x],(int)planetY[x],(int) (planetSize[x]/2));
			suns[x] = new Circle((int)planetX[x],(int)planetY[x],(int) (planetSize[x]/2));
			moons[x] = new Circle((int)planetX[x],(int)planetY[x],(int) (planetSize[x]/2));
			coins[x] = new Circle((int)planetX[x],(int)planetY[x],(int) (planetSize[x]/2));
		}
		
		for(int x = 0; x < coinX.length; x++){
		coins[x] = new Circle(0,0,0);
		}
			float[] points = new float[]{605,310, 645,310, 645,350, 605,350};
		
		square = new Polygon(points);
		
        Font font = new Font("Serif", Font.PLAIN, 20);
        Font font2 = new Font("Serif", Font.PLAIN, 20);
        
        fUnicodeFont = new UnicodeFont(font, 25, true, false);
        fUnicodeFont2 = new UnicodeFont(font2, 20, true, false);

        
		fUnicodeFont.getEffects().add(new ColorEffect());
        fUnicodeFont.addAsciiGlyphs();
        fUnicodeFont.loadGlyphs();
        
        fUnicodeFont2.getEffects().add(new ColorEffect());
        fUnicodeFont2.addAsciiGlyphs();
        fUnicodeFont2.loadGlyphs();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg,Graphics g) throws SlickException{
		
		//SETTING VARIABLES
		
		if(settingVariables){
	    for(int x = 0; x < planetX.length; x++){
	        isPlanetOrbiting[x] = false;
	        }
	    
	       for (int i = 0; i < coin1.length; i++ ) {
	    	   coin1[i] = new Image("res/1point.png");
	    	   coinAlive[i] = true;
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
		    if(objectCounter == planetX.length - 1){
		    	begin = true;
		    	setCoinPoints();
		    }else{
		    	if(objectSuccess != 0){
		    		objectCounter += objectSuccess;
		    		objectSuccess = 0;
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
			if(pause == false){
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
			}
			
			g.setColor(Color.white);
					
			for(int x = 0; x < starX.length; x++){
			g.fillRect((int) starX[x],(int) starY[x], 1, 1);
			}
			
			for(int x = 0; x < starMediumX.length; x++){
				g.fillRect((int) starMediumX[x],(int) starMediumY[x], 3, 3);
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
			
			if(spacebarPressed){
				shipLockRotation = shipRealRotation;
			}
			if(pause == false){
				
				if(shipXSpeed != 0 && shipYSpeed != 0){
			cordinateX += shipXSpeed;
			cordinateY += shipYSpeed;
				}else{
					shipXSpeed = shipXSpeedSave;
					shipYSpeed = shipYSpeedSave;
				}
			}else{
			if(shipXSpeed != 0 && shipYSpeed != 0){
				shipYSpeedSave = shipYSpeed;
				shipXSpeedSave = shipXSpeed;
			}
				shipXSpeed = 0;
				shipYSpeed = 0;
			}
			
			//SHIP TOTAL SPEED
			shipTotalSpeed = Math.sqrt((shipYSpeed*shipYSpeed) + (shipXSpeed*shipXSpeed));
			if(pause == false){
			//SHIP SPEEDS!
				if(spacebarPressed){
					
					if(shipTotalSpeed >= shipMaxSpeed){
						shipGoing = false;
						if(shipXSpeed < 0){
							
							if(shipRealRotation > 90 && shipRealRotation <= 270){									
							}else{
								shipGoing = true;
							shipXSpeed+=Math.cos(Math.toRadians((shipRealRotation)))/shipSpeedup;
							}
						
						}else{
							if(shipRealRotation > 90 && shipRealRotation <= 270){
								shipGoing = true;
							shipXSpeed+=Math.cos(Math.toRadians((shipRealRotation)))/shipSpeedup;
							}
						}
						
						if(shipYSpeed < 0){
							
							if(shipRealRotation >= 0 && shipRealRotation <= 180){
								shipGoing = true;
								shipYSpeed+=Math.sin(Math.toRadians((shipRealRotation)))/shipSpeedup;
							}
							
						}else{
							
							if(shipRealRotation >= 180 && shipRealRotation <= 360){
								shipGoing = true;
								shipYSpeed+=Math.sin(Math.toRadians((shipRealRotation)))/shipSpeedup;
							}	
							
						}
						
					}else{
						shipGoing = true;
						shipYSpeed+=Math.sin(Math.toRadians((shipRealRotation)))/shipSpeedup;
						shipXSpeed+=Math.cos(Math.toRadians((shipRealRotation)))/shipSpeedup;
					}
				}
			}
			
			//DRAWING BORDER OF LEVEL
			
			Color levelColor = new Color(67,205,239,128);
			g.setColor(levelColor);
			
			g.fillRect(systemX((-areaWidth/2) - 1500), systemY((-areaHeigth/2) - 1500), 1000, areaHeigth + 2999);
			g.fillRect(systemX((areaWidth/2) + 1500), systemY((-areaHeigth/2) - 1500), 1000, areaHeigth + 2999);
			g.fillRect(systemX((-areaWidth/2)-1500), systemY((areaHeigth/2) + 1500), areaWidth + 3999, 1000);
			g.fillRect(systemX((-areaWidth/2) - 499) , systemY((-areaHeigth/2) - 1500), areaWidth + 1998, 1000);
			
			//DRAWING COINS
			for(int x = 0; x < coinX.length; x++){
				if(systemX((int) coinX[x]) >  shipXConstant - 2000 && systemX((int) coinX[x]) < shipXConstant + 2000 && systemY((int) coinY[x]) > shipYConstant + 2000 && systemY((int) coinY[x]) < shipYConstant -2000){					
				}else{
				if(coinX[x] != 0){
			g.drawImage(coin1[x], systemX((int) coinX[x]),systemY((int) coinY[x]));
				}
				}
			}
			
			//DRAWING PLANET 
			for(int x = 0; x < planetCounter; x++){
				if(systemX((int) planetX[x]) >  shipXConstant - 2000 && systemX((int) planetX[x]) < shipXConstant + 2000 && systemY((int) planetY[x]) > shipYConstant + 2000 && systemY((int) planetY[x]) < shipYConstant -2000){					
				}else{
			Color tileColor = new Color((int) planetColorR[x],(int) planetColorG[x],(int) planetColorB[x]);
			g.setColor(tileColor);
			g.fillOval((float)systemX((int) planetX[x]), (float) systemY((int) planetY[x]), (float) planetSize[x],(float)  planetSize[x]);
				}
			}
			
			//DRAWING MOON
			for(int x = 0; x < moonCounter; x++){
				if(systemX((int) moonX[x]) >  shipXConstant - 2000 && systemX((int) moonX[x]) < shipXConstant + 2000 && systemY((int) moonY[x]) > shipYConstant + 2000 && systemY((int) moonY[x]) < shipYConstant -2000){					
				}else{
				if(moonX[x] != 0){
			Color moonColor = new Color((int) moonColorR[x],(int) moonColorG[x],(int) moonColorB[x]);
			g.setColor(moonColor);
			g.fillOval((float)systemX((int) moonX[x]), (float) systemY((int) moonY[x]), (float) moonSize[x],(float)  moonSize[x]);
				}
				}
			}
			
			//DRAWING SUN 
			for(int x = 0; x < sunCounter; x++){
				if(systemX((int) sunX[x]) >  shipXConstant - 2000 && systemX((int) sunX[x]) < shipXConstant + 2000 && systemY((int) sunY[x]) > shipYConstant + 2000 && systemY((int) sunY[x]) < shipYConstant -2000){					
				}else{
			Color sunColor = new Color((int) sunColorR[x],(int) sunColorG[x],(int) sunColorB[x]);
			g.setColor(sunColor);
			g.fillOval((float)systemX((int) sunX[x]), (float) systemY((int) sunY[x]), (float) sunSize[x],(float)  sunSize[x]);
			}
			}
			
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
		case 6:
			totalX = 75;
			totalY = 65;
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
		if(pause == false){
		cordinateX +=shipNetForceX;
		cordinateY +=shipNetForceY;
		}
		ship = new Image("res/ship.png");
		velocityScale = new Image("res/velocityScale.png");
		velocityScaleFront1 = new Image("res/speedFrontGround.png");
		velocityScaleFront2 = new Image("res/speedFrontGround.png");
		ship.setRotation(shipRotation);

		g.drawImage(ship, 600, 300);
		
		//drawing velocity scale(s)?
		g.drawImage(velocityScale, 1051, 482);
		
		Color velocityColor = new Color(94,223,26);
		g.setColor(velocityColor);
		if(pause == false){
			
		g.fillRect(1051, 494,(float) (Math.abs(((shipTotalSpeed*1280)/shipMaxSpeed/5.6))), 48);		
		}else{
			g.fillRect(1051, 494,(float) (Math.abs((((Math.sqrt((shipXSpeedSave*shipXSpeedSave)+(shipYSpeedSave*shipYSpeedSave)))*1280)/shipMaxSpeed/5.6))), 48);		
		}
		g.drawImage(velocityScaleFront1, 1051, 507);
		
		Color surroundColor = new Color(100,100,100);
		Color whiteColor = new Color(200,200,200);		
		Color healthColor = new Color(226,37,37);
		
		//drawing health bar
		g.setColor(surroundColor);
		g.fillRect(1055, 40, 220, 80);
		
		g.setColor(whiteColor);
		g.fillRect(1065, 50, 200, 60);
		
		if(shipCurrentHealth > 0){
		g.setColor(healthColor);
		g.fillRect(1065, 50, (int)((shipCurrentHealth/shipMaxHealth)*200), 60);
		}
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
		
		//DRAWING COIN TOTAL
		g.drawString("Coins : " + coinTotal, 1150, 400);
		
		if(pause == false){
			
			//SETTING CIRCLES
			setCircles();		
		
		//UPDATING PLANETS
		updatingPlanets();
		
		//DRAWING STARS
		drawingStars();
		
		//COLLISION DETECTION
		collisionDetection();
		
		//looping health delay
		if(healthDelay > 0){
			healthDelay--;
		}
		
		}else{
			//drawing pause stuff
			Color pauseBackground = new Color(39,75,116);
			Color borderBack = new Color(124,128,133);
			Color settingsBack = new Color(215,215,215);
			
			g.setColor(pauseBackground);
			g.fillRect(425, 400, 400, 250);
			
			g.setColor(borderBack);
			
			
			g.setColor(settingsBack);
			g.fillRect(435, 590, 50, 50);
			g.fillRect(765, 590, 50, 50);
			
			settingsIcon = new Image("res/settingsIcon.png");
			g.drawImage(settingsIcon, 435, 590);
			
			shopIcon = new Image("res/shopIcon.png");
			g.drawImage(shopIcon, 765, 590);
			
			fUnicodeFont.drawString(550, 415, "Game Paused",Color.white);
			
		}
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
					if(mapScale < 6){
				mapScale++;
					}
				}
			}
		}
		
		//GOING INTO SHOP
		if(pause){
		if(mouseX >= startup.toPixelsX(150) && mouseX <= startup.toPixelsX(160)){
			if(mouseY >= startup.toPixelsY(205) && mouseY <= startup.toPixelsY(222)){
				if(input.isMousePressed(0)){
					shop.setShopValues(coinTotal);
					sbg.enterState(2);
				}
			}
		}
		}
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			gc.exit();
		}
		
		//ROTATING SHIP
		if(pause == false){
		if(input.isKeyDown(Input.KEY_A)|| input.isKeyDown(Input.KEY_LEFT)){
			shipRotation-=3;
		}
		
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
			shipRotation+=3;
		}
		
		//making ship move
		if(input.isKeyDown(Input.KEY_SPACE)){
			spacebar = true;
			spacebarPressed = true;
		}else{
			spacebarPressed = false;
		}
		}
		
		//pausing game
		if(input.isKeyPressed(Input.KEY_P)){
			if(pause){
				pause = false;
			}else{
				pause = true;
			}
			
		}
		
		//slowing ship
		if(input.isKeyDown(Input.KEY_W)){
			
			if(shipXSpeed > 0){
				shipXSpeed -=.04;
			}else{
				shipXSpeed+=.04;
			}
			
			if(shipYSpeed > 0){
				shipYSpeed -=.04;
			}else{
				shipYSpeed+=.04;
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
			if(systemX((int) sunX[x]) >  shipXConstant - 2000 && systemX((int) sunX[x]) < shipXConstant + 2000 && systemY((int) sunY[x]) > shipYConstant + 2000 && systemY((int) sunY[x]) < shipYConstant -2000){					
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
			if(systemX((int) planetX[x]) >  shipXConstant - 2000 && systemX((int) planetX[x]) < shipXConstant + 2000 && systemY((int) planetY[x]) > shipYConstant + 2000 && systemY((int) planetY[x]) < shipYConstant -2000){					
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
			if(systemX((int) moonX[x]) >  shipXConstant - 2000 && systemX((int) moonX[x]) < shipXConstant + 2000 && systemY((int) moonY[x]) > shipYConstant + 2000 && systemY((int) moonY[x]) < shipYConstant -2000){					
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
		
		//UPDATING COINS
		for(int x = 0; x < coinX.length; x ++){
			if(coinAlive[x]){
			if(objectOrbiting[x] != -1){
				//MOVING COINS					
				
				if(coinRotation[x] <= 360){
					coinRotation[x] += coinVelocity[x]/5;
				}else{
					coinRotation[x] = 0;
				}
					
					if(objectOrbiting[x] == 0){
				//X DIRECTION
				coinX[x] = planetX[coinObjectNumber[x]] + (planetSize[coinObjectNumber[x]]/2) - 20 + coinDistance[x]*(Math.cos(Math.toRadians(coinRotation[x])));
				
				//Y DIRECTION			
				coinY[x] = planetY[coinObjectNumber[x]] + (planetSize[coinObjectNumber[x]]/2) -20 + coinDistance[x]*(Math.sin(Math.toRadians(coinRotation[x])));
				}
					
					if(objectOrbiting[x] == 1){
				//X DIRECTION
				coinX[x] = sunX[coinObjectNumber[x]] + (sunSize[coinObjectNumber[x]]/2) - 20 + coinDistance[x]*(Math.cos(Math.toRadians(coinRotation[x])));
				
				//Y DIRECTION			
				coinY[x] = sunY[coinObjectNumber[x]] + (sunSize[coinObjectNumber[x]]/2) -20 + coinDistance[x]*(Math.sin(Math.toRadians(coinRotation[x])));
				}
				}
			}
		}
			
			
		}
	
	//DRAWING EXAUST
	public static void exaust(){	
		
		for(int x = 1; x < 100; x++){
			if(spacebar && exaustOn[x] == false && shipGoing){
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
			exaustStationX[x] = Math.abs(6*Math.cos(Math.toRadians((shipRealRotation))));
			exaustStationDegreeX[x] = 0;
		}else{
			exaustStationX[x] = Math.abs(6*Math.cos(Math.toRadians((shipRealRotation))));
			exaustStationDegreeX[x] = 1;
		}
		if(shipRealRotation >= 180 && shipRealRotation < 360){
			exaustStationY[x] = Math.abs(6*Math.sin(Math.toRadians((shipRealRotation))));
			exaustStationDegreeY[x] = 0;
		}else{
			exaustStationY[x] = Math.abs(6*Math.sin(Math.toRadians((shipRealRotation))));
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
		for(int x = 0; x < starX.length; x++){
			if(playStart == false){
				
				for(int b = 0; b < starX.length; b++){
				starX[b] = -1500 + randomGenerator.nextInt(3500);
				starY[b] = -1000 + randomGenerator.nextInt(3500);
				}
				
				for(int c = 0; c < starMediumX.length; c++){
				starMediumX[c] = -1500 + randomGenerator.nextInt(3500);
				starMediumY[c] = -1000 + randomGenerator.nextInt(3500);
				}
				if(x == starX.length - 1){
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
					
						starX[x] += -shipXSpeed/starSpeed;
						starX[x] += -shipNetForceX/starSpeed;
										
						starY[x] += shipYSpeed/starSpeed;											
						starY[x] += shipNetForceY/starSpeed;

				}
			}
		}
		
		for(int x = 0; x < starMediumX.length; x++){
			if(starMediumX[x] < -1500 || starMediumX[x] > 2500 || starMediumY[x] < -1000 || starMediumY[x] > 2000){
				
				if(starMediumY[x] < -1000){
					starMediumX[x] = -1500 + randomGenerator.nextInt(3500);
					starMediumY[x] = 900 + randomGenerator.nextInt(500);
					}
					if(starMediumY[x] > 2000){
						starMediumX[x] = -1500 + randomGenerator.nextInt(3500);
						starMediumY[x] = -2 - randomGenerator.nextInt(990);
					}
					if(starMediumX[x] < -1500){
						starMediumX[x] = 1290 + randomGenerator.nextInt(500);
						starMediumY[x] = -1000 + randomGenerator.nextInt(3000);
					}
					if(starMediumX[x] > 2500){
						starMediumX[x] = 0 - randomGenerator.nextInt(1290);
						starMediumY[x] = -1000 + randomGenerator.nextInt(3000);
					}

				}else{
					
						starMediumX[x] += -shipXSpeed/starMediumSpeed;
						starMediumX[x] += -shipNetForceX/starMediumSpeed;
						
						starMediumY[x] += shipYSpeed/starMediumSpeed;
						starMediumY[x] += shipNetForceY/starMediumSpeed;					
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
						    objectSuccess ++;
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
						    objectSuccess ++;
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
								    objectSuccess ++;
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
									    objectSuccess ++;
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
										    objectSuccess ++;
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
				    objectSuccess ++;
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
				    objectSuccess ++;
				    }	
				 }
			}else{
					 z--;
			}
			 
			creatingMoon(x, moonCounterPlanetTotal,moonCounterSunTotal, randomX, randomY,createMoonR,createMoonR2, xy);
			}						
			}
			 }
	}
	
	public static void creatingMoon(int x, int moonCounterPlanetTotal, int moonCounterSunTotal, int randomX, int randomY, int createMoonR, int createMoonR2, int xy){
		if((moonCounterSunTotal > x && moonCounterPlanetTotal > x && (createMoonR == 1)) || ((xy == 2 && createMoonR == 1))){
			if((randomX > -300 && randomX < 1400) && (randomY < 900 && randomY > -900) && moonCounter < moonX.length - 1){
			}else{
		int e = 0;
		
		//CREATING FIRST MOON
		if(xy == 0){
			if(planetCounter < planetX.length - 5){
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
		}
	    //CREATING SECOND MOON
		if(createMoonR2 == 1 && moonOrbitingDistance[moonCounter] + moonSize[moonCounter] < moonOrbitingDistance[moonCounter-1] - moonSize[moonCounter-1]){
			
			if(planetCounter < planetX.length - 5){
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
	
	public static void setCoinPoints(){
		int setCoin = 0;
		int coinCounter = 0;
		
			for(int y = 0; y < planetX.length; y++){
				
				if(coinCounter < coin1.length - 1){					
				
				setCoin = randomGenerator.nextInt(3);

				switch(setCoin){
				//orbiting planet
				case 0:
					if(planetX[y] != 0){
						
						coinVelocity[coinCounter] = 2 + randomGenerator.nextInt(10);
						
					objectOrbiting[coinCounter] = 0;
					coinObjectNumber[coinCounter] = y;					
					
				coinDistance[coinCounter] = 200 + randomGenerator.nextInt(300);
				
					coinX[coinCounter]= planetX[y] - coinDistance[coinCounter] - (planetSize[y]/2) + 45;
					coinY[coinCounter]= planetY[y] - coinDistance[coinCounter] - (planetSize[y]/2) + 45;
					
					setCoin = 10;
					}
				break;
				
				//orbiting sun
				case 1:
					if(sunX[y] != 0){
						
						coinVelocity[coinCounter] = 1 + randomGenerator.nextInt(4);
						
					coinDistance[coinCounter] = 800 + randomGenerator.nextInt(800);
					
					objectOrbiting[coinCounter] = 1;		
					coinObjectNumber[coinCounter] = y;
					
					
					coinX[coinCounter]= sunX[y] + (sunSize[y]/2) - coinDistance[coinCounter] - (sunSize[y]/2) + 45;
					coinY[coinCounter]= sunY[y] + (sunSize[y]/2) - coinDistance[coinCounter] - (sunSize[y]/2) + 45;
					
					}
				break;
				
				//staying still
				case 2:
					coinX[coinCounter] = -(areaWidth/2) + randomGenerator.nextInt(areaWidth);
					coinY[coinCounter] = -(areaHeigth/2) + randomGenerator.nextInt(areaHeigth);
					objectOrbiting[coinCounter] = -1;
					break;
				}
				coinCounter++;
				}
				setCoin = 0;
			}
	}	
	
	//first Y is up and second Y is down
	public static void collisionDetection(){

		//MAP BORDERS
		if((cordinateX + shipXConstant) <= systemX((-areaWidth) - 1500)){
			shipXSpeed += (double) shipMaxSpeed/20;
		}
		
		if((cordinateX + shipXConstant) >= systemX((areaWidth) + 2350)){
			shipXSpeed -= (double) shipMaxSpeed/20;
		}
		
		if((cordinateY) >= (areaHeigth/2) + 830){
			shipYSpeed -= (double) shipMaxSpeed/20;
		}
		
		if((cordinateY) <= (-areaHeigth/2) - 1150){
			shipYSpeed += (double) shipMaxSpeed/20;
		}
		
		
		//COINS
		for(int x = 0; x < coinX.length; x++){
			
			if(square.intersects(coins[x]) || square.contains(coins[x])){
				coinTotal ++;

				coinX[x] = -10000;
				coinY[x] = -10000;
				coinAlive[x] = false;
		}else{			
			
		}
		}		
		
		//PLANETS
		
		for(int x = 0; x < planetX.length; x++){
			
			if(square.intersects(planets[x]) || square.contains(planets[x])){
				
				if(healthDelay <= 0){
				healthDelay = 40;
				shipCurrentHealth-=(Math.sqrt(((shipXSpeed*shipXSpeed) + (shipYSpeed*shipYSpeed)))*8)+8;
				}
				
				//x dir
				if(shipXConstant > systemX((int) planetX[x])){
					
					if(healthDelay == 40){
					shipXSpeed= Math.abs(shipNetForceX) + 1.5;
					}else{
					shipXSpeed += 1;	
					}
					
				}else{
					if(healthDelay == 40){
					shipXSpeed= -Math.abs(shipNetForceX) -1.5;
					}else{
						shipXSpeed -= 1;	
					}
				}
				
				//y dir
				
				if(shipYConstant > systemY((int) ((int) planetY[x]+(planetSize[x]/2)))){
					
					if(healthDelay == 40){
					shipYSpeed= -Math.abs(shipNetForceY) - 1.5;
					}else{
						shipYSpeed-=1;	
					}
					
				}else{
					
					if(healthDelay == 40){
					shipYSpeed= Math.abs(shipNetForceY) + 1.5;		
					}else{
						shipYSpeed+=1;	
					}
					
				}
			 
		}
		}
		
		//SUNS
		
		for(int x = 0; x < sunX.length; x++){
			
			if(square.intersects(suns[x]) || square.contains(suns[x])){
				
				if(healthDelay <= 0){
				healthDelay = 40;
				shipCurrentHealth-=(Math.sqrt(((shipXSpeed*shipXSpeed) + (shipYSpeed*shipYSpeed)))*8) + 8;
				}
				
				//x dir
				if(shipXConstant > systemX((int) sunX[x])){
					
					if(healthDelay == 40){
					shipXSpeed= Math.abs(shipNetForceX) +2.5;
					}else{
					shipXSpeed += 1;	
					}
					
				}else{
					if(healthDelay == 40){
					shipXSpeed= -Math.abs(shipNetForceX) -2.5;
					}else{
						shipXSpeed -= 1;	
					}
				}
				
				//y dir
				
				if(shipYConstant > systemY((int) ((int) sunY[x]+(planetSize[x]/2)))){
					
					if(healthDelay == 40){
					shipYSpeed= -Math.abs(shipNetForceY) - 2.5;
					}else{
						shipYSpeed-=1;	
					}
					
				}else{
					
					if(healthDelay == 40){
					shipYSpeed= Math.abs(shipNetForceY) + 2.5;		
					}else{
						shipYSpeed+=1;	
					}
					
				}
			 
		}
		}
		
		//MOONS
		
		for(int x = 0; x < moonX.length; x++){
			
			if(square.intersects(moons[x]) || square.contains(moons[x])){
				
				if(healthDelay <= 0){
				healthDelay = 40;
				shipCurrentHealth-=(Math.sqrt(((shipXSpeed*shipXSpeed) + (shipYSpeed*shipYSpeed)))*8) + 8;
				}
				
				//x dir
				if(shipXConstant > systemX((int) moonX[x])){
					
					if(healthDelay == 40){
					shipXSpeed= Math.abs(shipNetForceX) +1.5;
					}else{
					shipXSpeed += 1;	
					}
					
				}else{
					if(healthDelay == 40){
					shipXSpeed= -Math.abs(shipNetForceX) -1.5;
					}else{
						shipXSpeed -= 1;	
					}
				}
				
				//y dir
				
				if(shipYConstant > systemY((int) ((int) moonY[x]+(planetSize[x]/2)))){
					
					if(healthDelay == 40){
					shipYSpeed= -Math.abs(shipNetForceY) - 1.5;
					}else{
						shipYSpeed-=1;	
					}
					
				}else{
					
					if(healthDelay == 40){
					shipYSpeed= Math.abs(shipNetForceY) + 1.5;		
					}else{
						shipYSpeed+=1;	
					}
					
				}
			 
		}
		}
		
	}
	
	//resetting variables from shop
	public static void settingPlayVariables(int coins){
		coinTotal = coins;
	}

	public static void setCircles(){
		
		//planets
		for(int x = 0; x < planetX.length; x++){
			
				if(planetX[x] !=0){
			planets[x].setCenterX((float) (systemX((int) planetX[x]) + planetSize[x]/2));
			planets[x].setCenterY((float) (systemY((int)planetY[x]) + planetSize[x]/2));
			planets[x].setRadius((int)(planetSize[x]/2));
				}
		}
		
		//suns
		for(int x = 0; x < sunX.length; x++){
			
			if(sunX[x] !=0){
		suns[x].setCenterX((float) (systemX((int) sunX[x]) + sunSize[x]/2));
		suns[x].setCenterY((float) (systemY((int)sunY[x]) + sunSize[x]/2));
		suns[x].setRadius((int)(sunSize[x]/2));
			}
	}
		//moons
		for(int x = 0; x < moonX.length; x++){
			
			if(moonX[x] !=0){
		moons[x].setCenterX((float) (systemX((int) moonX[x]) + moonSize[x]/2));
		moons[x].setCenterY((float) (systemY((int)moonY[x]) + moonSize[x]/2));
		moons[x].setRadius((int)(moonSize[x]/2));
			}
	}
		
		//coins
		for(int x = 0; x < coinX.length; x++){
		
		coins[x].setCenterX((float) (systemX((int) coinX[x]) + 15));
		coins[x].setCenterY((float) (systemY((int)coinY[x]) + 15));
		coins[x].setRadius(30);
	}
	}
	
}
