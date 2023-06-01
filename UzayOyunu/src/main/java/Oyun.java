
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
    private int x;
    private int y; 

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Oyun extends JPanel implements KeyListener,ActionListener{
    
    Timer timer = new Timer(5,this);
    
    private int gecen_sure = 0;
    private int harcanan_ates = 0;
    
    private BufferedImage image;

    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    
    private int atesdirY = 15;
    private int topX = 0;
    private int topY = 0;
    private int topdirY = 8;
    private int topdirX = 5;
    private int uzaygemisiX = 0;
    private int dirUzayX = 20;
    
    public boolean kontrolEt(){
        
        for(Ates ates : atesler){
            
            if (new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,25,25))) {
                
                return true;
                
            }
            
            
        }
        return false;
        
    }

    public Oyun() {
        
        try {
            image = ImageIO.read(new FileImageInputStream(new File("roket.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.black);
        
        timer.start();
        
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        gecen_sure += 5;
        g.setColor(Color.red);
        g.fillOval(topX, topY, 25, 25);
        g.drawImage(image, uzaygemisiX, 515, image.getWidth()/18,image.getHeight()/18,this);
        
        g.setColor(Color.yellow);
        g.fillRect(0, 145, 800, 8);
        
        for(Ates ates : atesler){
            if(ates.getY() < 0){
                
                atesler.remove(ates);
            }
        }
        g.setColor(Color.BLUE);
        
        for(Ates ates : atesler){
            
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        if (kontrolEt()) {
            
            
            
            timer.stop();
            String message = "Kazandınız.... \n" + 
                    "Harcanan Ateş : " + harcanan_ates + 
                    "\n Geçen süre : " + gecen_sure/1000.0; 
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
            
        }
        
        
        
    }

    @Override
    public void repaint() {
        super.repaint(); 
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int c = e.getKeyCode();
        
        switch (c) {
            case KeyEvent.VK_LEFT:
                if (uzaygemisiX <= 0) {
                    uzaygemisiX = 0;
                    
                }
                else{
                    uzaygemisiX -= dirUzayX;
                }   break;
            case KeyEvent.VK_RIGHT:
                if (uzaygemisiX >= 750) {
                    uzaygemisiX = 750;
                    
                }
                else{
                    uzaygemisiX += dirUzayX;
                }   break;
            case KeyEvent.VK_CONTROL:
                atesler.add(new Ates(uzaygemisiX+10,490));
                harcanan_ates++;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(Ates ates : atesler){
            ates.setY(ates.getY() - atesdirY);
        }
        
        topX += topdirX;
        topY += +topdirY;
        
        if (topX >= 775 || topX <= 0) {
            topdirX = -topdirX;
            
        }
        if (topY >= 125|| topY <= 0) {
            topdirY = -topdirY;
            
        }
        
        repaint();
    }
    
}
