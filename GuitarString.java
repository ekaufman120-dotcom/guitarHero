/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    int time;
    RingBuffer string;

    public GuitarString(double frequency) 
    {
        time = 0;
        int length;
        try{
            length = Integer.parseInt((44100/frequency)+"");
        }
        catch(Exception e)
        {
            length = ((int)(44100/frequency)) + 1;
        }
        string = new RingBuffer(length);
    }

    public GuitarString(double[] array) 
    {
        time = 0;
        string = new RingBuffer(array.length);
        for(int i = 0; i < array.length; i++)
        {
            string.add(array[i]);
        }
    }

    public void pluck() 
    {
        while(!string.isEmpty())
        {
            string.remove();
        }
        while(!string.isFull())
        {
            string.add(Math.random()-0.5);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        time++;
        double val1 = string.remove();
        string.add((val1+string.peek())/2*.994);
    }

    // return the current sample
    public double sample() 
    {
        return string.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return time;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
