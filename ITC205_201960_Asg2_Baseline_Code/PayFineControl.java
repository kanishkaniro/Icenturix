public class PayFineControl {
	
	private PayFineUI Ui;
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // enum names are to start with an uppercase letter and to be in CamelBack. 
	private ControlState state; // CONTROL_STATE changed to ControlState  // variable names are to start with a lowercase letter and to be in camelBack // StAtE changed to state
	private Library library; // variable names are to start with a lowercase letter and to be in camelBack
	private Member member;  // MeMbEr changed to member. variable names are to start with a lowercase letter and to be in camelBack


	public PayFineControl() {
		this.library = library.INSTANCE(); // LiBrArY changed to library
		state = ControlState.INITIALISED; // CONTROL_STATE changed to ControlState  // StAtE changed to state
	}
	
	
	public void Set_UI(PayFineUI ui) {
		if (!state.equals(ControlState.INITIALISED)) { // CONTROL_STATE changed to ControlState  // StAtE changed to state
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = ui;
		ui.Set_State(PayFineUI.UI_STATE.READY);
		state = ControlState.READY; // StAtE changed to state		
	}


	public void Card_Swiped(int memberId) {
		if (!state.equals(ControlState.READY)) { // CONTROL_STATE changed to ControlState  // StAtE changed to state
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		MeMbEr = library.MEMBER(memberId); // LiBrArY changed to library
		
		if (MeMbEr == null) {
			Ui.DiSplAY("Invalid Member Id");
			return;
		}
		Ui.DiSplAY(MeMbEr.toString());
		Ui.Set_State(PayFineUI.UI_STATE.PAYING);
		state = ControlState.PAYING; // CONTROL_STATE changed to ControlState // StAtE changed to state
	}
	
	
	public void CaNcEl() {
		Ui.Set_State(PayFineUI.UI_STATE.CANCELLED);
		state = ControlState.CANCELLED; // CONTROL_STATE changed to ControlState  // StAtE changed to state
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!state.equals(ControlState.PAYING)) { // CONTROL_STATE changed to ControlState  // StAtE changed to state
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double ChAnGe = MeMbEr.Pay_Fine(AmOuNt);
		if (ChAnGe > 0) {
			Ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		}
		Ui.DiSplAY(MeMbEr.toString());
		Ui.Set_State(PayFineUI.UI_STATE.COMPLETED);
		state = ControlState.COMPLETED; // CONTROL_STATE changed to ControlState // StAtE changed to state
		return ChAnGe;
	}
	


}
