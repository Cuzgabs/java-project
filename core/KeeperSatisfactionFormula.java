package hva.core;
import java.io.*;
import java.util.*;

public class KeeperSatisfactionFormula implements SatisfactionStrategy,Serializable {
    @Override
    public int calculateSatisfaction(Employee employee,Hotel hotel) {
        Tratador keeper = (Tratador) employee;
        int res = 0;
        List<Habitats> habitats = keeper.getHabitats();
        
        for (Habitats h : habitats) {
            int n_zookeepers = hotel.findAllResponsibleEmployees(h,Tratador.class);
            int work = h.getWork(); 
            res += work / n_zookeepers;
        }
        return Math.round(300 - res);
    }
}