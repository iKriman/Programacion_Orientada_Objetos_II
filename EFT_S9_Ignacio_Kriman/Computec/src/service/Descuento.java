package service;

import java.math.BigDecimal;

public interface Descuento {

    BigDecimal aplicar(BigDecimal precioBase);

    String nombre();
    
    
}
