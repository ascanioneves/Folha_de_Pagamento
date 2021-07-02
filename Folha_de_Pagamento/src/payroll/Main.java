package payroll;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class Main {
  public static void main(String[] args) {
    int state = 1;
    List<Empregado> empregados = new ArrayList<Empregado>();
    while (state != 0) {
      Scanner input = new Scanner(System.in);
      input.useLocale(Locale.ENGLISH);
      System.out.println("[1] Para cadastrar um empregado\n[2] Remover um empregado\n[3] Lancar cartao de ponto\n[4] Listar os empregados");
      System.out.println("[5] Cadastrar venda\n[0] Para sair\n");
      state = input.nextInt();
      if (state == 0) {
        break;
      }
      input = new Scanner(System.in);
      input.useLocale(Locale.ENGLISH);
      if (state == 1) {
        System.out.println("Digite o nome do empregado: ");
        String nome = input.nextLine();
        System.out.println("Digite o endereco do empregado: ");
        String endereco = input.nextLine();
        System.out.println("Informe o tipo do empregado: \n[1] Horista\n[2] Assalariado\n[3] Comissionado");
        int tipo = input.nextInt();
        String id = UUID.randomUUID().toString();
      
        if (tipo == 1) {
          System.out.println("Digite o salario por hora do empregado " + nome + ": ");
          double salario_hora = input.nextDouble();
          empregados.add(EmpregadoCrud.create(nome, id, endereco, salario_hora));
        }
        else if (tipo == 3) {
          System.out.println("Digite o salario do empregado " + nome + ": ");
          double salario = input.nextDouble();
          System.out.println("Digite a comissao do empregado " + nome + ": ");
          double comissao = input.nextDouble();
          empregados.add(EmpregadoCrud.create(nome, id, endereco, salario, comissao));
        }
        else {
          System.out.println("Digite o salario do empregado " + nome + ": ");
          double salario = input.nextDouble();
          empregados.add(EmpregadoCrud.create(nome, id, salario, endereco));
        }
      }
      if (state == 2) {
        System.out.println("Informe o ID do empregado a ser removido: ");
        String idDel = input.nextLine();
        Boolean del = EmpregadoCrud.delete(empregados, idDel);
        if (del) {
          System.out.println("Empregado removido com sucesso!\n");
        }
        else {
          System.out.println("ID invalido!\n");
        }
      }
      if (state == 3) {
        Boolean exists = false;
        System.out.println("Informe o ID do empregado para o lancamento do cartao de ponto: ");
        String idEmp = input.nextLine();
        for (Empregado empregado : empregados) {
          if (empregado.getId().equals(idEmp)) {
            exists = true;
            System.out.println("Informe a data e horario de entrada: ");
            String entrada = input.nextLine();
            System.out.println("Informe a data e horario de saida");
            String saida = input.nextLine();
            empregado.cartoes.add(new CartaoDePonto(entrada, saida));
            break;
          }
        }
        if (!exists) {
          System.out.println("ID invalido!");
        }
      }
      if (state == 5) {
        Boolean exists = false;
        System.out.println("Informe o ID do empregado para cadastrar sua venda: ");
        String idEmp = input.nextLine();
        for (Empregado empregado : empregados) {
          if (empregado.getId().equals(idEmp)) {
            exists = true;
            System.out.println("Informe o valor da venda: ");
            double valor = input.nextDouble();
            String clear = input.nextLine();
            System.out.println("Informe a data da venda: ");
            String data = input.nextLine();
            empregado.vendas.add(new Vendas(valor, data));
            break;
          }
        }
        if (!exists) {
          System.out.println("ID invalido!");
        }
      }
      for (Empregado e : empregados) {
        System.out.println(e + "\n");
      }
    }
  }
}