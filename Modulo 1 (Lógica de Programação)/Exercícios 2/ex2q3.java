import java.util.Scanner;

public class ex2q3 {

    public static void main(String[] args) {
        System.out.print("Você deseja ver as despesas de Janeiro, Fevereiro ou Março? ");
        Scanner scanner = new Scanner(System.in);
        String mes = scanner.next().toUpperCase();
        boolean check = mes.equals("JANEIRO") || mes.equals("FEVEREIRO") || mes.equals("MARÇO");

        while(!check){
            System.out.print("Você deseja ver as despesas de Janeiro, Fevereiro ou Março? ");
            mes = scanner.next().toUpperCase();
            check = mes.equals("JANEIRO") || mes.equals("FEVEREIRO") || mes.equals("MARÇO");
        }

        if(mes.equals("JANEIRO")){
            System.out.printf("%s = %.2f", mes, Trimestre.JANEIRO.getGastos());
        }

        else if(mes.equals("FEVEREIRO")){
            System.out.printf("%s = %.2f", mes, Trimestre.FEVEREIRO.getGastos());
        }

        else{
            System.out.printf("%s = %.2f", mes, Trimestre.MARCO.getGastos());
        }
    }
}