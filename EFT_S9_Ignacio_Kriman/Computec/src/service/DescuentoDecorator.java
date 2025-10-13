package service;

import service.Descuento;
import java.math.BigDecimal;

public abstract class DescuentoDecorator implements Descuento {

    protected final Descuento wrappee;

    protected DescuentoDecorator(Descuento wrappee) {
        this.wrappee = wrappee;
    }

    public BigDecimal aplicar(BigDecimal precioBase) {
        return wrappee.aplicar(precioBase);
    }

    public String nombre() {
        return wrappee.nombre();
    }
}
