import java.io.Serializable;


@SuppressWarnings("serial")
public class book implements Serializable {
	
	private String title;// TITLE change as title   
	private String author; // AUTHOR change as author
	private String callNo; // CALLNO change as callNo
	private int ID; // ID change as id
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };// STATE change as State
	private STATE state; // State change as state
	
	
	public book(String author, String title, String callNo, int id) {
		this.author = author;// AUTHOR change as author
		this.title = title; // TITLE change as title 
		this.callNo = callNo;  // CALLNO change as callNo
		this.id = id;  // ID change as id
		this.state = STATE.AVAILABLE; // State change as state
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")// ID change as id
		  .append("  Title:  ").append(title).append("\n") // TITLE change as title
		  .append("  Author: ").append(author).append("\n") // AUTHOR change as author
		  .append("  CallNo: ").append(callNo).append("\n") // CALLNO change as callNo
		  .append("  State:  ").append(State); // State change as state
		
		return sb.toString();
	}

	public Integer id() { // ID() change as id() according to standard
		return ID;
	}

	public String title() { // TITLE() change as title() according to standard
		return TITLE;
	}


	
	public boolean available() { // AVAILABLE() change as available() according to standard
		return State == STATE.AVAILABLE;
	}

	
	public boolean onLoan() { // On_loan() change as onLoan() according to standard
		return State == STATE.ON_LOAN;
	}

	
	public boolean isDamaged() { // IS_Damaged() change as isDamaged() according to standard
		return State == STATE.DAMAGED;
	}

	
	public void Borrow() { // Borrow() change as borrow() according to standard
		if (State.equals(STATE.AVAILABLE)) {
			State = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (State.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				State = STATE.DAMAGED;
			}
			else {
				State = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void Repair() {
		if (State.equals(STATE.DAMAGED)) {
			State = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
