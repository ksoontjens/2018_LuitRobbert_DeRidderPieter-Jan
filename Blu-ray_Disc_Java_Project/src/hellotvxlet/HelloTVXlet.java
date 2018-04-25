package hellotvxlet;

import java.awt.event.*;
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

import javafx.scene.effect.BlurType;


public class HelloTVXlet implements Xlet, HActionListener {
    private HScene scene;
    
    int minValue = 1;
    int maxValue = 4;
    int randomNumber;

    ArrayList list = new ArrayList();
    ArrayList colorValues = new ArrayList();
    String[] colorNames = {
        "RED",
        "BLUE",
        "GREEN",
        "YELLOW"
    };

    Color colorrood = new DVBColor(241, 54, 54, 100);

    public HelloTVXlet() {

    }

    private HStaticText tekstLabel;
    private HTextButton groen, rood, bo, knop4;
    
    public void initXlet(XletContext context) throws XletStateChangeException {
        HSceneTemplate sceneTemplate    = new HSceneTemplate();
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
        new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
        new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
        
        scene   = HSceneFactory.getInstance().getBestScene(sceneTemplate).setBack;
        
        tekstLabel  = new HStaticText("Simon Says");
        tekstLabel.setLocation(250, 200);
        tekstLabel.setSize(400, 250);
        
        groen = new HTextButton(colorNames[0]);
        groen.setLocation(100, 100);
        groen.setSize(100, 50);
        groen.setBackground(new DVBColor(241, 54, 54, 100));
        groen.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        rood = new HTextButton(colorNames[1]);
        rood.setLocation(100, 200);
        rood.setSize(100, 50);
        rood.setBackground(colorrood);
        rood.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        blauw = new HTextButton(colorNames[2]);
        blauw.setLocation(250, 100);
        blauw.setSize(100, 50);
        blauw.setBackground(new DVBColor(241, 54, 54, 100));
        blauw.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        geel = new HTextButton(colorNames[3]);
        geel.setLocation(250, 200);
        geel.setSize(100, 50);
        geel.setBackground(new DVBColor(241, 54, 54, 100));
        geel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        groen.setFocusTraversal(null, rood, null, knop3);
        rood.setFocusTraversal(knop1, null, null, knop4);
        blauw.setFocusTraversal(null, knop4, knop1, null);
        geel.setFocusTraversal(knop3, null, knop2, null);
        
        groen.setActionCommand("groen");
        groen.addHActionListener(this);
  
        rood.setActionCommand("rood");
        rood.addHActionListener(this);
        
        blauw.setActionCommand("blauw");
        blauw.addHActionListener(this);
        
        geel.setActionCommand("geel");
        geel.addHActionListener(this);
        
        scene.add(groen);
        scene.add(rood);
        scene.add(blauw);
        scene.add(geel);
        
        knop1.requestFocus();
        
        /* Background for textlabel */
        /*
        tekstLabel.setBackground(new DVBColor(255, 255, 255, 0));
        tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        */
        
        scene.add(tekstLabel);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
    
    public void startXlet() {
        scene.validate();
        scene.setVisible(true);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}
