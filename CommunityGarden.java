import java.util.*;

public class CommunityGarden {
    private static List<GardenPlot> gardenPlots = new ArrayList<>();
    private static Stack<Plant> harvestedPlants = new Stack<>();
    private static Deque<String> actionHistory = new LinkedList<>();
    private static CNode wateringSchedule = null;
    private static AVLTree userTree = new AVLTree();
    private static BSTNode plantTree = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Community Garden Management System ---");
            System.out.println("1. Register User");
            System.out.println("2. Add Garden Plot");
            System.out.println("3. Add Plant to Plot");
            System.out.println("4. Water Plants");
            System.out.println("5. Harvest Plant");
            System.out.println("6. Display Garden");
            System.out.println("7. Search Plant");
            System.out.println("8. Sort Plants");
            System.out.println("9. Display Action History");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    addGardenPlot();
                    break;
                case 3:
                    addPlantToPlot();
                    break;
                case 4:
                    waterPlants();
                    break;
                case 5:
                    harvestPlant();
                    break;
                case 6:
                    displayGarden();
                    break;
                case 7:
                    searchPlant();
                    break;
                case 8:
                    sortPlants();
                    break;
                case 9:
                    displayActionHistory();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }

    private static void registerUser() {
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        User user = new User(name, id);
        userTree.root = userTree.insert(userTree.root, user);
        actionHistory.add("User registered: " + user);
        System.out.println("User registered successfully.");
    }

    private static void addGardenPlot() {
        GardenPlot plot = new GardenPlot();
        gardenPlots.add(plot);
        actionHistory.add("Garden plot added");
        System.out.println("Garden plot added successfully.");
    }

    private static void addPlantToPlot() {
        System.out.print("Enter Plot Number (0 to " + (gardenPlots.size() - 1) + "): ");
        int plotNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (plotNumber >= 0 && plotNumber < gardenPlots.size()) {
            System.out.print("Enter Plant Name: ");
            String plantName = scanner.nextLine();
            Plant plant = new Plant(plantName);
            gardenPlots.get(plotNumber).addPlant(plant);
            plantTree = insertPlant(plantTree, plant);
            actionHistory.add("Plant added: " + plant);
            System.out.println("Plant added successfully.");
        } else {
            System.out.println("Invalid plot number!");
        }
    }

    private static BSTNode insertPlant(BSTNode node, Plant plant) {
        if (node == null) return new BSTNode(plant);

        if (plant.getName().compareTo(node.data.getName()) < 0) {
            node.left = insertPlant(node.left, plant);
        } else {
            node.right = insertPlant(node.right, plant);
        }

        return node;
    }

    private static void waterPlants() {
        for (GardenPlot plot : gardenPlots) {
            plot.waterPlants();
        }
        actionHistory.add("All plants watered");
        System.out.println("All plants have been watered.");
    }

    private static void harvestPlant() {
        System.out.print("Enter Plot Number (0 to " + (gardenPlots.size() - 1) + "): ");
        int plotNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (plotNumber >= 0 && plotNumber < gardenPlots.size()) {
            GardenPlot plot = gardenPlots.get(plotNumber);
            LinkedList<Plant> plants = plot.getPlants();
            if (!plants.isEmpty()) {
                Plant plant = plants.getLast();
                if (plant.getGrowthStage() == 3) {
                    harvestedPlants.push(plant);
                    plants.removeLast();
                    actionHistory.add("Plant harvested: " + plant);
                    System.out.println("Plant harvested successfully.");
                } else {
                    System.out.println("Plant is not ready to be harvested.");
                }
            } else {
                System.out.println("No plants to harvest in this plot.");
            }
        } else {
            System.out.println("Invalid plot number!");
        }
    }

    private static void displayGarden() {
        System.out.println("\n--- Garden Status ---");
        for (int i = 0; i < gardenPlots.size(); i++) {
            System.out.println("Plot " + i + ": " + gardenPlots.get(i));
        }
    }

    private static void searchPlant() {
        System.out.print("Enter Plant Name to Search: ");
        String name = scanner.nextLine();
        Plant[] plants = getAllPlants();
        Arrays.sort(plants, Comparator.comparing(Plant::getName));
        int index = SearchingAlgorithms.binarySearch(plants, name);
        if (index != -1) {
            System.out.println("Plant found: " + plants[index]);
        } else {
            System.out.println("Plant not found.");
        }
    }

    private static Plant[] getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (GardenPlot plot : gardenPlots) {
            allPlants.addAll(plot.getPlants());
        }
        return allPlants.toArray(new Plant[0]);
    }

    private static void sortPlants() {
        System.out.println("\n--- Sorting Plants ---");
        Plant[] plants = getAllPlants();
        System.out.println("1. Bubble Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Selection Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort");
        System.out.print("Choose sorting algorithm: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                SortingAlgorithms.bubbleSort(plants);
                break;
            case 2:
                SortingAlgorithms.insertionSort(plants);
                break;
            case 3:
                SortingAlgorithms.selectionSort(plants);
                break;
            case 4:
                SortingAlgorithms.mergeSort(plants);
                break;
            case 5:
                SortingAlgorithms.quickSort(plants, 0, plants.length - 1);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.println("Plants sorted:");
        for (Plant plant : plants) {
            System.out.println(plant);
        }
    }

    private static void displayActionHistory() {
        System.out.println("\n--- Action History ---");
        for (String action : actionHistory) {
            System.out.println(action);
        }
    }
}