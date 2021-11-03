package util;

import javax.swing.JButton;


public class View {
    
    public static void  BTransparent(JButton button){
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }
    
}
