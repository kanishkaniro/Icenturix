import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // class name is to start with an uppercase letter 
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; // enum names are to start with an uppercase letter and to be in CamelBack. 
	
	private int loanId;  // variable name ID changed to loanId
	private Book book;   // variable names must meaningful
	private Member member; // variable names must meaningful
	private Date date;   // variable names must meaningful
	private LoanState state; // Declare variables of the enum type LoanState. enum names are to start with an uppercase letter and to be in CamelBack

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.loanId = loanId;   // variable name ID changed to loanId
		this.book = book; 		// variable names must meaningful
		this.member = member;   // variable names must meaningful
		this.date = dueDate;    // variable names must meaningful
		this.state = LoanState.CURRENT; // Assign values into enum variables.enum names are to start with an uppercase letter and to be in CamelBack
	}

	
	public void checkOverDue() {
		if (state == LoanState.CURRENT &&
			Calendar.INSTANCE().Date().after(D)) {
			this.state = LoanState.OVER_DUE; // enum names are to start with an uppercase letter and to be in CamelBack
		}
	}

	
	public boolean OVer_Due() {
		return state == LoanState.OVER_DUE; // enum names are to start with an uppercase letter and to be in CamelBack
	}

	
	public Integer loanId() {
		return loanId;  // variable name ID changed to loanId
	}


	public Date Get_Due_Date() {
		return D;
	}
	
	
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); // names are to be meaningful. sdb changed to simpleDateFormat

		StringBuilder stringBuilder = new StringBuilder(); // names are to be meaningful. sb changed to stringBuilder
		stringBuilder.append("Loan:  ").append(ID).append("\n") // names are to be meaningful. sb changed to stringBuilder
					 .append("  Borrower ").append(M.GeT_ID()).append(" : ")
					 .append(M.Get_LastName()).append(", ").append(M.Get_FirstName()).append("\n")
					 .append("  Book ").append(B.ID()).append(" : " )
					 .append(B.TITLE()).append("\n")
					 .append("  DueDate: ").append(simpleDateFormat.format(D)).append("\n") // names are to be meaningful. sdb changed to simpleDateFormat
					 .append("  State: ").append(state);		
		return stringBuilder.toString(); // names are to be meaningful. sb changed to stringBuilder
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void disCharge() { // Changed method name
		state = LoanState.DISCHARGED; // enum names are to start with an uppercase letter and to be in CamelBack. 
	}

}
