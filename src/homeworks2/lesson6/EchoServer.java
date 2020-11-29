package homeworks2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoServer {

    private final int PORT = 8189;
    private DataOutputStream out;
    private DataInputStream in;

    public static void main(String[] args) {

        EchoServer server = new EchoServer();
        server.start();

    }

//    Запускает сервер
    private void start() {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSocket.accept();

            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            startSendMessThread();

            while(waitMessageFromUsers());

            System.out.println("Сервер остановлен");

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

//    Запускает поток отправки сообщений
    private void startSendMessThread() {
        Thread t = new Thread(() -> {
            try(Scanner scanner = new Scanner(System.in)) {
                while(true) {
                    String mess = scanner.nextLine();
                    out.writeUTF("System:\n" + mess);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
    }

//    Ждет сообщений от юзеров
    private boolean waitMessageFromUsers() throws IOException {
        String mess;
        try {
            mess = in.readUTF();
        } catch (SocketException e) {
//            Если клиент отключился возвращаем false (выходим из цикла)
            System.out.println("Клиент отключился");
            return false;
        }

        if(mess.equals("exit"))
            return false;

        try{
            out.writeUTF("Я:\n" + mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
