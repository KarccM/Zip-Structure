package Structure.Factories;

import Structure.Zip.Operation.*;

public class EncodeFactory {


    public static Encoder getEncoder(String string , String path){
        try{
            if (string.equals("txt"))
                return new HuffmanEncoder(path);

            else if ( ( string.equals("jpg") || string.equals("png") ) && Values.bakr)
                return new PhotoEncoder(path);

            else
                return new DefaultEncoder(path);
        }catch (NullPointerException e){
            return new DefaultEncoder(path);
        }
    }

}
