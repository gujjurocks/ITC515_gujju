public class PayFineControl 
{	
	private PayFineUi ui;	// class name changed from PayFineUI to PayFineUi
	private enum controlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };	// variable name changed from CONTROL_STATE to controlState
	private CONTROL_STATE state;	// reference name changed from CONTROL_STATE to controlState
	private Library library;	// class name changed from library to Library
	private Member member;	// class name changed from member to Member


	public PayFineControl() 
	{
		this.library = library.instance();	// method name changed from INSTANCE to instance
		state = controlState.INITIALISED;	// variable name changed from CONTROL_STATE to controlState
	}
	
	
	public void setUi(PayFineUi ui)	// method name changed from setUI to setUi and class name from PayFineUI to PayFineUi
	{
		if (!state.equals(controlState.INITIALISED))	// variable name changed from CONTROL_STATE to controlState
		{
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(PayFineUi.uiState.READY); // class name changed from PayFineUI to PayFineUi and variable name changed from UI_STATE to uiState
		state = controlState.READY;	// variable name changed from CONTROL_STATE to controlState	
	}


	public void cardSwiped(int memberId) 
	{
		if (!state.equals(controlState.READY))	// variable name changed from CONTROL_STATE to controlState
		{
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) 
		{
			ui.display("Invalid Member Id");
			return;
		}
		ui.display(member.toString());
		ui.setState(PayFineUi.uiState.PAYING);	// class name changed from PayFineUI to PayFineUi and UI_STATE to uiState
		state = controlState.PAYING;	// variable name changed from CONTROL_STATE to controlState
	}
	
	
	public void cancel() 
	{
		ui.setState(PayFineUi.uiState.CANCELLED);	// class name changed from PayFineUI to PayFineUi and UI_STATE to uiState
		state = controlState.CANCELLED;	// variable name changed from CONTROL_STATE to controlState
	}


	public double payFine(double amount) 
	{
		if (!state.equals(controlState.PAYING))	// variable name changed from CONTROL_STATE to controlState 
		{
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		
		if (change > 0) 
		{
			ui.display(String.format("Change: $%.2f", change));
		}
		ui.display(member.toString());
		ui.setState(PayFineUi.uiState.COMPLETED);	// class name changed from PayFineUI to PayFineUi and UI_STATE to uiState
		state = controlState.COMPLETED;	// variable name changed from CONTROL_STATE to controlState
		return change;
	}
}

// edited by author Vismay
// As being a mediator i have done my woek (Mediator Bharatkumar)
