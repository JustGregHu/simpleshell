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
        outputCustomDirectoryInfo(filesincurrentdir, out);
        Collections.sort(out);
        outputStringVector(out);
    }

    public void outputFilesInCustomDirectory(String newdir) {
        File tempdir = new File(newdir);
        if (tempdir.exists()) {
            if (tempdir.isDirectory()) {
                File[] filesinnewdir = filesOfCDirectory(tempdir);
                Vector < String > out = new Vector < String > ();
                outputCustomDirectoryInfo(filesinnewdir, out);
                Collections.sort(out);
                outputStringVector(out);
            } else {
                System.out.println("Selected path is not a directory.");
            }
        } else {
            System.out.println("Selected path does not exist.");
        }
    }

    private void outputCustomDirectoryInfo(File[] filesinnewdir, Vector<String> out) {
        for (int i = 0; i < filesinnewdir.length; i++) {
            if (filesinnewdir[i].isFile()) {
                out.add("File:  " + filesinnewdir[i].getAbsolutePath());
            } else if (filesinnewdir[i].isDirectory()) {
                out.add("Dir:   " + filesinnewdir[i].getAbsolutePath());
            }
        }
    }
}