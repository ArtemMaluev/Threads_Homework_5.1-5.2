package maluevArtem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {

    private final int bufferSize;

    public Server(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void server() {
        try (final ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind (new InetSocketAddress("localhost", 8888));

            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(bufferSize);

                while (socketChannel.isConnected()) {
                    int bytesCount = socketChannel.read(inputBuffer);

                    if (bytesCount == -1) break;

                    final String msg = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                    inputBuffer.clear();

                    Thread.sleep(1000);

                    System.out.println("SERVER: Получено сообщение от клиента: " + msg);
                    String str = msg.replaceAll("\\s","");
                    socketChannel.write(ByteBuffer.wrap(("SERVER: ответ: " + str).getBytes(StandardCharsets.UTF_8)));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
