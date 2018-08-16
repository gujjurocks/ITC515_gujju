import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner input;	// Change variable from IN to input(variable must start with Lower case )
	private static library library;	// Change variable from LIB to library(variable must start with Lower case )
	private static String menu; //Change variable from MENU to menu(variable must start with Lower case )
	private static Calendar calendar; //Change variable from CAL to calendar(variable must start with Lower case )
	private static SimpleDateFormat simpleDateFormat;// Change variable name from SDF to simpleDateFormat
	
	
	private static String Get_menu() {
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
			library = library.INSTANCE();
			calendar = Calendar.getInstance();
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Change variable name from SDF to simpleDateFormat
	
			for (member member : library.Members()) { //Change variable from m to member
				output(member);		
			}
			output(" ");
			for (book book : library.Books()) {	// Change variable name from b to book
				output(book);
			}
						
			menu = Get_menu();		// Change variable name from MENU to menu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + simpleDateFormat.format(calendar.Date()));// 
				String choice = input(MENU);	//Change variable name from c to choice
				
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
				
				library.SAVE();
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
		for (loan loan : library.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (book book : library.Books()) {
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (member member : library.Members()) {
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
			output(simpleDateFormat.format(calendar.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		book book = library.Add_book(author, title, callNo);
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			member member = library.Add_mem(lastName, firstName, email, phoneNo);
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
