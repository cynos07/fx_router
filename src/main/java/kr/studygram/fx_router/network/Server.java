package kr.studygram.fx_router.network;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cynos07 on 2017-04-26.
 */
public enum Server implements Runnable {
    INSTANCE;
    private ServerSocket server;
    private Socket client;
    private PrintWriter writer;
    private BufferedReader reader;

    private final int PORT = 5555;

    @Override
    public void run() {
        try {
            server = new ServerSocket(PORT);
            client = server.accept();

            InetAddress inetaddr = client.getInetAddress();
            System.out.println(inetaddr.getHostAddress() + "클라이언트 연결");

            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();

            writer = new PrintWriter(new OutputStreamWriter(out));
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;

            while((line = reader.readLine()) != null)
            {
                if(line.startsWith("Connect::"))
                {
                    writer.println("Connect::Success"); writer.flush();
                }
            }
            reader.close();
            writer.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message)
    {
        if(writer != null) {
            writer.println(message);
            writer.flush();
        }
        else{
            System.out.println("연결된 클라이언트가 없습니다.");
        }
    }
    public static Server getInstance()
    {
        return INSTANCE;
    }

}
