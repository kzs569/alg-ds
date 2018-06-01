package DesignPattern.Observer;

import java.util.Observable;
import java.util.Observer;

public class BinaryObserver implements Observer{

    private Observable observable;
    private int state;

    public BinaryObserver(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Subject sub = (Subject)o;
        this.state = sub.getState();
        System.out.println( "Binary String: " + Integer.toBinaryString( state) );
    }
}
