/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author SHAH SAMI
 *//**/
public class JavaGame extends JFrame implements Runnable{
    
    int x,y, xDirection, yDirection;
    int bx, by;
    private Image dbImage;
    private Graphics dbg;
    Image heart;
    boolean readyToFire, shot = false;
    
    Rectangle bullet;
    
    public void run(){
        try{
            while(true){
                shoot();
                move();
                Thread.sleep(5);
            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
    
//    Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
    public void move(){
        x += xDirection;
        y+= yDirection;
        if(x <= 0)
            x = 0;
        if(x >= 200)
            x = 200;
        if(y <= 0)
            y = 0;
        if(y >= 200)
            y = 200;
        
    }
    public void shoot(){
            if (shot)
                bullet.x--;
            }
    public void setXDirection(int xdir){
        xDirection = xdir;
    }
    public void setYDirection(int ydir){
        yDirection = ydir;
    }
    public class AL extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode == e.VK_LEFT){
                setXDirection(-1);
            }
            if(keyCode == e.VK_RIGHT){
                setXDirection(+1);
            }
            if(keyCode == e.VK_UP){
               setYDirection(-1);
            }
            if(keyCode == e.VK_DOWN){
               setYDirection(+1);
            }
            if(keyCode == e.VK_SPACE){
               if (bullet == null)
                   readyToFire = true;
               if (readyToFire){
                  //x+18, y-7
                   by =  y+15;
                   bx = x;
                   bullet = new Rectangle(bx, by, 3, 5);
                   shot = true;
               }
            }
            
            
        }
       
        public void keyReleased(KeyEvent e){
            
             int keyCode = e.getKeyCode();
            if(keyCode == e.VK_LEFT){
                setXDirection(0);
            }
            if(keyCode == e.VK_RIGHT){
                setXDirection(0);
            }
            if(keyCode == e.VK_UP){
               setYDirection(0);
            }
            if(keyCode == e.VK_DOWN){
               setYDirection(0);
            }
            if(keyCode == e.VK_SPACE){
               readyToFire = true;
               if(bullet.x <= -5){
                   bullet = new Rectangle(0, 0, 0, 0);
                   shot = false;
                   readyToFire = true;
               }
            }
        
            
        }
        
}   
    
    public JavaGame(){
        //load images
        ImageIcon i = new ImageIcon("C:/Users/SHOWHARDO/Documents/NetBeansProjects/JavaGame/src/javagame/bheart.gif");
        heart = i.getImage();
        //game properties
        addKeyListener(new AL());
        setTitle("Java Game");
        setSize(250, 250);
        setResizable(false);
        setVisible(true);
        setBackground(Color.CYAN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        x = 150;
        y = 150;
    }
    
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
        
        g.setColor(Color.red);
        //g.fillRect(x, y, 40, 10);
       //g.fillRect(x+18, y-7, 4, 7);
        if(shot){
            g.setColor(Color.red);
           // g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
            g.fillOval(bullet.x, bullet.y, bullet.width, bullet.height);
        }
        
    }
    public void paintComponent(Graphics g){
        
//        g.setFont(font);
        g.setColor(Color.magenta);
//        g.drawString("Hello World", 50, 50);
        g.setColor(Color.red);
//        g.fillOval(x, y, 15, 15);
        g.drawImage(heart, x, y, this);
        repaint();       
    }
    
    public static void main(String[] args) {
        
        JavaGame jg = new JavaGame();  
        //Threads
        Thread t1 = new Thread(jg);
        t1.start();
    }
    
}
