import java.util.Scanner;

public class ex1q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        double numsorte = idade + .023;

        System.out.printf("O número da sorte é: %.3f", numsorte);
    }
}
