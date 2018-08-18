public class PayFineControl 
{	
	private PayFineUi ui;	// class name changed from PayFineUI to PayFineUi
	private enum CONTROL_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	private Library library;	// class name changed from library to Library
	private Member member;	// class name changed from member to Member


	public PayFineControl() 
	{
		this.library = library.instance();	// method name changed from INSTANCE to instance
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUi(PayFineUi ui)	// method name changed from setUI to setUi and class name from PayFineUI to PayFineUi
	{
		if (!state.equals(CONTROL_STATE.INITIALISED)) 
		{
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(PayFineUi.UI_STATE.READY); // class name changed from PayFineUI to PayFineUi
		state = CONTROL_STATE.READY;		
	}


	public void cardSwiped(int memberId) 
	{
		if (!state.equals(CONTROL_STATE.READY)) 
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
		ui.setState(PayFineUi.UI_STATE.PAYING);	// class name changed from PayFineUI to PayFineUi
		state = CONTROL_STATE.PAYING;
	}
	
	
	public void cancel() 
	{
		ui.setState(PayFineUi.UI_STATE.CANCELLED);	// class name changed from PayFineUI to PayFineUi
		state = CONTROL_STATE.CANCELLED;
	}


	public double payFine(double amount) 
	{
		if (!state.equals(CONTROL_STATE.PAYING)) 
		{
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		
		if (change > 0) 
		{
			ui.display(String.format("Change: $%.2f", change));
		}
		ui.display(member.toString());
		ui.setState(PayFineUi.UI_STATE.COMPLETED);	// class name changed from PayFineUI to PayFineUi
		state = CONTROL_STATE.COMPLETED;
		return change;
	}
}
