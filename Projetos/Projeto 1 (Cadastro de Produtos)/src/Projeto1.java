/*
GRUPO 5:
Ian Paschoal Oliveira Belato de Freitas
Julie Oliveira Duarte
Danilo Chaimsohn Gonçalves
Matheus Ribeiro Panobianco
Luis Guilherme Narguis Tabuso
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Projeto1 {
    static Scanner scanner = new Scanner(System.in);
    static List<Produtos> produtos = new ArrayList<>();
    static List<Carrinho> carrinho = new ArrayList<>();

    public static void main(String[] args) {
        try {
            if (produtos.size() == 0) lerLista();
        }catch (RuntimeException e){
            System.out.println("Arquivo de controle de estoque não encontrado.");
        }
        try {
            Menu();
        } catch (Throwable e) {
            System.out.println("Erro na execução do programa.");
        }

        try {
            salvarLista();
        } catch (Throwable e) {
            System.out.println("Erro salvando lista.");
        } finally {
            scanner.close();
        }
    }

    private static void lerLista() {
        List<String> listaProdutos;
        try {
            listaProdutos = Files.readAllLines(Path.of("lista_final.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int j = 0; j < (listaProdutos.size() - 1); j++) {
            try {
                String produtosDaLista = listaProdutos.get(j);
                produtosDaLista = produtosDaLista.replace("[", "").replace("]", "");
                produtosDaLista = produtosDaLista.replace(",", "").replace("]", "");
                produtosDaLista = produtosDaLista.trim();
                String[] splitProdutos = produtosDaLista.split(" ");
                String nm = splitProdutos[0];
                int qtd = parseInt(splitProdutos[1]);
                float prc = parseFloat(splitProdutos[2]);
                Produtos prod = new Produtos(nm, qtd, prc);
                produtos.add(prod);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao ler indice " + (j + 1) + " da lista.");
            }
        }
    }
    private static void salvarLista() throws Exception {
        List<String> linhasProdEstoque = new ArrayList<>();
        for (int i = 0; i < produtos.size(); i++) {
            String nome = produtos.get(i).getNome() + " ";
            String qtd = produtos.get(i).getQuantidade().toString() + " ";
            String prc = produtos.get(i).getPreco().toString() + "\n";
            String linha = nome + qtd + prc;
            linhasProdEstoque.add(linha);
        }
        try {
            Files.write(Path.of("lista_final.txt"), linhasProdEstoque.toString().getBytes(), StandardOpenOption.DELETE_ON_CLOSE);
        } catch (NoSuchFileException e) {
            System.out.println("Arquivo não encontrado. Criando um arquivo para salvar os produtos em estoque.\n" +
                    "Reinicie o Programa.");
        } catch (FileAlreadyExistsException e) {
            System.out.println("Não é possivel criar arquivo com mesmo nome.");
        }
        Files.write(Path.of("lista_final.txt"), linhasProdEstoque.toString().getBytes(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
    }
    public static void Menu() {
        int escolha = 0;
        do {
            System.out.println("""
                    =========MENU===========
                    1 - CRIAR PRODUTO
                    2 - EDITAR PRODUTO
                    3 - EXCLUIR PRODUTO
                    4 - PESQUISAR PRODUTOS
                    5 - VENDER DE PRODUTOS
                    0 - SAIR DO PROGRAMA
                    ========================""");
            boolean tratamento = true;
            while (tratamento) {
                try {
                    System.out.print("Digite a opção desejada: ");
                    escolha = scanner.nextInt();
                    tratamento = false;
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida...");
                }
                switch (escolha) {
                    case 1:
                        boolean continuarCriando = true;
                        do {
                            criarProduto();
                            continuarCriando = desejaContinuar();
                        } while (continuarCriando);
                        break;

                    case 2: {
                        if (produtos.size() != 0) {
                            boolean continuarEditando = true;
                            do {
                                editarProduto();
                                continuarEditando = desejaContinuar();
                            } while (continuarEditando);
                            listagemProdutos();
                        } else {
                            System.out.println("Não há produtos cadastrados.");
                            System.out.println();
                        }
                        break;
                    }
                    case 3: {
                        if (produtos.size() != 0) {
                            boolean continuarExcluindo = true;
                            do {
                                excluirProduto();
                                continuarExcluindo = desejaContinuar();
                            } while (continuarExcluindo);
                        } else {
                            System.out.println("Não há produtos cadastrados.");
                            System.out.println();
                        }
                        break;
                    }
                    case 4: {
                        if (produtos.size() != 0) {
                            boolean continuarPesquisando = true;
                            do {
                                pesquisarProduto();
                                continuarPesquisando = desejaContinuar();
                            } while (continuarPesquisando);
                        } else {
                            System.out.println("Não há produtos cadastrados.");
                            System.out.println();
                        }
                        break;
                    }
                    case 5: {
                        if (produtos.size() != 0) {
                            boolean continuarVendendo = true;
                            System.out.println("=============MENU VENDAS=============");
                            listagemProdutos();
                            do {
                                venderProdutos();
                                continuarVendendo = desejaContinuar();

                            } while (continuarVendendo);
                            seuCarrinho(carrinho);

                        } else {
                            System.out.println("Não há produtos cadastrados.");
                            System.out.println();
                        }
                        break;
                    }
                    case 0:
                        System.out.println("Finalizando programa...");
                        return;
                    default: {
                        System.out.println("Opção Inválida!");
                        break;
                    }
                }
            }
        } while (escolha != 0);
    }
    private static void venderProdutos() throws InputMismatchException {
        int quantidade = 0;
        float valorProduto = 0.0F;
        int codigo = 0;
        boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("Digite o codigo do produto:");
                codigo = scanner.nextInt();
                codigo -= 1;
                continuar = false;
            } catch (InputMismatchException e) {
                System.out.println("Erro de entrada de dados.");
                scanner.next();
            }
        }

        if (codigo >= 0 && codigo < produtos.size()) {
            try {
                System.out.println("Digite a quantidade do produto:");
                quantidade = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro de entrada de dados.");
                scanner.next();
            }
            if (quantidade <= produtos.get(codigo).getQuantidade() && quantidade >= 0) {
                valorProduto = produtos.get(codigo).getPreco() * quantidade;
                Carrinho car = new Carrinho(produtos.get(codigo).getNome(), quantidade, valorProduto);
                carrinho.add(car);
            } else if (quantidade < 0) {
                System.out.println("Valor invalido.");
            } else {
                System.out.println("Quantidade não encontrada em estoque.");
            }
        } else {
            System.out.println("Codigo nao encontrado a lista.");
        }
    }
    private static void seuCarrinho(List<Carrinho> carrinho) {
        float valorTotal = 0.0f;
        System.out.println("Seu Carrinho:");
        for (int i = 0; i < carrinho.size(); i++) {
            valorTotal += carrinho.get(i).getValor();
            System.out.print((i + 1) + " - Nome: " + carrinho.get(i).getNome() + "\t - Quantidade: " + carrinho.get(i).getQuantidade() + "\t - R$: " + carrinho.get(i).getValor() + "\n");
        }
        System.out.printf("Valor total: R$ %.2f. %n%n", valorTotal);

        for (int i = 0; i < produtos.size(); i++) {
            for (int j = 0; j < carrinho.size(); j++) {
                if (produtos.get(i).getNome().equalsIgnoreCase(carrinho.get(j).getNome())) {
                    produtos.get(i).setQuantidade(produtos.get(i).getQuantidade() - carrinho.get(j).getQuantidade());
                }
            }
        }
    }
    private static void listagemProdutos() {
        System.out.println("Lista de produtos:");
        System.out.println("Produto\t / Quantidade\t / Preço");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.print((i + 1) + " - Nome: " + produtos.get(i).getNome() + "\t - Quantidade: " + produtos.get(i).getQuantidade() + "\t - R$: " + produtos.get(i).getPreco() + "\n");
        }
    }
    private static void pesquisarProduto() {
        System.out.println("Digite o nome do produto:");
        String produtoPesquisado = scanner.next();
        int contador = 0;
        int tamanho = produtoPesquisado.length();
        for (int i = 0; i < produtos.size(); i++) {
            String produto = produtos.get(i).getNome();
            if (produtoPesquisado.equalsIgnoreCase(produto.substring(0, tamanho))) {
                System.out.print((i + 1) + " - Nome: " + produtos.get(i).getNome() + "\t - Quantidade: " + produtos.get(i).getQuantidade() + "\t - R$: " + produtos.get(i).getPreco() + "\n");
                System.out.println();
            } else {
                contador++;
            }
        }
        if (contador == produtos.size()) {
            System.out.println("Produto não encontrado!");
            System.out.println();
        }
    }
    private static void excluirProduto() {
        listagemProdutos();
        System.out.println("Escolha um item para excluir:");
        try {
            int indice = scanner.nextInt();

            if (indice <= produtos.size() && indice > 0) {
                produtos.remove(indice - 1);
                listagemProdutos();
            } else {
                System.out.println("Item inválido");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida...");
            scanner.next();
        }
    }
    private static void editarProduto() {
        listagemProdutos();
        System.out.println("Digite o indice do produto:");
        int indice = scanner.nextInt();
        if (indice > 0 && indice <= produtos.size()) {
            System.out.println("""
                    1 - EDITAR NOME
                    2 - EDITAR QUANTIDADE
                    3 - EDITAR PREÇO""");
            System.out.print("O que você deseja fazer? ");
            String tipoEdicao = scanner.next();
            switch (tipoEdicao) {
                case "1": {
                    System.out.println("Digite o novo NOME do produto:");
                    String novoNome = scanner.next();
                    produtos.get(indice - 1).setNome(novoNome);
                    break;
                }
                case "2": {
                    try {
                        System.out.println("Digite a nova QUANTIDADE do produto:");
                        int novaQuantidade = scanner.nextInt();
                        produtos.get(indice - 1).setQuantidade(novaQuantidade);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida.");
                        scanner.nextLine();
                    }
                    break;
                }
                case "3": {
                    try {
                        System.out.println("Digite o novo preço do produto:");
                        float novoPreco = scanner.nextFloat();
                        produtos.get(indice - 1).setPreco(novoPreco);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada invalida.");
                        scanner.nextLine();
                    }
                    break;
                }
                default: {
                    System.out.println("Opção inválida...");
                    break;
                }
            }
        } else {
            System.out.println("Indice inválido");
        }
    }
    private static void criarProduto() {
        System.out.print("Nome do produto: ");
        String nm = scanner.next();
        try {
            System.out.print("Quantidade em estoque: ");
            int qtd = scanner.nextInt();

            System.out.print("Preço do produto: R$");
            float prc = scanner.nextFloat();

            Produtos prod = new Produtos(nm, qtd, prc);

            produtos.add(prod);

        } catch (InputMismatchException e) {
            System.out.printf("Entrada inválida...\nDigite as informações do item (%s) novamente.\n", nm);
            scanner.nextLine();
        }
    }
    public static boolean desejaContinuar() {
        boolean continuar_Lista = true;
        int mais_Produtos;
        boolean adicionarNovoProduto = true;
        do {
            try {
                System.out.println("Deseja continuar?\n1 - Sim\n2 - Não");
                mais_Produtos = scanner.nextInt();
                if (mais_Produtos == 1) {
                    continuar_Lista = false;
                    adicionarNovoProduto = true;
                } else if (mais_Produtos == 2) {
                    continuar_Lista = false;
                    adicionarNovoProduto = false;
                    System.out.println("Finalizado.");
                } else {
                    System.out.println("Não é uma opção válida...");
                    continuar_Lista = true;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                continuar_Lista = true;
            }
        } while (continuar_Lista);
        return adicionarNovoProduto;
    }
}
