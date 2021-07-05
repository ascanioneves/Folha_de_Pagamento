package payroll;

public class Taxa {
  private double valor;
  private String servico;
  public Taxa(double valor, String servico) {
    this.valor = valor;
    this.servico = servico;
  }
  public double getValor() {
    return valor;
  }
  public String getServico() {
    return servico;
  }
  @Override
    public String toString() 
    {
      String str = "Valor: " + getValor() + "\n";
      str += "Servico: " + getServico() + "\n\n";
      return str;
    }
}
