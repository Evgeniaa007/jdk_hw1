import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ServerWindow extends JFrame{
    public static final String LOG_FILE = "src\\main\\java\\serverLog.txt";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    boolean isServerWorking;
    JButton btnStart;
    JButton btnStop;
    JTextArea log;
    ArrayList<ClientGui> clients; //для добавления пользователей и работы с ними

    public ServerWindow() {
        clients = new ArrayList<>();
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
        log.setEditable(false);
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    //Метод добавления текста в окне сервера
    public void appendLog(String text){
        log.append(text + "\n");
    }

    // получение записей из файла
    public String getLog() {
        return readLog();
    }
    // при работе сервера добавляет клиента в список для дальнейшей работы
    public boolean loginClient(ClientGui client){
        if(isServerWorking){
            clients.add(client);
            return true;
        }
        else{
            return false;
        }
    }
    // запись в файл
    public void saveLog(String text){
        try(FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // чтение из файла
    public String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try(FileReader reader = new FileReader(LOG_FILE)){
            int c;
            while((c=reader.read())!=-1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //метод, позволяющий отображать сообщения, а также сохранять их в файл
    public void message(String text){
        if (isServerWorking) {
            text += "";
            appendLog(text);
            totalDisplay(text);
            saveLog(text);
        }

    }

    private void totalDisplay(String text){
        for (ClientGui client: clients){
            client.appendLog(text);
        }
    }


}
