package payroll;

import java.util.ArrayList;
import java.util.List;

public abstract class Empregado
{
  private String name;
  private String id;
  private String address;
  private String metodoPagamento;
  private List<CartaoDePonto> cartoes = new ArrayList<CartaoDePonto>();
  private List<Vendas> vendas = new ArrayList<Vendas>();
  private Sindicato sindicato;


  public Empregado(String name, String id, String address, Sindicato sindicato, List<CartaoDePonto> cartoes, List<Vendas> vendas, String metodoPagamento)
  {
    this.name = name;
    this.id = id;
    this.address = address;
    this.metodoPagamento = metodoPagamento;
    this.sindicato = sindicato;
    this.vendas = new ArrayList<Vendas>();
    this.cartoes = new ArrayList<CartaoDePonto>();
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
  public Sindicato getSindicato() {
    return sindicato;
  }
  public void setSindicato(Sindicato sindicato) {
    this.sindicato = sindicato;
  }
  public List<Vendas> getVendas() {
    return vendas;
  }
  public List<CartaoDePonto> getCartoes() {
    return cartoes;
  }
  public String getMetodo() {
    return metodoPagamento;
  }
  public void setMetodo(String metodoPagamento) {
    this.metodoPagamento = metodoPagamento;
  }
  @Override
    public String toString() 
    {
      String str = "Id: " + getId();
      str += "\nNome: " + getName();
      str += "\nEndereço: " + getAddress() + "\nMetodo de Pagamento: " + getMetodo() + "\n-------- Cartao de Ponto --------\n";
      for (CartaoDePonto cartaoDePonto : cartoes) {
        str += cartaoDePonto.toString();
      }
      str += "\n-------- Vendas --------\n";
      for (Vendas venda : vendas) {
        str += venda.toString();
      }
      if (getSindicato() == null) {
        str += "Nao pertence a sindicato";
      }
      else {
        str += getSindicato().toString();
      }
      return str;
    }
}