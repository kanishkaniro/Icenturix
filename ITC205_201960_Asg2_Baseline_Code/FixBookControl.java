public class FixBookControl {
	
	private FixBookUI UI;
	private enum ControlState { INITIALISED, READY, FIXING };// All enum names are to start with an upper case letter and to be in CamelBack. 
	private ControlState StAtE;// All enum names are to start with an upper case letter and to be in CamelBack. 
	
	private library LIB;
	private book Cur_Book;


	public FixBookControl() {
		this.LIB = LIB.INSTANCE();
		StAtE = ControlState.INITIALISED;// All enum names are to start with an upper case letter and to be in CamelBack. 
	}
	
	
	public void Set_Ui(FixBookUI ui) {
		if (!StAtE.equals(ControlState.INITIALISED)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.UI = ui;
		ui.Set_State(FixBookUI.UI_STATE.READY);
		StAtE = ControlState.READY;// All enum names are to start with an upper case letter and to be in CamelBack. 		
	}


	public void Book_scanned(int bookId) {
		if (!StAtE.equals(ControlState.READY)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		Cur_Book = LIB.Book(bookId);
		
		if (Cur_Book == null) {
			UI.display("Invalid bookId");
			return;
		}
		if (!Cur_Book.IS_Damaged()) {
			UI.display("Book has not been damaged");
			return;
		}
		UI.display(Cur_Book.toString());
		UI.Set_State(FixBookUI.UI_STATE.FIXING);
		StAtE = ControlState.FIXING;	// All enum names are to start with an upper case letter and to be in CamelBack. 	
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!StAtE.equals(ControlState.FIXING)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			LIB.Repair_BOOK(Cur_Book);
		}
		Cur_Book = null;
		UI.Set_State(FixBookUI.UI_STATE.READY);
		StAtE = ControlState.READY;// All enum names are to start with an upper case letter and to be in CamelBack. 		
	}

	
	public void SCannING_COMplete() {
		if (!StAtE.equals(ControlState.READY)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		UI.Set_State(FixBookUI.UI_STATE.COMPLETED);		
	}






}
