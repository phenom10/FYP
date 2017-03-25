import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args){
		Addition additionOption;
		Subtraction subtractionOption;
		//Subtraction subtractionOption;
		
		Scanner selection;
		selection = new Scanner(System.in);
		
		{ int userSelection=0;
		while (userSelection==0)
		{
		   try {
			   System.out.println("Which Operator would you like to use?" + '\n' + "Enter 1 for Addition or 2 for Subtraction or 3 to Exit: ");	
			   userSelection = selection.nextInt();	
			   System.out.println("You have selected "+ userSelection);  // error trapping to ensure the user enters an integer.
			   }
	          catch (InputMismatchException e) 
			   {
			      selection.next();		// clears remaining text 
			      System.out.println("You entered an incorrect value");
			      userSelection = 0;
			   }
		}
		
		switch (userSelection){
			case 1: additionOption = new Addition();
					additionOption.getEquation();
					break;
			case 2: subtractionOption = new Subtraction();
					subtractionOption.getEquation();
					break;
			case 3: System.exit(1);
					break;
			default: System.out.print("You have entered an invalid option");
					break;
		
		}

	}	
		
}
}	
