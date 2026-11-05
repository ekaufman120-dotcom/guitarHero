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
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for(int i = 0; i < strings.length; i++)
        {
            strings[i] = new GuitarString(440.0 * Math.pow(1.05956, i-24));
        }
        
        //new GuitarString(440.0 * Math.pow(1.05956, -24));

        // the main input loop
        while (true) 
        {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) 
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                for(int i = 0; i < keyboard.length(); i++)
                {
                    if(key==keyboard.charAt(i))
                        strings[i].pluck();
                }
            }

            // compute the superposition of the samples
            double sample = 0;
            for(int i = 0; i < strings.length; i++)
            {
                sample+=strings[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for(int i = 0; i < strings.length; i++)
            {
                strings[i].tic();
            }
        }
    }
}
