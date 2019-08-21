import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// All enum names are to start with an upper case letter and to be in CamelBack. 

	private BorrowBookControl borrowBookControl; // All class object should be meaningful and camel case
	private Scanner input;
	private UiState uiState;// All enum names are to start with an upper case letter and to be in CamelBack. 

	
	public BorrowBookUI(BorrowBookControl control) {
		this.CONTROL = control;
		input = new Scanner(System.in);
		uiState = UiState.INITIALISED;// All enum names are to start with an upper case letter and to be in CamelBack. 
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void Set_State(UiState STATE) {// All enum names are to start with an upper case letter and to be in CamelBack. 
		this.uiState = STATE;// All enum names are to start with an upper case letter and to be in CamelBack. 
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (uiState) {	// All enum names are to start with an upper case letter and to be in CamelBack. 		
			
			case CANCELLED:
				output("Borrowing Cancelled");
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
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				CONTROL.cancel();
				break;
			
				
			case SCANNING:
				String Book_Str = input("Scan Book (<enter> completes): ");
				if (Book_Str.length() == 0) {
					CONTROL.Complete();
					break;
				}
				try {
					int BiD = Integer.valueOf(Book_Str).intValue();
					CONTROL.Scanned(BiD);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String Ans = input("Commit loans? (Y/N): ");
				if (Ans.toUpperCase().equals("N")) {
					CONTROL.cancel();
					
				} else {
					CONTROL.Commit_LOans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + uiState);// All enum names are to start with an upper case letter and to be in CamelBack. 			
			}
		}		
	}


	public void Display(Object object) {
		output(object);		
	}


}
