package hellotvxlet;

import javax.tv.xlet.*;

// AWT
import java.awt.event.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

// UTILS
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

// DVB
import org.dvb.ui.*;
import org.dvb.event.*;

// UI
import org.havi.ui.event.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HState;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;

import sun.net.www.content.text.plain;

public class HelloTVXlet implements Xlet {
  
    // Core dingen
    private boolean startGame;
    private boolean endGame;
    private int score;

    // 
    private Random random;


    /** Panel colors. */
    private Color linksBoven = Color.GREEN;
    private Color topRightColor = Color.RED;
    private Color bottomLeftColor = Color.YELLOW;
    private Color bottomRightColor = Color.BLUE;

    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
      
     
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}
