package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private boolean isServerWorking;
    private JButton btnStart;
    private JButton btnStop;

    public ServerWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Chat Server");
        isServerWorking = false;
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
            }
        });


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
            }
        });

        JPanel serverW = new JPanel(new GridLayout(1, 2));
        serverW.add(btnStart);
        serverW.add(btnStop);
        add(serverW);
        setVisible(true);

    }





}
