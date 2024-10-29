import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGui extends JFrame {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    ServerWindow serverWindow;
    private boolean connected;


    JPanel topPanel;
    JPanel bottomPanel;
    JTextField loginField, passwField, ipField, portField, messageField;
    JTextArea log;
    JButton btnLogin, btnSend;

    ClientGui(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(serverWindow.getX() - 500, serverWindow.getY());
        createClientWin();
        setVisible(true);
    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    public void displayText(){
        String message = messageField.getText();
        appendLog(message);

    }

    //верхняя часть окна клиента
    private Component createTopPanel(){
        topPanel = new JPanel(new GridLayout(2, 3));
        ipField = new JTextField ("127.0.0.1");
        portField = new JTextField ("8189");
        loginField = new JTextField ("Ivan_Igorevich");
        passwField = new JPasswordField ("123456");
        btnLogin = new JButton("Login");

        topPanel.add(ipField);
        topPanel.add(portField);
        topPanel.add(loginField);
        topPanel.add(passwField);
        topPanel.add(btnLogin);

        return topPanel;
        }

    //окно вывода сообщений
    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    //нижняя часть окна клиента, связанная с наббором и отправкой сообщений
    private Component createBottomPanel(){
        bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayText();
            }
        });

        bottomPanel.add(messageField);
        bottomPanel.add(btnSend, BorderLayout.EAST);

        return bottomPanel;
    }

    // формирование окна клиента
    private void createClientWin(){
        add(createTopPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createBottomPanel(), BorderLayout.SOUTH);
    }



}


