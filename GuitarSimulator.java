/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    public static void main(String[] args) 
    {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        /*
        // Create two guitar strings, for concert A and C
        GuitarString stringA = new GuitarString(440.0);
        GuitarString stringC = new GuitarString(440.0 * Math.pow(2, 3.0/12.0));
        */
        GuitarString[] strings = new GuitarString(440.0 * Math.pow(1.05956, -24));

        // the main input loop
        while (true) 
        {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) 
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (key == 'a')
                    stringA.pluck();
                if (key == 'c') 
                    stringC.pluck();
            }

            // compute the superposition of the samples
            double sample = stringA.sample() + stringC.sample();

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            stringA.tic();
            stringC.tic();
        }
    }
}
