package payroll;

import java.util.List;
import java.util.UUID;

public class Comissionado extends Empregado
{
  private double salario;
  private double salario_inicial;
  private double comissao;
  //public Comissionado(double salario, double comissao)
  //{
    //this.salario = salario;
    //this.comissao = comissao;
  //} 
  public Comissionado(String name, String id, String address, double salario, double salario_inicial, double comissao, Sindicato sindicato, int type, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, type, cartoes, vendas, metodoPagamento);
    this.salario = salario;
    this.comissao = comissao;
    this.salario_inicial = salario_inicial;
  }
  public double getSalario() {
    return salario;
  }
  public void setSalario(double salario) {
    this.salario = salario;
  }
  public double getComissao() {
    return comissao;
  }
  public double getSalarioInicial() {
    return salario_inicial;
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