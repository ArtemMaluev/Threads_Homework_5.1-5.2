package maluevArtem;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void server(){
        try (ServerSocket serverSocket = new ServerSocket(8888);
             Socket client = serverSocket.accept();
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            while (true) {
                String number = in.readLine();
                if (number == null) {
                    break;
                }
                int result;
                try {
                    int num = Integer.parseInt(number);
                    result = fibonacciNumbers(num);
                    out.println("число Фибщначчи: " + result);
                } catch (NumberFormatException ex) {
                    System.out.println("Введено не венрое значение");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int fibonacciNumbers(int number) {
        int a1 = 0;
        int a2 = 1;
        int an = 0;
        if (a1 == number) {
            an = a1;
        } else if (a2 == number) {
            an = a2;
        } else {
            for (int i = 1; i < number; i ++) {
                an = a1 + a2;
                a1 = a2;
                a2 = an;
            }
        }
        return an;
    }
}
