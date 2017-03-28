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
		
		char [] uChars = uniqueChars.toCharArray();
		Arrays.sort(uChars);

		permutations(digits, 0, digits.length-1);
		
		for(int i = 0; i < combinations.size(); i++){
			for(int j = 0; j < uChars.length; j++){
				letters.put(uChars[j], combinations.get(i).get(j));
			}
			stringVal1 = getLetterValue(addend1);
			stringVal2 = getLetterValue(addend2);
			stringVal3 = getLetterValue(result);
			
			if((stringVal3 == stringVal1 + stringVal2) && (getIntegerLengths() == true) && (count < 1)){ 
				solutionFound = true;
				answer.setText("Your equation is " + addend1 + " + " + addend2 + " = " + result + "\n");
				answer.append("The result is " + stringVal1 + " + " + stringVal2 + " = " + stringVal3 + "\n");
				answer.append("The letter substitutions are: " + "\n");
				for(Character l: letters.keySet()){
					answer.append(l + " = " + letters.get(l)+";" + " ");
				}
				count++;
			}
		}
		if(!solutionFound){
			answer.setText("A solution could not be found");
		}
		
	}
	
	public static void solveSubEquation(){
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
			stringVal1 = getLetterValue(subtrahend1);
			stringVal2 = getLetterValue(subtrahend2);
			stringVal3 = getLetterValue(result);
			
			if((stringVal3 == stringVal1 - stringVal2) && (getSubIntegerLengths() == true) && (count < 1)){ 
				solutionFound = true;
				answer.setText("Your equation is " + subtrahend1 + " - " + subtrahend2 + " = " + result + "\n");
				answer.append("The result is " + stringVal1 + " - " + stringVal2 + " = " + stringVal3 + "\n");
				answer.append("The letter substitutions are: " + "\n");
				for(Character l: letters.keySet()){
					answer.append(l + " = " + letters.get(l)+";" + " ");
				}
				count++;
			}
		}
		if(!solutionFound){
			answer.setText("A solution could not be found");
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		JButton btnAddition = new JButton("Addition");
		btnAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				addend1 = word1.getText();
				addend2 = word2.getText();
				result = word3.getText();
				
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
		});
		btnAddition.setBounds(142, 184, 117, 51);
		frame.getContentPane().add(btnAddition);
		
		JButton btnSubtraction = new JButton("Subtraction");
		btnSubtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subtrahend1 = word1.getText();
				subtrahend2 = word2.getText();
				result = word3.getText();
				
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
		});
		btnSubtraction.setBounds(372, 184, 117, 51);
		frame.getContentPane().add(btnSubtraction);
		
		lblThisIsYour = new JLabel("Here's your answer:");
		lblThisIsYour.setBounds(94, 287, 165, 28);
		frame.getContentPane().add(lblThisIsYour);
		
		answer = new JTextArea();
		answer.setBounds(269, 257, 370, 96);
		frame.getContentPane().add(answer);
		answer.setColumns(10);
	}
}
