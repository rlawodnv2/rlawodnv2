package backjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class QuickAplusB {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			String test = br.readLine();
			
			int a = Integer.parseInt(test.split(" ")[0]);
			int b = Integer.parseInt(test.split(" ")[1]);
			bw.write(a+b+"\n");
		}
		
		bw.flush();
	}

}
