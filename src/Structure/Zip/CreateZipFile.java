package Structure.Zip;

import Structure.Zip.ZipStructure.CentralTail;
import Structure.Zip.ZipStructure.FileEntry;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateZipFile extends FilterOutputStream {

    private List<FileEntry> entries = new ArrayList<>();
    private CentralTail tail;
    private int offset;
    private int dicSize;

    public CreateZipFile(String destPath ,String outputFileName) throws FileNotFoundException {
        super(new BufferedOutputStream(new FileOutputStream(outputFileName + ".zip")));
        offset = 0;
        dicSize= 0;
        if(new File (destPath).isDirectory()){
            entries.add(new FileEntry(destPath));
            createEntries(destPath , entries.get(0).getFileHeader().getName());
        }
        else createEntries(destPath , "");
        writeToMemory();
    }
    public CreateZipFile(List<String> paths ,String outputFileName) throws FileNotFoundException {
        super(new BufferedOutputStream(new FileOutputStream(outputFileName + ".zip")));
        offset = 0;
        dicSize= 0;
        for(String destPath : paths){
            if(new File (destPath).isDirectory()){
                entries.add(new FileEntry(destPath));
                createEntries(destPath , entries.get(entries.size()-1).getFileHeader().getName());
            }
            else createEntries(destPath , "");
        }
            writeToMemory();
    }
    //This Method Will Write At Stream
    public void writeToMemory(){
        byte [] bytes = new byte[100];
        createTail();
        //Write File Header First & Data
        for(FileEntry e : entries){
            try {
                e.writeHeader(bytes);   //-> File Entry Write Header Method
                super.write(bytes , 0 , e.getFileHeader().getLength()); //-> Write on stream
                e.writeData(this);  //-> File Entry Write Data Method -> in Turn Will Write on Stream
            } catch (IOException e1) {
                e1.printStackTrace();   //Catch exception like File Not Found .. etc
            }
        }

        // Write Central Header
        for(FileEntry e : entries){
            try {
                e.writeCentral(bytes); // -> File Entry Write Central Method
                super.write(bytes ,0 , e.getCentralHeader().getLength()); // -> Write On Stream

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        //write Tail
        try {
            tail.write(bytes); // Write Func In CentralTail
            super.write(bytes,0 , tail.getLength());// Write on Stream
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            super.close(); // Save Changes
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Create Entries For Every File In Path
    private void createEntries(String path , String parent){
        if(new File(path).isDirectory()){
            File [] files = new File(path).listFiles();
            for (File f : files){
                entries.add(new FileEntry(f));
                entries.get(entries.size()-1).setName(parent +"/"+f.getName());
                if(f.isDirectory()) {
                    //for add '/' to dir
                    entries.get(entries.size()-1).setName(entries.get(entries.size()-1).getName()+"/");
                    createEntries(f.getPath() ,parent+"/"+f.getName());
                }
            }
        }
        else{
            entries.add(new FileEntry(path));
            entries.get(entries.size()-1).getFileHeader().setName(new File(path).getName());
        }
    }
    //Create Tail
    private void createTail(){
        countDicSize();
        countOffset();
        tail = new CentralTail(entries.size() , entries.size() ,offset,dicSize);
    }
    //Calculate Dic Size
    private void countDicSize(){
        /*
        Directory size = CH1 + CH2 + ... + CHN
         */
        for(FileEntry e: entries){
            dicSize += e.getCentralHeader().getLength();
        }
    }
    //Calculate Offset
    private void countOffset(){
        /*
        Offset From Start To First CentralHeader = FH1 + FD1 + FH2 + FD2 + .... + FHN + FDN
         */
        for(FileEntry e: entries){
            offset += e.getFileHeader().getLength() + e.getFileHeader().getLength();
        }
    }
}
