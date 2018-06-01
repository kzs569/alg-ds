package DesignPattern.Factory;

public class LinuxButton implements Button {
    @Override
    public void displayButton() {
        System.out.println("Linux Button displays!");
    }
}
