import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class FYP_Cryptarithmetic_GUI{
	
	private static HashMap<Character, Integer> letters = new HashMap<Character, Integer>(); 
	private static ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
	private static int digits[]= {0,1,2,3,4,5,6,7,8,9};
	private static int stringVal1, stringVal2, stringVal3, count = 0; 	
	private static String addend1, addend2, subtrahend1, subtrahend2, result;
	private static String eWords = "";
	private static ArrayList<Character> uChars = new ArrayList<Character>();
	private static boolean solutionFound = false;
	
	private JFrame frame;
	private JTextField word1;
	private JTextField word2;
	private JTextField word3;
	private JLabel lblWord;
	private JLabel lblWord_1;
	private JLabel lblWord_2;
	private JLabel lblThisIsYour;
	private static JTextArea answer;
	private JButton btnAddition, btnSubtraction, btnReset; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FYP_Cryptarithmetic_GUI window = new FYP_Cryptarithmetic_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	
	public static boolean checkLengths(String a1, String a2, String r){
		Boolean lengths = false;
		if((r.length() >= a1.length()) && (r.length() >= a2.length())){
			lengths = true;
		}
		return lengths;
	}
	
	public static boolean checkLengths2(String s1, String s2, String r2){
		Boolean lengths = false;
		if((r2.length() <= s1.length()) && (r2.length() <= s2.length())){
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
		
		char [] uniChars = uniqueChars.toCharArray();
		Arrays.sort(uniChars);
		
		for(char c : uniChars){
			uChars.add(c);
		}
		
		addSearch();

		permutations(digits, 0, digits.length-1);
		
		for(int i = 0; i < combinations.size(); i++){
			for(int j = 0; j < uChars.size(); j++){
				letters.put(uChars.get(j), combinations.get(i).get(j));
			}
			stringVal1 = getLetterValue(addend1);
			stringVal2 = getLetterValue(addend2);
			stringVal3 = getLetterValue(result);
			
			if((stringVal1 + stringVal2 == stringVal3) && (getIntegerLengths() == true) && (count < 1)){ 
				solutionFound = true;
				answer.setText("Your equation is " + addend1 + " + " + addend2 + " = " + result + "\n");
				answer.append("The result is " + stringVal1 + " + " + stringVal2 + " = " + stringVal3 + "\n");
				answer.append("The letter substitutions are: " + "\n");
				for(Character l: letters.keySet()){
					answer.append(l + " = " + letters.get(l)+";" + " ");
				}
				/*System.out.print("Your equation is " + addend1 + " + " + addend2 + " = " + result + "\n");
				System.out.print("The result is " + stringVal1 + " + " + stringVal2 + " = " + stringVal3 + "\n"); // For the demo
				System.out.print("The letter substitutions are: " + "\n");
				for(Character l: letters.keySet()){
					System.out.print(l + " = " + letters.get(l)+";" + " ");
				}*/
				count++;
			}
		}
		if(!solutionFound){
			answer.setText("A solution could not be found");
		}
		
	}
	
	public static void addSearch(){
		boolean search1 = false;
		
		if(((result.length() - addend1.length()) >= 1) && (((result.length() - addend2.length()) >= 1))){
			letters.put(result.charAt(0), 1);
			letters.put(result.charAt(1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			
			for(int i = 0; i < uChars.size(); i++){
				if(result.charAt(0) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			
			for(int j = 0; j < uChars.size(); j++){
				if(result.charAt(1) == uChars.get(j)){
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
		if ((addend1.charAt(addend1.length()-1) == result.charAt(result.length()-1)) && (search1 == true)){
			letters.remove(result.charAt(1));
			letters.put(addend2.charAt(addend2.length()-1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addend2.charAt(addend2.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(result.charAt(1));
		}
		if((addend1.charAt(addend1.length()-1) == result.charAt(result.length()-1)) && (search1 == false)){
			letters.put(addend2.charAt(addend2.length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addend2.charAt(addend2.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
		}
		if ((addend2.charAt(addend2.length()-1) == result.charAt(result.length()-1)) && (search1 == true)){
			letters.remove(result.charAt(1));
			letters.put(addend1.charAt(addend1.length()-1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addend1.charAt(addend1.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(result.charAt(1));
		}
		if((addend2.charAt(addend2.length()-1) == result.charAt(result.length()-1)) && (search1 == false)){
			letters.put(addend1.charAt(addend1.length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addend1.charAt(addend1.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
		}
		if ((addend1.charAt(addend1.length()-1) == result.charAt(result.length()-1)) && (addend2.charAt(addend2.length()-1) == result.charAt(result.length()-1) && (search1 == true))){
			letters.remove(result.charAt(1));
			letters.put(addend1.charAt(addend1.length()-1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addend1.charAt(addend1.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(result.charAt(1));
		}
		if((addend1.charAt(addend1.length()-1) == result.charAt(result.length()-1)) && (addend2.charAt(addend2.length()-1) == result.charAt(result.length()-1) && (search1 == false))){
			letters.put(addend1.charAt(addend1.length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(addend1.charAt(addend1.length()-1) == uChars.get(i)){
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
	
	public static void solveSubEquation(){
		String uniqueChars = "";
		
		for (int i = 0; i < eWords.length(); i++){
			if(uniqueChars.indexOf(eWords.charAt(i)) == -1){
				uniqueChars = uniqueChars + eWords.charAt(i);
			}
		}
		
		char [] uniChars = uniqueChars.toCharArray();
		Arrays.sort(uniChars);
		
		for(char c : uniChars){
			uChars.add(c);
		}
		
		subSearch();

		permutations(digits, 0, digits.length-1);
		
		for(int i = 0; i < combinations.size(); i++){
			for(int j = 0; j < uChars.size(); j++){
				letters.put(uChars.get(j), combinations.get(i).get(j));
			}
			stringVal1 = getLetterValue(subtrahend1);
			stringVal2 = getLetterValue(subtrahend2);
			stringVal3 = getLetterValue(result);
			
			if((stringVal1 - stringVal2 == stringVal3) && (getSubIntegerLengths() == true) && (count < 1)){ 
				solutionFound = true;
				answer.setText("Your equation is " + subtrahend1 + " - " + subtrahend2 + " = " + result + "\n");
				answer.append("The result is " + stringVal1 + " - " + stringVal2 + " = " + stringVal3 + "\n");
				answer.append("The letter substitutions are: " + "\n");
				for(Character l: letters.keySet()){
					answer.append(l + " = " + letters.get(l)+";" + " ");
				}
				/*System.out.print("Your equation is " + subtrahend1 + " - " + subtrahend2 + " = " + result + "\n");
				System.out.print("The result is " + stringVal1 + " - " + stringVal2 + " = " + stringVal3 + "\n");		// For the demo
				System.out.print("The letter substitutions are: " + "\n");
				for(Character l: letters.keySet()){
					System.out.print(l + " = " + letters.get(l)+";" + " ");
				}*/				
				count++;
			}
		}
		if(!solutionFound){
			answer.setText("A solution could not be found");
		}
		
	}
	
	public static void subSearch(){
		boolean search1 = false;
		
		if(((subtrahend1.length() - subtrahend2.length()) >= 1) && (((subtrahend1.length() - result.length()) >= 1))){
			letters.put(subtrahend1.charAt(0), 1);
			letters.put(subtrahend1.charAt(1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			
			for(int i = 0; i < uChars.size(); i++){
				if(subtrahend1.charAt(0) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			
			for(int j = 0; j < uChars.size(); j++){
				if(subtrahend1.charAt(1) == uChars.get(j)){
					uChars.remove(j);
				}
			}
			search1 = true;
		}
		
		if((subtrahend1.charAt(subtrahend1.length()-1) == result.charAt(result.length()-1)) && (search1 == true)){
			letters.remove(subtrahend1.charAt(1));
			letters.put(subtrahend2.charAt(subtrahend2.length()-1), 0);
			digits = new int[] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(subtrahend2.charAt(subtrahend2.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(subtrahend1.charAt(1));
		}
		
		if((subtrahend1.charAt(subtrahend1.length()-1) == result.charAt(result.length()-1)) && (search1 == false)){
			letters.put(subtrahend2.charAt(subtrahend2.length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(subtrahend2.charAt(subtrahend2.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
		}
		
		if ((subtrahend1.charAt(subtrahend1.length()-1) == result.charAt(result.length()-1)) && (subtrahend2.charAt(subtrahend2.length()-1) == result.charAt(result.length()-1) && (search1 == true))){
			letters.remove(subtrahend1.charAt(1));
			letters.put(subtrahend1.charAt(subtrahend1.length()-1), 0);
			digits = new int [] {2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(subtrahend1.charAt(subtrahend1.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
			uChars.add(subtrahend1.charAt(1));
		}
		
		if((subtrahend1.charAt(subtrahend1.length()-1) == result.charAt(result.length()-1)) && (subtrahend2.charAt(subtrahend2.length()-1) == result.charAt(result.length()-1) && (search1 == false))){
			letters.put(subtrahend1.charAt(subtrahend1.length()-1), 0);
			digits = new int[] {1,2,3,4,5,6,7,8,9};
			for(int i = 0; i < uChars.size(); i++){
				if(subtrahend1.charAt(subtrahend1.length()-1) == uChars.get(i)){
					uChars.remove(i);
				}
			}
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
		if((String.valueOf(stringVal1).length() == addend1.length()) && (String.valueOf(stringVal2).length() == addend2.length()) && (String.valueOf(stringVal3).length() == result.length())){
			intLength = true;
		}
		
		return intLength;
	}
	
	public static boolean getSubIntegerLengths(){
		boolean intLength = false;
		if((String.valueOf(stringVal1).length() == subtrahend1.length()) && (String.valueOf(stringVal2).length() == subtrahend2.length()) && (String.valueOf(stringVal3).length() == result.length())){
			intLength = true;
		}
		
		return intLength;
	}

	/**
	 * Create the application.
	 */
	public FYP_Cryptarithmetic_GUI() {
		initialize();
		Solve();
		Restart();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCryptarithmeticSolver = new JLabel("Cryptarithmetic Solver");
		lblCryptarithmeticSolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblCryptarithmeticSolver.setFont(new Font("Calibri", Font.BOLD, 25));
		lblCryptarithmeticSolver.setBounds(142, 21, 380, 28);
		frame.getContentPane().add(lblCryptarithmeticSolver);
		
		word1 = new JTextField();
		word1.setBounds(31, 103, 165, 46);
		frame.getContentPane().add(word1);
		word1.setColumns(10);
		
		word2 = new JTextField();
		word2.setBounds(240, 103, 165, 46);
		frame.getContentPane().add(word2);
		word2.setColumns(10);
		
		word3 = new JTextField();
		word3.setBounds(451, 103, 165, 46);
		frame.getContentPane().add(word3);
		word3.setColumns(10);
		
		lblWord = new JLabel("Word 1:");
		lblWord.setToolTipText("This is your first addend/subtrahend");
		lblWord.setBounds(79, 81, 46, 14);
		frame.getContentPane().add(lblWord);
		
		lblWord_1 = new JLabel("Word 2:");
		lblWord_1.setToolTipText("This is your second addend/subtrahend");
		lblWord_1.setBounds(298, 81, 46, 14);
		frame.getContentPane().add(lblWord_1);
		
		lblWord_2 = new JLabel("Word 3:");
		lblWord_2.setToolTipText("This is your result word");
		lblWord_2.setBounds(512, 78, 46, 14);
		frame.getContentPane().add(lblWord_2);
		
		btnAddition = new JButton("Addition");
		btnAddition.setBounds(46, 184, 117, 51);
		frame.getContentPane().add(btnAddition);
		
		btnSubtraction = new JButton("Subtraction");
		btnSubtraction.setBounds(271, 184, 117, 51);
		frame.getContentPane().add(btnSubtraction);
		
		lblThisIsYour = new JLabel("Here's your answer:");
		lblThisIsYour.setBounds(125, 287, 165, 28);
		frame.getContentPane().add(lblThisIsYour);
		
		answer = new JTextArea();
		answer.setEditable(false);
		answer.setBounds(262, 257, 370, 96);
		frame.getContentPane().add(answer);
		answer.setColumns(10);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(480, 184, 117, 51);
		frame.getContentPane().add(btnReset);
	}
	
	public void Solve(){
		btnAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == btnAddition){
					addend1 = word1.getText().toLowerCase();
					addend2 = word2.getText().toLowerCase();
					result = word3.getText().toLowerCase();
					
					eWords = addend1 + addend2 + result;
					
					checkIsDigit(eWords);
					if(checkIsDigit(eWords) == false){
						answer.setText("You entered a digit(s) for a character(s)" + "\n");
					}
					
					checkIsSpecial(eWords);
					if(checkIsSpecial(eWords) == false){
						answer.append("You entered a special character(s)" + "\n");
					}
					
					checkUnique(eWords);
					if(checkUnique(eWords) == false){
						answer.append("This equation has more than 10 unique characters" + "\n");
					}
					checkLengths(addend1,addend2,result);
					if (checkLengths(addend1,addend2,result) == false){
						answer.append("The result word is smaller than an addend" + "\n");
					}
					
					if((checkUnique(eWords) == true) && (checkLengths(addend1,addend2,result) == true) && (checkIsDigit(eWords) == true) && (checkIsSpecial(eWords) == true)){
						solveEquation();
					}
				}
			}
		});
		
		btnSubtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == btnSubtraction){
					subtrahend1 = word1.getText().toLowerCase();
					subtrahend2 = word2.getText().toLowerCase();
					result = word3.getText().toLowerCase();
					
					eWords = subtrahend1 + subtrahend2 + result;
					
					checkIsDigit(eWords);
					if(checkIsDigit(eWords) == false){
						answer.setText("You entered a digit(s) for a character(s)" + "\n");
					}
					
					checkIsSpecial(eWords);
					if(checkIsSpecial(eWords) == false){
						answer.append("You entered a special character(s)" + "\n");
					}
					
					checkUnique(eWords);
					if(checkUnique(eWords) == false){
						answer.append("This equation has more than 10 unique characters" + "\n");
					}
					
					checkLengths2(subtrahend1,subtrahend2,result);
					if (checkLengths2(subtrahend1,subtrahend2,result) == false){
						answer.append("The result word is bigger than a subtrahend" + "\n");
					}
					
					if((checkUnique(eWords) == true) && (checkLengths2(subtrahend1,subtrahend2,result) == true) && (checkIsDigit(eWords) == true) && (checkIsSpecial(eWords) == true)){
						solveSubEquation();
					}
				}
			}
		});
	}
	
	public void Restart(){
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == btnReset){
					word1.setText("");
					word2.setText("");
					word3.setText("");
					answer.setText("");
					FYP_Cryptarithmetic_GUI restart = new FYP_Cryptarithmetic_GUI();
					restart.initialize();
					restart.Solve();
					restart.Restart();
				}
			}
		});
	}
}
