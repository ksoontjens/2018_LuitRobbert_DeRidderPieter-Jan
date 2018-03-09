package hellotvxlet;

import javax.tv.xlet.*; // Xlet

// AWT
import java.awt.event.*; // ActionListener
import java.awt.*;
import java.awt.Color; // variabelen topLeft, topRight, bottomLeft, bottomRight 
import java.awt.event.ActionEvent;
import java.awt.Graphics;

//  
import javax.swing.JFrame; // variabele frame

// UTILS
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random; // variabele random
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

public class HelloTVXlet implements Xlet, ActionListener {
     
    // Hoogte & breedte waarin het spel gaat runnen
    public static int WIDTH     = 600;        
    public static int HEIGHT    = 600;   

    // Kleuren die bij Simon Says horen
    private Color topLeft       = Color.GREEN;
    private Color topRight      = Color.RED;
    private Color bottomLeft    = Color.YELLOW;
    private Color bottomRight   = Color.BLUE;

    // Essentiele dingen
    private boolean startGame;
    private boolean endGame;
    private int score;

    // 
    private Random random;
    private JFrame frame;
    
    public HelloTVXlet() {
        // Start het spel
        startGame = true;
        // Score op 0 bij start van het spel
        score = 0;
        // Nieuwe random bij begin van het spel
        random = new Random();
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
