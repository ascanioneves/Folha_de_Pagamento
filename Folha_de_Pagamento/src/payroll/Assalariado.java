package payroll;

import java.util.List;
import java.util.UUID;

public class Assalariado extends Empregado
{
  private double salario;
  private double salario_inicial;
  //public Assalariado(double salario)
  //{
    //this.salario = salario;
  //}
  public Assalariado(String name, String id, String address, double salario, double salario_inicial, Sindicato sindicato, int type, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, type, cartoes, vendas, metodoPagamento);
    this.salario = salario;
    this.salario_inicial = salario_inicial;
  }
  public double getSalario() {
    return salario;
  }
  public void setSalario(double salario) {
    this.salario = salario;
  }
  public double getSalarioInicial() {
    return salario_inicial;
  }
  public void setSalarioInicial(double salario_inicial) {
    this.salario_inicial = salario_inicial;
  }
  @Override
    public String toString() 
    {
      return "Assalariado: \n" + super.toString() + "\nSalario: " + getSalario();
    }
}