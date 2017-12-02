package Main;

import Main.ConsoleApp.ConsoleApp;
import Main.Utils.ConnectionUtils;
import Main.Utils.SessionUtils;

public class Main {
    public static void main(String[] args) {
        SessionUtils sessionUtils = new SessionUtils();
        sessionUtils.startSessionFactory();
        ConnectionUtils.doConnection();

        ConsoleApp app = new ConsoleApp();

        app.consoleRead();


    }
}
