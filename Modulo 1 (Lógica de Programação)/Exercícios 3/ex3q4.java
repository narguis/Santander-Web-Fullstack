import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ex3q4 {
    public static void main(String[] args) {
        float total = 0;
        String continuar = "";
        List<String> drinks = new ArrayList<>();
        System.out.println("""
                1 - Coca-Cola - R$ 5
                2 - Coca-Cola 0 - R$ 4.50
                3 - Pepsi - R$ 4,40
                4 - Guaraná Antarctica - R$ 3,50
                5 - Fanta Laranja - R$ 4,23
                6 - Água - R$ 2,50""");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Qual bebida você quer? ");
        int bebida = scanner.nextInt();

        while (!continuar.equalsIgnoreCase("não")) {
                if (bebida == 1){
                    drinks.add("Coca-Cola");
                    total += 5;
                }
                else if (bebida == 2) {
                    drinks.add("Coca-Cola 0");
                    total += 4.5;
                }
                else if (bebida == 3) {
                    drinks.add("Pepsi");
                    total += 4.4;
                }
                else if (bebida == 4) {
                    drinks.add("Guaraná Antarctica");
                    total += 3.50;
                }
                else if (bebida == 5) {
                    drinks.add("Fanta Laranja");
                    total += 4.23;
                }
                else if (bebida == 6) {
                    drinks.add("Água");
                    total += 2.5;
                }
                else {
                    System.out.println("Opção inválida!");
                }
            System.out.print("Deseja continuar? ");
            continuar = scanner.next();
            if (continuar.equalsIgnoreCase("nao")){
                break;
            }
            System.out.print("Qual bebida você quer? ");
            bebida = scanner.nextInt();
        }
        String lista = drinks.toString();
        System.out.printf("Você comprou %s, totalizando R$%.2f", lista, total);
    }
}