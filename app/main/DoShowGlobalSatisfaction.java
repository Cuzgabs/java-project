package hva.app.main;

import hva.core.Animal;
import hva.core.Employee;
import hva.core.HotelManager;
import java.util.List; 
import pt.tecnico.uilib.menus.Command;

/**
 * Command for show the global satisfaction of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
    DoShowGlobalSatisfaction(HotelManager receiver) {
        super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
    }

    @Override
    protected final void execute() {
        double totalSatisfaction = 0.0;
        int count = 0;
        
        // Supondo que HotelManager tenha um método para obter todos os funcionários
        List<Employee> employees = _receiver.getEmployees();
        for (Employee employee : employees) {
            totalSatisfaction += employee.getSatisfaction();
            count++;
        }

        // Supondo que HotelManager tenha um método para obter todos os animais
        List<Animal> animals = _receiver.getAnimals(); // Altere se a classe Animal for diferente
        for (Animal animal : animals) {
            totalSatisfaction += animal.getSatisfactionOfAnimal(); // Assumindo que há um método getSatisfaction
            count++;
        }

        // Calcula a satisfação global
        double globalSatisfaction = count > 0 ? totalSatisfaction / count : 0.0;

        // Arredonda ao inteiro mais próximo
        int roundedSatisfaction = (int) Math.round(globalSatisfaction);

        // Exibe o resultado
        // Se estiver utilizando uma biblioteca para exibição de resultados
        _display.addLine(String.format("%d", roundedSatisfaction));
        _display.display();
    }

    
}
