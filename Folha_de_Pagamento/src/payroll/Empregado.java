package payroll;

import java.util.ArrayList;
import java.util.List;

public abstract class Empregado
{
  private String name;
  private String id;
  private String address;
  List<CartaoDePonto> cartoes = new ArrayList<CartaoDePonto>();
  List<Vendas> vendas = new ArrayList<Vendas>();

  public Empregado(String name, String id, String address)
  {
    this.name = name;
    this.id = id;
    this.address = address;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  @Override
    public String toString() 
    {
      String str = "Id: " + getId();
      str += "\nNome: " + getName();
      str += "\nEndereço: " + getAddress() + "\n-------- Cartao de Ponto --------\n";
      for (CartaoDePonto cartaoDePonto : cartoes) {
        str += cartaoDePonto.toString();
      }
      str += "\n-------- Vendas --------\n";
      for (Vendas venda : vendas) {
        str += venda.toString();
      }
      return str;
    }
}