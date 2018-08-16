import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class loan implements Serializable {
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int id; // Change variable name from ID to id
	private book book; // Change  variable name from B to book.
	private member member;	// Change variable name from M to member.
	private Date dueDate;	// Change variable name from D to dueDate
	private LOAN_STATE state;

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId;
		this.book = book;	// Change variable name from B to book.
		this.member = member;	//Change variable name from M to member
		this.dueDate = dueDate;	// Change variable name from D to dueDate
		this.state = LOAN_STATE.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(dueDate)) { // Change variable name from D to dueDate
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer getId() { 
		return id;	// change variable name from ID to id
	}


	public Date getDueDate() {
		return dueDate;	//// Change variable name from D to dueDate
	}
	
	
	public String toString() {
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("dd/MM/yyyy"); // Change from sdf to simpleDateFormate

		StringBuilder stringBuilder = new StringBuilder();	// Change variable name from sb to stringBuilder
		stringBuilder.append("Loan:  ").append(id).append("\n")// Change variable name from sb to stringBuilder and ID to id
		  .append("  Borrower ").append(member.getId()).append(" : ") // Change variable ma,e M to member
		  .append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n")
		  .append("  Book ").append(book.id()).append(" : " ) // Change varibale name from ID to id nad B to book
		  .append(B.Title()).append("\n")
		  .append("  DueDate: ").append(simpleDateFormate.format(D)).append("\n")	// Change from sdf to simpleDateFormate
		  .append("  State: ").append(state);		
		return stringBuilder.toString();	//Change variable name from sb to stringBuilder
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void Loan() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
