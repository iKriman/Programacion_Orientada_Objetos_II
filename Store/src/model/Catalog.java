package model;

import java.util.ArrayList;
import java.util.List;

// creamos un catalogo de productos para ser vendidos en la tienda
// se podria aplicar lectura de archivos y cargar un CSV con el inventario
// de esta forma se podria actualizar de manera mas facil el catalogo
public class Catalog {

    List<Product> products = new ArrayList<>();

    public Catalog() {
        products.add(new Product(20000, "Camiseta Negra", "Tops"));
        products.add(new Product(28000, "Poleron", "Tops"));
        products.add(new Product(40000, "Jeans", "Bottoms"));
        products.add(new Product(30000, "Buzo", "Bottoms"));
        products.add(new Product(60000, "Zapatillas Running", "Footwear"));
        products.add(new Product(15000, "Chalas", "Footwear"));

    }
    
    // METODOS
    public List<Product> getProducts() {
        return products;
    }
}
