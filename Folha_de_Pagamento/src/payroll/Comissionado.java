package payroll;

import java.util.List;
import java.util.UUID;

public class Comissionado extends Empregado
{
  private double salario;
  private double comissao;
  //public Comissionado(double salario, double comissao)
  //{
    //this.salario = salario;
    //this.comissao = comissao;
  //} 
  public Comissionado(String name, String id, String address, double salario, double comissao, Sindicato sindicato,List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, cartoes, vendas, metodoPagamento);
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
      return "Comissionado: \n" + super.toString() + "\nSalario: " + getSalario();
    }
}