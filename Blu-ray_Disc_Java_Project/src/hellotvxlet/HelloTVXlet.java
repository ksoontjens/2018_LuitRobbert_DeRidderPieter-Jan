package hellotvxlet;

import java.awt.event.*;
import java.util.ArrayList;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.event.*;
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
    
    int minValue = 1;
    int maxValue = 4;
    int randomNumber;

    ArrayList list = new ArrayList();
    ArrayList colorValues = new ArrayList();
    ArrayList colorNames = new ArrayList();

    

    DVBColor colorgroen     = new DVBColor(255, 0, 0, 50);
    DVBColor colorrood      = new DVBColor(0, 255, 0, 50);
    DVBColor colorblauw     = new DVBColor(0, 0, 255, 50);
    DVBColor colorgeel      = new DVBColor(255, 255, 0, 50);
    DVBColor colorzwart     = new DVBColor(0, 0, 0, 255);
    
    public HelloTVXlet() {
        String[] colorNames = {
            "GROEN",
            "ROOD",
            "BLAUW",
            "GEEL"
        };

    }

    private HStaticText tekstLabel;
    private HTextButton groen, rood, blauw, geel;
    
    public void initXlet(XletContext context) throws XletStateChangeException {
        HSceneTemplate sceneTemplate    = new HSceneTemplate();
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
        new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
        new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
        
        scene   = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        scene.setBackground(colorzwart);
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        tekstLabel  = new HStaticText("Simon Says");
        tekstLabel.setLocation(250, 200);
        tekstLabel.setSize(400, 250);
        
        groen = new HTextButton(colorNames.get(Integer(0)));
        groen.setLocation(100, 100);
        groen.setSize(100, 50);
        groen.setBackground(colorgroen);
        groen.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        rood = new HTextButton(colorNames.get(Integer(1)));
        rood.setLocation(100, 200);
        rood.setSize(100, 50);
        rood.setBackground(colorrood);
        rood.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        blauw = new HTextButton(colorNames.get(Integer(2)));
        blauw.setLocation(250, 100);
        blauw.setSize(100, 50);
        blauw.setBackground(colorblauw);
        blauw.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        geel = new HTextButton(colorNames.get(Integer(3)));
        geel.setLocation(250, 200);
        geel.setSize(100, 50);
        geel.setBackground(colorgeel);
        geel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        groen.setFocusTraversal(null, rood, null, blauw);
        rood.setFocusTraversal(groen, null, null, geel);
        blauw.setFocusTraversal(null, geel, groen, null);
        geel.setFocusTraversal(blauw, null, rood, null);
        
        groen.setActionCommand(colornames[0]);
        groen.addHActionListener(this);
  
        rood.setActionCommand(colornames[1]);
        rood.addHActionListener(this);
        
        blauw.setActionCommand(colornames[2]);
        blauw.addHActionListener(this);
        
        geel.setActionCommand(colornames[3]);
        geel.addHActionListener(this);
        
        scene.add(groen);
        scene.add(rood);
        scene.add(blauw);
        scene.add(geel);
        
        groen.requestFocus();
        
        /* Background for textlabel */
        /*
        tekstLabel.setBackground(new DVBColor(255, 255, 255, 0));
        tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        */
        
        scene.add(tekstLabel);
    }

    public RandomNumber(){
        randomNumber = minValue + (int)(Math.random() * maxValue);
        Integer number = new Integer(randomNumber);
        list.add(number);
        System.out.println(colorNames[randomNumber-1]);
        return colorNames[randomNumber-1];
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        this.RandomNumber();
    }
    
    public void startXlet() {
        scene.validate();
        scene.setVisible(true);
        colorValues.add(this.RandomNumber());
        System.out.println(colorValues);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}
