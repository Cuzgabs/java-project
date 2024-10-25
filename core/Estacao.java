package hva.core;
import java.io.Serializable;
public enum Estacao implements Serializable {
    SPRING,  //0
    SUMMER,   //1
    FALL,     //2
    WINTER;   //3


    public Estacao avancaEstacao() {
        return values()[(this.ordinal() + 1) % values().length];
    }
    
}
