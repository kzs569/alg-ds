package DesignPattern.Factory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("Windows")){
            return new WindowsFactory();
        } else if(choice.equalsIgnoreCase("Linux")){
            return new LinuxFactory();
        }
        return null;
    }

    public static void main(String[] args) {

        //获取工厂
        AbstractFactory factory_w = FactoryProducer.getFactory("Windows");

        factory_w.createButton().displayButton();

        factory_w.createTextBox().displayTextBox();

        AbstractFactory factory_l = FactoryProducer.getFactory("Linux");

        factory_l.createButton().displayButton();

        factory_l.createTextBox().displayTextBox();
    }
}
