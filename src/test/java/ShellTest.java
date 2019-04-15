import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShellTest {
    Interpreter interpreter=new Interpreter();
    DirectoryHandler directoryHandler=new DirectoryHandler();
    FileHandler fileHandler=new FileHandler();
    @Test
    public void Test_Main_ExtractQuotation(){
        assertEquals(Main.extractQuotation("test1 test2 \"test3\" test4"),"test3");
        assertEquals(Main.extractQuotation("\"test1 test2 test3 test4\""),"test1 test2 test3 test4");
        assertTrue(!Main.extractQuotation("\"test\"").contains("\""));
    }
    @Test
    public void Test_DirectoryHandler(){
        directoryHandler.setCurrentDirectory("c:\\windows");
        assertEquals(directoryHandler.getCurrentDirectory(),"c:\\windows");
        directoryHandler.setCurrentDirectory("c:\\program files");
        assertEquals(directoryHandler.getCurrentDirectory(),"c:\\program files");
        Vector<String> stringVector = new Vector<String>();
        stringVector.add("test1");stringVector.add("test2");stringVector.add("test3");
        directoryHandler.outputStringVector(stringVector);
        directoryHandler.outputFilesInCurrentDirectory();
        directoryHandler.outputFilesInCustomDirectory("c:\\windows");
    }

    @Test
    public void Test_FileHandler(){
        fileHandler.setCurrentFile("C:\\Windows\\System32\\cmd.exe");
        assertEquals("C:\\Windows\\System32\\cmd.exe",fileHandler.getCurrentFile().getAbsolutePath());
    }

    @Test
    public void Test_Interpreter(){
        interpreter.outputCurrentDirectory();
        interpreter.outputHelpSheet();
        interpreter.interpret("help");
        interpreter.interpret("cd c:\\windows");
        interpreter.interpret("cd \"c:\\program files\"");
        assertEquals("c:\\program files",interpreter.directoryHandler.getCurrentDirectory());
        interpreter.interpret("dir");
        interpreter.interpret("dir c:\\program files");
        interpreter.interpret("echo test echoable test");
        interpreter.interpret("cmd ipconfig");
        interpreter.interpret("viewfile C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\en-US\\default.help.txt");
        interpreter.interpret("extract_quotation test1 \"test2\" test3");
        interpreter.interpret("date");
    }

}
