import java.util.Scanner;
import java.lang.Math;

public class Teste {
    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double entropiaClasse = 1;
        double entropiaAtributo = 0;
        for(int i=0; i<2; i++){
            double valor1 = sc.nextDouble();
            double valor2 = sc.nextDouble();
            double porcentagem = sc.nextDouble();
            entropiaAtributo += (-valor1*log2(valor1)-valor2*log2(valor2))*porcentagem;
        }
        System.out.println(entropiaClasse - entropiaAtributo);

        sc.close();
    }
}
