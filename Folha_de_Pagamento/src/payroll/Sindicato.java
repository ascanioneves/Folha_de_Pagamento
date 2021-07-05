package payroll;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sindicato {
  UUID id = UUID.randomUUID();
  String idEmp;
  private double taxaSindicato;
  List<Taxa> taxas = new ArrayList<Taxa>();

  public  Sindicato(UUID id, String idEmp, double taxaSindicato) {
    this.id = id;
    this.idEmp = idEmp;
    this.taxaSindicato = taxaSindicato;
    this.taxas = new ArrayList<Taxa>();
  }

  public UUID getId() {
    return id;
  }
  public String getIdEmp() {
    return idEmp;
  }
  public double getTaxaSindicato() {
    return taxaSindicato;
  }
  public List<Taxa> getTaxa() {
    return taxas;
  }
  public void setID(UUID id) {
    this.id = id;
  }
  @Override
    public String toString() 
    {
      String str = "-------- Taxas -------- \n" + getTaxa() + "\n";
      str += "Taxa do sindicato: " + getTaxaSindicato();
      return str;
    }
}
