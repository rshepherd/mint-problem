package hps.mint;

public class Main
{
    public static void main(String[] args)
    {
        new Main().run(args);
    }

    public void run(String[] args)
    {
        System.out.println("Running Exact Change..");
        ExactChange.main(args);
        
        System.out.println("\nRunning Exchange..");
        Exchange.main(args);
    }
}
