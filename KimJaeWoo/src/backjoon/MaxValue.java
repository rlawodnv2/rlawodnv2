import java.util.Scanner;

public class MaxValue {
    public static void main(String[] args){
        int[] arr = {in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()
                    ,in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()}
        in.close();
        
        int count = 0;
        int max = 0;
        int index = 0;
        
        for(int value : arr){
            count++;
            
            if(value > max){
                max = value;
                index = count;
            }
        }
        System.out.println(max + "\n" + index);
        
    }
}
