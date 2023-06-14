package uol.compass.ecommerce.view;

import uol.compass.ecommerce.controller.MenuController;

import java.util.Scanner;

public class Menus {

    private Scanner scan;
    private Integer option;
    private MenuController controller;
    private static final String HEADER = "--------------[ECommerce Manager]---------------";
    private static final String FOOTER = "----------------------------------";
    public Menus(Scanner scan){
        this.scan = scan;
        this.controller = new MenuController(this, scan);
    }

    public void showMainMenu(){
        this.option = -1;
        System.out.println(HEADER);
        System.out.println("1- Entrar como GERENTE");
        System.out.println("2- Entrar como CLIENTE");
        System.out.println("3- Configuracoes");
        System.out.println("0- Sair");
        System.out.println(FOOTER);
        requestUserInput();
        this.controller.mainMenu(option);
    }

    public void showConfigMenu(){
        this.option = -1;
        System.out.println(HEADER);
        System.out.println("1- Configurar HOST MySQL");
        System.out.println("2- Configurar PORTA MySQL");
        System.out.println("3- Configurar DATABASE MySQL");
        System.out.println("4- Configurar USUARIO MySQL");
        System.out.println("5- Configurar SENHA MySQL");
        System.out.println("6- Voltar");
        System.out.println("0- Sair");
        System.out.println(FOOTER);
        requestUserInput();
        this.controller.configMenu(option);
    }

    public void showManagerMenu(){
        this.option = -1;
        System.out.println(HEADER);
        System.out.println("1- Adicionar novo produto");
        System.out.println("2- Deletar produto existente");
        System.out.println("3- Editar produto existente");
        System.out.println("3- Adicionar estoque de produto existente");
        System.out.println("4- Remover estoque de produto existente");
        System.out.println("5- Alterar preco de produto existente");
        System.out.println("0- Sair");
        System.out.println(FOOTER);
        requestUserInput();
        this.controller.managerMenu(this.option);
    }

    public void showClientMenu(){
        this.option = -1;
        System.out.println(HEADER);
        System.out.println("1- Listar todos produtos");
        System.out.println("2- Adicionar produto ao carrinho");
        System.out.println("3- Remover produto do carrinho");
        System.out.println("4- Alterar quantidade de produto");
        System.out.println("5- Ver carrinho");
        System.out.println("6- Esvaziar carrinho");
        System.out.println("7- Finalizar compra");
        System.out.println("0- Sair");
        System.out.println(FOOTER);
        requestUserInput();
        this.controller.clientMenu(this.option);
    }

    public void confirmPurchase(){
        this.option = -1;
        System.out.println(HEADER);
        System.out.println("Deseja finalizar a compra?");
        System.out.println("1- SIM");
        System.out.println("2- Continuar comprando");
        System.out.println("0- Sair");
        System.out.println(FOOTER);
        requestUserInput();

    }

    public void requestUserInput(){
        System.out.print("Digite a opcao desejada: ");
        this.option = this.scan.nextInt();
    }



}
