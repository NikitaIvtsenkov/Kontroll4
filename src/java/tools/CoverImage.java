package tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.Cover;


public class CoverImage {
    public List<String> getListCovers(){
        List<String> listFileNames = new ArrayList<>();
        File coverDir = new File(new File("")
                .getPath()+File.pathSeparator
                +"web"+File.pathSeparator+"images");
        for(File file : coverDir.listFiles()){
            listFileNames.add(file.getName().toString());
        }
        return listFileNames;
    }
}