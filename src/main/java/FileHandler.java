import java.io.*;

public class FileHandler {
    File currentfile;
    BufferedReader bufferedReader;

    public FileHandler() {
        currentfile = new File("C:\\Windows\\System32\\cmd.exe");
    }

    public File getCurrentFile() {
        return currentfile;
    }

    public void setCurrentFile(String newpath){
        File tempdir = new File(newpath);
        if (tempdir.exists()) {
            if (tempdir.isFile()) {
                currentfile = new File(newpath);
            } else {
                System.out.println("Selected path is not a file.");
            }
        } else {
            System.out.println("Selected path does not exist.");
        }
    }

    public String getCurrentFileContents() throws IOException{
        String input="";
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(currentfile.getAbsolutePath())));
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
               input+=line+="\n";
            }
        } finally {
            bufferedReader.close();
        }
        return input;
    }

    public void viewFile(String inputString, FileHandler fileHandler) {
        fileHandler.setCurrentFile(Main.extractQuotation(inputString));
        try {
            System.out.println(fileHandler.getCurrentFileContents());
        }catch (Exception e){
            System.out.println("File import error.");
        }
    }

    public void createNewFile(String inputString){
        try {
            PrintWriter writer = new PrintWriter((Main.extractQuotation(inputString)), "UTF-8");
            boolean inputting = true;
            String in = "";
            System.out.println("Input mode started. Every line sent to the console will be written to the file. Send \"EOF\" to finish inputting.");
            do {
                in = Main.userInput.readLine();
                if (in.equals("EOF")){
                    inputting=false;
                }else{
                    writer.println(in);
                }
            } while (inputting);
            writer.close();
            System.out.println("New file created at "+(Main.extractQuotation(inputString)));
        }
        catch(Exception e){
            System.out.println("Cannot create file: user input is incorrect.");
        }
    }

    public void deleteFile(String inputString, FileHandler fileHandler){
        fileHandler.setCurrentFile(Main.extractQuotation(inputString));
        if (fileHandler.getCurrentFile().isFile()){
            try{
                fileHandler.getCurrentFile().delete();
                System.out.println("file deleted!");
            }
            catch (Exception e){
                System.out.println("Deletion unsuccessful!");
                e.printStackTrace();
            }
        }
    }
}
