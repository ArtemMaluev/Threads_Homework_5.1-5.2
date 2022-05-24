package maluevArtem;

/**
 *  Быд выбран способ взаимодействия Blocking, т.к. поток CLIENT, после отправки числа введенного пользователем,
 *  не выполняет никаких других задач, и может быть заблокирован до чтения из потока SERVER ответа.
 */

public class Main {

    public static void main(String[] args) {

        Server server = new Server();
        Client client = new Client();

        Thread serverThread = new Thread(server::server);
        Thread clientThread = new Thread(client::client);

        serverThread.start();
        clientThread.start();

    }
}
