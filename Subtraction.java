import java.util.*;

public class Subtraction {

	private static HashMap<Character, Integer> letters = new HashMap<Character, Integer>(); 
	private static ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
	private static int digits[]= {0,1,2,3,4,5,6,7,8,9};
	private static int stringVal1, stringVal2, stringVal3, count = 0; 	
	private static String subEquation[] = new String[3];
	private static String eWords = "";
	private static boolean solutionFound = false;
	
	public void getEquation(){
		
		Scanner wordInput;
		wordInput = new Scanner(System.in);

		System.out.println("Please enter your cryptarithm one word at a time." + '\n' + "First two words will be the subtrahends and the third word will be the result" );
		for (int i = 0; i < 3; i++){
			System.out.println("Please enter a word: ");
			subEquation[i] = wordInput.next();	
		}
		
		for(int i = 0; i < subEquation.length; i++){
			 eWords = eWords + subEquation[i];
		}
		
		char [] subtrahend1 = subEquation[0].toCharArray();
		char [] subtrahend2 = subEquation[1].toCharArray();
		char [] result = subEquation[2].toCharArray();
		
		checkIsDigit(eWords);
		if(checkIsDigit(eWords) == false){
			System.out.println("You entered a digit(s) for a character(s)");
		}
		
		checkIsSpecial(eWords);
		if(checkIsSpecial(eWords) == false){
			System.out.println("You entered a special(s) character(s)");
		}
		
		checkUnique(eWords);
		if(checkUnique(eWords) == false){
			System.out.println("This equation has more than 10 unique characters");
		}
		
		checkLengths(subtrahend1,subtrahend2,result);
		if (checkLengths(subtrahend1,subtrahend2,result) == false){
			System.out.println("The result word is bigger than a subtrahend");
		}
		
		if((checkUnique(eWords) == true) && (checkLengths(subtrahend1,subtrahend2,result) == true) && (checkIsDigit(eWords) == true) && (checkIsSpecial(eWords) == true)){
			solveEquation();
		}
	
	}
	
	public static boolean checkIsDigit(String characters){
		Boolean character = true;
		for(int i = 0; i < characters.length(); i++){
			if(Character.isDigit(characters.charAt(i))){
				character = false;
			}
		}
		return character;
	}		
	
	public static boolean checkIsSpecial(String specialChar){
		Boolean special = true;
		for(int i = 0; i < specialChar.length(); i++){
			if(!Character.isLetterOrDigit(specialChar.charAt(i))){
				special = false;
			}
		}
		return special;
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
		if((r.length <= a1.length) && (r.length <= a2.length)){
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

		permutations(digits, 0, digits.length-1);
		
		for(int i = 0; i < combinations.size(); i++){
			for(int j = 0; j < uChars.length; j++){
				letters.put(uChars[j], combinations.get(i).get(j));
			}
			stringVal1 = getLetterValue(subEquation[0]);
			stringVal2 = getLetterValue(subEquation[1]);
			stringVal3 = getLetterValue(subEquation[2]);
			
			if((stringVal3 == stringVal1 - stringVal2) && (getIntegerLengths() == true) && (count < 1)){ 
				solutionFound = true;
				System.out.println("Your equation is " + subEquation[0] + " - " + subEquation[1] + " = " + subEquation[2]);
				System.out.println("The result is " + stringVal1 + " - " + stringVal2 + " = " + stringVal3);
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
	
	public static void swap(int[]vals, int x , int y){
		int temp = vals[x];
        vals[x] = vals[y];
        vals[y] = temp;
	}
	
	public static void permutations(int []a, int k, int n ){
		if(k == n)
		{
			ArrayList<Integer> combos = new ArrayList<Integer>();
			for(int i = 0; i < a.length; i++)
			{
				combos.add(a[i]);
			}
			combinations.add(combos);
		}	
		else
		{	
			for (int j = k; j <= n; j++)
				 	{
				 		swap(a, k, j);
				 		permutations(a, k + 1, n);
				 		swap(a, k, j);
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
		if((String.valueOf(stringVal1).length() == subEquation[0].length()) && (String.valueOf(stringVal2).length() == subEquation[1].length()) && (String.valueOf(stringVal3).length() == subEquation[2].length())){
			intLength = true;
		}
		
		return intLength;
	}
	
}