import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    boolean isServerWorking;
    JButton btnStart;
    JButton btnStop;
    JTextArea log;

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Chat Server");
        createServerWin();
        setVisible(true);
    }


    // Метод, который создает кнопки запуска и остановки на начальном окне
    private Component createButtons(){
        JPanel serverW = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isServerWorking){
                    appendLog("Сервер уже запущен.");
                }
                else{
                    isServerWorking = true;
                    appendLog("Сервер запущен!");
                }
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                appendLog("Работа сервера остановлена.");
            }
        });
        serverW.add(btnStart);
        serverW.add(btnStop);
        return serverW;
    }

    //Метод сборки окна сервера
    private void createServerWin(){
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    //Метод добавления текста в окне сервера
    private void appendLog(String text){
        log.append(text + "\n");
    }



}
