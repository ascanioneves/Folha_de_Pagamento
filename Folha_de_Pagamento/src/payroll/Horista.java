package payroll;

import java.util.List;
import java.util.UUID;

public class Horista extends Empregado
{
  private double salario_hora;
  private double salario;
  public Horista(String name, String id, String address, double salario_hora, double salario, Sindicato sindicato, int type, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    super(name, id, address, sindicato, type, cartoes, vendas, metodoPagamento);
    this.salario_hora = salario_hora;
    this.salario = salario;
  }
  public double getSalario_hora() {
    return salario_hora;
  }
  public void setSalario_hora(double salario_hora) {
    this.salario_hora = salario_hora;
  }
  public void setSalario(double salario) {
    this.salario = salario;
  }
  public double getSalario() {
    int total = 0;
    double retirar = 0;
    if (this.getSindicato() != null) {
      retirar = this.getSindicato().getTaxaSindicato();
      for (Taxa s : this.getSindicato().getTaxa()) {
        retirar += s.getValor();
      }
    }
    for (CartaoDePonto c : this.getCartoes()) {
      String ent;
      String sai;
      ent = c.getEntrada();
      sai = c.getSaida();

      int horas = Integer.parseInt(sai) - Integer.parseInt(ent);
      total += horas;
    }
    double parcela = 0;
    if (total > 8) {
      parcela = total - 8;
      return (8 * this.getSalario_hora() + parcela * this.getSalario_hora() * 1.5) - retirar;
    }
    else {
      return (total * this.getSalario_hora()) - retirar;
    }
  }
  @Override
    public String toString() 
    {
      return "Horista: \n" + super.toString() + "\nSalario: " + getSalario();
    }
}