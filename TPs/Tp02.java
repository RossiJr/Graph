import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Tp02 {
    public static void main1(String[] args) throws IOException {
        String path = "D:\\graphs\\Data\\graph-test-small.txt";
        NotDirectedGraph ndg = new NotDirectedGraph(path);
        System.out.println(ndg.naive());
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer nVertices = 10;
        Boolean save = false;
        String path = "D:\\graphs\\Data\\graph-random-eurelian-" + nVertices + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices, 2, 4, save, "eurelian");
        path = "D:\\graphs\\Data\\graph-random-eurelian-" + nVertices * 10 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 10, 2, 20, save, "eurelian");
        path = "D:\\graphs\\Data\\graph-random-eurelian-" + nVertices * 100 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 100, 2, 30, save, "eurelian");
        path = "D:\\graphs\\Data\\graph-random-eurelian-" + nVertices * 1000 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 1000, 2, 50, save, "eurelian");
        path = "D:\\graphs\\Data\\graph-random-eurelian-" + nVertices * 10000 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 10000, 2, 70, save, "eurelian");


        path = "D:\\graphs\\Data\\graph-random-semi_eurelian-" + nVertices + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices, 2, 4, save, "semi-eurelian");
        path = "D:\\graphs\\Data\\graph-random-semi_eurelian-" + nVertices * 10 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 10, 2, 20, save, "semi-eurelian");
        path = "D:\\graphs\\Data\\graph-random-semi_eurelian-" + nVertices * 100 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 100, 2, 30, save, "semi-eurelian");
        path = "D:\\graphs\\Data\\graph-random-semi_eurelian-" + nVertices * 1000 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 1000, 2, 50, save, "semi-eurelian");
        path = "D:\\graphs\\Data\\graph-random-semi_eurelian-" + nVertices * 10000 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 10000, 2, 70, save, "semi-eurelian");


        path = "D:\\graphs\\Data\\graph-random-not_eurelian-" + nVertices + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices, 2, 4, save, "not-eurelian");
        path = "D:\\graphs\\Data\\graph-random-not_eurelian-" + nVertices * 10 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 10, 2, 20, save, "not-eurelian");
        path = "D:\\graphs\\Data\\graph-random-not_eurelian-" + nVertices * 100 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 100, 2, 30, save, "not-eurelian");
        path = "D:\\graphs\\Data\\graph-random-not_eurelian-" + nVertices * 1000 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 1000, 2, 50, save, "not-eurelian");
        path = "D:\\graphs\\Data\\graph-random-not_eurelian-" + nVertices * 10000 + ".txt";
        GraphsUtils.generateRandomGraph(path, nVertices * 10000, 2, 70, save, "not-eurelian");
        sc.close();
    }
}
