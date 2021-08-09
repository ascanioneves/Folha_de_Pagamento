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
  public Comissionado(String name, String id, String address, double salario, double salario_inicial, double comissao, Sindicato sindicato, int type, int tipo_pagamento, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, type, tipo_pagamento, cartoes, vendas, metodoPagamento);
    this.salario = salario;
    this.comissao = comissao;
    this.salario_inicial = salario_inicial;
  }
  public double getSalario() {
    double total = 0;
    if (this.getVendas() != null) {
      for (Vendas v : this.getVendas()) {
        total += v.getValor() * this.getComissao();
      }
    }
    double sal = 0;
    sal = salario + total;
    //sou bi-semanal mas quero receber semanal
    if (this.getTipoPagamento() == 1) {
      return sal / 2;
    }
    //sou bi-semanal mas quero receber mensal
    else if (this.getTipoPagamento() == 2) {
      return sal * 2;
    }
    return sal;
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