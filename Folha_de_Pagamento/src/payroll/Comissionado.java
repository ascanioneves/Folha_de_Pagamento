package payroll;

import java.util.UUID;

public class Comissionado extends Empregado
{
  private double salario;
  private double comissao;
  public Comissionado(double salario, double comissao)
  {
    this.salario = salario;
    this.comissao = comissao;
  } 
  public Comissionado(String name, String id, String address, double salario, double comissao)
  {
    super(name, id, address);
    this.salario = salario;
    this.comissao = comissao;
  }
  public double getSalario() {
    return salario + comissao * 1.5;
  }
  public void setSalario(double salario) {
    this.salario = salario;
  }
  public void setComissao(double comissao) {
    this.comissao = comissao;
  }
  @Override
    public String toString() 
    {
      return super.toString() + "\nSalario: " + getSalario();
    }
}