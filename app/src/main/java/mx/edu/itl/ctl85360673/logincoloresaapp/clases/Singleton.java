package mx.edu.itl.ctl85360673.logincoloresaapp.clases;

import java.util.HashMap;

public class Singleton {
    private static Singleton instancia = null;
    private String usuario = null;
    private String contrasena = null;
    private HashMap<String,String> hmapValores = new HashMap<String, String>();

    private Singleton(){}

    public static Singleton getInstance(){
        if(instancia == null){
            instancia = new Singleton();
        }
        return instancia;
    }

    public void setValores(String clave, String valor){
        hmapValores.put(clave, valor);
    }

    public String getValores(String clave){
        return hmapValores.get(clave);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public HashMap<String, String> getHmapValores() {
        return hmapValores;
    }

    public void setHmapValores(HashMap<String, String> hmapValores) {
        this.hmapValores = hmapValores;
    }
}
