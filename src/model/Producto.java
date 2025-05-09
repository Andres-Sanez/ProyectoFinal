package model;

public class Producto {
    private String nombre;
    private double precio;
    private String rutaImagen;
    private String talla;

    public Producto(String nombre, double precio, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
        
        if (nombre.contains("1")) {
        this.talla = "40";
    } else if (nombre.contains("3")) {
        this.talla = "41";
    } else if (nombre.contains("4")) {
        this.talla = "42";
    } else if (nombre.contains("5")) {
        this.talla = "43";
    } else if (nombre.contains("6")) {
        this.talla = "44";
    } else if (nombre.contains("7")) {
        this.talla = "39";
    } else if (nombre.contains("8")) {
        this.talla = "38";
    } else if (nombre.contains("11")) {
        this.talla = "42";
    } else if (nombre.contains("12")) {
        this.talla = "40";
    } else {
        this.talla = "41";
  }
        
}

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getTalla() {
    return talla;
}

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }

    public String getImagen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
