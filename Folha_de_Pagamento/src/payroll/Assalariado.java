package payroll;

import java.util.UUID;

public class Assalariado extends Empregado
{
  private double salario;
  public Assalariado(double salario)
  {
    this.salario = salario;
  }
  public Assalariado(String name, String id, String address, double salario)
  {
    super(name, id, address);
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
      return super.toString() + "\nSalario: " + getSalario();
    }
}