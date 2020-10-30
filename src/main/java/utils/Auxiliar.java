package utils;

public class Auxiliar {

    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";


    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void sendErrMsg(String string) {
        System.out.println(TEXT_PURPLE + "Error: " +TEXT_RESET+string);
    }

}
