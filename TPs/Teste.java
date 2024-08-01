import java.awt.*;
import java.util.*;
import java.lang.Math;
import java.util.List;

class TesteOrdDupla {
    Integer e1;
    Integer e2;

    public TesteOrdDupla(Integer e1, Integer e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
}

public class Teste {
    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    //    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        double entropiaClasse = 1;
//        double entropiaAtributo = 0;
//        for(int i=0; i<2; i++){
//            double valor1 = sc.nextDouble();
//            double valor2 = sc.nextDouble();
//            double porcentagem = sc.nextDouble();
//            entropiaAtributo += (-valor1*log2(valor1)-valor2*log2(valor2))*porcentagem;
//        }
//        System.out.println(entropiaClasse - entropiaAtributo);
//    }
    public static void main(String[] args) {
        List<TesteOrdDupla> listOrd = new ArrayList<>();
        listOrd.addAll(Arrays.asList(new TesteOrdDupla(1, 2),
                new TesteOrdDupla(2, 2), new TesteOrdDupla(2, 3),
                new TesteOrdDupla(2, 4), new TesteOrdDupla(3, 3),
                new TesteOrdDupla(5, 3), new TesteOrdDupla(7, 8),
                new TesteOrdDupla(4, 5), new TesteOrdDupla(7, 10)));
        Collections.shuffle(listOrd);
        Collections.sort(listOrd, (i1, i2) -> {
            if (i1.e1.equals(i2.e1)) {
                return i1.e2.compareTo(i2.e2);
            } else {
                return i1.e1.compareTo(i2.e1);
            }
//                return (i1.e1.equals(i2.e1) ? i1.e2.compareTo(i2.e2) : i1.e1.compareTo(i2.e1));
//                int cmp = Double.compare(c2.getMedia(), c1.getMedia());
//                if (cmp == 0) { // se as médias forem iguais, desempata pelo menor preço
//                    cmp = Double.compare(c1.getValorPretendido(), c2.getValorPretendido());
//                    if (cmp == 0) { // se os preços forem iguais, desempata pela menor data
//                        cmp = c1.getDate().compareTo(c2.getDate());
//                    }
//                }
//                return cmp;
        });
        listOrd.stream().forEach(e -> System.out.print(e.e1 + " - " + e.e2 + "  |  "));
    }
}

class Idades {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Entre com a quantidade de idades");
        int idades = scan.nextInt();
        //int = scan. nextInt();

        int idade;
        int soma = 0;


        for (int i = 0; i < idades; i++) {

            System.out.println("Entre com a idade da pessoa " + (i + 1));
            idade = scan.nextInt();

            soma += idade;
        }

        double media = soma / idades;

        System.out.println("Media de idade: + media");

        if (media >= 0 && media <= 25) {
            System.out.println("jovem");
        } else if (media >= 26 && media <= 60) {
            System.out.println("Adulto");
        } else if (media > 60) {
            System.out.println("idosa");
        }
    }
}