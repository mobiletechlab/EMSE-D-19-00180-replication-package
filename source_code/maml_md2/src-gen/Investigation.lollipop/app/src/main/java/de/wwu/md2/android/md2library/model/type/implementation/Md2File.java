package de.wwu.md2.android.md2library.model.type.implementation;

import java.io.File;

import de.wwu.md2.android.md2library.model.type.implementation.AbstractMd2DataType;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;

/**
 * Created by c_rieg01 on 24.07.2018.
 */

public class Md2File extends AbstractMd2DataType {

    File file = null;

    public Md2File(){

    }

    public Md2File(File file){
        this.file = file;
    }

    public Md2Type clone(){
        return new Md2File(file);
    }

    @Override
    public Md2String getString() {
        return null;
    }

    @Override
    public boolean equals(Md2Type md2Type) {
        if(md2Type instanceof Md2File){
            return ((Md2File) md2Type).file == file;
        }

        return false;
    }

    @Override
    public Object getPlatformValue() {
        return file;
    }

    public boolean isSet(){
        return file != null;
    }
}
