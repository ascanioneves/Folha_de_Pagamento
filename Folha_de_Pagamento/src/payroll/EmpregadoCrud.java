package payroll;
import java.util.List;

public class EmpregadoCrud {

  public EmpregadoCrud() {

  }
  public static Empregado create(String name, String id, String address, double salario_hora, double salario, Sindicato sindicato, int type, int tipo_pagamento, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento) {
    return new Horista(name, id, address, salario_hora, salario, sindicato, type, tipo_pagamento, cartoes, vendas, metodoPagamento);
  }
  public static Empregado createAssalariado(String name, String id, String address, double salario, double salario_inicial, Sindicato sindicato, int type, int tipo_pagamento, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento) {
    return new Assalariado(name, id, address, salario, salario_inicial, sindicato, type, tipo_pagamento, cartoes, vendas, metodoPagamento);
  }
  public static Empregado createComissionado(String name, String id, String address, double salario, double salario_inicial, double comissao, Sindicato sindicato, int type, int tipo_pagamento, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento) {
    return new Comissionado(name, id, address, salario, salario_inicial, comissao, sindicato, type, tipo_pagamento, cartoes, vendas, metodoPagamento);
  }
  public static Boolean delete(List<Empregado> empregados, String idDel) {
    for (Empregado empregado : empregados) {
      if (empregado.getId().equals(idDel)) {
        empregados.remove(empregado);
        return true;
      }
    }
    return false;
  }
  public Empregado update() {
    return null;
  }
}
