package Structure.StructureComponent.Header;

import Structure.Zip.Controller.FileHeader.DateModified;
import Structure.Zip.Operation.ZipArchiver;

public class ModifiedTime extends HeaderComponent<Long>{

    public ModifiedTime(long value , int offset , int length) {
        this.value  = value;
        this.offset = offset;
        this.length = length;

    }

    public Object read(byte[] buffer) { return ZipArchiver.read(buffer, offset, length); }

    public void write(byte[] buffer) { ZipArchiver.write(buffer , offset , length , value); }


}
