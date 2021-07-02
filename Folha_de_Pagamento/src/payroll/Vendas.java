package payroll;

public class Vendas {
  private double valor;
  private String data;

  public Vendas(double valor, String data) {
    this.valor = valor;
    this.data = data;
  }
  public double getValor() {
    return valor;
  }
  public String getData() {
    return data;
  }
  @Override
    public String toString() 
    {
      String str = "Valor: R$ " + getValor() + "\n";
      str += "Data: " + getData() + "\n";
      return str;
    }
}
