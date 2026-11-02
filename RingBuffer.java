/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
    public int head;
    public int tail;
    public double[] arr;


    public RingBuffer(int capacity)
    {
        head = 0;
        tail = 0;
        arr = new double[capacity];
    }

    public int size()
    {
        /*
        if(tail > head)
            return tail - head;
        if(tail==head)
        {
            if(arr[head]==null)
            {
                return 0;
            }
            return arr.length;
        }
        return arr.length-1-head+tail;
        */
        int size = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i]!=0)
                size++;
        }
        return size;
    }
    
    //array is considered empty if all values are zero
    public boolean isEmpty()
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i]!=0)
                return false;
        }
        return true;
    }
    
    //array is considered full if no zeroes are present
    public boolean isFull()
    {   
        
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i]==0)
                return false;
        }
        return true;
        
        //return tail==head&&arr[head]!=null;
    }
    
    public void add(double value) throws IndexOutOfBoundsException
    {
        if(!this.isFull())
        {
            arr[tail] = value;
            if(tail+1==arr.length)
            {
                tail = 0;
            }
            else
            {
                tail++;
            }
        }
        else
        {
            throw new IndexOutOfBoundsException("Attempted to add value to full array.");
        }
    }
    
    public double peek()
    {
        return arr[head];
    }
    
    public double remove()
    {
        /*
        if(arr[head]==null)
        {
            throw new NullPointerException("Attempted to remove null value from array.");
        }
        */
        double temp = arr[head];
        arr[head] = 0;
        if(head+1==arr.length)
            head=0;
        else
            head++;
        return temp;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
