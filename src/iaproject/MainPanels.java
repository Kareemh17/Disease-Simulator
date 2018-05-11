/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iaproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.*;

public class MainPanels {

    Queue<Integer> storage = new LinkedList<Integer>() {
    };
    Timer timer = new Timer();
    humanpixel human = new humanpixel();
    ArrayList<humanpixel> HumanList = new ArrayList<humanpixel>();
    RunPanel run = new RunPanel();
    Font plagueFont = new Font("ENGRAVERS MT", Font.BOLD, 12);
    private JFrame titleframe;
    public static JFrame simulationframe;
    private JPanel titlePanel;
    public static JPanel simulationpanel;
    private JButton titlebutton;
    private JButton popdensbutton;
    private JButton virulencebutton;
    private JButton infectivitybutton;
    private JTextField popdensfield;
    private JTextField virulencefield;
    private JTextField infectivityfield;
    private JLabel background;
    private JLabel Label;
    private JLabel Label2;
    private JLabel popdenslabel;
    private JLabel virulencelabel;
    private JLabel infectivitylabel;
    private JLabel popdensvaluelabel;
    private JLabel virulencevaluelabel;
    private JLabel infectivityvaluelabel;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screensize = tk.getScreenSize();
    Random r = new Random();
    double screenwidth = screensize.getWidth();
    double screenheight = screensize.getHeight();
    int randomxcoordinate = r.nextInt((int) screenwidth);
    int randomycoordinate = r.nextInt((int) screenheight);
    Color panelcolor = new Color(43, 43, 43);
    String popdensitystring;
    String virulencestring;
    String infectivitystring;
    int popdensity = 0;
    int virulence = 0;
    int infectivity = 0;
    JButton simulationbutton;

    public void guidisplay() {

        ImageIcon ii = new ImageIcon(getClass().getResource("/images/DNA.jpg"));
        Image image = ii.getImage();
        Image newimg = image.getScaledInstance(screensize.width,
                screensize.height, java.awt.Image.SCALE_DEFAULT);

        ii = new ImageIcon(newimg);
        titleframe = new JFrame("IA Project");
        titleframe.setVisible(true);
        titleframe.setSize(screensize);
        titleframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        background = new JLabel();
        background.setIcon((ii));
        background.setSize(screensize);

        Label = new JLabel("Plague Simulator");
        Label.setBounds(screensize.width / 2 - 110, 0, 200, 100);
        Label.setFont(plagueFont);
        Label.setForeground(Color.white);

        titlePanel.add(Label);

        Label2 = new JLabel("Created by Kareem Hirani");
        Label2.setBounds(screensize.width / 2 - 150, screensize.height / 2 + 100, 280, 200);
        Label2.setFont(plagueFont);
        Label2.setForeground(Color.white);
        titlePanel.add(Label2);

        titlePanel.setBackground(Color.red);

        titlePanel.add(background, 0);
        titlePanel.add(Label, 0);
        titlePanel.add(Label2, 0);

        titlebutton = new JButton("Begin The Simulation");
        titlebutton.setBounds(screensize.width / 2 - 170, screensize.height / 2 - 100, 300, 100);
        titlebutton.setFont(plagueFont);
        titlebutton.setForeground(Color.black);
        titlePanel.add(titlebutton, 0);

        titleframe.add(titlePanel, 0);

        titlebutton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) { //Creates Secondary Frame
                titleframe.setVisible(false);
                Label.setVisible(false);
//Create Frame
                simulationframe = new JFrame("IA Project");
                simulationframe.setVisible(true);
                simulationframe.setSize(screensize);
                simulationframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Font plagueFont = new Font("ENGRAVERS MT", Font.BOLD, 12);
//Create variablePanel
                simulationpanel = new JPanel();
                simulationpanel.setLayout(null);
                simulationpanel.setSize(screensize);
                simulationpanel.setBackground(panelcolor);
                background = new JLabel();
                background.setSize(screensize);

// Create Label
                simulationbutton = new JButton("Begin Simulation");
                simulationbutton.setBounds(screensize.width / 2 - 150, 50, 300, 30);
                simulationbutton.setFont(plagueFont);
                simulationbutton.setForeground(Color.black);
                simulationbutton.setBackground(Color.white);

//Create textfield and labelfor populationdensity
                popdenslabel = new JLabel("Enter Population Density, from 1 to 9");
                popdenslabel.setBounds(800, 40, 400, 200);
                popdenslabel.setForeground(Color.WHITE);

                popdensfield = new JTextField();
                popdensfield.setBounds(1100, 100, 90, 70);

                popdensvaluelabel = new JLabel();
                popdensvaluelabel.setBounds(1100, 100, 90, 70);

                popdensvaluelabel.setForeground(Color.WHITE);
                popdensvaluelabel.setVisible(false);
                popdensbutton = new JButton("Confirm");
                popdensbutton.setBounds(800, 180, 90, 40);
                popdensbutton.setForeground(Color.white);
                popdensbutton.setBackground(Color.black);
                popdensbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        popdensitystring = popdensfield.getText();
                        popdensity = Integer.parseInt(popdensitystring);
                        System.out.println("Population Density in Main Panels: " + popdensity);
                        run.setpopulationdensity(popdensity);
                        popdensfield.setVisible(false);
                        popdenslabel.setText("Population Density:");
                        popdensbutton.setVisible(false);
                        popdensvaluelabel.setText(popdensfield.getText());
                        popdensvaluelabel.setVisible(true);

                        //Code to repaint panel to meet population density
                        for (int i = 0; i < run.pixellist.size(); i++) {
                            run.pixellist.get(i).setBounds(run.arrangex()
                                    / popdensity, run.arrangey() / popdensity, 10, 10);
                            run.repaint();
                        }

                    }
                });

//create textfield and label for virulence
                virulencelabel = new JLabel("Enter Disease Virulence, from 1 to 9");
                virulencelabel.setBounds(800, 200, 400, 200);
                virulencelabel.setForeground(Color.white);
                virulencefield = new JTextField();
                virulencefield.setBounds(1100, 270, 90, 70);
                virulencevaluelabel = new JLabel();
                virulencevaluelabel.setBounds(1100, 270, 90, 70);
                virulencevaluelabel.setForeground(Color.white);
                virulencevaluelabel.setVisible(false);

                virulencebutton = new JButton("Confirm");
                virulencebutton.setBounds(800, 360, 90, 40);
                virulencebutton.setForeground(Color.white);
                virulencebutton.setBackground(Color.black);
                virulencebutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        virulencestring = virulencefield.getText();
                        virulence = Integer.parseInt(virulencestring);
                        System.out.println("Virulence in main panels: " + virulencestring);
                        run.setvirulence(virulence);

                        virulencefield.setVisible(false);
                        virulencelabel.setText("Disease Virulence:");

                        virulencebutton.setVisible(false);

                        virulencevaluelabel.setText(virulencefield.getText());

                        virulencevaluelabel.setVisible(true);

                    }
                });
                //Create TextField and Labels for infectivity
                infectivitylabel = new JLabel("Enter Disease Infectivity, from 1 to 9");
                infectivitylabel.setBounds(800, 360, 400, 200);
                infectivitylabel.setForeground(Color.white);
                infectivityfield = new JTextField();
                infectivityfield.setBounds(1100, 420, 90, 70);
                infectivityvaluelabel = new JLabel("");
                infectivityvaluelabel.setBounds(1100, 420, 90, 70);
                infectivityvaluelabel.setForeground(Color.white);
                infectivityvaluelabel.setVisible(false);
                infectivitybutton = new JButton("Confirm");
                infectivitybutton.setBounds(800, 500, 90, 40);
                infectivitybutton.setForeground(Color.white);
                infectivitybutton.setBackground(Color.black);
                infectivitybutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        infectivitystring = infectivityfield.getText();
                        infectivity = Integer.parseInt(infectivitystring);
                        System.out.println("Infectivity in Main Panels: " + infectivity);
                        run.setinfectivity(infectivity);
                        infectivityfield.setVisible(false);
                        infectivitylabel.setText("Disease Infectivity:");

                        infectivitybutton.setVisible(false);

                        infectivityvaluelabel.setText(infectivityfield.getText());
                        infectivityvaluelabel.setVisible(true);

                    }
                });

                simulationbutton.addActionListener(new ActionListener() { //Calls methods from RunPanel Class
                    public void actionPerformed(ActionEvent e) {
                        simulationbutton.setVisible(false);
                        run.infectfirstpixel();
                        run.infectiontime();
                    }
                });

//Format and Layer Frame
                simulationpanel.add(background, 0);
                simulationpanel.add(simulationbutton, 0);
                simulationpanel.add(popdenslabel, 0);
                simulationpanel.add(popdensfield, 0);
                simulationpanel.add(popdensvaluelabel, 0);
                simulationpanel.add(popdensbutton, 0);
                simulationpanel.add(virulencefield, 0);
                simulationpanel.add(virulencelabel, 0);
                simulationpanel.add(virulencebutton, 0);
                simulationpanel.add(infectivityfield, 0);
                simulationpanel.add(infectivityvaluelabel, 0);
                simulationpanel.add(infectivitylabel, 0);
                simulationpanel.add(infectivitybutton, 0);
                simulationpanel.add(virulencevaluelabel, 0);
                simulationpanel.add(run);
                simulationpanel.add(run.Healthy);
                simulationpanel.add(run.numhealthy);
                simulationpanel.add(run.infected);
                simulationpanel.add(run.numinfected);
                simulationframe.add(simulationpanel);
            }
        });

    }

}
