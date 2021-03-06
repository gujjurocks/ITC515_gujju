import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {	//Change class name from loan to Loan (CamelBack)
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int id; // Change variable name from ID to id
	private Book book; // Change  variable name from B to book.
	private Member member;	// Change variable name from M to member.
	private Date dueDate;	// Change variable name from D to dueDate
	private LOAN_STATE loanState;

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId;
		this.book = book;	// Change variable name from B to book.
		this.member = member;	//Change variable name from M to member
		this.dueDate = dueDate;	// Change variable name from D to dueDate
		this.loanState = LOAN_STATE.CURRENT; //Change variable name state to loanState
	}

	
	public void checkOverDue() {
		if (loanState == LOAN_STATE.CURRENT &&	////Change variable name state to loanState
			Calendar.getInstance().Date().after(dueDate)) { // Change variable name from D to dueDate
			this.loanState = LOAN_STATE.OVER_DUE;			//Change variable name state to loanState
		}
	}

	
	public boolean isOverDue() {	//reverted the changes made by author from isNoOverDue to isOverDue, as no need of changing method name from original
		return loanState == LOAN_STATE.OVER_DUE;	////Change variable name state to loanState
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
		  .append("  DueDate: ").append(simpleDateFormate.format(dueDate)).append("\n")	// Change from sdf to simpleDateFormate and D to dueDate
		  .append("  State: ").append(loanState);		
		return stringBuilder.toString();	//Change variable name from sb to stringBuilder
	}


	public member Member() {
		return member;
	}


	public book Book() {
		return book;
	}


	public void Loan() {
		loanState = LOAN_STATE.DISCHARGED;		
	}

}

// This file has been reviewed by reviewer Vismay
// this file was monitered by himalay as medatior with no conflit.
