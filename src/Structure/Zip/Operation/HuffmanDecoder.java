package Structure.Zip.Operation;

import Huffman.HuffmanDeCompress;

import java.io.FilterInputStream;

public class HuffmanDecoder implements Decoder {
    @Override
    public void read(String pathZip
            , String name
            , String destPath
            , long size
            , long skip) {
        HuffmanDeCompress huffmanDeCompress = new HuffmanDeCompress(pathZip,size,skip,name,destPath);
        huffmanDeCompress.decompress();
    }
}