package DesignPattern.Observer;

public class Subject extends java.util.Observable{
//    private List<Observer> observers;
    private int state;

    public Subject() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }
    //继承的Observable自动实现观察者的添加和删除
//    public void registerObserver(Observer observer){
//        observers.add(observer);
//    }
//
//    public void removeObserver(Observer o){
//        int i = observers.indexOf(o);
//        if(i > 0) {
//            observers.remove(o);
//        }
//    }
//
//    public void notifyAllObservers(){
//        for (Observer observer : observers) {
//            observer.update();
//        }
//    }

    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
