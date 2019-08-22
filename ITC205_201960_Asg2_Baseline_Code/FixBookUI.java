import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };// All enum names are to start with an upper case letter and to be in CamelBack.

	private FixBookControl fixBookControl; // object name should be camel case
	private Scanner scannerInput; // change input to meaningful name
	private UiState uiState;// All enum names are to start with an upper case letter and to be in CamelBack, object, reference and variable names should be camel case

	
	public FixBookUI(FixBookControl control) {
		this.fixBookControl = control;  // object name should be camel case
		scannerInput = new Scanner(System.in);// change input to meaningful name
		uiState = UiState.INITIALISED;// All enum names are to start with an upper case letter and to be in CamelBack, , object, reference and variable names should be camel case
		control.setFixBookUi(this);// Change method name to meaningful and camel case
	}


	public void setState(UiState state) {// All enum names are to start with an upper case letter and to be in CamelBack, Change method name to meaningful and camel case
		this.uiState = state; // object, reference and variable names should be camel case
	}

	
	public void runFixBookUi() { // Change method name to meaningful and camel case
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (uiState) { // object, reference and variable names should be camel case
			
			case READY:
				String bookString = scannerInput("Scan Book (<enter> completes): "); // variable name Book_STR change to bookString, change input to meaningful name
				if (bookString.length() == 0) { // variable name Book_STR change to bookString
					fixBookControl.SCannING_COMplete();  // object name should be camel case
				}
				else {
					try {
						int Book_ID = Integer.valueOf(bookString).intValue(); // variable name Book_STR change to bookString
						fixBookControl.Book_scanned(Book_ID); // object name should be camel case
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String answer = scannerInput("Fix Book? (Y/N) : "); // variable name AnS change to answer, change input to meaningful name
				boolean isFixed = false; // variable name FiX change to isFixed
				if (answer.toUpperCase().equals("Y")) { // variable name AnS change to answer
					isFixed = true; // variable name FiX change to isFixed
				}
				fixBookControl.fixBook(isFixed); // object name should be camel case, Change method name FIX_Book to fixBook, variable name FiX change to isFixed
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + uiState);	// object, reference and variable names should be camel case		
			
			}		
		}
		
	}

	
	private String scannerInput(String prompt) { // change input to meaningful name
		System.out.print(prompt);
		return scannerInput.nextLine(); // change input to meaningful name
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
