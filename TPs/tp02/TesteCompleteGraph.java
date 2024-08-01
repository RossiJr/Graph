package tp02;

import java.io.IOException;

public class TesteCompleteGraph {
    public static void main(String[] args) throws IOException {
        int vertices = 10;
        String path = "D:\\graphs\\Data\\others\\tp2\\graph-{type}-{n}.txt";


        for(int i=10; i<=10000; i*=10){
            path = path.replace("{n}", String.valueOf(i));
            GraphGenerator.generateCompleteGraph(path.replace("{type}", "complete"), i);
            GraphGenerator.generateNullGraph(path.replace("{type}", "null"), i);
            GraphGenerator.generateCircularGraphDirected(path.replace("{type}", "circular"), i);
            path = path.replace("-" + i, "-{n}");
        }
    }
}
