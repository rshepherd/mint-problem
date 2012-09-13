package hps.mint;


public class Main
{
    public static void main(String[] args)
    {
        float weight = 4.0F;
        
        System.out.println("Executing " + ". N = " + weight);

        Mint mint;
        System.out.println("creating problem: exact");
        mint = new Mint(weight);
        Solution s = mint.solve();
        
        s.print();
    }
}
