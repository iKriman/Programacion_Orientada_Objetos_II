package service;
import java.math.BigDecimal;

public class DescuentoBase implements Descuento {
  public BigDecimal aplicar(BigDecimal precioBase) { 
      return precioBase; 
  }
  public String nombre() { 
      return "Sin Descuento"; 
  }
}
