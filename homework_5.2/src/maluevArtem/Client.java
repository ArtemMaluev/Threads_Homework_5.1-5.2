package maluevArtem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private final int bufferSize;

    public Client(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void client() {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8888);
        try (final SocketChannel socketChannel = SocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(socketAddress);
            final ByteBuffer inputBuffer = ByteBuffer.allocate(bufferSize);

            String msg;
            while (true) {
                System.out.println("Введите строку, в которой нужно убрать пробелы:");
                System.out.println("или \"end\", чтобы закончить");
                System.out.print(">> ");
                msg = scanner.nextLine();
                if ("end".equals(msg)) break;

                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));

                Thread.sleep(1000);

                int bytesCount = socketChannel.read(inputBuffer);
                System.out.println(new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8).trim());
                inputBuffer.clear();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
