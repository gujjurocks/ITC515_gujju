import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner input;	// Change variable from IN to input(variable must start with Lower case )
	private static Library library;	// Change variable from LIB to library(variable must start with Lower case ) Change Class name from library to Library
	private static String menu; //Change variable from MENU to menu(variable must start with Lower case )
	private static Calendar calendar; //Change variable from CAL to calendar(variable must start with Lower case )
	private static SimpleDateFormat simpleDateFormat;// Change variable name from SDF to simpleDateFormat
	
	
	private static String getMenu() {	// Change method name from Get_menu to getMenu
		StringBuilder stringBuilder = new StringBuilder();// Change variable name from sb to stringBuilder
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")	
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")	
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return stringBuilder.toString();// Change variable name from sb to stringBuilder
	}


	public static void main(String[] args) {		
		try {			
			input = new Scanner(System.in);	//Change variable name from IN to input
			library = Library.instance();// Change class name from library to Library and method from INSTANCE to instance
			calendar = Calendar.getInstance();
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Change variable name from SDF to simpleDateFormat
	
			for (Member member : library.members()) { //Change variable from m to member,LIB to library and class name from member to Member
				output(member);		
			}
			output(" ");
			for (Book book : library.books()) {	// Change variable name from b to book,LIB to library and class name from book to Book,method from Books to book
				output(book);
			}
						
			menu = getMenu();		// Change variable name from MENU to menu and method from Get_menu to getMenu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + simpleDateFormat.format(calendar.date()));//  Change method name from Date to date
				String choice = input(menu);	//Change variable name from c to choice
				
				switch (choice.toUpperCase()) {	//Change variable name from c to choice
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.save();// Change class name from library to Library and method from SAVE to save
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void payFine() {
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		for (Loan loan : library.CurrentLoans()) {	// Change class name From loan to Loan and variable name from LIB to library
			output(loan + "\n");
		}		
	}


	private static void listBooks() {
		output("");
		for (Book book : library.Books()) {	// Change class name From book to Book and variable name from LIB to library
			output(book + "\n");
		}		
	}


	private static void listMembers() {
		output("");
		for (Member member : library.Members()) { // Change class name From member to Member
			output(member + "\n");
		}		
	}


	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calendar.incrementDate(days);
			library.checkCurrentLoans();
			output(simpleDateFormat.format(calendar.date()));// Change method name from Date to date 
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		Book book = library.addBook(author, title, callNo); // Change class name From book to Book and Add_book to addBook
		output("\n" + book + "\n");	
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			Member member = library.addMember(lastName, firstName, email, phoneNo);	// Change class name From member to Member and method from Add_mem to addMember
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}	
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}
	
	
	private static void output(Object object) {
		System.out.println(object);
	}	
}

// This file has been reviewed by reviewer Vismay with no changes
// this file was monitered by himalay as medatior with no conflit.
