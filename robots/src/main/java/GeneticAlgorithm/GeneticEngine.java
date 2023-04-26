package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class GeneticEngine {
    List<Parasite> generation = new ArrayList<>();
    int n = 10;
    private void generateFirstGeneration(){
        for (int i = 0; i < n; i++){
            generation.add(new Parasite());
        }
    }
}
