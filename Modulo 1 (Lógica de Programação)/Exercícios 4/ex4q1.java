import java.util.*;

public class ex4q1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int cont = 0;

        List<Boolean> entradas = new ArrayList<>();
        List<Integer> entrada1 = new ArrayList<>();
        List<Integer> entrada2 = new ArrayList<>();
        List<Integer> entrada3 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.print("Entradas de teste 1 (Não vota): ");
            entrada1.add(scanner.nextInt());
        }
        for (int i = 0; i < 3; i++) {
            System.out.print("Entradas de teste 2 (Voto obrigatório): ");
            entrada2.add(scanner.nextInt());
        }
        for (int i = 0; i < 3; i++) {
            System.out.print("Entradas de teste 3 (Voto opcional): ");
            entrada3.add(scanner.nextInt());
        }


        for (int i = 0; i < entrada1.size(); i++) {
            if (entrada1.get(i) < 16){
                cont += 1;
            }
        }

        if (cont == 3){
            entradas.add(true);
        }
        else{
            entradas.add(false);
        }

        cont = 0;

        for (int i = 0; i < entrada1.size(); i++) {
            if (entrada2.get(i) >= 18 && entrada2.get(i) <= 70){
                cont += 1;
            }
        }

        if (cont == 3){
            entradas.add(true);
        }
        else {
            entradas.add(false);
        }

        cont = 0;

        for (int i = 0; i < entrada1.size(); i++) {
            if ((entrada3.get(i) >= 16 && entrada3.get(i) < 18) || entrada3.get(i) > 70){
                cont += 1;
            }
        }

        if (cont == 3){
            entradas.add(true);
        }
        else {
            entradas.add(false);
        }


        for (int i = 0; i < entradas.size(); i++) {
            if (entradas.get(i)){
                System.out.printf("Teste %d - Sucesso\n", i+1);
            }
            else {
                System.out.printf("Teste %d - Falhou\n", i);
            }

        }
    }
}
