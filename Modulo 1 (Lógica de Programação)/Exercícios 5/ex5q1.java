import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ex5q1 {
    public static <Int> void main(String[] args){
        List<String> produtos = new ArrayList<>();
        List<Integer> quantidades = new ArrayList<>();
        List<Float> precos = new ArrayList<>();
        String continuar = "sim";
        int i = 1;
        float precototal = 0;
        Scanner scanner = new Scanner(System.in);

        while (!continuar.equalsIgnoreCase("nao")){
            System.out.printf("%d Produto: ", i);
            String prod = scanner.next();
            produtos.add(prod);

            System.out.printf("Quantidade do %d produto: ", i);
            int qtd = scanner.nextInt();
            quantidades.add(qtd);

            System.out.printf("Preço unitário do %d produto: ", i);
            float preco = scanner.nextFloat();
            precos.add(preco);
            precototal += (preco * qtd);

            i++;

            System.out.print("Deseja continuar? ");
            continuar = scanner.next();
        }

        for (i = 0; i < produtos.size(); i++) {
            System.out.printf("Você comprou %d unidades de %s, por R$%.2f cada uma.\n", quantidades.get(i), produtos.get(i), precos.get(i));
        }
        System.out.printf("No total, sua compra deu %.2f", precototal);
    }
}
