package DesignPattern.Decorator;

public abstract class Component {
    String description = "Unknown";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
