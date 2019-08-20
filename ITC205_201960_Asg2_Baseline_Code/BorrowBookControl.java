import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI borrowBookUI; // UI change to camel case
	
	private Library library; // variable name should be camel case and class name first letter should be upper case
	private Member member; // variable name should be camel case and class name first letter should be upper case
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE State;
	
	private List<book> PENDING;
	private List<loan> COMPLETED;
	private book BOOK;
	
	
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
			PENDING = new ArrayList<>();
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.SCANNING); // UI change to camel case
			State = CONTROL_STATE.SCANNING; }
		else 
		{
			borrowBookUI.Display("Member cannot borrow at this time"); // UI change to camel case
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED); }} // UI change to camel case
	
	
	public void Scanned(int bookId) {
		BOOK = null;
		if (!State.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		BOOK = library.Book(bookId); // variable name should be camel case and class name first letter should be upper case
		if (BOOK == null) {
			borrowBookUI.Display("Invalid bookId");// UI change to camel case
			return;
		}
		if (!BOOK.AVAILABLE()) {
			borrowBookUI.Display("Book cannot be borrowed");// UI change to camel case
			return;
		}
		PENDING.add(BOOK);
		for (book B : PENDING) {
			borrowBookUI.Display(B.toString());// UI change to camel case
		}
		if (library.Loans_Remaining_For_Member(M) - PENDING.size() == 0) { // variable name should be camel case and class name first letter should be upper case
			borrowBookUI.Display("Loan limit reached"); // UI change to camel case
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			borrowBookUI.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				borrowBookUI.Display(B.toString()); // UI change to camel case
			}
			COMPLETED = new ArrayList<loan>();
			borrowBookUI.Set_State(BorrowBookUI.UI_STATE.FINALISING);// UI change to camel case
			State = CONTROL_STATE.FINALISING;
		}
	}


	public void Commit_LOans() {
		if (!State.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			loan LOAN = library.ISSUE_LAON(B, M); // variable name should be camel case and class name first letter should be upper case
			COMPLETED.add(LOAN);			
		}
		borrowBookUI.Display("Completed Loan Slip");// UI change to camel case
		for (loan LOAN : COMPLETED) {
			borrowBookUI.Display(LOAN.toString());// UI change to camel case
		}
		borrowBookUI.Set_State(BorrowBookUI.UI_STATE.COMPLETED);// UI change to camel case
		State = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		borrowBookUI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);// UI change to camel case
		State = CONTROL_STATE.CANCELLED;
	}
	
	
}
