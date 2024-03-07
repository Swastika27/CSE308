import java.util.Scanner;

public class Client {
    public Client(String serverAddress, int serverPort) {
        Scanner scanner = new Scanner(System.in);
        ClientType type = ClientType.user;
        try {
            System.out.println("enter name of the client : ");
            String clientName = scanner.nextLine();
            if(clientName.equalsIgnoreCase("admin")) {
                type = ClientType.admin;
            }
            SocketWrapper socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write(clientName);
            new ReadThreadClient(socketWrapper);
            new WriteThreadClient(socketWrapper, clientName, type);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 5000;
        new Client(serverAddress, serverPort);
    }
}
