package PracExer3;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

class Employee {
	String name;
	String id;
	int employmentpassnum;
	
	public Employee(String name, String id, int employmentpassnum) {
		this.name = name;
		this.id = id;
		this.employmentpassnum = employmentpassnum;
	}
	
	public void showDetails() {
		System.out.println(name + ", " + id + ", " + employmentpassnum);
	}
}

class Travelorders extends Employee{
	String destination;
	String ordernumber;
	
	public Travelorders(String name, String id, int employmentpassnum, String destination, String ordernumber) {
		super (name, id, employmentpassnum);
		this.destination = destination;
		this.ordernumber = ordernumber;
	}
	
	public void showOrderdetails() {
		System.out.println("   - Travel Order: " + destination + ", Order Number: " + ordernumber);
	}
	
	public void showEmployeeHeader() {
		System.out.println(name + ", " + "Employemnt Id: " + id + ", " + "Pass Number: EP00" + employmentpassnum + "0");
	}
	
	public String getordernumber() {
		return ordernumber;
	}
	
	public static void Greet() {
		System.out.println("Welcome to TravelFlow Management System");
	}
}


public class PracExer3_MangundayaoClaribelle {
	public static void main(String[] args) {
		ArrayList <Travelorders> travelList = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		String clock = sdf.format(new Date());
		LocalDate mydate = LocalDate.now();
		
		Travelorders.Greet();
		System.out.println("Date: " + mydate + " | " + "Time: " + clock);
		
		System.out.println("Please enter the number of employees you wish to manage: ");
		int num = input.nextInt();
		input.nextLine();
		
		
		
		for(int i = 1; i <= num; i++) {
			String name;
			while (true) {
				System.out.println("Employee" + i);
				System.out.println("Enter employee name (First Last): ");
				name = input.nextLine().trim();
				
				boolean myspace = false;
				boolean allletter = true;
				
				for(int k = 0; k< name.length(); k++) {
					char c = name.charAt(k);
					
					if (c == ' ') {
						myspace = true;
					} else if (!Character.isAlphabetic(c)) {
						allletter = false;
					}
				}
				
				if(myspace && allletter) {
					break;
				}
				else {
					System.out.println("re-enter the information");
				}
				
			}
			
			String id;
			while (true) {
				System.out.println("Enter employee Id( 6 digits, cannot start with 0): ");
				id = input.nextLine();
				
				boolean is6digit = true;
				boolean isstartletter = true;
				
				if (id.length() != 6) {
					is6digit = false;
				}
				
				for (int x = 0; x < id.length(); x++) {
					char c = id.charAt(x);
					if (x == 0) {
						if (c == '0') {
							isstartletter = false;
						}
					}
				}
				
				if (is6digit && isstartletter) {
					break;
				} else {
					System.out.println("re-enter the information");
				}
			}
			
			int employmentpassnum = 10 +((i - 1) *2);
			
			  System.out.println();
	            System.out.println(
	                    "Employee Pass Number generated: EP"
	                    + String.format("%05d", employmentpassnum));
	            System.out.println();

	           
			
	            int hm;

	            while(true) {

	                System.out.print("How many travel orders does "
	                        + name + " have? ");

	                hm = input.nextInt();
	                input.nextLine();

	                if(hm >= 1 && hm <= 3) {
	                    break;
	                }

	                System.out.println(
	                        "Each employee can only have up to 3 travel orders.");
	            }
	            
			
			
	            String destination;

	            for (int n = 1; n <= hm; n++) {

	                System.out.println();
	                System.out.println("Travel Order " + n + " for " + name);

	                while (true) {
					System.out.println("Enter destination (Only alphabetic characters): ");
					destination = input.nextLine();
					
					boolean alphabetonly = true;
					boolean atleast3Char = true;
					
					for(int p = 0; p < destination.length(); p++) {
						char ch = destination.charAt(p);
						
						if (!Character.isLetter(ch) && ch != ' ') {
							alphabetonly = false;
					    }
					}
					
					if (alphabetonly && destination.length() > 3) {
						break;
					} else {
						System.out.println("re-enter the information");
					} 
				}
				String ordnum;
				while (true) {
					System.out.println("Enter travel order number (TO + 4 digit unique number): ");
					ordnum = input.nextLine();
					
					boolean checker = true;
					for (Travelorders list : travelList) {
						if (ordnum.length() != 6 || !ordnum.startsWith("TO") || ordnum.equals(list.getordernumber()) ) {
							System.out.println("re-enter the information");
							checker = false;
						}
					}
					
					if (checker) {
						break;
					}
				}
				Travelorders TO = new Travelorders(name, id, i, destination, ordnum);
				travelList.add(TO);
			}
		}
		
		System.out.println("Summary of Employee and Travel Orders:");
		String lastName = "";
        for (Travelorders o : travelList) {
            if (!o.name.equals(lastName)) {
                lastName = o.name;
                System.out.println();
                o.showEmployeeHeader();
            }
            o.showOrderdetails();
        }
		
	}
}