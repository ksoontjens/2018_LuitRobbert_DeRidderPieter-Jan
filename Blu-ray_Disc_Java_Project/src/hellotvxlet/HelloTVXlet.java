package hellotvxlet;

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


public class HelloTVXlet implements Xlet {
    private HScene scene;
    public HelloTVXlet() {
        int[] colorsArray;
    }

    private HStaticText tekstLabel;
    private HTextButton knop1, knop2, knop3, knop4;
    
    public void initXlet(XletContext context) throws XletStateChangeException {
        HSceneTemplate sceneTemplate    = new HSceneTemplate();
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
        new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION,
        new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
        
        scene   = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        
        tekstLabel  = new HStaticText("Simon Says");
        tekstLabel.setLocation(250, 200);
        tekstLabel.setSize(400, 250);
        
        knop1   = new HTextButton("KNOP 1");
        knop1.setLocation(100, 100);
        knop1.setSize(100, 50);
        knop1.setBackground(new DVBColor(241, 54, 54, 100));
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop2   = new HTextButton("KNOP 2");
        knop2.setLocation(100, 200);
        knop2.setSize(100, 50);
        knop2.setBackground(new DVBColor(241, 54, 54, 100));
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop3   = new HTextButton("KNOP 3");
        knop3.setLocation(250, 100);
        knop3.setSize(100, 50);
        knop3.setBackground(new DVBColor(241, 54, 54, 100));
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop4   = new HTextButton("KNOP 4");
        knop4.setLocation(250, 200);
        knop4.setSize(100, 50);
        knop4.setBackground(new DVBColor(241, 54, 54, 100));
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop1.setFocusTraversal(null, knop2, null, knop3);
        knop2.setFocusTraversal(knop1, null, null, knop4);
        knop3.setFocusTraversal(null, knop4, knop1, null);
        knop4.setFocusTraversal(knop3, null, knop2, null);
        
        knop1.setActionCommand("knop1_actioned");
        
        scene.add(knop1);
        scene.add(knop2);
        scene.add(knop3);
        scene.add(knop4);
        
        knop1.requestFocus();
        
        /* Background for textlabel */
        /*
        tekstLabel.setBackground(new DVBColor(255, 255, 255, 0));
        tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        */
        
        scene.add(tekstLabel);
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
