package payroll;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class Main {
  public static void main(String[] args) {
    int state = 1;
    List<Empregado> empregados = new ArrayList<Empregado>();
    List<Empregado> emp_aux2 = new ArrayList<Empregado>();
    List<Empregado> emp_aux3 = new ArrayList<Empregado>();


    int undo = 0, redo = 0, ult_op = 0;
    String id_aux = "";
    while (state != 0) {
      Scanner input = new Scanner(System.in);
      input.useLocale(Locale.ENGLISH);
      System.out.println("[1] Para cadastrar um empregado\n[2] Remover um empregado\n[3] Lancar cartao de ponto\n[4] Listar os empregados");
      System.out.println("[5] Cadastrar venda\n[6] Lancar taxa de servico\n[7] Alterar dados de um empregado\n[8] Rodar folha para hoje");
      System.out.println("[9] Undo\n[0] Sair");
      if (state != 4) { //pois o estado 4 é de listar empregados, ou seja, n é uma operação
        ult_op = state;
      }
      state = input.nextInt();
      if (state == 0) {
        break;
      }
      copy_emp(empregados, emp_aux2);
      input = new Scanner(System.in);
      input.useLocale(Locale.ENGLISH);
      if (state == 1) {
        System.out.println("Digite o nome do empregado: ");
        String nome = input.nextLine();
        System.out.println("Digite o endereco do empregado: ");
        String endereco = input.nextLine();
        System.out.println("Digite o metodo de pagamento do empregado: ");
        String metodo = input.nextLine();
        System.out.println("Informe o tipo do empregado: \n[1] Horista\n[2] Assalariado\n[3] Comissionado");
        int tipo = input.nextInt();
        String id = UUID.randomUUID().toString();
        Empregado empregado;
        undo = 1;
        if (tipo == 1) {
          System.out.println("Digite o salario por hora do empregado " + nome + ": ");
          double salario_hora = input.nextDouble();
          empregado = EmpregadoCrud.create(nome, id, endereco, salario_hora, 0, null, tipo, null, null, metodo);
        }
        else if (tipo == 3) {
          System.out.println("Digite o salario do empregado " + nome + ": ");
          double salario = input.nextDouble();
          System.out.println("Digite a comissao do empregado " + nome + ": ");
          double comissao = input.nextDouble();
          empregado = EmpregadoCrud.createComissionado(nome, id, endereco, salario, salario, comissao, null, tipo, null, null, metodo);
        }
        else {
          System.out.println("Digite o salario do empregado " + nome + ": ");
          double salario = input.nextDouble();
          empregado = EmpregadoCrud.createAssalariado(nome, id, endereco, salario, salario, null, tipo, null, null, metodo);
        }
        empregados.add(empregado);
        System.out.println("Deseja participar do sindicato?\n[1] Sim\n[2] Nao\n");
        int participarSindicato = input.nextInt();
        if (participarSindicato == 1) {
          System.out.println("Informe a taxa do sindicato que o empregado pertence: ");
          double taxa = input.nextDouble();
          UUID idSindicato = UUID.randomUUID();
          Sindicato sindicato = new Sindicato(idSindicato, id, taxa);
          empregado.setSindicato(sindicato);
        }
      }
      if (state == 2) {
        System.out.println("Informe o ID do empregado a ser removido: ");
        String idDel = input.nextLine();
        Boolean del = EmpregadoCrud.delete(empregados, idDel);
        undo = 1;
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
        id_aux = idEmp;
        for (Empregado empregado : empregados) {
          if (empregado.getId().equals(idEmp)) {
            exists = true;
            System.out.println("Informe o horario de entrada: ");
            String entrada = input.nextLine();
            System.out.println("Informe o horario de saida");
            String saida = input.nextLine();
            empregado.getCartoes().add(new CartaoDePonto(entrada, saida));
            undo = 1;
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
        id_aux = idEmp;
        for (Empregado empregado : empregados) {
          if (empregado.getId().equals(idEmp)) {
            exists = true;
            System.out.println("Informe o valor da venda: ");
            double valor = input.nextDouble();
            String clear = input.nextLine();
            System.out.println("Informe a data da venda: ");
            String data = input.nextLine();
            empregado.getVendas().add(new Vendas(valor, data));
            undo = 1;
            break;
          }
        }
        if (!exists) {
          System.out.println("ID invalido!");
        }
      }
      if (state == 6) {
        Predicate<Empregado> empFiltro = empregado -> empregado.getSindicato() != null;
        ArrayList<Empregado> sindicatoLista = empregados.stream().filter(empFiltro).
                collect(Collectors.toCollection(ArrayList::new));

        Boolean exists = false;
        System.out.println("Informe o ID do empregado para lancar a sua taxa de servico: ");
        String idEmp = input.nextLine();
        id_aux = idEmp;
        for (Empregado empregado : sindicatoLista) {
          if (empregado.getId().equals(idEmp)) {
            exists = true;
            System.out.println("Informe o valor da taxa: ");
            double valor = input.nextDouble();
            String clear = input.nextLine();
            System.out.println("Informe o nome do servico: ");
            String servico = input.nextLine();
            empregado.getSindicato().getTaxa().add(new Taxa(valor, servico));
            undo = 1;
            break;
          }
        }
        if (!exists) {
          System.out.println("ID invalido!");
        }
      }
      if (state == 7) {
        Boolean exists = false;
        System.out.println("Informe o ID do empregado a ser alterado: ");
        String idEmp = input.nextLine();
        Empregado empAtual = null;
        for (Empregado empregado : empregados) {
          if (empregado.getId().equals(idEmp)) {
            exists = true;
            empAtual = empregado;
            break;
          }
        }
        if (!exists) {
          System.out.println("ID invalido!");
        }
        else {
          int menuAlteracao = 1;
          undo = 1;
          while (menuAlteracao != 0) {
            Scanner in = new Scanner(System.in);
            in.useLocale(Locale.ENGLISH);
            System.out.println("[1] Alterar nome\n[2] Alterar endereco\n[3] Alterar tipo\n[4] Alterar metodo de pagamento");
            System.out.println("[5] Alterar participacao no sindicato\n[6] Alterar indentificacao no sindicato\n[7] Alterar taxa sindical");
            System.out.println("[8] Undo\n[0] Sair");
            menuAlteracao = in.nextInt();
            String clear = in.nextLine();
            if (menuAlteracao == 0) {
              break;
            }
            if (menuAlteracao == 1) {
              System.out.println("Informe o novo nome: ");
              String newName = in.nextLine();
              empAtual.setName(newName);
            }
            else if (menuAlteracao == 2) {
              System.out.println("Informe o novo endereco: ");
              String newAddress = in.nextLine();
              empAtual.setAddress(newAddress);
            }
            else if (menuAlteracao == 3) {
              System.out.println("Informe o novo tipo: \n[1] Horista\n[2] Assalariado\n[3] Comissionado\n");
              int tipo = in.nextInt();
              clear = in.nextLine();
              Empregado aux = empAtual;
              empregados.remove(empAtual);
              if (tipo == 1) {
                System.out.println("Informe o salario por hora: ");
                double salario_hora = in.nextDouble();
                clear = in.nextLine();
                empAtual = EmpregadoCrud.create(aux.getName(), aux.getId(), aux.getAddress(), salario_hora, 0, aux.getSindicato(), tipo, aux.getCartoes(), aux.getVendas(), aux.getMetodo());
              }
              else if (tipo == 2) {
                System.out.println("Digite o salario do empregado: ");
                double salario = input.nextDouble();
                clear = in.nextLine();
                empAtual = EmpregadoCrud.createAssalariado(aux.getName(), aux.getId(), aux.getAddress(), salario, salario, aux.getSindicato(), tipo, aux.getCartoes(), aux.getVendas(), aux.getMetodo());
              }
              else if (tipo == 3) {
                System.out.println("Digite o salario do empregado: ");
                double salario = input.nextDouble();
                System.out.println("Digite a comissao do empregado: ");
                double comissao = input.nextDouble();
                empAtual = EmpregadoCrud.createComissionado(aux.getName(), aux.getId(), aux.getAddress(), salario, salario, comissao, aux.getSindicato(), tipo, aux.getCartoes(), aux.getVendas(), aux.getMetodo());
              }
              empregados.add(empAtual);
            }
            else if (menuAlteracao == 4) {
              System.out.println("Digite o novo metodo de pagamento");
              String newMethod = in.nextLine();
              empAtual.setMetodo(newMethod);
            }
            else if (menuAlteracao == 5) {
              if (empAtual.getSindicato() != null) {
                System.out.println("Voce deseja retirar o empregado do sindicato?\n[1] Sim\n[2] Nao");
                int sind = in.nextInt();
                if (sind == 1) {
                  empAtual.setSindicato(null);
                }
              }
              else {
                System.out.println("Voce deseja adicionar o empregado ao sindicato?\n[1] Sim\n[2] Nao");
                int sind = in.nextInt();
                if (sind == 1) {
                  System.out.println("Informe a taxa do sindicato que o empregado pertence: ");
                  double taxa = input.nextDouble();
                  UUID idSindicato = UUID.randomUUID();
                  Sindicato sindicato = new Sindicato(idSindicato, empAtual.getId(), taxa);
                  empAtual.setSindicato(sindicato);
                }
              }
            }
            else if (menuAlteracao == 6) {
              if (empAtual.getSindicato() == null) {
                System.out.println("O empregado não pertence a um sindicato!");
              }
              else {
                String idEmpSind = UUID.randomUUID().toString();
                empAtual.getSindicato().setIdEmp(idEmpSind);
                System.out.println("ID alterado para: " + idEmpSind);
              }
            }
            else if (menuAlteracao == 7) {
              if (empAtual.getSindicato() == null) {
                System.out.println("O empregado não pertence a um sindicato!");
              }
              else {
                System.out.println("Digite a nova taxa para o sindicato: ");
                double tx = input.nextDouble();
                empAtual.getSindicato().setTaxaSindicato(tx);
              }
            }
            else if (menuAlteracao == 8) {
              
            }
      
          }
        }
      }
      if (state == 4) {
        for (Empregado e : empregados) {
          System.out.println(e + "\n");
        }
      }
      if (state == 8) { 
        LocalDate date = LocalDate.now();  
        DayOfWeek dayOfWeek = DayOfWeek.from(date);
        int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        ArrayList<Empregado> ans = new ArrayList<Empregado>();
        undo = 1;
        for (Empregado e : empregados) {
          //[1] Horista [2] Assalariado [3] Comissionado
          if (e.getType() == 1) {
            if (dayOfWeek.getValue() == 5) {
              ans.add(e);
            }
          }
          else if (e.getType() == 2) {
            if (date.getDayOfMonth() == lastDay) {
              ans.add(e);
            }
          }
          else if (e.getType() == 3) {
            //pagando por padrao nas 2 primeiras sextas
            if (dayOfWeek.getValue() == 4 && (date.getDayOfMonth() <= 14)) {
              ans.add(e);
            }
          }
        }
        System.out.println("Lista de funcionarios: ");
        System.out.println("------------------------------");
        for (Empregado x : ans) {
          System.out.println(x);
          System.out.print("################################\n");
        }
      }
      if (state == 9) { //undo e redo
        if (undo == 1) {
          //adicionei um emp
          if (ult_op == 1) {
            System.out.println("Undo realizado com sucesso!");
            System.out.println("O Empregado " + empregados.get(empregados.size() - 1).getName() + " foi retirado!");
            EmpregadoCrud.delete(empregados, empregados.get(empregados.size() - 1).getId());
            //copy_emp(emp_aux2, empregados);
          }
          //removi um emp
          else if (ult_op == 2) {
            System.out.println("Undo realizado com sucesso!");
            System.out.println("O Empregado " + emp_aux2.get(emp_aux2.size() - 1).getName() + " foi recuperado!");
            empregados.add(emp_aux2.get(emp_aux2.size() - 1));
            //copy_emp(emp_aux2, empregados);
          }
          //lançamento de cartao de ponto
          else if (ult_op == 3) {
            for (Empregado empregado : empregados) {
              if (empregado.getId().equals(id_aux)) {
                empregado.getCartoes().remove(empregado.getCartoes().size() - 1);
                System.out.println("Undo realizado com sucesso!");
                System.out.println("O cartao de ponto do empregado " + empregado.getName() + " que foi adicionado foi removido!");
                System.out.println(empregado);
                break;
              }
            }
          }
          //cadastrar venda
          else if (ult_op == 5) {
            for (Empregado empregado : empregados) {
              if (empregado.getId().equals(id_aux)) {
                empregado.getVendas().remove(empregado.getVendas().size() - 1);
                System.out.println("Undo realizado com sucesso!");
                System.out.println("A venda do empregado " + empregado.getName() + " que foi adicionada foi removida!");
                System.out.println(empregado);
                break;
              }
            }
          }
          //lançar taxa de serviço
          else if (ult_op == 6) {
            for (Empregado empregado : empregados) {
              if (empregado.getId().equals(id_aux)) {
                //empregado.getTaxas().remove(empregado.getVendas().size() - 1);
                System.out.println("Undo realizado com sucesso!");
                System.out.println("A taxa referente a " + empregado.getSindicato().getTaxa().get(empregado.getSindicato().getTaxa().size() - 1).getServico() + " do empregado " + empregado.getName() + " foi removida!");
                empregado.getSindicato().getTaxa().remove(empregado.getSindicato().getTaxa().size() - 1);
                //System.out.println(empregado);
                break;
              }
            }
          }
          else if (ult_op == 7) {

          }
          redo = 1;
          //System.out.println("");
        }
        //for (Empregado e : emp_aux2) {
          //System.out.println(e + "\n");
        //}
      }
    }
  }
  public static void copy_emp(List<Empregado> emp, List<Empregado> emp_aux) {
    for (Empregado e : emp) {
      String name = e.getName();
      String id = e.getId();
      String address = e.getAddress();
      Sindicato sindicato = e.getSindicato();
      int type = e.getType();
      List<CartaoDePonto> cartoes = e.getCartoes();
      List<Vendas> vendas = e.getVendas();
      String metodo = e.getMetodo();

      if (type == 1) {
        Horista h = (Horista) e;
        emp_aux.add(new Horista(name, id, address, h.getSalario_hora(), h.getSalario(), sindicato, type, cartoes, vendas, metodo));
      }
      else if (type == 2) {
        Assalariado a = (Assalariado) e;
        emp_aux.add(new Assalariado(name, id, address, a.getSalario(), a.getSalarioInicial(), sindicato, type, cartoes, vendas, metodo));
      }
      else if (type == 3) {
        Comissionado c = (Comissionado) e;
        emp_aux.add(new Comissionado(name, id, address, c.getSalario(), c.getSalarioInicial(), c.getComissao(), sindicato, type, cartoes, vendas, metodo));
      }
    }
  }
}