public class FixBookControl {
	
	private FixBookUI ui;
	private enum CONTROL_STATE { INITIALISED, READY, FIXING }; // Changed ENUM to enum by Author Purva
	private CONTROL_STATE state;
	
	private Library library; // Changed Library to library by Author Purva
	private Book currentBook; // Changed book to Book by Author Purva


	public fixBookControl() { // Method name changed FixBookControl to fixBookControl by Author Purva
		this.library = library.instance(); // Method name changed INSTANCE to instance by Author Purva
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
