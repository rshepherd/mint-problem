package hps.mint;

import java.util.List;

public class Solution
{
    public enum Problem {
        EXACT, EXCHANGE
    }

    public Problem       problem;
    public List<Integer> denoms;
    public Float         number;
    public Long          elapsed;
    public Float         weight;

    public void print()
    {
        StringBuilder denomStr = new StringBuilder();
        denomStr.append("1, ");
        for (Integer d : denoms)
        {
            denomStr.append(d);
            denomStr.append(", ");
        }
        denomStr.append("100");

        System.out.println("Denom\t\t" + denomStr + "\nNumber\t\t" + number
                + "\nRuntime\t\t" + elapsed);
    }
}
