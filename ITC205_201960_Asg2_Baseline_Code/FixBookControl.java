public class FixBookControl {
	
	private FixBookUI fixBookUi; // Change UI to fixBookUi
	private enum ControlState { INITIALISED, READY, FIXING };// All enum names are to start with an upper case letter and to be in CamelBack. 
	private ControlState StAtE;// All enum names are to start with an upper case letter and to be in CamelBack. 
	
	private Library library; // library class name should be Library, LIB object name should be library
	private Book book; // book class name should be Book, Cur_Book object name should be meaningful and camel case


	public FixBookControl() {
		this.library = library.INSTANCE(); // LIB object name should be library
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
		book = library.Book(bookId); // LIB object name should be library,Cur_Book object name should be meaningful and camel case
		
		if (book == null) { // Cur_Book object name should be meaningful and camel case
			fixBookUi.display("Invalid bookId"); // Change UI to fixBookUi
			return;
		}
		if (!book.IS_Damaged()) {//Cur_Book object name should be meaningful and camel case
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
			library.Repair_BOOK(Cur_Book); // LIB object name should be library
		}
		book = null;//Cur_Book object name should be meaningful and camel case
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
