package enteties;

import database.Database;
import database.SQLDatabase;
import utils.Auxiliar;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Company {

    private Auxiliar auxiliar = new Auxiliar();
    private Database database = new SQLDatabase();
    private String CompanyName;

    public Company() {
        boolean isLoginValid = false;
        while (!isLoginValid) {
            User user = login();
            if (user != null) {
                isLoginValid = true;
                new Warehouse(user);
            } else {
                System.out.println("LogIn invalid");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                auxiliar.clearConsole();
            }
        }
    }

    public void addNewEmployee() {
        //TODO create new employee. can only be create by manager or higher.
    }

    public User login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Username");
        String username = input.nextLine();
        System.out.println("password");
        String password = input.nextLine();
        return database.getUserByNameAndPass(username, password);
    }

}
