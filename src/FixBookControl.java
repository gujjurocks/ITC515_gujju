public class FixBookControl {
	
	private FixBookUI ui;
	private Enum CONTROL_STATE { INITIALISED, READY, FIXING }; // Class name changed enum to Enum by Reviewer Bharatkumar
	private CONTROL_STATE state;
	
	private Library library; // Changed Library to library by Reviewer Bharatkumar
	private Book currentBook; // Changed book to Book by Reviewer Bharatkumar


	public fixBookControl() { // Method name changed FixBookControl to fixBookControl by Reviewer Bharatkumar
		this.library = library.instance(); // Method name changed INSTANCE to instance by Reviewer Bharatkumar
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(FixBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.book(bookId); // Method name changed Book to book by Reviewer Bharatkumar
		
		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!currentBook.damaged()) {// Method name changed Damaged to damaged by Reviewer Bharatkumar
			ui.display("\"Book has not been damaged");
			return;
		}
		ui.display(currentBook.toString());
		ui.setState(FixBookUI.UI_STATE.FIXING);
		state = CONTROL_STATE.FIXING;		
	}


	public void fixBook(boolean fix) {
		if (!state.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		ui.setState(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

	
	public void scanningComplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUI.UI_STATE.COMPLETED);		
	}






}
//Reviwed by the Reviewer Bharatkumar and made required changes 
