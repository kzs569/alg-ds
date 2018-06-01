package DesignPattern.Observer;

import java.util.Observable;
import java.util.Observer;

public class OctalObserver implements Observer {

    private Observable observable;
    private int state;

    public OctalObserver(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Subject oct =(Subject)o;
        this.state = oct.getState();
        System.out.println( "Octal String: " + Integer.toOctalString( state ) );
    }

}
