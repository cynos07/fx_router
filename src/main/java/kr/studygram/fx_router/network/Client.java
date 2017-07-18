package kr.studygram.fx_router.network;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import sample.Controller;

import java.io.*;
import java.net.Socket;

/**
 * Created by cynos07 on 2017-04-26.
 */
public enum Client2 implements Runnable{
    INSTANCE;
    Socket socket;
    private final int SERVER_PORT = 5555;
    PrintWriter writer;
    BufferedReader reader;
    String line = null;
    private int status = 0;
    private ObservableList<String> listItems;

    public void init(ObservableList<String> listItems)
    {
        this.listItems = listItems;
    }

    @Override
    public void run() {
        try {
            socket = new Socket("127.0.0.1", SERVER_PORT);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            writer = new PrintWriter(new OutputStreamWriter(out));
            reader = new BufferedReader(new InputStreamReader(in));

            while ((line = reader.readLine()) != null) {
                if(line == "Connect::Success")
                    status = 200;
                if(line == "Connect::Failed")
                    status = 500;
            }
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message)
    {
        if(writer!=null)
        {
            writer.println(message);
            writer.flush();
        }
        else{
            System.out.println("no server connection");
        }

    }

    public static Client getInstance()
    {
        return INSTANCE;
    }
}
