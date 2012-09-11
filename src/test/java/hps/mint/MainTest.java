package hps.mint;

import static org.junit.Assert.assertTrue;
import hps.mint.Solution.Problem;

import org.junit.Test;

public class MainTest 
{
    @Test
    public void testExactChange()
    {
        Solution s = new Main().run(1.0F, Problem.EXACT);
        assertTrue(s.problem == Problem.EXACT);
    }
    
    @Test
    public void testExchange()
    {
        Solution s = new Main().run(1.0F, Problem.EXCHANGE);
        assertTrue(s.problem == Problem.EXCHANGE);
    }

    @Test
    public void testBadArgs()
    {
        try
        {
            String[] args = new String[2];
            args[0] = "1.0";
            args[1] = "Invalid";
            Main.main(args);
        } 
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
}
