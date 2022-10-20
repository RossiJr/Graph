import java.io.IOException;
import java.util.Scanner;

public class Tp02 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        GraphsUtils.generateRandomGraph(path, 100, 5, 13);
        sc.close();
    }
}
