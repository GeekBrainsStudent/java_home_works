package homeworks2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {

    private static final String HOST = "localhost";
    private static final int PORT = 8189;

    private final DataOutputStream out;
    private final DataInputStream in;
    private final Socket socket;

    public Connection(String host, int port)
            throws IOException {
        socket = new Socket(host, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    public Connection() throws IOException {
        this(HOST,PORT);
    }

    public void close() throws IOException {
        socket.close();
    }

    public void sendMess(String mess) throws IOException {
        out.writeUTF(mess);
    }

    public DataInputStream getIn() {
        return in;
    }
}
