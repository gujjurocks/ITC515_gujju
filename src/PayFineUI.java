import java.util.Scanner;



public class PayFineUi { // class name changed from PayFineUI to PayFineUi

	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // variable name changed from UI_STATE to UiState

	private PayFineControl control;
	private Scanner input;
	private UiState state; // variable name changed from UI_STATE to UiState

	
	public PayFineUi(PayFineControl control) { // class name changed from PayFineUI to PayFineUi
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED; // variable name changed from UI_STATE to UiState
		control.setUi(this); // variable name changed from setUI to setUi
	}
	
	
	public void setState(UiState state) { // variable name changed from UI_STATE to UiState
		this.state = state;
	}


	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String memberStr = input("Swipe member card (press <enter> to cancel): ");  // Changed variable name from memStr to memberStr
				if (memberStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberStr).intValue();
					control.cardSwiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;
				String amountStr = input("Enter amount (<Enter> cancels) : ");  // Changed variable name from amtStr to amountStr
				if (amountStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					amount = Double.valueOf(amountStr).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(amount);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void display(Object object) {
		output(object);
	}


}
// edited by Himalay Patel
// This file reviewed by Niyati Patel

