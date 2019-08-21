import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// All enum names are to start with an upper case letter and to be in CamelBack. 

	private BorrowBookControl borrowBookControl; // All class object should be meaningful and camel case
	private Scanner scannerInput; // variable or object names should be meaningful 
	private UiState uiState;// All enum names are to start with an upper case letter and to be in CamelBack. 

	
	public BorrowBookUI(BorrowBookControl control) {
		this.CONTROL = control;
		scannerInput = new Scanner(System.in); // variable or object names should be meaningful 
		uiState = UiState.INITIALISED;// All enum names are to start with an upper case letter and to be in CamelBack. 
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return scannerInput.nextLine();// variable or object names should be meaningful 
	}	
		
		
	private void showOutput(Object object) { // change method name output to meaningful name - showOutput
		System.out.println(object);
	}
	
			
	public void setState(UiState state) {// All enum names are to start with an upper case letter and to be in CamelBack. , change method name
		this.uiState = state;// All enum names are to start with an upper case letter and to be in CamelBack. 
	}

	
	public void run() {
		showOutput("Borrow Book Use Case UI\n"); // change method name output to meaningful name - showOutput
		
		while (true) {
			
			switch (uiState) {	// All enum names are to start with an upper case letter and to be in CamelBack. 		
			
			case CANCELLED:
				showOutput("Borrowing Cancelled");// change method name output to meaningful name - showOutput
				return;

				
			case READY:
				String MEM_STR = input("Swipe member card (press <enter> to cancel): ");
				if (MEM_STR.length() == 0) {
					CONTROL.cancel();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(MEM_STR).intValue();
					CONTROL.Swiped(Member_ID);
				}
				catch (NumberFormatException e) {
					showOutput("Invalid Member Id");// change method name output to meaningful name - showOutput
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				CONTROL.cancel();
				break;
			
				
			case SCANNING:
				String bookName = input("Scan Book (<enter> completes): "); // variable should be camel case
				if (bookName.length() == 0) { // variable should be camel case
					CONTROL.Complete();
					break;
				}
				try {
					int bookId = Integer.valueOf(bookName).intValue(); // variable should be camel case
					CONTROL.Scanned(bookId); // variable should be camel case
					
				} catch (NumberFormatException e) {
					showOutput("Invalid Book Id");// change method name output to meaningful name - showOutput
				} 
				break;
					
				
			case FINALISING:
				String answer = input("Commit loans? (Y/N): "); // variable should be camel case
				if (answer.toUpperCase().equals("N")) { // variable should be camel case
					CONTROL.cancel();
					
				} else {
					CONTROL.Commit_LOans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				showOutput("Borrowing Completed");// change method name output to meaningful name - showOutput
				return;
	
				
			default:
				showOutput("Unhandled state");// change method name output to meaningful name - showOutput
				throw new RuntimeException("BorrowBookUI : unhandled state :" + uiState);// All enum names are to start with an upper case letter and to be in CamelBack. 			
			}
		}		
	}


	public void display(Object object) {//change method name Display to display
		showOutput(object);		// change method name output to meaningful name - showOutput
	}


}
