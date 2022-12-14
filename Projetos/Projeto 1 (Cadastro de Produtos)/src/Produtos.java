
public class Produtos {
    String nome;
    int quantidade;
    float preco;

    public Produtos(String nome, int quantidade, float preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public Float getPreco() {
        return preco;
    }
    void setPreco (float preco) {
        this.preco = preco;
    }
    void setNome (String nome) {
        this.nome = nome;
    }
    void setQuantidade (int quantidade) {
        this.quantidade = quantidade;
    }

}
