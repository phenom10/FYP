import java.util.*;

public class Addition {

	private static HashMap<Character, Integer> letters = new HashMap<Character, Integer>(); 
	private static ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<Integer> digits= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
	private static int stringVal1, stringVal2, stringVal3, count = 0; 	
	private static String addEquation[] = new String[3];
	private static String eWords = "";
	private static boolean solutionFound = false;
	
	public void getEquation(){
		
		Scanner wordInput;
		wordInput = new Scanner(System.in);

		System.out.println("Please enter your cryptarithm one word at a time." + '\n' + "First two words will be the addends and the third word will be the result" );
		for (int i = 0; i < 3; i++){
			System.out.println("Please enter a word: ");
			addEquation[i] = wordInput.next();	
		}
		
		for(int i = 0; i < addEquation.length; i++){
			 eWords = eWords + addEquation[i];
		}
		
		char [] addend1 = addEquation[0].toCharArray();
		char [] addend2 = addEquation[1].toCharArray();
		char [] result = addEquation[2].toCharArray();
		
		checkUnique(eWords);
		if(checkUnique(eWords) == false){
			System.out.println("This equation has more than 10 unique characters");
		}
		
		checkLengths(addend1,addend2,result);
		if (checkLengths(addend1,addend2,result) == false){
			System.out.println("The result word is smaller than an addend");
		}
		
		if((checkUnique(eWords) == true) && (checkLengths(addend1,addend2,result) == true)){
			solveEquation();
		}
	
	}
	
	public static boolean checkUnique(String s){
		Boolean unique = false;
		String lowerCase = s.toLowerCase();
	    char cEquation[] = lowerCase.toCharArray();
	    int countOfUniqueChars = s.length();
	    for (int i = 0; i < cEquation.length; i++) {
	        if (i != lowerCase.indexOf(cEquation[i])) {
	            countOfUniqueChars--;
	        }
	    }
		if(countOfUniqueChars <= 10){
			 unique = true;			
		}
		return unique;
	}
	
	public static boolean checkLengths(char [] a1, char [] a2, char [] r){
		Boolean lengths = false;
		if((r.length >= a1.length) && (r.length >= a2.length)){
			lengths = true;
		}
		return lengths;
	}
	
	public static void solveEquation(){
		String uniqueChars = "";
		
		for (int i = 0; i < eWords.length(); i++){
			if(uniqueChars.indexOf(eWords.charAt(i)) == -1){
				uniqueChars = uniqueChars + eWords.charAt(i);
			}
		}
		
		char [] uChars = uniqueChars.toCharArray();
		Arrays.sort(uChars);

		permutations(digits, 0);
		
		for(int i = 0; i < combinations.size(); i++){
			for(int j = 0; j < uChars.length; j++){
				letters.put(uChars[j], combinations.get(i).get(j));
			}
			stringVal1 = getLetterValue(addEquation[0]);
			stringVal2 = getLetterValue(addEquation[1]);
			stringVal3 = getLetterValue(addEquation[2]);
			
			if((stringVal3 == stringVal1 + stringVal2) && (getIntegerLengths() == true) && (count < 1)){ 
				solutionFound = true;
				System.out.println("Your equation is " + addEquation[0] + " + " + addEquation[1] + " = " + addEquation[2]);
				System.out.println("The result is " + stringVal1 + " + " + stringVal2 + " = " + stringVal3);
				System.out.println("The letter substitutions are: ");
				for(Character l: letters.keySet()){
					System.out.print(l + " = " + letters.get(l)+";" + " ");
				}
				count++;
			}
		}
		if(!solutionFound){
			System.out.println("A solution could not be found");
		}
		
	}
	
	public static void permutations(ArrayList<Integer> vals, int k ){
		if(k == vals.size() -1)
		{
			ArrayList<Integer> combos = new ArrayList<Integer>();
			for(int i: vals)
			{
				combos.add(vals.get(i));
			}
			combinations.add(combos);
		}	
		else
		{	
			for(int i = k; i < vals.size(); i++){
	            java.util.Collections.swap(vals, i, k);
	            permutations(vals, k+1);
	            java.util.Collections.swap(vals, k, i);
	        }
		}
	}
	
	public static int getLetterValue(String word){
		String temp = "";
		for(int i = 0; i < word.length(); i++){
			temp = temp + letters.get(word.charAt(i));
		}
		return Integer.parseInt(temp);
	}
	
	public static boolean getIntegerLengths(){
		boolean intLength = false;
		if((String.valueOf(stringVal1).length() == addEquation[0].length()) && (String.valueOf(stringVal2).length() == addEquation[1].length()) && (String.valueOf(stringVal3).length() == addEquation[2].length())){
			intLength = true;
		}
		
		return intLength;
	}
	
}
