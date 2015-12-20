package View;

import javax.swing.*;
import java.awt.*;

public class NotificationView {
    public static void createGUI(String str) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("NOTIFICATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//TODO

        JLabel label = new JLabel(str);
        frame.getContentPane().add(label);

        frame.setPreferredSize(new Dimension(200, 100));

        frame.pack();
        frame.setVisible(true);
    }



}
