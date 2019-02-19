package AdapterDesignPattern;

public class People {

    private RuningMode runingMode;

    public People(){}

    public People(RuningMode runingMode){this.runingMode = runingMode;}

    public  void setRuningMode(RuningMode runingMode){this.runingMode = runingMode;}

    public void run() {runingMode.run();}


}
