import java.io.*;

public class ArithmeticalProgression {
  
  public static int hanNumber(int N) {
    int result = 0;
    
    if(N<100){
     return N;
    } else {
      return = 99;
      
      if(N == 100){
        N = 999; 
      }
      
      for(int i = 100; i <= N; i++){
        int thirdNum = i/100;
        int secondNum = (i/10)%10;
        int firstNum = i%10;
        
        if((thirdNum - secondNum) == (secondNum - firstNum)) {
          result++; 
        }
      }
      return result;
    }
  }
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    
    System.out.println(hanNumber(N));
  }
}
