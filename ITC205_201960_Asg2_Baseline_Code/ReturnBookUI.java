import java.util.Scanner;


public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED }; 
	private ReturnBookControl CoNtRoL; // CoNtRoL variable name change as control variable name change according to standard
	private Scanner input;
	private UI_STATE state; // StATe variable name change as state variable name change according to standard

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control; // CoNtRoL change as control
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED; // StATe variable name change as state variable name change according to standard
		control.Set_UI(this);
	}


	public void RuN() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (StATe) { // StATe change as state
			
			case INITIALISED:
				break;
				
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					CoNtRoL.Scanning_Complete();
				}
				else {
					try {
						int Book_Id = Integer.valueOf(Book_STR).intValue();
						CoNtRoL.Book_scanned(Book_Id);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean Is_Damaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					Is_Damaged = true;
				}
				CoNtRoL.Discharge_loan(Is_Damaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
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
	
	public void Set_State(UI_STATE state) {
		this.StATe = state;
	}

	
}
