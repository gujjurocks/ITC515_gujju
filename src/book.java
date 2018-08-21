import java.io.Serializable;


@SuppressWarnings("serial")
public class book implements Serializable {
	
	private String title;  // Change variable name from T to title
	private String author;  //Change variable name from A to author
	private String callNo;  //Change variable name from C to callNo
	private int id;  //Change variable name from ID to id
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public book(String author, String title, String callNo, int id) {
		this.author = author;  // Change variable name from A to author
		this.title = title;  // Change variable name from T to title
		this.callNo = callNo;  // Change variable name from C to callNo
		this.id = id; // Change variable name from ID to id
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(ID).append("\n")
		  .append("  Title:  ").append(T).append("\n")
		  .append("  Author: ").append(A).append("\n")
		  .append("  CallNo: ").append(C).append("\n")
		  .append("  State:  ").append(state);
		
		return sb.toString();
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
