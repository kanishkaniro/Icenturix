import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI borrowBookUI; // UI change to camel case
	
	private Library library; // variable name should be camel case and class name first letter should be upper case
	private Member member; // variable name should be camel case and class name first letter should be upper case
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE State;
	
	private List<book> pending; // PENDING should be lower case
	private List<loan> completed; // COMPLETED should be lower case
	private Book book;  // BOOK should be lower case and class name should be Book
	
	
	public BorrowBookControl() {
		this.library = library.INSTANCE(); // variable name should be camel case and class name first letter should be upper case
		State = CONTROL_STATE.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!State.equals(CONTROL_STATE.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.borrowBookUI = ui; // UI change to camel case
		ui.Set_State(BorrowBookUI.UI_STATE.READY);
		State = CONTROL_STATE.READY;		
	}

		
	public void Swiped(int MEMMER_ID) {
		if (!State.equals(CONTROL_STATE.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		M = library.member(MEMMER_ID); // variable name should be camel case and class name first letter should be upper case
		if (M == null) {
			borrowBookUI.Display("Invalid memberId"); // UI change to camel case
			return;
		}
		if (library.MEMBER_CAN_BORROW(M)) { // variable name should be camel case and class name first letter should be upper case
			pending = new ArrayList<>(); // PENDING should be lower case
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.SCANNING); // UI change to camel case
			State = CONTROL_STATE.SCANNING; }
		else 
		{
			borrowBookUI.Display("Member cannot borrow at this time"); // UI change to camel case
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }} // UI change to camel case
	
	
	public void Scanned(int bookId) {
		book = null;  // BOOK should be lower case
		if (!State.equals(CONTROL_STATE.SCANNING)) {
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
		pending.add(book);  // PENDING should be lower case  // BOOK should be lower case
		for (book B : pending) {  // PENDING should be lower case
			borrowBookUI.Display(B.toString());// UI change to camel case
		}
		if (library.Loans_Remaining_For_Member(M) - pending.size() == 0) { // variable name should be camel case and class name first letter should be upper case
			borrowBookUI.Display("Loan limit reached"); // UI change to camel case
			Complete();
		}
	}
	
	
	public void Complete() {
		if (pending.size() == 0) { // PENDING should be lower case
			cancel();
		}
		else {
			borrowBookUI.Display("\nFinal Borrowing List");
			for (book B : pending) {  // PENDING should be lower case
				borrowBookUI.Display(B.toString()); // UI change to camel case
			}
			completed = new ArrayList<loan>();  // COMPLETED should be lower case
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.FINALISING);// UI change to camel case
			State = CONTROL_STATE.FINALISING;
		}
	}


	public void Commit_LOans() {
		if (!State.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : pending) { // PENDING should be lower case
			loan LOAN = library.ISSUE_LAON(B, M); // variable name should be camel case and class name first letter should be upper case
			completed.add(LOAN);	 // COMPLETED should be lower case		
		}
		borrowBookUI.Display("Completed Loan Slip");// UI change to camel case
		for (loan LOAN : completed) {  // COMPLETED should be lower case
			borrowBookUI.Display(LOAN.toString());// UI change to camel case
		}
		borrowBookUI.Set_State(BorrowBookUI.UI_STATE.completed);// UI change to camel case  // COMPLETED should be lower case
		State = CONTROL_STATE.completed;  // COMPLETED should be lower case
	}

	
	public void cancel() {
		borrowBookUI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);// UI change to camel case
		State = CONTROL_STATE.CANCELLED;
	}
	
	
}
