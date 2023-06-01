
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame{

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        
        OyunEkrani ekrani = new OyunEkrani("Gök Taşını Vur");
        
        ekrani.setVisible(false);
        ekrani.setFocusable(false);
        ekrani.setSize(800,600);
        ekrani.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekrani.add(oyun);
        ekrani.setVisible(true);
        
        
        
        
    }
    
    
    
}
