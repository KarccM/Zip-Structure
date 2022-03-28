package Structure.Factories;

import Structure.Zip.Operation.Decoder;
import Structure.Zip.Operation.DefaultDecoder;
import Structure.Zip.Operation.HuffmanDecoder;

public class DecoderFactory {
    public static Decoder getDecoder(int value){

        if (value == 21)
            return new HuffmanDecoder();

        return new DefaultDecoder();
    }
}
