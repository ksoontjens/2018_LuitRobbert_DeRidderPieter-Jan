
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
    
    ArrayList list = new ArrayList();
    ArrayList colorValues = new ArrayList();
    ArrayList colorValuesUserInput = new ArrayList();
    
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

    // Voegt een random kleur toe aan de colorValues array
    public String RandomColor(){
        randomNumber = minValue + (int)(Math.random() * maxValue);
        Integer number = new Integer(randomNumber);
        list.add(number);
        String x = (String) colorNames[randomNumber - 1];
        this.AddToColorValuesArray(x);
        System.out.println("ColorValuesArray = " + colorValues);
        return x;
    }

    // Score toevoegen
    public int AddScore()  {
        score++;
        scene.add(scoreLabel);
        System.out.println("Score: " + score);
        
        String currentScore = scoreLabel.getTextContent(HVisible.NORMAL_STATE);
        currentScore    = "Score: " + score;
        
        scoreLabel.setTextContent(currentScore, HVisible.NORMAL_STATE);
        scoreLabel.repaint();
        
        return score;
    }
    
    // Speelveld waarden terug op 0 zetten (score, kleuren array, etc)
    public int ResetScore() {
        
        //@TODO array moet nog terug naar een lege array gezet worden
        //colorValues.clear();
        //System.out.println(colorValues);
        
        score = 0;
        scene.add(scoreLabel);
        
        String currentScore = scoreLabel.getTextContent(HVisible.NORMAL_STATE);
        currentScore    = "" + score;
                
        scoreLabel.setTextContent(currentScore, HVisible.NORMAL_STATE);
        scoreLabel.repaint();

        return score;
    }

    public void AddToUserInputArray(String value) {
        colorValuesUserInput.add(value);
        // System.out.println(colorValuesUserInput);
    }

    public void AddToColorValuesArray(String value) {
        colorValues.add(value);
        //System.out.println(colorValues);
    }

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

        System.out.println("colorvalues array = " + colorValues.size());
        System.out.println("colorvalues userinput array = " + colorValuesUserInput.size());
        
        // @TEST blauw.setVisible(false);

            // Check of de laatste kleur van de array overeen komt met de kleur die op de gedrukte aanwezig was
            if(colorValues.get(colorValues.size() -1 ) == e.getActionCommand()) {

                // System.out.println("Punt erbij, er werd op de correcte kleur: " + e.getActionCommand() + " gedrukt" );
                // System.out.println("De kleur die op de laatste plek staat in de array = " + colorValues.get(colorValues.size() -1 ));

                // Voegt score toe bij een juist 'kleur antwoord'
                this.AddScore();

                // Voeg een nieuwe kleur toe aan het einde van de vorige colorValueArray()
                String newcolor = (String)this.RandomColor();
                System.out.println("Nieuwe kleur toegevoegd aan de colorValuesArray: " + newcolor);
            } else {
                // Zet de score terug op 0 bij een foute ingave
                this.ResetScore();
            }
    }

    public void callable(int seconds) {
        for(int i = 0; i < (int)colorValues.size(); i++ ) {
            System.out.println("aaa " + colorValues.size());
            if (colorValues.size() == colorValuesUserInput.size()) {
                // System.out.println("cvalues size = " + colorValues.size());
                // System.out.println("userinput size = " + colorValuesUserInput.size());
                //Voeg een nieuwe kleur toe aan de kleurenarray zodat deze voor de volgende beurt kan dienen
                this.RandomColor();
            }                
        }
        
        // TEST
        if(colorValues.size() > seconds - 1) {
            this.highlightTile((String)colorValues.get(seconds-1));
        }
        
        //this.highlightTile((String)colorNames[seconds - 1]);
        System.out.println("Huidige tijd: " + seconds);
    }

    public void startXlet() {

        // Check of de scene kan worden aangemaakt, zet hem zichtbaar
        scene.validate();
        scene.setVisible(true);

        // Zet een random kleur bij het begin van het spel in de colorValueArray()
        this.RandomColor();

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