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
        hps.mint.ExactChange.main(args);
        
        System.out.println("\nRunning Exchange..");
        hps.mint.Exchange.main(args);
    }
}
