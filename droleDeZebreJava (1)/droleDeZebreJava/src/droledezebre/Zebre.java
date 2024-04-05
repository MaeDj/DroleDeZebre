/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package droledezebre;

/**
 *
 * @author mae
 */
public final class Zebre extends Animal {

    private boolean cache;

    public boolean getCache() {
        return (this.cache);
    }

    public void setCache(boolean a) {
        this.cache = a;
    }

    public Zebre() {
        super();
        cache = false;
        indicateur = "Z";
        this.pts = 6;
    }
}
