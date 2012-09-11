package hps.mint;

import java.util.List;
import scala.Int;

public class Solution
{
    public enum Problem {
        EXACT, EXCHANGE
    }

    public Problem       problem;
    public List<Int> denoms;
    public Float         number;
    public Long          elapsed;
    public Float         weight;

    public void print()
    {
        StringBuilder denomStr = new StringBuilder();
        denomStr.append("1");
        for (Int d : denoms)
        {
            denomStr.append(d);
            denomStr.append(", ");
        }
        denomStr.append("100");

        System.out.println("Denom\t\t" + denomStr + "\nNumber\t\t" + number
                + "\nRuntime\t\t" + elapsed);
    }
}
