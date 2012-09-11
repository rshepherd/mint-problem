package hps.mint;

import hps.mint.Solution.Problem;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            usage(args);
            System.exit(-1);
        }

        Float weight = 1.0F;
        try
        {
            weight = Float.valueOf(args[0]);
        } 
        catch (NumberFormatException e)
        {
            usage(args);
            throw new IllegalArgumentException(e);
        }
        
        Problem problem = Problem.EXACT;
        try
        {
            problem = Problem.valueOf(args[1].toUpperCase());
        } catch (Exception e)
        {
            usage(args);
            throw new IllegalArgumentException(e);
        }

        System.out.println("Executing " + problem.toString() + ". N = " + weight);
        new Main().run(weight, problem);
    }

    private static void usage(String[] args)
    {   
        String argStr = "";
        for(String a : args) argStr += ", " + a; 
        System.out.println("Invalid/missing arguments. " + argStr);
        System.out.println("\tusage: java -jar mint-1.0.0.jar 4.0 exact|exchange");
    }

    public Solution run(Float weight, Problem problem)
    {
        Solution s = null;
        if (problem == Problem.EXACT)
        {
            s = new ExactChange(weight).run();
        } else
        {
            s = new Exchange(weight).run();
        }
        s.problem = problem;
        s.print();
        
        return s;
    }

}
