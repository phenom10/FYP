import java.util.*;

public class Addition {

	private static HashMap<Character, Integer> letters = new HashMap<Character, Integer>(); 
	private static ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
	private static int digits[]= {0,1,2,3,4,5,6,7,8,9};
	private static int stringVal1, stringVal2, stringVal3, count = 0; 	
	private static String addEquation[] = new String[3];
	private static ArrayList<Character> uChars = new ArrayList<Character>();
	private static String eWords = "";
	private static char [] addend1, addend2, result;
	private static boolean solutionFound = false;
	
	public void getEquation(){
		
		Scanner wordInput;
		wordInput = new Scanner(System.in);

		System.out.println("Please enter your cryptarithm one word at a time." + '\n' + "First two words will be the addends and the third word will be the result");
		for (int i = 0; i < 3; i++){
			System.out.println("Please enter a word: ");
			addEquation[i] = wordInput.next();	
			addEquation[i] = addEquation[i].toLowerCase();
		}
		
		for(int i = 0; i < addEquation.length; i++){
			 eWords = eWords + addEquation[i];
		}
		
		addend1 = addEquation[0].toCharArray();
		addend2 = addEquation[1].toCharArray();
		result = addEquation[2].toCharArray();
		
		checkIsDigit(eWords);
		if(checkIsDigit(eWords) == false){
			System.out.println("You entered a digit(s) for a character(s)");
		}
			
		checkIsSpecial(eWords);
		if(checkIsSpecial(eWords) == false){
			System.out.println("You entered a special character(s)");
		}
		
		checkUnique(eWords);
		if(checkUnique(eWords) == false){
			System.out.println("This equation has more than 10 unique characters");
		}
		
		checkLengths(addend1,addend2,result);
		if (checkLengths(addend1,addend2,result) == false){
			System.out.println("The result word is smaller than an addend");
		}
		
		if((checkUnique(eWords) == true) && (checkLengths(addend1,addend2,result) == true) && (checkIsDigit(eWords) == true) && (checkIsSpecial(eWords) == true)){
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
		
		char uniChars[] = uniqueChars.toCharArray();
		Arrays.sort(uniChars);
		
		for(char c : uniChars){
			uChars.add(c);
		}
		
		search();

		permutations(digits, 0, digits.length-1);
		
		for(int i = 0; i < combinations.size(); i++){
			for(int j = 0; j < uChars.size(); j++){
				letters.put(uChars.get(j), combinations.get(i).get(j));
			}
			stringVal1 = getLetterValue(addEquation[0]);
			stringVal2 = getLetterValue(addEquation[1]);
			stringVal3 = getLetterValue(addEquation[2]);
			
			if((stringVal1 + stringVal2 == stringVal3) && (getIntegerLengths() == true) && (count < 1)){ 
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
	
	public static void search(){
		boolean search1 = false;
		
		if(((addEquation[2].length() - addEquation[0].length()) >= 1) && (((addEquation[2].length() - addEquation[1].length()) >= 1))){
			letters.put(addEquation[2].charAt(0), 1);
			letters.put(addEquation[2].charAt(1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			
			for(int i = 0; i < uChars.size(); i++){
				if(addEquation[2].charAt(0) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			
			for(int j = 0; j < uChars.size(); j++){
				if(addEquation[2].charAt(1) == uChars.get(j)){
					uChars.remove(j);
				}
			}
			search1 = true;
			
			/*for(int i = 0; i < uChars.size(); i++){
				System.out.print(uChars.get(i) + " ");
			}
			
			for(int j = 0; j < digits.length; j++){
				System.out.print(digits[j] + " ");
			}
			for(Character l: letters.keySet()){
				System.out.print(l + " = " + letters.get(l)+";" + " ");
			}*/
		}
		if ((addEquation[0].charAt(addEquation[0].length()-1) == addEquation[2].charAt(addEquation[2].length()-1)) && (search1 == true)){
			letters.remove(addEquation[2].charAt(1));
			letters.put(addEquation[1].charAt(addEquation[1].length()-1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addEquation[1].charAt(addEquation[1].length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(addEquation[2].charAt(1));
		}
		if((addEquation[0].charAt(addEquation[0].length()-1) == addEquation[2].charAt(addEquation[2].length()-1)) && (search1 == false)){
			letters.put(addEquation[1].charAt(addEquation[1].length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addEquation[1].charAt(addEquation[1].length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
		}
		if ((addEquation[1].charAt(addEquation[1].length()-1) == addEquation[2].charAt(addEquation[2].length()-1)) && (search1 == true)){
			letters.remove(addEquation[2].charAt(1));
			letters.put(addEquation[0].charAt(addEquation[0].length()-1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addEquation[0].charAt(addEquation[0].length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(addEquation[2].charAt(1));
		}
		if((addEquation[1].charAt(addEquation[1].length()-1) == addEquation[2].charAt(addEquation[2].length()-1)) && (search1 == false)){
			letters.put(addEquation[0].charAt(addEquation[0].length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addEquation[0].charAt(addEquation[0].length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
		}
		if ((addEquation[0].charAt(addEquation[0].length()-1) == addEquation[2].charAt(addEquation[2].length()-1)) && (addEquation[1].charAt(addEquation[1].length()-1) == addEquation[2].charAt(addEquation[2].length()-1) && (search1 == true))){
			letters.remove(addEquation[2].charAt(1));
			letters.put(addEquation[0].charAt(addEquation[0].length()-1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addEquation[0].charAt(addEquation[0].length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(addEquation[2].charAt(1));
		}
		if((addEquation[0].charAt(addEquation[0].length()-1) == addEquation[2].charAt(addEquation[2].length()-1)) && (addEquation[1].charAt(addEquation[1].length()-1) == addEquation[2].charAt(addEquation[2].length()-1) && (search1 == false))){
			letters.put(addEquation[0].charAt(addEquation[0].length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addEquation[0].charAt(addEquation[0].length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
		}
		
		/*else{
			for(int i = 0; i < digits.length; i++){
				System.out.print(digits[i] + " ");
			}
		}*/
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
		if((String.valueOf(stringVal1).length() == addEquation[0].length()) && (String.valueOf(stringVal2).length() == addEquation[1].length()) && (String.valueOf(stringVal3).length() == addEquation[2].length())){
			intLength = true;
		}
		
		return intLength;
	}
}

// add multiplication and three word inputs
// ask if there's another word
// reset button for gui
