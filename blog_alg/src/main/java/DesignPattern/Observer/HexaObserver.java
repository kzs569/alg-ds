package DesignPattern.Observer;

import java.util.Observable;
import java.util.Observer;

public class HexaObserver implements Observer {

    private Observable observable;
    private int state;

    public HexaObserver(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o,Object arg) {
        Subject oct =(Subject)o;
        this.state = oct.getState();
        System.out.println( "Hex String: "
                + Integer.toHexString( state ).toUpperCase() );
    }
}
