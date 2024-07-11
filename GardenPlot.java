import java.util.LinkedList;

public class GardenPlot {
    private LinkedList<Plant> plants;

    public GardenPlot() {
        this.plants = new LinkedList<>();
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void waterPlants() {
        for (Plant plant : plants) {
            plant.grow();
        }
    }

    public LinkedList<Plant> getPlants() {
        return plants;
    }

    @Override
    public String toString() {
        return "GardenPlot [Plants=]" + plants + "]";
    }
}
