import java.io.File;
import java.util.Collections;
import java.util.Vector;

public class DirectoryHandler {
    File currentdir;
    File[] filesincurrentdir;

    public DirectoryHandler() {
        currentdir = new File("c:/");
    }

    public String getCurrentDirectory() {
        return currentdir.getAbsolutePath();
    }

    public void setCurrentDirectory(String newdir) {
        File tempdir = new File(newdir);
        if (tempdir.exists()) {
            if (tempdir.isDirectory()) {
                currentdir = new File(newdir);
                System.out.println(getCurrentDirectory());
            } else {
                System.out.println("Selected path is not a directory.");
            }
        } else {
            System.out.println("Selected path does not exist.");
        }
    }

    private void fillFilesInCurrentDirectory() {
        filesincurrentdir = currentdir.listFiles();
    }

    private File[] filesOfCDirectory(File f) {
        return f.listFiles();
    }

    public void outputStringVector(Vector < String > vector) {
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.elementAt(i));
        }
    }

    public void outputFilesInCurrentDirectory() {
        Vector < String > out = new Vector < String > ();
        fillFilesInCurrentDirectory();
        for (int i = 0; i < filesincurrentdir.length; i++) {
            if (filesincurrentdir[i].isFile()) {
                out.add("File:  " + filesincurrentdir[i].getAbsolutePath());
            } else if (filesincurrentdir[i].isDirectory()) {
                out.add("Dir:   " + filesincurrentdir[i].getAbsolutePath());
            }
        }
        Collections.sort(out);
        outputStringVector(out);
    }

    public void outputFilesInCustomDirectory(String newdir) {
        File tempdir = new File(newdir);
        if (tempdir.exists()) {
            if (tempdir.isDirectory()) {
                File[] filesinnewdir = filesOfCDirectory(tempdir);
                Vector < String > out = new Vector < String > ();
                for (int i = 0; i < filesinnewdir.length; i++) {
                    if (filesinnewdir[i].isFile()) {
                        out.add("File:  " + filesinnewdir[i].getAbsolutePath());
                    } else if (filesinnewdir[i].isDirectory()) {
                        out.add("Dir:   " + filesinnewdir[i].getAbsolutePath());
                    }
                }
                Collections.sort(out);
                outputStringVector(out);
            } else {
                System.out.println("Selected path is not a directory.");
            }
        } else {
            System.out.println("Selected path does not exist.");
        }
    }
}