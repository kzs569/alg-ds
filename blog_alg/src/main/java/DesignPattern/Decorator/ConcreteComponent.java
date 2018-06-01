package DesignPattern.Decorator;

public class ConcreteComponent extends Component{

    public ConcreteComponent() {
        description = "Shopping list:";
    }

    @Override
    public double cost() {
        return 0;
    }
}
