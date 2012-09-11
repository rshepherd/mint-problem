package hps.mint;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ExchangeTest
{

    @Test
    public void test()
    {
        Exchange exchange = new Exchange(1.0F);

        Integer[] d = { 1, 5, 10, 25, 50, 100 };
        List<Integer> denoms = Arrays.asList(d);

        
        assertTrue(exchange.compute(denoms) == 275.0F);
    }

}
