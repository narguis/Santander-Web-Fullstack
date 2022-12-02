import java.util.Scanner;

public class ex3q2 {
    public static void main(String[] args) {
        System.out.println("\"Voto obrigatório\"  - para eleitoras e eleitores, com idades entre 18 e 70 anos.\n" +
                "\n" +
                "\"Voto  facultativo\" - para maiores ou iguais a 16 anos e menores de 18 anos;\n" +
                " assim como maiores de 70 anos.\n" +
                "\n" +
                "\"Sem direito a votar\" - para o restante.\n" +
                "\n" +
                "Fonte: https://www.tre-sc.jus.br/eleicoes/tire-suas-duvidas/voto-obrigatoriedade");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Qual é a sua idade? ");
        int idade = scanner.nextInt();

        System.out.println((idade >= 16) ? "Pode votar" : "Não pode votar");
    }
}
