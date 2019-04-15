import java.text.SimpleDateFormat;
import java.util.Date;

public class Interpreter {
    DirectoryHandler directoryHandler;
    boolean echoEnabled = true;

    public Interpreter() {
        directoryHandler = new DirectoryHandler();
    }

    public void interpret(String inputString) {
        String[] input = inputString.split(" ");
        if (input[0].equals("help")) {
            System.out.println("" +
                    "\n- Greg's Simpleshell 1.0 -\n" +
                    "cd [directory]: Change current directory.\n" +
                    "cmd [params]: Opens and passes parameters to the Windows Command Line.\n" +
                    "perfmon: Opens the Windows Performance Monitor." +
                    "dir: Lists all files found in the current directory.\n" +
                    "dir [directory]: Lists all files found in the provided directory.\n" +
                    "exit: Exits this command line.\n" +
                    "clear: Empties this console window's contents.\n" +
                    "help: Displays the help screen.\n" +
                    "date: Displays the current date (yyyy-MM-dd at HH-mm-ss z).\n");
        } else if (input[0].equals("cd")) {
            if (input.length == 2) {
                System.out.println(directoryHandler.getCurrentDirectory());
                directoryHandler.setCurrentDirectory(input[1]);
            } else {
                System.out.println(directoryHandler.getCurrentDirectory());
            }
        } else if (input[0].equals("dir")) {
            if (input.length == 2) {
                directoryHandler.outputFilesInCustomDirectory(input[1]);
            } else {
                directoryHandler.outputFilesInCurrentDirectory();
            }
        } else if (input[0].equals("echo")) {
            if (input.length == 2) {
                System.out.println(input[1]);
            } else {
                //System.out.println("echo = enabled");
                System.out.println("echo");
            }
        } else if (input[0].equals("cmd")) {
            try {
                if (input.length > 1) {
                    Process cmd = Runtime.getRuntime().exec("cmd.exe /c start " + inputString.substring(inputString.indexOf(" ")));
                } else {
                    Process cmd = Runtime.getRuntime().exec("cmd.exe /c start");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (input[0].equals("perfmon")) {
            try {
                Process cmd = Runtime.getRuntime().exec("perfmon.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (input[0].equals("clear")) {
            Main.clearScreen();
        } else if (input[0].equals("date")) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis())));
        } else if (input[0].equals("exit")) {
            Runtime.getRuntime().exit(0);
        } else {
            System.out.println("No such command found. Use 'help' if you need help.");
        }
    }
}