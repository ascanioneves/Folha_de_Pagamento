package payroll;

import java.util.List;
import java.util.UUID;

public class Horista extends Empregado
{
  private double salario_hora;
  public Horista(String name, String id, String address, double salario_hora, Sindicato sindicato, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, cartoes, vendas, metodoPagamento);
    this.salario_hora = salario_hora;
  }
  public double getSalario_hora() {
    return salario_hora;
  }
  public void setSalario_hora(double salario_hora) {
    this.salario_hora = salario_hora;
  }
  @Override
    public String toString() 
    {
      return "Horista: \n" + super.toString() + "\nSalario: " + getSalario_hora();
    }
}