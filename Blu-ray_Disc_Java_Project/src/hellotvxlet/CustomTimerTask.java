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

    public void run() {
        currentTime = this.repaintTime();
        System.out.println(currentTime);
    }
    
    public int repaintTime () {
        i++;
        return i;
    }
}
