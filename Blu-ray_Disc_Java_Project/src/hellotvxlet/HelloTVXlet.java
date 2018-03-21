package hellotvxlet;

import javax.tv.xlet.*; // Xlet

// AWT
import java.awt.event.*; // ActionListener

// variabelen topLeft, topRight, bottomLeft, bottomRight 
import java.awt.*;// variabelen topLeft, topRight, bottomLeft, bottomRight 

// AWT Events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // ActionListener
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// SWING
import javax.swing.JFrame; // variabele frame
import javax.swing.Renderer; // 

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
import org.havi.ui.event.HActionListener;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HState;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;

public class HelloTVXlet implements Xlet, ActionListener, MouseListener {
     
    // Hoogte & breedte waarin het spel gaat runnen
    public static int WIDTH     = 600;        
    public static int HEIGHT    = 600;   

    // Kleuren die bij Simon Says horen
    private Color topLeft       = Color.GREEN;
    private Color topRight      = Color.RED;
    private Color bottomLeft    = Color.YELLOW;
    private Color bottomRight   = Color.BLUE;

    // Essentiele dingen
    private boolean startGame   = false;
    private boolean endGame     = false;
    private int score;

    // Random
    private Random random;

    // JFrame
    private JFrame frame;
    public Renderer renderer; 

    public HelloTVXlet() {

        renderer = new Renderer();
        
        // Kader waarin het spel gaat runnen
        frame = new JFrame("SimonSays"); // Titel
        
        frame.setSize(HEIGHT, WIDTH); // Hoogte & Breedte
        frame.setResizable(false); // Vaste breedte en hoogte (kan aangepast worden)
        frame.setLayout(new BorderLayout()); // Layout structuur

        frame.addMouseListener(this); // Om te testen

        pattern = new ArrayList<Integer>(); // Patroon waarin kleuren worden afgespeeld..

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
