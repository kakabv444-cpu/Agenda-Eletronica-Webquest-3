import java.util.Scanner;
import java.util.List;

public class AgendaApplication {
    public static void main(String[] args) {

        AgendaManager agenda = new AgendaManager();
        Scanner sc = new Scanner(System.in);

        try {
            agenda.adicionarContato(new Contato("Kaká", "1111-1111", "kaka@email.com"));
            agenda.adicionarContato(new Contato("Marcos", "2222-2222", "marcos@email.com"));
            agenda.adicionarContato(new Contato("Filipe", "3333-3333", "filipe@email.com"));
            agenda.adicionarContato(new Contato("Nathy", "4444-4444", "nathy@email.com"));
            agenda.adicionarContato(new Contato("Dani", "5555-5555", "dani@email.com"));
        } catch (Exception e) {}

        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Buscar Contato");
            System.out.println("3. Remover Contato");
            System.out.println("4. Listar Todos");
            System.out.println("5. Salvar CSV");
            System.out.println("6. Carregar CSV");
            System.out.println("7. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String n = sc.nextLine();
                        System.out.print("Telefone: ");
                        String t = sc.nextLine();
                        System.out.print("Email: ");
                        String e = sc.nextLine();
                        agenda.adicionarContato(new Contato(n,t,e));
                        break;

                    case 2:
                        System.out.print("Nome: ");
                        Contato c = agenda.buscarContato(sc.nextLine());
                        System.out.println(c);
                        break;

                    case 3:
                        System.out.print("Nome: ");
                        agenda.removerContato(sc.nextLine());
                        break;

                    case 4:
                        List<Contato> todos = agenda.listarTodosContatos();
                        for (Contato ct : todos) System.out.println(ct);
                        break;

                    case 5:
                        agenda.salvarContatosCSV("contatos.csv");
                        System.out.println("Salvo!");
                        break;

                    case 6:
                        agenda.carregarContatosCSV("contatos.csv");
                        System.out.println("Carregado!");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }

        sc.close();
    }
}
