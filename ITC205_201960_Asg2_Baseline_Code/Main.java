import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner scanner; // variable names are to start with a lowercase letter and to be in camelBack
	private static Library library; // variable names are to start with a lowercase letter and to be in camelBack
	private static String menu; // variable names are to start with a lowercase letter and to be in camelBack
	private static Calendar calendar; // variable names are to be meaningful and start with a lowercase letter and to be in camelBack
	private static SimpleDateFormat simpleDateFormat; // variable names are to be meaningful and start with a lowercase letter and to be in camelBack
	
	
	private static String getMenu() {  // changed method Get_menu() uppercase to lowercase
		StringBuilder stringBuilder = new StringBuilder(); // sb to stringBuilder. Meaningful variable name
		
		stringBuilder.append("\nLibrary Main Menu\n\n") // sb to stringBuilder. Meaningful variable name
					 .append("  M  : add member\n")
					 .append("  LM : list members\n")
					 .append("\n")
					 .append("  B  : add book\n")
					 .append("  LB : list books\n")
					 .append("  FB : fix books\n")
					 .append("\n")
					 .append("  L  : take out a loan\n")
					 .append("  R  : return a loan\n")
					 .append("  LL : list loans\n")
					 .append("\n")
					 .append("  P  : pay fine\n")
					 .append("\n")
					 .append("  T  : increment date\n")
					 .append("  Q  : quit\n")
					 .append("\n")
					 .append("Choice : ");
		  
		return stringBuilder.toString(); // sb to stringBuilder. Meaningful variable name
	}


	public static void main(String[] args) {		
		try {			
			scanner = new Scanner(System.in); // variable names are to start with a lowercase letter and to be in camelBack
			library = Library.INSTANCE(); // variable names are to start with a lowercase letter and to be in camelBack
			calendar = Calendar.INSTANCE(); // variable names are to start with a lowercase letter and to be in camelBack
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); // variable names are to start with a lowercase letter and to be in camelBack
	
			for (member m : LIB.MEMBERS()) {
				output(m);
			}
			output(" ");
			for (book b : LIB.BOOKS()) {
				output(b);
			}
						
			menu = getMenu(); // changed method Get_menu() uppercase to lowercase
			
			boolean status = false; // variable names are to be meaningful. e changed to status
			
			while (!status ) { // variable names are to be meaningful. e changed to status
				
				output("\n" + simpleDateFormat.format(calendar.Date())); // sb to stringBuilder. Meaningful variable name
				String bookItem = input(menu); // variable names are to be meaningful. c changed to bookItem
				
				switch (bookItem.toUpperCase()) { // variable names are to be meaningful. c changed to bookItem 
				
				case "M": 
					ADD_MEMBER();
					break;
					
				case "LM": 
					MEMBERS();
					break;
					
				case "B": 
					ADD_BOOK();
					break;
					
				case "LB": 
					BOOKS();
					break;
					
				case "FB": 
					FIX_BOOKS();
					break;
					
				case "L": 
					BORROW_BOOK();
					break;
					
				case "R": 
					RETURN_BOOK();
					break;
					
				case "LL": 
					CURRENT_LOANS();
					break;
					
				case "P": 
					FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
					break;
					
				case "Q": 
					status = true; // variable names are to be meaningful. e changed to status
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void FINES() {
		new PayFineUI(new PayFineControl()).RuN();		
	}


	private static void CURRENT_LOANS() {
		output("");
		for (loan loan : LIB.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void BOOKS() {
		output("");
		for (book book : LIB.BOOKS()) {
			output(book + "\n");
		}		
	}



	private static void MEMBERS() {
		output("");
		for (member member : LIB.MEMBERS()) {
			output(member + "\n");
		}		
	}



	private static void BORROW_BOOK() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void RETURN_BOOK() {
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}


	private static void FIX_BOOKS() {
		new FixBookUI(new FixBookControl()).RuN();		
	}


	private static void INCREMENT_DATE() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();
			output(SDF.format(CAL.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String A = input("Enter author: ");
		String T  = input("Enter title: ");
		String C = input("Enter call number: ");
		book B = LIB.Add_book(A, T, C);
		output("\n" + B + "\n");
		
	}

	
	private static void ADD_MEMBER() {
		try {
			String LN = input("Enter last name: ");
			String FN  = input("Enter first name: ");
			String EM = input("Enter email: ");
			int PN = Integer.valueOf(input("Enter phone number: ")).intValue();
			member M = LIB.Add_mem(LN, FN, EM, PN);
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
