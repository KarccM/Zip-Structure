package Structure.Zip.ZipStructure;

import Structure.Zip.Controller.FileHeader.CompressionMethodManger;
import Structure.Zip.Operation.Encoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileEntry {

    private FileHeader fileHeader;
    private FileData fileData;
    private CentralHeader centralHeader;
    private String path;
    private boolean isDir;
    private Encoder encoder;

    public FileEntry(String path){
        isDir = true;
        fileHeader = new FileHeader(path);
        centralHeader = new CentralHeader(path);
        this.path = path;
        fileData = new FileData(path);
        encoder = CompressionMethodManger.getEncoder(path);
        if(!Files.isDirectory(Paths.get(path))){
            isDir = false;
            setCompSize(encoder.getLength());
        }
        setCompMethod(CompressionMethodManger.getCompMethod(encoder));
        System.out.println("********************* : "+fileHeader.getCompMethod());
        System.out.println(encoder.getClass());

    }
    public FileEntry(File file){
        isDir = true;
        fileHeader = new FileHeader(file);
        centralHeader = new CentralHeader(file);
        this.path = file.getPath();
        fileData = new FileData(file);
        encoder = CompressionMethodManger.getEncoder(path);
        if(!file.isDirectory()){
            isDir = false;
            setCompSize(encoder.getLength());
        }
        setCompMethod(CompressionMethodManger.getCompMethod(encoder));
        System.out.println(encoder.getClass());
    }
    //Setter
    public void setName(String name){
        fileHeader.setName(name);
        centralHeader.setName(name);
    }
    private void setCompSize(long val){
        fileHeader.setCompSize(val);
        centralHeader.setCompSize(val);
    }
    private void setCompMethod(long val){
        fileHeader.setCompMethod(val);
        centralHeader.setCompMethod(val);
    }
    // Getter
    public String getName(){
        return fileHeader.getName();
    }
    public FileHeader getFileHeader() {
        return fileHeader;
    }
    public FileData getFileData() {
        return fileData;
    }
    public CentralHeader getCentralHeader() {
        return centralHeader;
    }

    // Write File Header On Byte Array
    public void writeHeader(byte [] buffer) throws IOException {
        fileHeader.write(buffer);
    }

    //Write Direct To Zip File
    public void writeData(FilterOutputStream filterOutputStream) throws IOException {
        encoder.write(filterOutputStream,path);
    }

    // Write Central Header On Byte Array
    public void writeCentral(byte [] buffer) throws IOException {
        centralHeader.write(buffer);
    }

    //Read FileHeader
    public int readHeader(){
        return -1;
    }
    //Read CentralHeader
    public int readData(){
        return -1;
    }
    //Read CentralHeader
    public int readCentral(){
        return -1;
    }
    //Read Tail
    public int readTail(){
        return -1;
    }


}
