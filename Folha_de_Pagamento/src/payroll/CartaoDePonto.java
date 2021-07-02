package payroll;

public class CartaoDePonto {
  private String entrada;
  private String saida;
  public CartaoDePonto(String entrada, String saida) {
    this.entrada = entrada;
    this.saida = saida;
  }
  public String getEntrada() {
    return entrada;
  }
  public String getSaida() {
    return saida;
  }
  @Override
    public String toString() 
    {
      String str = "Entrada: " + getEntrada() + "\n";
      str += "Saida: " + getSaida() + "\n";
      return str;
    }
}
