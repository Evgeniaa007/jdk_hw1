import javax.swing.*;
import java.awt.*;

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
        setLocationRelativeTo(null);
    }

    private Component createTopPanel(){
        topPanel = new JPanel(new GridLayout(2, 3));
        ipField = new JTextField ("127.0.0.1");
        portField = new JTextField ("8189");
        loginField = new JTextField ("Ivan_Igorevich");
        passwField = new JTextField ("123456");
        btnLogin = new JButton("Login");

        topPanel.add(ipField);
        topPanel.add(portField);
        topPanel.add(loginField);
        topPanel.add(passwField);
        topPanel.add(btnLogin);

        return topPanel;
        }

        private Component createLog(){
            log = new JTextArea();
            log.setEditable(false);
            return new JScrollPane(log);
        }

        private Component createBottomPanel(){
            bottomPanel = new JPanel(new BorderLayout());
            messageField = new JTextField();
            btnSend = new JButton("Send");

            bottomPanel.add(messageField);
            bottomPanel.add(btnSend);

            return bottomPanel;
        }


}


