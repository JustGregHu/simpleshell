import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {

    /**
     * Feladat:
     * Írjon ki promptot, ezután kérjen be parancsokat (paraméterekkel)
     * A paramétereket kezelje le (", ', szóközök, escape karakterek kezelése)
     * Belső parancsok: cd, echo
     * Tudjon külső parancsokat futtatni
     * Valósítsd meg a pipe műveletet (|). Például: echo "asd" | grep "s" kiírja az asd stringet, majd a kimenetet beleirányítja a grep "s" parancs bemenetébe
     *
     */

    static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args){
        boolean running=true;

        Interpreter interpreter =  new Interpreter();
        if (!System.getProperty("os.name").toLowerCase().startsWith("windows")){
            System.out.println("Simpleshell only runs on Windows related operating systems. Press enter to exit.");
            running=false;
            try{
                userInput.readLine();
            }
            catch(Exception e){
                System.exit(0);
            }
        }
        while(running){
            try{
                interpreter.interpret(userInput.readLine());
            }
            catch(Exception e){
                System.out.println("Input error.");
                e.printStackTrace();
            }
        }
    }

    public static String extractQuotation(String inputString){
        String quotation="";
        if (inputString.contains("\\\"")){
            inputString=inputString.split(" ")[1];
            for (int i = 0; i <inputString.length() ; i++) {
                if (inputString.charAt(i)!='\\')
                    quotation+=inputString.charAt(i);
            }
        }
        else if(inputString.contains("\"")) {
            boolean quotationFound = false;
            int quotationCount = 0;
            for (int i = 0; i < inputString.length(); i++) {
                if (quotationCount == 1) {
                    quotation += inputString.charAt(i);
                }
                if (inputString.charAt(i) == '\"') {
                    quotationFound = !quotationFound;
                    quotationCount += 1;
                }
            }
            quotation=quotation.substring(0,quotation.indexOf("\""));
        }
        else{
            quotation=inputString.split(" ")[1];
        }
        return quotation;
    }
}
