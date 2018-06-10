
package hellotvxlet;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;
import org.havi.ui.HScreenDimension;
import org.havi.ui.HScreenPoint;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet implements Xlet, HActionListener {

    private HScene scene;
    private HStaticText tekstLabel;
    private HStaticText tekstbyLabel;
    private HStaticText scoreLabel;
    private HStaticText timeLabel;
    private HTextButton groen, rood, blauw, geel;
    
    int score = 0;
    int minValue = 1;
    int maxValue = 4;
    int randomNumber;
    int seconds = 0;
    String scoreString = "0";
    
    int timeLighted     = 0;
    int prevState       = 0;
    boolean wasActive   = false;
    
    ArrayList list = new ArrayList();
    ArrayList colorValues = new ArrayList();
    ArrayList colorValuesUserInput = new ArrayList();

    String colorValuesUserInputString = "";
    String[] colorNames = {
        "GROEN",
        "ROOD",
        "BLAUW",
        "GEEL"
    };    

    DVBColor colorzwart     = new DVBColor(0, 0, 0, 255);
    DVBColor colortxtlabel  = new DVBColor(0, 0, 0, 120);

    //Donkere kleuren
    DVBColor colorgroen     = new DVBColor(0, 255, 0, 50);
    DVBColor colorrood      = new DVBColor(255, 0, 0, 50);
    DVBColor colorblauw     = new DVBColor(0, 0, 255, 50);
    DVBColor colorgeel      = new DVBColor(255, 255, 0, 50);
    //Lichte kleuren
    DVBColor colorgroenL    = new DVBColor(0, 255, 0, 150);
    DVBColor colorroodL     = new DVBColor(255, 0, 0, 150);
    DVBColor colorblauwL    = new DVBColor(0, 0, 255, 150);  
    DVBColor colorgeelL     = new DVBColor(255, 255, 0, 150);

    boolean activeHigh;

    public HelloTVXlet() {

    }

    // Initialisatie bij het begin van het spel
    public void initXlet(XletContext context) throws XletStateChangeException {
        HSceneTemplate sceneTemplate    = new HSceneTemplate();

        // Timer timer = new Timer();
        // // Om de halve seconde
        // timer.scheduleAtFixedRate(null, 0, 500); 
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
        new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
        new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
        
        scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        scene.setBackground(colorzwart);
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        tekstLabel = new HStaticText("Simon Says");
        tekstLabel.setLocation(250, 200);
        tekstLabel.setSize(400, 400);

        tekstbyLabel = new HStaticText("by Pieter-Jan & Robbert");
        tekstbyLabel.setLocation(250, 250);
        tekstbyLabel.setSize(400, 400);    

        scoreLabel = new HStaticText("0");
        scoreLabel.setLocation(400, 75);
        scoreLabel.setSize(200, 200);

        // timeLabel = new HStaticText("0");
        // timeLabel.setLocation(250, 50);
        // timeLabel.setSize(400, 400);
        
        groen = new HTextButton((String) colorNames[0]);
        groen.setLocation(100, 100);
        groen.setSize(100, 100);
        groen.setBackground(colorgroen);
        groen.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        rood = new HTextButton((String) colorNames[1]);
        rood.setLocation(100, 200);
        rood.setSize(100, 100);
        rood.setBackground(colorrood);
        rood.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        blauw = new HTextButton((String) colorNames[2]);      
        blauw.setLocation(200, 100);
        blauw.setSize(100, 100);
        blauw.setBackground(colorblauw);
        blauw.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        geel = new HTextButton((String) colorNames[3]);
        geel.setLocation(200, 200);
        geel.setSize(100, 100);
        geel.setBackground(colorgeel);
        geel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        groen.setFocusTraversal(null, rood, null, blauw);
        rood.setFocusTraversal(groen, null, null, geel);
        blauw.setFocusTraversal(null, geel, groen, null);
        geel.setFocusTraversal(blauw, null, rood, null);
        
        groen.setActionCommand((String) colorNames[0]);
        groen.addHActionListener(this);
        scene.add(groen);
  
        rood.setActionCommand((String) colorNames[1]);
        rood.addHActionListener(this);
        scene.add(rood);
        
        blauw.setActionCommand((String) colorNames[2]);
        blauw.addHActionListener(this);
        scene.add(blauw);
        
        geel.setActionCommand((String) colorNames[3]);
        geel.addHActionListener(this);
        scene.add(geel);
        
        groen.requestFocus();
        rood.requestFocus();
        blauw.requestFocus();
        geel.requestFocus();
        
        tekstLabel.setBackground(colortxtlabel);
        tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);

        tekstbyLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        scene.add(tekstLabel);
        scene.add(tekstbyLabel);
        scene.add(scoreLabel);
        // scene.add(timeLabel);
    }

    // Zet de kleurwaarden terug op de standaard niet opgelichte kleuren
    public void resetTileColors() {
        groen.setBackground(colorgroen);
        rood.setBackground(colorrood);
        blauw.setBackground(colorblauw);
        geel.setBackground(colorgeel);

        groen.repaint();
        rood.repaint();
        blauw.repaint();
        geel.repaint();
    }

    // Voegt een random kleur toe aan de colorValues array
    public String randomColor(){
        randomNumber = minValue + (int)(Math.random() * maxValue);
        Integer number = new Integer(randomNumber);
        list.add(number);
        String x = (String) colorNames[randomNumber - 1];
        this.addToColorValuesArray(x);
        // System.out.println("ColorValuesArray = " + colorValues);
        return x;
    }

    // Score toevoegen
    public int addScore()  {
        score++;
        scene.add(scoreLabel);
        
        String currentScore = scoreLabel.getTextContent(HVisible.NORMAL_STATE);
        currentScore    = "Score: " + score;
        
        scoreLabel.setTextContent(currentScore, HVisible.NORMAL_STATE);
        scoreLabel.repaint();
        
        return score;
    }
    
    // Score reset
    public int resetScore() {
        score = 0;
        scene.add(scoreLabel);
        
        String currentScore = scoreLabel.getTextContent(HVisible.NORMAL_STATE);
        currentScore    = "" + score;
                
        scoreLabel.setTextContent(currentScore, HVisible.NORMAL_STATE);
        scoreLabel.repaint();

        return score;
    }

    // Voeg toe aan de userInput array
    public void addToUserInputArray(String value) {
        colorValuesUserInput.add(value);
        System.out.println("USER INPUT ARRAY = " + colorValuesUserInput);
    }

    // Voeg toe aan de colorValues array
    public void addToColorValuesArray(String value) {
        colorValues.add(value);
        System.out.println("COLOR VALUES ARRAY = " + colorValues);
    }

    // Laat een tegel oplichten
    public void highlightTile(String color) {
        int nr = 0;
        if(color == (String)colorNames[0]) {
            nr = 0;
        }
        if(color == (String)colorNames[1]) {
            nr = 1;
        }
        if(color == (String)colorNames[2]) {
            nr = 2;
        }
        if(color == (String)colorNames[3]) {
            nr = 3; 
        }
        switch(nr) {
            case 0: 
                groen.setBackground(colorgroenL);
                groen.repaint();
            break;
            case 1: 
                rood.setBackground(colorroodL);
                rood.repaint();
            break;
            case 2: 
                blauw.setBackground(colorblauwL);
                blauw.repaint();
            break;
            case 3: 
                geel.setBackground(colorgeelL);
                geel.repaint();
            break;
        } 
    }
    
    // Checkt letterlijk welke actie er worden uitgevoerd
    public void actionPerformed(ActionEvent e) {

        // Logt op welke kleur er gedrukt wordt
        System.out.println("Er wordt op kleur: " + e.getActionCommand() + " gedrukt");
        this.addToUserInputArray(e.getActionCommand());
        
        // Check of de lengtes van beide arrays gelijk zijn, pas dan gaan checken of de inhoudt ook hetzelfde is
        if(colorValues.size() == colorValuesUserInput.size()) 
        {
            if (colorValues.equals(colorValuesUserInput)) 
            {
                wasActive       = false;
                
                // Voegt score toe bij een juist 'kleur antwoord'
                this.addScore();

                // Voeg een nieuwe kleur toe aan het einde van de vorige colorValueArray()
                this.randomColor();
                colorValuesUserInput.clear();
            } 
            else 
            {
                // Zet de score terug op 0 bij een foute ingave
                this.resetScore();
                colorValuesUserInput.clear();
                colorValues.clear();
                this.randomColor();
            }
        }

    }

    public void callable(int seconds) {
        System.out.println("User input = " + colorValuesUserInput);

        // Versnel het spel naar mate de score hoger wordt
        if ( score <= 5 ) {
            timeLighted     = 2000;
        }
        else if ( score > 5 && score <= 10 ) {
            timeLighted     = 1500;
        }
        else if ( score > 10 && score <= 20 ) {
            timeLighted     = 1000;
        }
        else if ( score > 20 && score <= 30 ) {
            timeLighted     = 750;
        }
        else if ( score > 20 && score <= 30 ) {
            timeLighted     = 500;
        }
        else {
            timeLighted     = 300;
        }
            
        // Als de arrays overeen komen in lengte dan is alles ingevoerd dat gechecked moet worden
        if(colorValues.size() == colorValuesUserInput.size()) 
        {
            wasActive       = false;

            //Voeg een nieuwe kleur toe aan de kleurenarray zodat deze voor de volgende beurt kan dienen
            String newcolor = this.randomColor();
            System.out.println("Nieuwe kleur toegevoegd aan de colorValuesArray: " + newcolor);
        }
        
        // Loop door de kleuren array om ze te laten oplichten 1 voor 1 , voorlopig licht enkel de laatste nieuwe op
        if(colorValues.size() != colorValuesUserInput.size()) {
            if ( !wasActive ) {            
                for(int i = 0; i < colorValues.size(); i++) {
                    // Wacht 1 seconde
                    try {
                        Thread.sleep( 1000 );
                    } catch ( InterruptedException ex ) {
                        System.out.println( ex );
                    }

                    // Do stuff na 1 seconde
                    this.highlightTile((String)colorValues.get(i));

                    // Wacht 1.5 seconden
                    try {
                        Thread.sleep( timeLighted );
                    } catch ( InterruptedException ex ) {
                        System.out.println( ex );
                    }

                    // Do stuff na 1.5 seconden
                    this.resetTileColors(); 
                    wasActive   = true;
                }
            }
        } else {
            this.resetTileColors();
        }
    }

    public void startXlet() {

        // Check of de scene kan worden aangemaakt, zet hem zichtbaar
        scene.validate();
        scene.setVisible(true);

        // Zet een random kleur bij het begin van het spel in de colorValueArray()
        this.randomColor();

        // Custom timer elementen
        Timer timer         = new Timer();
        CustomTimerTask ctt = new CustomTimerTask(this);
        timer.scheduleAtFixedRate(ctt, 0, 1000);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

}