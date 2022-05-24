package maluevArtem;

/**
 *  Быд выбран способ взаимодействия Non-Blocking IO, т.к. поток Клиент, после отправки сообщения на Сервер
 *  продолжает свою работу и принимает новые сообщения от пользователя.
 */

public class Main {

    private static final int BUFFER_SIZE = 100000;

    public static void main(String[] args) {

        Server server = new Server(BUFFER_SIZE);
        Client client = new Client(BUFFER_SIZE);

        Thread serverThread = new Thread(server::server);
        Thread clientThread = new Thread(client::client);

        serverThread.start();
        clientThread.start();
    }
}
