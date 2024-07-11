public class Plant {
    private String name;
    private int growthStage; // 0: Seed, 1: Sprout, 2: Mature, 3: Ready to Harvest

    public Plant(String name){
        this.name = name;
        this.growthStage = 0;
    }

    public String getName() {
        return name;
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public void grow() {
        growthStage++;
    }

    @Override
    public String toString() {
        String[] stages = {"Seed", "Sprout", "Mature", "Ready to Harvest"};
        return "Plant [Name=" + name + ", Growth Stage=" + stages[growthStage] + "]";
    }
}