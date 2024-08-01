import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class InimaogosWords {
    public static void main(String[] args) throws IOException {
        final String path = "D:\\graphs\\Data\\others\\palavras_dessa_merda.txt";

        Map<String, Integer> words = new LinkedHashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
        String lines = br.readLine().replace("\"", "").replace("[","")
                .replace("]", "").replace(", ", "\n")
                .replace("”", "").replace("“", "")
                .replace("ç", "c").replace("á", "a")
                .replace("ã", "a").replace("à", "a")
                .replace("â", "a").replace("í", "i")
                .replace("ó", "o").replace("õ", "o")
                .replace("ô", "o").replace("é", "e")
                .replace("ê", "e").replace("?", "");
        lines.lines().sorted(Comparator.naturalOrder()).forEach(i -> {
            Integer classification;
            Integer lengthWord = i.length() - repeteadChars(i);
            if(lengthWord <= 3)
                classification = 1;
            else if (lengthWord <= 6)
                classification = 2;
            else
                classification = 3;
            words.put(i, classification);
        });
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(path+".aux.txt", StandardCharsets.UTF_8));
        words.forEach((k, v) -> {
            try {
                bw.write(k + "-" + v + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }
    private static Integer repeteadChars(String word){
        Integer chars = 0;
        for(char c1 : word.toCharArray()) {
            int count = 0;
            for(char c2 : word.toCharArray()) {
                if(c1 == c2) {
                    count++;
                }
            }
            word = word.replaceAll(String.valueOf(c1) , "");
            if(count > 1)
                chars += count-1;
        }
        return chars;
    }
}
