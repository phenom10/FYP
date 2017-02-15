import java.util.Scanner;
import java.util.Arrays;
public class Addition {
	
	
	private String addResult = ""; // 'addResult to be returned to 'Main' class'
	
	public void getEquation(){
		
		Scanner wordInput;
		wordInput = new Scanner(System.in);
		
		//String wI = wordInput.next();
		
		String addEquation[] = new String[3];
		/*char [] addend1= new char[25];
		char [] addend2= new char[25];
		char [] result= new char[25];*/		
		
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
	}
	
	public String checkUnique(String s){							//String[] gEquation
		
		//Arrays.sort(cEquation);
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
	
	public void checkLengths(char[] a1, char[] a2, char[] r){
		if (addResult == "true"){
			if((r.length >= a1.length) && (r.length >= a2.length)){
				addResult = "This equation meets the requirements";
			}
			else{
				addResult = "This equation does not meet the requirements";
			}
			
		}
		System.out.println(addResult); 
	}
	
/*	public void checkEquation(String s) {							//String[] gEquation
				
		//Arrays.sort(cEquation);
		
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
			addResult = "This equation is acceptable";			
		}
		else{
			addResult = "This equation does not meet the requirements";
		}
	
		//return (addResult);
		System.out.println(addResult); 
	}*/
		
	
}
