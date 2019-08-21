//Author: Kanishka, Mediator: Yoshan, Reviewer: Lahiru
import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI borrowBookUI; // UI change to camel case
	
	private Library library; // variable name should be camel case and class name first letter should be upper case
	private Member member; // variable name should be camel case and class name first letter should be upper case
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; // All enum names are to start with an upper case letter and to be in CamelBack. 
	private ControlState State; // CONTROL_STATE changed to ControlState
	
	private List<book> bookList; // PENDING changed to bookList - meaningful name
	private List<loan> loanList; // COMPLETED changed to loanList - meaningful name
	private Book book;  // BOOK should be lower case and class name should be Book
	
	
	public BorrowBookControl() {
		this.library = library.INSTANCE(); // variable name should be camel case and class name first letter should be upper case
		State = ControlState.INITIALISED; // All enum names are to start with an upper case letter and to be in CamelBack. 
	}
	

	public void setUI(BorrowBookUI borrowBookUI) { //variable name should be meaningful
		if (!State.equals(ControlState.INITIALISED)) // All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.borrowBookUI = borrowBookUI; // UI change to camel case //variable name should be meaningful
		borrowBookUI.Set_State(BorrowBookUI.UI_STATE.READY); //variable name should be meaningful
		State = ControlState.READY;	// All enum names are to start with an upper case letter and to be in CamelBack. 	
	}

		
	public void swiped(int memberId) { // variable name and method name should be camel case
		if (!State.equals(ControlState.READY)) // All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.member(memberId); // variable name should be camel case and class name first letter should be upper case
		if (member == null) {
			borrowBookUI.Display("Invalid memberId"); // UI change to meaningful name and camel case
			return;
		}
		if (library.MEMBER_CAN_BORROW(member)) { // variable name should be camel case and class name first letter should be upper case
			bookList = new ArrayList<>(); // PENDING changed to bookList - meaningful name
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.SCANNING); // UI change to camel case
			State = ControlState.SCANNING; } // All enum names are to start with an upper case letter and to be in CamelBack. 
		else 
		{
			borrowBookUI.Display("Member cannot borrow at this time"); // UI change to camel case
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }} // UI change to camel case
	
	
	public void scanned(int bookId) { // All method names are to start with a lowercase letter and to be in camelBack. 
		book = null;  // BOOK should be lower case
		if (!State.equals(ControlState.SCANNING)) { // All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.book(bookId); // variable name should be camel case and class name first letter should be upper case
		if (book == null) {  // BOOK should be lower case
			borrowBookUI.Display("Invalid bookId");// UI change to camel case
			return;
		}
		if (!book.AVAILABLE()) {  // BOOK should be lower case
			borrowBookUI.Display("Book cannot be borrowed");// UI change to camel case
			return;
		}
		bookList.add(book);  // PENDING changed to bookList - meaningful name  // BOOK should be lower case
		for (book B : bookList) {  // PENDING changed to bookList - meaningful name
			borrowBookUI.Display(B.toString());// UI change to camel case
		}
		if (library.Loans_Remaining_For_Member(member) - bookList.size() == 0) { // variable name should be camel case and class name first letter should be upper case
			borrowBookUI.Display("Loan limit reached"); // UI change to camel case
			complete(); // All method names are to start with a lower case letter and to be in camelBack. 
		}
	}
	
	
	public void complete() { // All method names are to start with a lower case letter and to be in camelBack. 
		if (bookList.size() == 0) { // PENDING changed to bookList - meaningful name
			cancel();
		}
		else {
			borrowBookUI.Display("\nFinal Borrowing List");
			for (book B : bookList) {  // PENDING changed to bookList - meaningful name
				borrowBookUI.Display(B.toString()); // UI change to camel case
			}
			loanList = new ArrayList<loan>();  // COMPLETED changed to loanList - meaningful name
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.FINALISING);// UI change to camel case
			State = ControlState.FINALISING; // All enum names are to start with an upper case letter and to be in CamelBack. 
		}
	}


	public void commitLoans() { // All method names are to start with a lower case letter and to be in camelBack. 
		if (!State.equals(ControlState.FINALISING)) { // All enum names are to start with an upper case letter and to be in CamelBack. 
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : bookList) { // PENDING changed to bookList - meaningful name
			Loan loan = library.ISSUE_LAON(B, member); // variable name should be camel case and class name first letter should be upper case, loan class name changed to Loan, LOAN object name changed to loan
			loanList.add(loan);	 // COMPLETED changed to loanList - meaningful name, LOAN object name changed to loan  		
		}
		borrowBookUI.Display("Completed Loan Slip");// UI change to camel case
		for (Loan loan : loanList) {  // COMPLETED changed to loanList - meaningful name, loan class name changed to Loan, LOAN object name changed to loan 
			borrowBookUI.Display(loan.toString());// UI change to camel case, loan class name changed to Loan, LOAN object name changed to loan 
		}
		borrowBookUI.Set_State(BorrowBookUI.UI_STATE.loanList);// UI change to camel case  // COMPLETED changed to loanList - meaningful name
		State = ControlState.COMPLETED;  // All enum names are to start with an upper case letter and to be in CamelBack. 
	}

	
	public void cancel() {
		borrowBookUI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);// UI change to camel case
		State = ControlState.CANCELLED; // All enum names are to start with an upper case letter and to be in CamelBack. 
	}
	
	
}
