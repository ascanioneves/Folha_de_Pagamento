package payroll;

import java.util.List;
import java.util.UUID;

public class Assalariado extends Empregado
{
  private double salario;
  //public Assalariado(double salario)
  //{
    //this.salario = salario;
  //}
  public Assalariado(String name, String id, String address, double salario, Sindicato sindicato, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, cartoes, vendas, metodoPagamento);
    this.salario = salario;
  }
  public double getSalario() {
    return salario;
  }
  public void setSalario(double salario) {
    this.salario = salario;
  }
  @Override
    public String toString() 
    {
      return "Assalariado: \n" + super.toString() + "\nSalario: " + getSalario();
    }
}