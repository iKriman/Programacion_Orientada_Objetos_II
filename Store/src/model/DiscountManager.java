package model;

public class DiscountManager {

    // creamos la variable privada que contendra la instancia 
    /** usamos la palabra reservada volatile para garantizar que la instancia 
    siempre sea llamada desde la RAM mejorando la visibilidad de esta**/
    private static volatile DiscountManager instance; 


    // constructor vacio y privado para que solo pueda ser instanciado desde la clase
    private DiscountManager() {}

    
    /** metodo de diseno double-checked locking para asegurar una doble verificacion,
    de esta forma el singleton se vuelve mas eficiente al momento de llamarlo **/
    public static DiscountManager getInstance() {
        if (instance == null) {
            synchronized (DiscountManager.class) {
                if (instance == null) {
                    instance = new DiscountManager();
                }
            }
        }
        return instance;
    }


}
