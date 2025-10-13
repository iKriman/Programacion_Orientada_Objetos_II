package service;

import service.DescuentoDecorator;
import service.Descuento;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DescuentoPorcentaje extends DescuentoDecorator {
    private final BigDecimal porcentaje; 

    public DescuentoPorcentaje(Descuento base, BigDecimal porcentaje) {
        super(base); 
        this.porcentaje = porcentaje;
    }
    
    @Override 
    public BigDecimal aplicar(BigDecimal precioBase) {
        BigDecimal base = super.aplicar(precioBase);
        return base.subtract(base.multiply(porcentaje));
    }
    
    @Override 
    public String nombre() { 
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);


        String porcentajeStr = df.format(porcentaje.multiply(new BigDecimal("100")));
        
        return "Descuento " + porcentajeStr + "%"; 
    }
}