/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.rage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Richard Dong
 */
public class TutorialSlideShow extends JPanel {
    
    private JFrame tutorialScreen;
    private ArrayList<Image>imageList;
    private int currentSlideNumber;
    private final int numberSlides=3;
    private Font font;
    
    public TutorialSlideShow() {
        tutorialScreen = new JFrame();
        tutorialScreen.setSize(800,800);
        font = new Font("Arial", Font.PLAIN, 12);//set font
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//size
        tutorialScreen.setLocation((int)(screenSize.getWidth()/2)-400,(int)(screenSize.getHeight()/2)-400);//set location to center
        tutorialScreen.setTitle("Tutorial");//title
        tutorialScreen.setResizable(false);
        tutorialScreen.getContentPane().add(this);
        tutorialScreen.repaint();//paints on info
        tutorialScreen.setVisible(true);
        this.setLayout(null);
        imageList = new ArrayList<Image>();
        for(int i=2;i<4;i++) {
            imageList.add(ImageManager.getImage(i));
        }
        currentSlideNumber = 0;
        addButtons();
    }
    
    private void addButtons() {
        
        JButton back = new JButton();
        //draws out the upgrade health onto button
        back.setBorderPainted(false);
        back.setContentAreaFilled(true);
        back.setHorizontalTextPosition(SwingConstants.CENTER);
        back.setText("Back");
        back.setFont(font);
        back.setForeground(Color.black);
        back.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                currentSlideNumber--;
                if(currentSlideNumber<0) {
                    tutorialScreen.dispose();
                }
                addButtons();
                repaint();
            }
        });
        back.setBounds(0,0,100,50);//set location
        this.add(back);//add to frame.
        
        JButton next = new JButton();
        //draws out the upgrade health onto button
        next.setBorderPainted(false);
        next.setContentAreaFilled(true);
        next.setHorizontalTextPosition(SwingConstants.CENTER);
        next.setText("Next");
        next.setFont(font);
        next.setForeground(Color.black);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                currentSlideNumber++;
                System.out.println(currentSlideNumber);
                addButtons();
                repaint();
            }
        });
        next.setBounds(700,0,100,50);//set location
        if(currentSlideNumber<numberSlides) {
            this.add(next);//add to frame.
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        try {
            g.drawImage(imageList.get(currentSlideNumber),0,0,getWidth(),getHeight(),null);
        }
        catch(Exception ex) {
            ErrorLogger.logRuntimeError("Could not draw menu graphic", ex);
        }
        repaint();//refresh screen
    }
    
}
