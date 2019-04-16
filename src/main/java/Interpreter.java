import java.text.SimpleDateFormat;
import java.util.Date;

public class Interpreter {
    DirectoryHandler directoryHandler;
    FileHandler fileHandler;

    public Interpreter() {
        directoryHandler = new DirectoryHandler();
        fileHandler = new FileHandler();
    }

    public void outputCurrentDirectory() {
        System.out.println(directoryHandler.getCurrentDirectory());
    }

    public void outputHelpSheet() {
        System.out.println("" +
                "\n- Greg's Simpleshell 1.0 -\n" +
                "+---------------------------------------------------\n" +
                "|Quotation marks can be used in: [directory] [params] [path] [filename]\n" +
                "|Quotation marks aren't required to interpret [string]\n" +
                "+--------------------------+------------------------\n" +
                "|help                      |Displays the current sheet.\n" +
                "|echo [string]             |The console repeats the given string.\n"+
                "|cd [directory]            |Change current directory.\n" +
                "|dir                       |Lists all files found in the current directory.\n" +
                "|dir [directory]           |Lists all files found in the provided directory.\n" +
                "+--------------------------+------------------------\n" +
                "|cmd                       |Opens the Windows Command Line.\n" +
                "|cmd [params]              |Opens and passes parameters to the Windows Command Line.\n" +
                "|perfmon                   |Opens the Windows Performance Monitor.\n" +
                "+--------------------------+------------------------\n" +
                "|viewfile [path]           |Draws the contents of any text file.\n" +
                "|createfile [filename]     |Accepts multiple lines of user input to be pasted into a .txt \n" +
                "|                          |found in the same folder as this executable.\n" +
                "|deletefile [path]         |Removes the file found at the given path. [if user is admin]\n" +
                "+--------------------------+------------------------\n" +
                "|date                      |Displays the current date (yyyy-MM-dd at HH-mm-ss z).\n" +
                "|extract_quotation [string]|Displays the first quote found in the given string \n" +
                "|exit                      |Exits this command line.\n" +
                "+--------------------------+------------------------\n");
    }

    public void interpret(String inputString) {
        String[] input = inputString.split(" ");
        String currentFunction = input[0];
        boolean hasParams = false;
        if (input.length > 1) {
            hasParams = true;
        }

        if (currentFunction.equals("help")) {
            outputHelpSheet();

        } else if (currentFunction.equals("cd")) {
            if (hasParams) {
                directoryHandler.setCurrentDirectory(Main.extractQuotation(inputString));
                outputCurrentDirectory();
            } else {
                outputCurrentDirectory();
            }

        } else if (currentFunction.equals("dir")) {
            if (hasParams) {
                directoryHandler.outputFilesInCustomDirectory((Main.extractQuotation(inputString)));
            } else {
                directoryHandler.outputFilesInCurrentDirectory();
            }

        } else if (currentFunction.equals("echo")) {
            if (hasParams) {
                System.out.println(inputString.substring(inputString.indexOf(" ")));
            } else {
                System.out.println("echo");
            }

        } else if (currentFunction.equals("cmd")) {
            try {
                if (hasParams) {
                    Process cmd = Runtime.getRuntime().exec("cmd.exe /c start " + inputString.substring(inputString.indexOf(" ")));
                } else {
                    Process cmd = Runtime.getRuntime().exec("cmd.exe /c start");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (currentFunction.equals("perfmon")) {
            try {
                Process cmd = Runtime.getRuntime().exec("perfmon.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (currentFunction.equals("viewfile")) {
            if (hasParams) {
                fileHandler.viewFile(inputString, fileHandler);
            } else {
                System.out.println("No path entered!");
            }

        } else if (currentFunction.equals("createfile")) {
            if (hasParams) {
                fileHandler.createNewFile(inputString);

            } else {
                System.out.println("You must specify the filename.");
            }
        } else if (currentFunction.equals("deletefile")) {
            if (hasParams) {
                fileHandler.deleteFile(inputString, fileHandler);
            } else {
                System.out.println("You must specify a path.");
            }

        } else if (currentFunction.equals("extract_quotation")) {
            if (hasParams) {
                System.out.println(Main.extractQuotation(inputString.substring(inputString.indexOf(" "))));
            } else {
                System.out.println("Patrameters are required for this function");
            }

        } else if (currentFunction.equals("date")) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z").format(new Date(System.currentTimeMillis())));

        } else if (currentFunction.equals("exit")) {
            Runtime.getRuntime().exit(0);

        } else {
            System.out.println("No such command found. Use 'help' if you need help.");
        }
    }
}