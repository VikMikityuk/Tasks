package View;

import javax.swing.*;
import java.awt.*;


/**
 * Выводит оповещение в виде окна с текстом.
 */

public class NotificationView {

    /**
     * Создает окно оповещения.
     *
     * @param str текстовое поле, которое отображается в оповещении
     */

    public static void createGUI(String str) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("NOTIFICATION");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JLabel label = new JLabel(str);
        Font font = new Font("Verdana", Font.PLAIN, 20);
        label.setFont(font);
        label.setIcon(new ImageIcon("cat.jpg"));
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }



}
