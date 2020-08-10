package integration;

public class HelloService {
    public String sayHello(String name) throws Exception {
        System.out.println("Hello " + name);
        return "Hello " + name;
    }

}
