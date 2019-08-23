import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class member implements Serializable {

	private String lastName; // LN change as lastName according to standard
	private String firstName; // FN change as firstName according to standard
	private String email; // EM change as email according to standard
	private int phoneNo; // PN change as phoneNo according to standard
	private int id; // ID change as id according to standard
	private double fines; // FINES change as fines according to standard
	
	private Map<Integer, loan> LNS;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName; // LN change as lastName according to standard
		this.firstName = firstName; // FN change as firstName according to standard
		this.email = email; // EM change as email according to standard
		this.phoneNo = phoneNo; // PM change as phoneNo according to standard
		this.id = id; // ID change as id according to standard
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(ID).append("\n")
		  .append("  Name:  ").append(LN).append(", ").append(FN).append("\n")
		  .append("  Email: ").append(EM).append("\n")
		  .append("  Phone: ").append(PN)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FINES))
		  .append("\n");
		
		for (loan LoAn : LNS.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getId() { // GeT_ID change as getId according to standard
		return ID;
	}

	
	public List<loan> getLoans() { // method name GeT_LoAnS change as getLoans according to standard
		return new ArrayList<loan>(LNS.values());
	}

	
	public int numberOfCurrentLoans() { // method name Number_Of_Current_Loans change as numberOfCurrentLoans according to standard
		return LNS.size();
	}

	
	public double finesOwEd() { // method name Fines_OwEd change as finesOwEd according to standard
		return fines; // FINES change as fines according to standard
	}

	
	public void Take_Out_Loan(loan loan) { 
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String Get_LastName() {
		return lastName; // LN change as lastName according to standard
	}

	
	public String Get_FirstName() {
		return firstName; // FN change as firstName according to standard
	}


	public void Add_Fine(double fine) {
		FINES += fine; // FINES change as fines according to standard
	}
	
	public double payFine(double amount) { // AmOuNt change as amount according to standard and method name Pay_Fine change as payFine according to standard
		if (amount < 0) { // AmOuNt change as amount according to standard
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) { // AmOuNt change as amount according to standard and FINES change as fines according to standard
			change = amount - fines; // AmOuNt change as amount according to standard and FINES change as fines according to standard
			fines = 0; // FINES change as fines according to standard
		}
		else {
			fines -= amount; // AmOuNt change as amount according to standard and FINES change as fines according to standard
		}
		return change;
	}


	public void dIsChArGeLoAn(loan LoAn) {
		if (LNS.containsKey(LoAn.ID())) {
			LNS.remove(LoAn.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
