package hps.mint;


public class Main
{
    public static void main(String[] args)
    {
        float weight = 4.0F;
        
        
        System.out.println("Executing " + ". N = " + weight);

        Solution s = null;
        Mint mint1, mint2;
        System.out.println("creating problem: exact");
        mint1 = new ExactChange(weight);
        mint1.solve();
        mint1.print();

        System.out.println("creating problem: exchange");
        mint2 = new Exchange(weight);
        mint2.solve();
        mint2.print();
    }
}
