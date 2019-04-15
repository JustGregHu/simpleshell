import java.io.BufferedReader;
import java.io.InputStreamReader;

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

    public static void main(String[] args){
        boolean running=true;
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        Interpreter interpreter =  new Interpreter();
        if (!System.getProperty("os.name").toLowerCase().startsWith("windows")){
            System.out.println("Simpleshell only runs on Windows related operating systems. Press enter to exit.");
            running=false;
            try{
                userInput.readLine();
            }
            catch(Exception e){
            }
        }
        while(running){
            try{
                interpreter.interpret(userInput.readLine());
            }
            catch(Exception e){
                System.out.println("Input error.");
            }
        }
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
