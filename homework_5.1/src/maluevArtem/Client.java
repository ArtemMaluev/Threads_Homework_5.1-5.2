package maluevArtem;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void client() {
        try (Socket client = new Socket("127.0.0.1", 8888);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Введите порядковый номер числа Фибоначчи, который необходимр вычеслить");
                System.out.println("Или \"end\" для выхода");
                System.out.print("CLIENT: >> ");
                String number = scanner.nextLine();
                if ("end".equals(number)) {
                    System.out.println("Программа завершена");
                    break;
                }
                out.println(number);

                System.out.println("SERVER: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
