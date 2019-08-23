//Author: Kanishka, Mediator: Yoshan, Reviewer: Lahiru
public class FixBookControl {
	
	private FixBookUI fixBookUi; // Change UI to fixBookUi
	private enum ControlState { INITIALISED, READY, FIXING };// All enum names are to start with an upper case letter and to be in CamelBack. 
	private ControlState controlState;// All enum names are to start with an upper case letter and to be in CamelBack. 
	
	private Library library; // library class name should be Library, LIB object name should be library
	private Book book; // book class name should be Book, Cur_Book object name should be meaningful and camel case


	public FixBookControl() {
		this.library = library.INSTANCE(); // LIB object name should be library
		controlState = ControlState.INITIALISED;// All enum names are to start with an upper case letter and to be in CamelBack. 
	}
	
	
	public void setFixBookUi(FixBookUI bookUi) { // Change method name to meaningful and camel case, change ui object name to bookUi
		if (!controlState.equals(ControlState.INITIALISED)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUi = bookUi;  // Change UI to fixBookUi, change ui object name to bookUi
		bookUi.setState(FixBookUI.UiState.READY);// change ui object name to bookUi, change Set_State method name to setState, All enum names are to start with an upper case letter and to be in CamelBack. 
		controlState = ControlState.READY;// All enum names are to start with an upper case letter and to be in CamelBack. 		
	}


	public void bookScanned(int bookId) { // Change method name to meaningful and camel case
		if (!controlState.equals(ControlState.READY)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		book = library.Book(bookId); // LIB object name should be library,Cur_Book object name should be meaningful and camel case
		
		if (book == null) { // Cur_Book object name should be meaningful and camel case
			fixBookUi.display("Invalid bookId"); // Change UI to fixBookUi
			return;
		}
		if (!book.isDamage()) {//Cur_Book object name should be meaningful and camel case, Change IS_Damaged method to isDamage
			fixBookUi.display("Book has not been damaged"); // Change UI to fixBookUi
			return;
		}
		fixBookUi.display(book.toString()); // Change UI to fixBookUi, Cur_Book object name should be meaningful and camel case
		fixBookUi.setState(FixBookUI.UiState.FIXING); // Change UI to fixBookUi, change Set_State method name to setState, All enum names are to start with an upper case letter and to be in CamelBack. 
		controlState = ControlState.FIXING;	// All enum names are to start with an upper case letter and to be in CamelBack. 	
	}


	public void fixBook(boolean isFixed) { // Change method name to meaningful and camel case
		if (!controlState.equals(ControlState.FIXING)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (isFixed) { // Change variable to meaningful and camel case
			library.repairBook(book); // LIB object name should be library, Cur_Book object name should be meaningful and camel case, change Repair_BOOK method name to repairBook
		}
		book = null;//Cur_Book object name should be meaningful and camel case
		fixBookUi.setState(FixBookUI.UiState.READY); // Change UI to fixBookUi, change Set_State method name to setState, All enum names are to start with an upper case letter and to be in CamelBack. 
		controlState = ControlState.READY;// All enum names are to start with an upper case letter and to be in CamelBack. 		
	}

	
	public void completeScanning() { // Change method name to meaningful and camel case
		if (!controlState.equals(ControlState.READY)) {// All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUi.setState(FixBookUI.UiState.COMPLETED);	 // Change UI to fixBookUi, change Set_State method name to setState, All enum names are to start with an upper case letter and to be in CamelBack. 	
	}






}
