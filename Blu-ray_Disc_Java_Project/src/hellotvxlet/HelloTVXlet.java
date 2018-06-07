
package hellotvxlet;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
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
    private HStaticText scoreLabel;
    private HTextButton groen, rood, blauw, geel;
    
    int minValue = 1;
    int maxValue = 4;
    int randomNumber;
    int score = 0;
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

    DVBColor colorgroen     = new DVBColor(0, 255, 0, 50);
    DVBColor colorrood      = new DVBColor(255, 0, 0, 50);
    DVBColor colorblauw     = new DVBColor(0, 0, 255, 50);
    DVBColor colorgeel      = new DVBColor(255, 255, 0, 50);
    DVBColor colorzwart     = new DVBColor(0, 0, 0, 255);
    DVBColor colortxtlabel  = new DVBColor(0, 0, 0, 120);
    
    public HelloTVXlet() {

    }

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
        
        scoreLabel = new HStaticText("0");
        scoreLabel.setLocation(400, 50);
        scoreLabel.setSize(200, 200);
        
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
        
        scene.add(tekstLabel);
        scene.add(scoreLabel);
    }

    public String RandomNumber(){
        randomNumber = minValue + (int)(Math.random() * maxValue);
        Integer number = new Integer(randomNumber);
        list.add(number);
        String x = (String) colorNames[randomNumber - 1];
        colorValues.add(x);
        System.out.println("ColorValuesArray = " + colorValues);
        return x;
    }

    public String NumberInUserArray(){
        randomNumber = minValue + (int)(Math.random() * maxValue);
        Integer number = new Integer(randomNumber);
        list.add(number);
        String x = (String) colorNames[randomNumber - 1];
        colorValuesUserInput.add(x);
        System.out.println("UserValueArray = " + colorValuesUserInput);
        return x;
    }

    public int AddScore()  {
        score++;
        scoreString = Integer.toString(score);
        scene.add(scoreLabel);
        System.out.println("Score = " + score);
        
        String currentScore = scoreLabel.getTextContent(HVisible.NORMAL_STATE);
        currentScore    = "" + score;
        
        scoreLabel.setTextContent(currentScore, HVisible.NORMAL_STATE);
        scoreLabel.repaint();
        
        return score;
    }
            
    public void actionPerformed(ActionEvent e) {

        // Logt op welke kleur er gedrukt wordt
        System.out.println("actioncommand = " + e.getActionCommand());

        // System.out.println(colorValues.size());
        // System.out.println(colorValuesUserInput.size());
        if(colorValues.get(colorValues.size() -1 ) == e.getActionCommand()) {
            int result = (int)colorValues.size() - (int)1;
            System.out.println(result);

            System.out.println("PUNT ERBIJ! = " + e.getActionCommand() );
            System.out.println("De kleur die op de laatste plek staat in de array = " + colorValues.get(colorValues.size() -1 ));

            for(int i = 0; i < result; i++ ) {
                System.out.println(i);
                if (colorValues.size() == colorValuesUserInput.size()) {
                    this.RandomNumber();
                }                
            }

            // Voegt score toe bij een juist 'kleurantwoord'
            this.AddScore();

            // Voeg een nieuwe kleur toe aan het einde van de vorige colorValueArray()
            String newcolor = (String)this.RandomNumber();
            System.out.println("new color = " + newcolor);
        }
    }
    
    public void startXlet() {

        // Check of de scene kan worden aangemaakt, daarna zet hem zichtbaar
        scene.validate();
        scene.setVisible(true);

        // Zet een random kleur bij het begin van het spel in de colorValueArray()
        this.RandomNumber();
        
        // Custom timer elementen
        CustomTimerTask objCustomTimerTask  = new CustomTimerTask();
        Timer timer     = new Timer();
        timer.scheduleAtFixedRate(objCustomTimerTask, 0, 1000);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}