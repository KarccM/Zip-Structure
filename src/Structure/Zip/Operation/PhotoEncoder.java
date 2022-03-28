package Structure.Zip.Operation;


import PhotoCompression.Jpeg.jpeg;

import java.io.*;

public class PhotoEncoder implements Encoder {
    private long length;
    public PhotoEncoder(String path){

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        jpeg jpeg = new jpeg(path , 1 , 8 , "low" , os);
        length = os.size();

    }
    public PhotoEncoder(){

    }
    @Override
    public void write(FilterOutputStream filterOutputStream, String path) throws IOException {
        jpeg jpeg = new jpeg(path , 1 , 8 , "low" , filterOutputStream);
    }
    public long getLength(){
        return length;
    }
}
