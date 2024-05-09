public class Vehicle {
    private final String brand;
    private final String model;
    private final int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void start() throws EngineStartException {
        // Simulate starting the engine
        if (Math.random() < 0.5) {
            throw new EngineStartException("Engine failed to start");
        } else {
            System.out.println("Engine started successfully");
        }
    }

    public void drive(int distance) {
        // Simulate driving
        System.out.println("Driving " + distance + " miles");
    }
}

class Car extends Vehicle {
    private final int numberOfDoors;

    public Car(String brand, String model, int year, int numberOfDoors) {
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public void start() throws EngineStartException {
        // Additional car-specific starting logic
        super.start();
        System.out.println("Car specific start procedure");
    }
}

class Motorcycle extends Vehicle {
    private final boolean hasSideCar;

    public Motorcycle(String brand, String model, int year, boolean hasSideCar) {
        super(brand, model, year);
        this.hasSideCar = hasSideCar;
    }

    public boolean hasSideCar() {
        return hasSideCar;
    }
}

class EngineStartException extends Exception {
    public EngineStartException(String message) {
        super(message);
    }
}