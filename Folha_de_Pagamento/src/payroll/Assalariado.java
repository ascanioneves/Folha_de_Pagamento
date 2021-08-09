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
  public Assalariado(String name, String id, String address, double salario, double salario_inicial, Sindicato sindicato, int type, int tipo_pagamento, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, type, tipo_pagamento, cartoes, vendas, metodoPagamento);
    this.salario = salario;
    this.salario_inicial = salario_inicial;
  }
  public double getSalario() {
    double retirar = 0;
    if (this.getSindicato() != null) {
      retirar = this.getSindicato().getTaxaSindicato();
      for (Taxa s : this.getSindicato().getTaxa()) {
        retirar += s.getValor();
      }
    }
    double sal = 0;
    double alt = 1;
    sal = salario_inicial - retirar;
    //sou mensal mas quero pagar semanal: /4
    if (this.getTipoPagamento() == 1) { 
      alt = 4;
    }
    //sou mensal mas quero pagar bi-semanal
    else if (this.getTipoPagamento() == 3) {
      alt = 2;
    }
    return sal / alt;
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