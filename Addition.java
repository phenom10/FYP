import java.util.Scanner;
import java.util.Arrays;
public class Addition {
	
	
	private String addResult = ""; // 'addResult to be returned to 'Main' class'
	private String Result = "";
	
	public void getEquation(){
		
		Scanner wordInput;
		wordInput = new Scanner(System.in);
		
		//String wI = wordInput.next();
		
		String addEquation[] = new String[3];	
		
		System.out.println("Please enter your cryptarithm one word at a time." + '\n' + "First two words will be the addends and the third word will be the result" );
		for (int i = 0; i < 3; i++){
			System.out.println("Please enter a word: ");
			addEquation[i] = wordInput.next();	
		}
		/*for (int j = 0; j < addEquation.length; j++){
			addResult = addResult + (addEquation[j]);
		}
		return (addResult);*/
		String eWords = "";
		
		for(int i = 0; i < addEquation.length; i++){
			 eWords = eWords + addEquation[i];
			//addResult = eWords;
		}
		
		char [] addend1 = addEquation[0].toCharArray();
		char [] addend2 = addEquation[1].toCharArray();
		char [] result = addEquation[2].toCharArray();
		
		//checkEquation(addEquation);
		checkUnique(eWords);
		checkLengths(addend1, addend2, result);
		solveEquation(eWords, addend1, addend2, result);
	}
	
	public String checkUnique(String s){							//String[] gEquation
		
		Boolean unique = false;
		String lowerCase = s.toLowerCase();
	    char cEquation[] = lowerCase.toCharArray();
	    int countOfUniqueChars = s.length();
	    for (int i = 0; i < cEquation.length; i++) {
	        if (i != lowerCase.indexOf(cEquation[i])) {
	            countOfUniqueChars--;
	        }
	    }
	    //return countOfUniqueChars;
		if(countOfUniqueChars <= 10){
			 unique = true;			
		}
		
		addResult = unique.toString();
		
		if(addResult == "false"){
			addResult = "This equation does not meet the requirements";
		}
		
		return (addResult);
		//return (addResult);
	}
	
	public String checkLengths(char[] a1, char[] a2, char[] r){
		if (addResult == "true"){
			if((r.length >= a1.length) && (r.length >= a2.length)){
				addResult = "true";
			}
			else{
				addResult = "This equation does not meet the requirements";
				System.out.println(addResult);
			}
			
		}
		return (addResult); 
	}
	
	public void solveEquation(String a, char[] b1, char[] b2, char[] r2){
		
		if (addResult == "true"){
			int b1Difference = 0;
			int b2Difference = 0;
					
			String uniqueChars = "";
			
			for (int i = 0; i < a.length(); i++){
				if(uniqueChars.indexOf(a.charAt(i)) == -1){
					uniqueChars = uniqueChars + a.charAt(i);
				}
			}
			
			int [] digits= {0,1,2,3,4,5,6,7,8,9};
			int [] carry = {0,1};
			char [] uChars = uniqueChars.toCharArray();
			char [] b1a = new char [r2.length];
			char [] b2a = new char [r2.length];
			
			char [][] sum = {b1a,b2a,r2};
			
			b1Difference = r2.length - b1.length;
			for(int i = 0; i < b1Difference; i++){
				b1a[i] = '0';
			}
			for(int j = 0; j < b1.length; j++){
				for(int k = b1Difference; k < b1a.length; k++){
					b1a[k] = b1[j];
				}
			}
			
			b2Difference = r2.length - b2.length;
			for(int i = 0; i < b2Difference; i++){
				b2a[i] = '0';
			}
			for(int j = 0; j < b2.length; j++){
				for(int k = b2Difference; k < b2a.length; k++){
					b2a[k] = b2[j];
				}
			}
			
							
			
			/*while (addResult == "true"){
				
				
			}*/
			
			Result = Result + sum[0][1];
			
			System.out.println(Result);
			
			}
		else{
			Result = addResult;
			System.out.println(Result);
		}
		}
		

}
