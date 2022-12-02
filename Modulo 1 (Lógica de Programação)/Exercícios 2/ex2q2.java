public class ex2q2 {
    public static void main(String[] args){
        String nome = "João Maria da Silva";
        String[] separado = nome.split(" ");
        String resultado = String.join("!", separado);
        System.out.println("!"+resultado);
    }
}
