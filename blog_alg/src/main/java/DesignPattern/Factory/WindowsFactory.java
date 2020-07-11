package DesignPattern.Factory;

public class WindowsFactory extends AbstractFactory {
    @Override
    Button createButton() {
        return new WindowsButton();
    }

    @Override
    TextBox createTextBox() {
        return new WindowsTextBox();
    }
}
