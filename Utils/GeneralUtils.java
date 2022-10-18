import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GeneralUtils{
    public static Boolean isValidFile(BufferedReader file) throws IOException {
        return !(file == null || !file.ready());
    }
    public static Boolean isEmpty(String path){
        return (path == null || path.isEmpty());
    }
    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
    public static String[] onlyNumbers(String line){
        StringBuilder returnString = new StringBuilder();
        int lineLenght = line.length();
        for(int i=0; i<lineLenght; i++){
            if(line.charAt(i) != ' '){
                returnString.append(line.charAt(i));
            } else if(i < lineLenght-1 && line.charAt(i+1) != ' ' && returnString.length() > 0){
                returnString.append(" ");
            }
        }
        return returnString.toString().split(" ");
    }
}
