public class FixBookControl {
	
	private FixBookUI fixBookUi; // Change UI to fixBookUi
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
		this.fixBookUi = ui;  // Change UI to fixBookUi
		ui.Set_State(FixBookUI.UI_STATE.READY);
		StAtE = ControlState.READY;// All enum names are to start with an upper case letter and to be in CamelBack. 		
	}


	public void Book_scanned(int bookId) {
		if (!StAtE.equals(ControlState.READY)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		Cur_Book = LIB.Book(bookId);
		
		if (Cur_Book == null) {
			fixBookUi.display("Invalid bookId"); // Change UI to fixBookUi
			return;
		}
		if (!Cur_Book.IS_Damaged()) {
			fixBookUi.display("Book has not been damaged"); // Change UI to fixBookUi
			return;
		}
		fixBookUi.display(Cur_Book.toString()); // Change UI to fixBookUi
		fixBookUi.Set_State(FixBookUI.UI_STATE.FIXING); // Change UI to fixBookUi
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
		fixBookUi.Set_State(FixBookUI.UI_STATE.READY); // Change UI to fixBookUi
		StAtE = ControlState.READY;// All enum names are to start with an upper case letter and to be in CamelBack. 		
	}

	
	public void SCannING_COMplete() {
		if (!StAtE.equals(ControlState.READY)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.Set_State(FixBookUI.UI_STATE.COMPLETED);	 // Change UI to fixBookUi	
	}






}
