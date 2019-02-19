package AdapterDesignPattern;

public class FlyingModeAdapter implements RuningMode {

    private FlyingMode mode;

    public FlyingModeAdapter(){ mode = new FlyingMode(); }

    @Override
    public void run() {
     mode.fly();
    }

}
