package Structure;

import Structure.Factories.Values;
import Structure.Zip.CreateZipFile;
import Structure.Zip.ExtractZipFile;

import java.io.File;
import java.io.IOException;

public class ZipFormat /*extends ArchiveFormat*/ {
    ZipFormat(boolean bakr){
        Values.bakr = bakr;
    }
/*
    public ZipFormat() {
        outputFileName = "";
        desPath = "C:\\Users\\" + System.getProperty("user.name", "") + "\\Desktop\\";
    }

    @Override
    public void archive(File... files) throws IOException {
        new CreateZipFile(outputFileName, desPath, files);
    }

    @Override
    public void extract(String inputFilePath) throws IOException {
        new ExtractZipFile(inputFilePath, desPath);
    }
*/
}
