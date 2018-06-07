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
    
    public void run() {
        i++;
        System.out.println(i);
    }
    
    
    public void bla () {
        
    }
}
