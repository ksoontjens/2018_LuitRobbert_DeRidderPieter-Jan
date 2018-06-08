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

    int i = 0;
    int currentTime;

    HelloTVXlet xlet;
    
    // Constructor
    public CustomTimerTask(HelloTVXlet x) {
        xlet = x;
    }

    public void run() {
        currentTime = this.repaintTime();
        System.out.println(currentTime);
        this.runCallable();
    }
    
    // Gaat optellen in tijd
    public int repaintTime () {
        i++;
        return i;
    }

    public void runCallable() {
        HelloTVXlet hello = new HelloTVXlet();
        hello.callable(currentTime);
    } 
}
