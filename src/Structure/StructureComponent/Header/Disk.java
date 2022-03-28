package Structure.StructureComponent.Header;

import Structure.Zip.Operation.ZipArchiver;

import java.io.IOException;

public class Disk extends HeaderComponent<Long> {

    public Disk(long value , int offset , int length) {
        this.value  = value;
        this.offset = offset;
        this.length = length;

    }

    @Override
    public Object read(byte[] buffer) {
        return null;
    }

    @Override
    public void write(byte[] buffer) throws IOException {
        ZipArchiver.write(buffer , offset , length , value);
    }
}
