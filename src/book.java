import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {  // Change class name from book to Book
	private String title;  // Change variable name from T to title
	private String author;  //Change variable name from A to author
	private String callNo;  // Change variable name from C to callNo
	private int id;  // Change variable name from ID to id
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public Book(String author, String title, String callNo, int id) {    // Change constructor name from book to Book
		this.author = author;  // Change variable name from A to author
		this.title = title;  // Change variable name from T to title
		this.callNo = callNo;  // Change variable name from C to callNo
		this.id = id; // Change variable name from ID to id
		this.state = STATE.AVAILABLE;
	}
	
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();  // Change variable name from sb to stringBuilder
		stringBuilder.append("Book: ").append(id).append("\n")   // Change variable name from ID to id
		  .append("  Title:  ").append(title).append("\n")  // Change variable name from T to title
		  .append("  Author: ").append(author).append("\n")  // Change variable name from A to author
		  .append("  CallNo: ").append(callNo).append("\n")  // Change variable name from C to callNo
		  .append("  State:  ").append(state);
		
		return stringBuilder.toString();
	}
	

	public Integer id() {    // Change method name from ID to id(CamelBack)
		return id;  // Change from ID to id
	}

	
	public String title() {     // Change method name from Title to title(CamelBack)
		return title;  // Change from TITLE to title
	}
	
	
	public boolean available() {    // Change method name from Available to Available(CamelBack)
		return state == STATE.AVAILABLE;
	}

	
	public boolean onLoan() {    // Change method name from On_loan to onLoan	
		return state == STATE.ON_LOAN;
	}
	
	
	public boolean damaged() {    // Change method name from Damaged to damaged
		return state == STATE.DAMAGED;
	}
	
	
	public void borrow() {    // Change method name from Borrow to borrow
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}
	
	
	public void return(boolean damaged) {     // Change variable name from DAMAGED to damaged and change method name from Return to return
		if (state.equals(STATE.ON_LOAN)) {
			if (damaged) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void repair() {    // Change method name from Repair to repair
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}
}

// Mediator role performed by Vismay