package main;

import java.io.*;
import java.net.*;

public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args)  {

    ServerSocket ServSock = null;
        try {
            ServSock = new ServerSocket(PORT);
            System.out.println("Старт: " + ServSock);
            try {
                System.out.println("Ожидание подключения...");
                while (true) {
                    Socket socket = ServSock.accept();
                    System.out.println("Соединение установлено: " + socket);
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())), true);

                        String str = in.readLine();
                        System.out.println(str);


                        try {
                           out.print("HTTP/1.1 200 OK \r\n\r\n");
                            String address = str.split(" ")[1].substring(1);
                            out.println(address);
                            BufferedReader reader = new BufferedReader(new FileReader(address));
                            String b;
                            b = reader.readLine();
                            while (b != null) {
                                System.out.println(b);
                                out.println(b);
                                b = reader.readLine();

                            }
                            out.flush();
                            reader.close();
                        } catch (Exception ex) {
                        }


                    } finally {
                        socket.close();
                    }
                }
            } finally {
                ServSock.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }
}

