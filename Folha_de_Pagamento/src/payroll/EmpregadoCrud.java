package payroll;
import java.util.List;

public class EmpregadoCrud {

  public EmpregadoCrud() {

  }
  public static Empregado create(String name, String id, String address, double salario_hora, Sindicato sindicato, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento) {
    return new Horista(name, id, address, salario_hora, sindicato, cartoes, vendas, metodoPagamento);
  }
  public static Empregado createAssalariado(String name, String id, String address, double salario, Sindicato sindicato, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento) {
    return new Assalariado(name, id, address, salario, sindicato, cartoes, vendas, metodoPagamento);
  }
  public static Empregado createComissionado(String name, String id, String address, double salario, double comissao, Sindicato sindicato, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento) {
    return new Comissionado(name, id, address, salario, comissao, sindicato, cartoes, vendas, metodoPagamento);
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
