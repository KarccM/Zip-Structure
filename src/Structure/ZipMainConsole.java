package Structure;

import Structure.Zip.CreateZipFile;
import Structure.Zip.ExtractZipFile;

import java.io.IOException;
import java.util.ArrayList;

public class ZipMainConsole {
    private static String desPath = "C:\\Users\\" + System.getProperty("user.name", "") + "\\Desktop\\";

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("background.png");
        new ZipFormat(true);
        CreateZipFile createZipFile = new CreateZipFile(list, "output");
       ExtractZipFile extractZipFile = new ExtractZipFile("output.zip", desPath);
    }
}