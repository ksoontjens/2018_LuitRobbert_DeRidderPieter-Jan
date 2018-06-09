/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class CustomTimerTask extends TimerTask {

    int currentTime;
    HelloTVXlet xlet;
    
    // Constructor
    public CustomTimerTask(HelloTVXlet x) {
        xlet = x;
    }

    public void run() {
        this.currentTime++;
        xlet.callable(currentTime);;
    }
}
