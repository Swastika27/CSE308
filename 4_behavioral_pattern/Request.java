import java.io.Serializable;

public class Request implements Serializable {
    ClientType type;
    String clientName;
    String command;

    public Request(ClientType type, String name, String command) {
        this.type = type;
        this.command = command;
        this.clientName = name;
    }

    public ClientType getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }
    public String getClientName() {
        return clientName;
    }
}
