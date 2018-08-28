import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class library implements Serializable {
	
	private static final String library_file = "library.obj"; // Changed LIBRARY_FILE to library_file by Author Purva
	private static final int loan_limit = 2;// Changed LOAN_LIMIT to loan_limit by Author Purva
	private static final int loan_period = 2;	// Changed LOAN_PERIOD to loan_period by Author Purva
	private static final double fine_per_day = 1.0;	// Changed FINE_PER_DAY to fine_per_day by Author Purva
	private static final double max_fines_owned = 5.0;	// Changed MAX_FINES_OWED  to max_fines_owned  by Author Purva
	private static final double damage_fee = 2.0;	// Changed DAMAGE_FEE to damage_fee by Author Purva
	
	private static library self;
	private int bid;	// Changed BID to bid by Author Purva
	private int mid;	// Changed MID to mid by Author Purva
	private int lid;	// Changed LID to lid by Author Purva
	private Date loadDate;
	
	private Map<Integer, book> catalog;
	private Map<Integer, member> members;
	private Map<Integer, loan> loans;
	private Map<Integer, loan> currentLoans;
	private Map<Integer, book> damagedBooks;
	

	private library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bid = 1;	// Changed BID to bid by Author Purva
		mid = 1;	// Changed MID to mid by Author Purva	
		lid = 1;	// Changed LID to lid by Author Purva	
	}

	
	public static synchronized library INSTANCE() {		
		if (self == null) {
			Path path = Paths.get(library_file); // Changed LIBRARY_FILE to library_file by Author Purva			
			if (Files.exists(path)) {	
				try (ObjectInputStream lof = new ObjectInputStream(new FileInputStream(library_file));) {
			    
					self = (library) lof.readObject();
					Calendar.getInstance().setDate(self.loadDate);
					lof.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new library();
		}
		return self;
	}

	
	public static synchronized void SAVE() {
		if (self != null) {
			self.loadDate = Calendar.getInstance().Date();
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(library_file));) {
				lof.writeObject(self);
				lof.flush();
				lof.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int bookID() { //Method name change BookID to bookID by Reviewer Bharatkumar
		return bid;	// Changed BID to bid by Author Purva
	}
	
	
	public int memberID() {//Method name change MemberID to memberID by Reviewer Bharatkumar
		return mid;	// Changed MID to mid by Author Purva
	}
	
	
	private int nextBID() {
		return bid++;	// Changed BID to bid by Author Purva
	}

	
	private int nextMID() {
		return mid++; // Changed MID to mid by Author Purva
	}

	
	private int nextLID() {
		return lid++;	// Changed LID to lid by Author Purva	
	}

	
	public List<member> Members() {		
		return new ArrayList<member>(members.values()); 
	}


	public List<book> Books() {		
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoans.values());
	}


	public member add_mem(String lastName, String firstName, String email, int phoneNo) {	//Method name change Addmem to addmem by Reviewer Bharatkumar	
		member member = new member(lastName, firstName, email, phoneNo, nextMID());
		members.put(member.getId(), member);		
		return member;
	}

	
	public book add_book(String a, String t, String c) {	//Method name change Add_book to add_book by Reviewer Bharatkumar	
		book b = new book(a, t, c, nextBID());
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public member getMember(int memberId) {
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int loanLimit() {
		return loan_limit; // Changed LOAN_LIMIT to loan_limit by Author Purva
	}

	
	public boolean memberCanBorrow(member member) {		
		if (member.getNumberOfCurrentLoans() == loan_limit ) // Changed LOAN_LIMIT to loan_limit by Author Purva 
			return false;
				
		if (member.getFinesOwed() >= max_fines_owned ) // Changed MAX_FINES_OWED  to max_fines_owned  by Author Purva
			return false;
				
		for (loan loan : member.getLoans()) 
			if (loan.isOverDue()) 
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {		
		return loan_limit - member.getNumberOfCurrentLoans(); // Changed LOAN_LIMIT to loan_limit by Author Purva 
	}

	
	public loan issueLoan(book book, member member) {
		Date dueDate = Calendar.getInstance().getDueDate(loan_period); // Changed LOAN_PERIOD to loan_period by Author Purva
		loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverDue * fine_per_day; // Changed FINE_PER_DAY to fine_per_day by Author Purva
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(damage_fee); // Changed DAMAGE_FEE to damage_fee by Author Purva
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
// Reviwed by Reviewer Bharatkumar aand made required few changes
