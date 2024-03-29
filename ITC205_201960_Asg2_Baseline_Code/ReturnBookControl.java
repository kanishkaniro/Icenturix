public class ReturnBookControl {

	private ReturnBookUI returnBookUI; // Ui changed to returnBookUI
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state; // sTaTe changed to state
	
	private Library library; // variable names are to start with a lowercase letter and to be in camelBack
	private loan CurrENT_loan;
	

	public ReturnBookControl() {
		this.library = library.INSTANCE(); // lIbRaRy changed to library
		state = CONTROL_STATE.INITIALISED; // sTaTe changed to state
	}
	
	
	public void Set_UI(ReturnBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) { // sTaTe changed to state
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.returnBookUI = ui;
		ui.Set_State(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY; // sTaTe changed to state	
	}


	public void Book_scanned(int Book_ID) {
		if (!state.equals(CONTROL_STATE.READY)) { // sTaTe changed to state
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book CUR_book = lIbRaRy.Book(Book_ID); // lIbRaRy changed to library
		
		if (CUR_book == null) {
			returnBookUI.display("Invalid Book Id");
			return;
		}
		if (!CUR_book.On_loan()) {
			returnBookUI.display("Book has not been borrowed"); // Ui changed to returnBookUI
			return;
		}		
		CurrENT_loan = lIbRaRy.LOAN_BY_BOOK_ID(Book_ID);	
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.OVer_Due()) {
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(CurrENT_loan);
		}
		returnBookUI.display("Inspecting"); // Ui changed to returnBookUI
		returnBookUI.display(CUR_book.toString()); // Ui changed to returnBookUI
		returnBookUI.display(CurrENT_loan.toString()); // Ui changed to returnBookUI
		
		if (CurrENT_loan.OVer_Due()) {
			Ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		}
		returnBookUI.Set_State(ReturnBookUI.UI_STATE.INSPECTING); // Ui changed to returnBookUI
		sTaTe = CONTROL_STATE.INSPECTING;		
	}


	public void Scanning_Complete() {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		returnBookUI.Set_State(ReturnBookUI.UI_STATE.COMPLETED);	// Ui changed to returnBookUI	
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!sTaTe.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.Discharge_loan(CurrENT_loan, isDamaged); // lIbRaRy changed to library
		CurrENT_loan = null;
		returnBookUI.Set_State(ReturnBookUI.UI_STATE.READY); // Ui changed to returnBookUI
		sTaTe = CONTROL_STATE.READY;				
	}


}
