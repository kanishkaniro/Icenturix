import java.util.Scanner;


public class PayFineUI {


	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // enum names are to start with an uppercase letter and to be in CamelBack

	private PayFineControl payFineControl; // CoNtRoL changed to payFineControl
	private Scanner input;
	private UiState state; // enum names are to start with an uppercase letter and to be in CamelBack // StAtE changed to state

	
	public PayFineUI(PayFineControl control) {
		this.payFineControl = control; // CoNtRoL changed to payFineControl
		input = new Scanner(System.in);
		state = UiState.INITIALISED; // enum names are to start with an uppercase letter and to be in CamelBack // StAtE changed to state
		control.Set_UI(this);
	}
	
	
	public void Set_State(UiState state) { // UI_STATE changed to UiState 
		this.state = state; // StAtE changed to state
	}


	public void run() { // Method name RuN changed to run
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) { // StAtE changed to state
			
			case READY:
				String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
				if (Mem_Str.length() == 0) {
					CoNtRoL.CaNcEl();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(Mem_Str).intValue();
					payFineControl.Card_Swiped(Member_ID); // CoNtRoL changed to payFineControl
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0; // variable names are to start with a lowercase letter
				String Amt_Str = input("Enter amount (<Enter> cancels) : ");
				if (Amt_Str.length() == 0) {
					payFineControl.CaNcEl();  // CoNtRoL changed to payFineControl
					break;
				}
				try {
					amount = Double.valueOf(Amt_Str).doubleValue(); // variable names are to start with a lowercase letter
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				payFineControl.PaY_FiNe(amount); // CoNtRoL changed to payFineControl
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
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
			

	public void DiSplAY(Object object) { // Method name DiSplAY changed to display
		output(object);
	}


}
