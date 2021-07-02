package payroll;
import java.util.List;

public class EmpregadoCrud {

  public EmpregadoCrud() {

  }
  public static Empregado create(String name, String id, String address, double salario_hora) {
    return new Horista(name, id, address, salario_hora);
  }
  public static Empregado create(String name, String id, double salario, String address) {
    return new Assalariado(name, id, address, salario);
  }
  public static Empregado create(String name, String id, String address, double salario, double comissao) {
    return new Comissionado(name, id, address, salario, comissao);
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
