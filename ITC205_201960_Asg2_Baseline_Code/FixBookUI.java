import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };// All enum names are to start with an upper case letter and to be in CamelBack.

	private FixBookControl CoNtRoL;
	private Scanner input;
	private UiState uiState;// All enum names are to start with an upper case letter and to be in CamelBack, object, reference and variable names should be camel case

	
	public FixBookUI(FixBookControl control) {
		this.CoNtRoL = control;
		input = new Scanner(System.in);
		uiState = UiState.INITIALISED;// All enum names are to start with an upper case letter and to be in CamelBack, , object, reference and variable names should be camel case
		control.Set_Ui(this);
	}


	public void Set_State(UiState state) {// All enum names are to start with an upper case letter and to be in CamelBack.
		this.uiState = state; // object, reference and variable names should be camel case
	}

	
	public void RuN() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (uiState) { // object, reference and variable names should be camel case
			
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					CoNtRoL.SCannING_COMplete();
				}
				else {
					try {
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						CoNtRoL.Book_scanned(Book_ID);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) {
					FiX = true;
				}
				CoNtRoL.FIX_Book(FiX);
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

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
