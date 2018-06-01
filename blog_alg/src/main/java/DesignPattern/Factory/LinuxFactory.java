package DesignPattern.Factory;

public class LinuxFactory extends AbstractFactory {
    @Override
    Button createButton() {
        return new LinuxButton();
    }

    @Override
    TextBox createTextBox() {
        return new LinuxTextBox();
    }
}
