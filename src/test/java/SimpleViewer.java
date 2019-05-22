/*
 * Copyright (c) 2003 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;

import vavi.media.ui.cc.ClosedCaption;
import vavi.media.ui.cc.Viewer;
import vavi.util.Debug;


/**
 * サブタイトルビューアのサンプルです。
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 030218 nsano initial version <br>
 *          0.01 030305 nsano be simple <br>
 */
public class SimpleViewer extends JWindow implements Viewer {

    /** */
    private JTextArea textArea;

    /** */
    public SimpleViewer() {
        try {
            textArea = new JTextArea(2, 20);
//            textArea.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 120));
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("tmp/cinecaption226.ttf"));
            textArea.setFont(font.deriveFont(120f));
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            textArea.setFocusable(false);
            textArea.setForeground(Color.magenta);
            textArea.setBackground(new Color(0, 0, 0, 0));

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            setPreferredSize(new Dimension((int) (screen.width * 0.7), (int) (screen.height * 0.25)));

            //
            setBackground(new Color(0, 0, 0, 0));

            JRootPane root = getRootPane();
            root.putClientProperty("Window.shadow", false);

            setAlwaysOnTop(true);

            getContentPane().add(textArea, BorderLayout.CENTER);

            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    /** */
    public void showClosedCaption(ClosedCaption cc) {
Debug.println(cc.getText());
        textArea.setText(cc.getText());

        pack();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2,
                    (screen.height / 4 - getHeight()) / 2 +
                     screen.height / 8 * 6);
    }
}

/* */
