package hps.mint;

import static org.junit.Assert.assertTrue;
import hps.mint.Solution.Problem;

import org.junit.Test;

public class MainTest 
{
    @Test
    public void testApp()
    {
        new Main().run(1.0F, Problem.EXACT);
        assertTrue(true);
    }
}
