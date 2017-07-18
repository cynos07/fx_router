package kr.studygram.fx_router.network;

import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;

/**
 * Created by cynos07 on 2017-04-26.
 */
public enum Client implements Runnable{
    INSTANCE;
    private final String ipAddress;
    Socket socket;
    private final int SERVER_PORT;
    PrintWriter writer;
    BufferedReader reader;
    String line = null;
    private int status = 0;
    private ObservableList<String> listItems;

//    public Client(String ipAddress, final int SERVER_PORT)
//    {
//        this.ipAddress = ipAddress;
//        this.SERVER_PORT = SERVER_PORT;
//    }

    Client()
    {
        this.ipAddress = "10.220.146.61";
        this.SERVER_PORT = 9999;
    }

    public void init(ObservableList<String> listItems)
    {
        this.listItems = listItems;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ipAddress, SERVER_PORT);
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
