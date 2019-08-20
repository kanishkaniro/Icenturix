import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // class name is to start with an uppercase letter 
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int ID;
	private book B;
	private member M;
	private Date D;
	private LOAN_STATE state;

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.ID = loanId;
		this.B = book;
		this.M = member;
		this.D = dueDate;
		this.state = LOAN_STATE.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.INSTANCE().Date().after(D)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean OVer_Due() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer ID() {
		return ID;
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


	public void DiScHaRgE() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
