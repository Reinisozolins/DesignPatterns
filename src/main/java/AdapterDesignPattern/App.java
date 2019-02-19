package AdapterDesignPattern;

/**
 * In this app I test adapter  design pattern.
 * People only know how to walking. But with adapter I will give flying possibility too.
 */

public class App {
    public static void main(String[] args) {
        //People know how to walk but with adapter they can fly too.
        People people = new People(new FlyingModeAdapter());
        people.run();
    }
}
