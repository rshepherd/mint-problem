package hps.mint;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MainTest 
{
    @Test
    public void testApp()
    {
        String[] args = new String[1];
        args[0] = "1";
        new Main().run(args);
        assertTrue(true);
    }
}
