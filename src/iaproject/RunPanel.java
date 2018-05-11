/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaproject;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author s506717
 */
public class RunPanel extends JPanel {

    LinkedList<Integer> storage = new LinkedList<>();
    int populationdensity = 0;
    int virulence;
    int infectivity;
    int lifecycle;
    int count = 0;
    double infectionchance;
    Random r = new Random();
    java.util.Timer timer = new java.util.Timer();
    int chance = 0;
    int delay5 = 5000;
    int delay4 = 4000;
    int delay3 = 3000;
    int delay2 = 2000;
    humanpixel pixel = new humanpixel();
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screensize = tk.getScreenSize();
    int width = screensize.width / 2;
    int height = screensize.height;
    ArrayList<humanpixel> pixellist = new <humanpixel> ArrayList();
    ArrayList<Integer> xcoordinates = new <Integer> ArrayList();
    ArrayList<Integer> ycoordinates = new <Integer> ArrayList();
    ArrayList<Integer> infectedpixelplace = new <Integer> ArrayList();
    boolean found = false;
    int x = r.nextInt(width - 40);
    int y = r.nextInt(height - 40);
    int randompixel = 0;
    JLabel Healthy = new JLabel("Number of Healthy: ");
    JLabel numhealthy = new JLabel("" + (100 - count));
    JLabel infected = new JLabel("Number of Infected: ");
    JLabel numinfected = new JLabel("" + count);

    public RunPanel() {

        super(); //Call to JPanel Class
        System.out.println(populationdensity);
        this.setBounds(0, 100, width, 570);
        this.setBackground(Color.black);
        this.setLayout(null);
        Healthy.setBounds(800, 550, 180, 40);
        Healthy.setForeground(new Color(73, 242, 21));

        numhealthy.setBounds(1000, 550, 180, 40);
        numhealthy.setForeground(new Color(73, 242, 21));

        infected.setBounds(800, 610, 180, 40);
        infected.setForeground(new Color(216, 19, 55));
        numinfected.setBounds(1000, 610, 180, 40);
        numinfected.setForeground(new Color(216, 19, 55));
        addpixeltoList();
        addpixeltoPanel();
    }

    public void addpixeltoList() { //Adds 100 humanpixel objects to a humanpixel arraylist
        for (int i = 0; i < 100; i++) {
            pixellist.add(new humanpixel());
        }
    }

    public void addpixeltoPanel() { //adds the 100 pixel objects to the RunPanel
        for (int i = 0; i < 100; i++) {
            arrangex();
            arrangey();
            pixellist.get(i).setBounds(x, y, 10, 10); //Sets Random X and Random Y coordinates

        }
        for (int i = 0; i < 100; i++) {
            this.add(pixellist.get(i)); //Adds pixel objects to panel
            this.pixellist.get(i).setVisible(true);
        }
    } //Post Condition: pixels added to panel

    public int arrangex() { //Generates Random X coordinate

        x = r.nextInt(width);
        xcoordinates.add(x);

        return x;
    } //Post Condition: Random X coordinate created 

    public int arrangey() { //Generates Random Y coordinate
        y = r.nextInt(height);
        ycoordinates.add(y);
        return y;
    } //Post Condition: Random Y coordinate created

    public void infectfirstpixel() {
        randompixel = r.nextInt(100);
        pixellist.get(randompixel).setBackground(Color.red);
        count++;
        storage.add(randompixel);

    } //Infect one random Pixel object

    public void setpopulationdensity(int x) {
        populationdensity = x;
    } //Sets populationdensity

    public void setvirulence(int x) {
        virulence = x;
    }// Sets virulence

    public void setinfectivity(int x) {
        infectivity = x;
    }// Sets infectivity

    public void infectiontime() { //Uses variable values to color objects using a timer task
        if (count < 100) {
            infectionchance = (r.nextInt(6) + infectivity) * (r.nextInt(11) + populationdensity) + (count);

            if (infectionchance <= 67 && infectionchance > 0) {
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        infection(); //

                    }
                }, 0, 150);

            } else if (infectionchance > 67 && infectionchance <= 133) {
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        infection();
                    }
                }, 0, 100);
            } else if (infectionchance > 133 && infectionchance <= 200) {
                timer.scheduleAtFixedRate(new TimerTask() {

                    public void run() {
                        infection();
                    }
                }, 0, 50);
            } else if (infectionchance > 200 && infectionchance <= 266) {
                timer.schedule(new TimerTask() {
                    public void run() {
                        infection();
                    }
                }, 0, 10);
            }
        }

    }

    public void infection() { // Colors random object, and keeps count of infected and healthy

        chance = r.nextInt(100); //Random Integer
        found = false; // Boolean Value
        for (int i = 0; i < storage.size(); i++) {
            if (chance == storage.get(i)) { //if random int is equal to any element in List
                found = true; // Set boolean value 
                break;

            } else {
                found = false;
                numhealthy.setText("" + (100 - count));
                numinfected.setText("" + count);

            }
        }

        if (found == false) {
            storage.add(chance);
            pixellist.get(chance).setBackground(new Color(216, 19, 55));
            count++;
            numhealthy.setText("" + (100 - count));
            numinfected.setText("" + count);
        }

    }

}
