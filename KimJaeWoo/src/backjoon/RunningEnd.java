import java.util.Arrays;

class RunningEnd {
    public String solution(String[] participant, String[] completion) {
        
        String answer = "";
        
        Arrays.sort(participant);
		Arrays.sort(completion);
		
		for(int i=0; i<completion.length; i++) {
			if(!participant[i].equals(completion[i])) 
				break;
			
			answer = participant[i+1];
		}
        
		return answer;
    }
}
