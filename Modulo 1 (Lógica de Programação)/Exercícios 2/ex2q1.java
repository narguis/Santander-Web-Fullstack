public class ex2q1 {
    public static void main(String[] args) {

        String frase = "Janeiro: 1543, Fevereiro: 1222, Março: 1234";
        String[] meses = frase.split(", ");
        int jan = Integer.parseInt(meses[0].substring(meses[0].indexOf("1")));
        int fev = Integer.parseInt(meses[1].substring(meses[1].indexOf("1")));
        int mar = Integer.parseInt(meses[2].substring(meses[2].indexOf("1")));

        System.out.println(meses[0]);
        System.out.println(meses[1]);
        System.out.println(meses[2]);
        System.out.println("Total: " + (jan+fev+mar));
    }
}
