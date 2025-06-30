package studentmanager;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class StudentSystem_update {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> stu = new ArrayList<>();
		Student s1 = new Student("459608", "Kai Yang", 30, "Chemnitz");
        Student s2 = new Student("459609", "Xiaohui Du", 34, "Inner Mongolia");
        Student s3 = new Student("459610", "Yadong Xu", 34, "Wuhan");
        Student s4 = new Student("459611", "Xu Zhang", 30, "Chemnitz");
		stu.add(s1);
		stu.add(s2);
		stu.add(s3);
		stu.add(s4);
		
		ArrayList<User> user = new ArrayList<>();
		
		boolean flag = false;
		while(true) {
			Usermenu.showup();			
			int key = 0;			
			if(!sc.hasNextInt()) {
				System.out.println("Invalid input, exiting program.");
				sc.next();
				continue;//jump to next loop
			}
			key = sc.nextInt();
			if (key < 1 || key > 3) {
				System.out.println("Please enter a number between 1 and 3!");
		        continue;
		    }
			
			switch (key) {
			case 1: {
				if (login(user, sc)) {
					flag = true;
				} else {
					System.out.println("Login failed, please try again!");
				}
				break;
			}
			
			case 2:{
				String acc = isAccount(user, sc);
				String psw = pswConfirm(user, sc);
				String idNummber = idNummber(user, sc);
				String phoneNummber = phoneNummber(user, sc);
				User u = new User(acc,psw,idNummber,phoneNummber);
				user.add(u);	
				   System.out.println("Registration successful, welcome to the student management system (redirecting to main menu)");
				flag = false;	//Back to menu, only success could exit while-loop
				break;
			}
			
			case 3:{
				resetPsw(user, sc);
				flag = false;	//Back to menu, only success could exit while-loop
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + key);
			}
			//After the break <switch>, flag is checked and the while statement is exited only when login is successful.
			if(flag) {
				break;
			}
			
		}
		
		while(true){
			
			Menu.showup();
			int key = 0;
			if(!sc.hasNextInt()) {
				 System.out.println("Invalid input, exiting program.");
				sc.next();
				continue;
			}
			key = sc.nextInt();
			if (key < 1 || key > 5) {
				System.out.println("Please enter a number between 1 and 5!");
		        continue;
		    }
			//key 5 to exit
			if(key == 5) {
				break;
			}
			
			switch (key) {
			
			case 1:{
				addStudent(stu,sc);
				showInfo(stu);
				break;
			}
			
			case 2:{
				deleteStudent(stu, sc);
				showInfo(stu);
				break;
			}
			
			case 3:{
				correctStudent(stu, sc);
				showInfo(stu);
				break;
			}
			
			case 4:{
				searchStudent(stu, sc);
				break;
			}
			
			default:
				  throw new IllegalArgumentException("Invalid input: " + key);
			
			}
		}
		
		sc.close();//finish input
	}
	
//注册要求	
	
	//Username unique + legal verification
	public static String isAccount(ArrayList<User> user,Scanner sc) {
		while(true) {
			 System.out.println("Enter account name:");
			String acc = sc.next();
			char[] arr = acc.toCharArray();
			boolean hasnum = false;
			boolean hasalph = false;
			boolean format = false;
			if(arr.length<=15 &&arr.length>=3) {
				for (char c : arr) {
					//number check
					if( c>='0' && c<='9') {
						hasnum = true;
					}
					//letter check
					if(c>='a' && c<='z' || c>='A' && c<='Z') {
						hasalph = true;
					}					
				}
				//at least one letter and number
				if(hasnum && hasalph) {
					format = true;
				}
				else {
					System.out.println("Account format error! Must include at least one letter and one number.");
				}
			}
			else {
				System.out.println("Account name length error!");
				continue;
			}
			//length,format both is ok, check the uniqueness
			if(format) {
				for (User u : user) {
					if(u.getAccount().equals(acc)) {
						System.out.println("Account already exists");
						continue;	//exist,re-enter
					}
				}
				return acc;	//Unique and legal, ensure that the "false" condition does not escape the while loop, return acc
				
			}		
		}
	}
	
	//Password double check
	public static String pswConfirm(ArrayList<User> user,Scanner sc) {
		while(true) {
			System.out.println("Enter password:");
			String psw1 = sc.next();
			System.out.println("Re-enter password:");
			String psw2 = sc.next();
			if(psw1.equals(psw2)) {
				return psw1;
			}
			else {
				System.out.println("Passwords do not match!");
				continue;
			}
		}
		
	}
	
	//legal ID number
	public static String idNummber(ArrayList<User> user,Scanner sc) {
		while(true) {
			System.out.println("Enter ID number:");
			String n = sc.next();
			char[] arr = n.toCharArray();
			boolean f17 = true;
			boolean l1 = false;
			if(n.length() != 18) {
				System.out.println("Length error, please re-enter!");
				continue;
			}
			else if(n.charAt(0) == '0'){
				System.out.println("ID cannot start with 0!");
				continue;
			}
			else {
				for (int i = 0; i < arr.length-1; i++) {
					if(arr[i] < '0' || arr[i] > '9') {
						f17 = false;
						break;	//只要有非数字就f17为false,跳出循环
					}
				}
				if(((n.charAt(17) == 'x')||(n.charAt(17) == 'X'))||(n.charAt(17)>='0' && n.charAt(17)<='9')) {
					l1 = true;
				}
				else {
					System.out.println("Last character must be a digit or 'x'/'X'!");
					continue;
				}
				if(f17 && l1) {
					return n;
				}
				else {
					System.out.println("Invalid ID format, please try again.");
					continue;
				}
			}
		}
			
	}
	
	//legal Phone number
	public static String phoneNummber(ArrayList<User> user,Scanner sc) {
		while(true) {
			System.out.println("Please enter your phone number:");
			String p = sc.next();
			char[] arr = p.toCharArray();
			boolean allDigits = true;
			if(p.length() != 11) {
				System.out.println("Length error, please re-enter!");
				continue;
			}
			if(p.charAt(0) == '0'){
				System.out.println("Phone number cannot start with 0!");
				continue;
			}
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] < '0' || arr[i] > '9') {
					allDigits = false;
					break;	//只要有非数字就allDigits为false,跳出循环
				}
			}
			if(allDigits) {
				return p;
			}
			else {
				System.out.println("Invalid phone number format, please re-enter.");
				continue;
			}
			
		}
			
	}
	
	//login check
	public static boolean login(ArrayList<User> user,Scanner sc) {
		//3times try
		for(int z = 3; z > 0; z--) {
			System.out.println("Enter account:");
			String acc = sc.next();
			System.out.println("Enter password:");
			String psw = sc.next();
			String code = creatCode();
			System.out.println("Verification code: " + code);
			System.out.println("Please enter the code below:");
			String inputCode = sc.next();
			boolean a = false;	//account
			boolean p = false;
			if(!inputCode.equals(code)) {
				System.out.println("Incorrect verification code! You have " + (z - 1) + " attempt(s) left.");
				continue;
			}
			for (int i = 0; i <user.size(); i++) {
				User u = user.get(i);
				if(u.getAccount().equals(acc)) {
					a = true;
					if(u.getPassword().equals(psw)) {
						p = true;
						break;
					}
					
				}	
			}
			if(!a) {
				System.out.println("Username not registered, please sign up first.");
				return false;
			}
			else if(a&&p) {
				return true;	//check successful
			}
			else {
				System.out.println("Incorrect password! You have " + (z - 1) + " attempt(s) left.");
				continue;
			}
		}
		return false;

	}
	
	//Verification Code
	public static String creatCode() {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		char[] arr = new char[5];
		for(int i = 0; i<5;i++) {
			boolean isUpper = r.nextBoolean();
			char letter = (char)(r.nextInt(26) + (isUpper ? 'A' : 'a'));
			arr[i] = letter;
		}
		int num = r.nextInt(10);
		int index = r.nextInt(5);
		arr[index] = (char)(num + '0');
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		String code = sb.toString();
		return code;
	}
	
	//forgot password?
	public static ArrayList<User> resetPsw(ArrayList<User> user,Scanner sc) {
		System.out.println("Enter account:");
		String acc = sc.next();
		boolean checkID = false;//ID号
		boolean checkPN = false;//手机号
		for (int i=user.size()-1; i>= 0;i--) {
			User u = user.get(i);
			if(!u.getAccount().equals(acc)) {
				continue;	
			}
			else {
				String id = idNummber(user,sc);
				String pn = phoneNummber(user, sc);
				if(id.equals(u.getIdnummber())) {
					checkID = true;
				}
				if(pn.equals(u.getPhonenummber())) {
					checkPN = true;
				}
				if(checkID && checkPN) {
					System.out.println("Account info matched, please enter new password:");
					String newpsw = sc.next();
					user.get(i).setPassword(newpsw);
					return user;
				}

			}		
		}
		return user;
	}
	public static ArrayList<Student> addStudent(ArrayList<Student> stu,Scanner sc){
		System.out.println("Enter new student ID:");
		String id = sc.next();
		int index = idExist(stu, id);
		if(index == -1) {
			sc.nextLine();//eat the enter
			System.out.println("Enter student name:");
			String name = sc.nextLine();
			int age = 0;
			while(true) {
				System.out.println("Enter student age:");	
				if(sc.hasNextInt()){
					age = sc.nextInt();
					break;
				}
				else {
					System.out.println("Invalid input, please try again.");
					sc.next();
				}
			}
			sc.nextLine();//eat the enter
			System.out.println("Enter home address (province/city):");
			String adress = sc.next();
			
			Student ns = new Student(id, name, age, adress);
			stu.add(ns);
			System.out.println("Successfully added. Returning to main menu.");
		}
		else {
			System.out.println("This ID already exists. Returning to main menu.");	
		}

		return stu;
	}
	
	public static ArrayList<Student> deleteStudent(ArrayList<Student> stu,Scanner sc){
		System.out.println("Enter ID of student to delete:");
		String id = sc.next();
		int index = idExist(stu, id);
		if(index != -1) {
			stu.remove(index);
		}
		else {
			System.out.println("ID not found. Returning to main menu.");
		}
		
		return stu;
		
	}
	
	public static ArrayList<Student> correctStudent(ArrayList<Student> stu,Scanner sc){
		System.out.println("Enter ID of student to update:");
		String id = sc.next();
		sc.nextLine();//eat the enter
		int index = idExist(stu, id);
			if(index != -1) {
				Student s = stu.get(index);
				System.out.println(s.getId() + "     " + s.getName() + "     " + s.getAge() + "     " + s.getAdress());
				System.out.println("Enter updated information:");
				System.out.println("New name:");
				String name = sc.nextLine();
				stu.get(index).setName(name);
				System.out.println("New age:");
				int age = sc.nextInt();
				stu.get(index).setAge(age);
				sc.nextLine();
				System.out.println("New address:");
				String adress = sc.next();
				stu.get(index).setAdress(adress);
				
				System.out.println("Update successful. Returning to main menu.");
				return stu;
			}

		System.out.println("Student ID not found.");
		return stu;
	}
	
	public static ArrayList<Student> searchStudent(ArrayList<Student> stu,Scanner sc){
		System.out.println("Enter ID to search:");
		String id = sc.next();
		int index = idExist(stu, id);
		
			if(index != -1) {
				Student s = stu.get(index);
				System.out.println("Search result:");
				System.out.printf("%-10s %-12s %-8s %-11s\n","ID", "姓名","年龄","家庭住址");
				System.out.printf("%-10s %-12s %-6d %-10s\n", s.getId(), s.getName(), s.getAge(), s.getAdress());
			}
			else {
				System.out.println("ID not found. Returning to main menu.");
			}

		return stu;
	}
	
	public static int idExist(ArrayList<Student> stu, String id) {
	    for (int i = 0; i < stu.size(); i++) {
	        if (stu.get(i).getId().equals(id)) {
	            return i;
	        }
	    }
	    return -1; // not found
	}
	
	public static void showInfo(ArrayList<Student> stu) {
		System.out.printf("%-10s %-12s %-8s %-11s\n", "ID", "Name", "Age", "Address");
		for (Student s : stu) {
			System.out.printf("%-10s %-12s %-6d %-10s\n", s.getId(), s.getName(), s.getAge(), s.getAdress());

		}
	}
}
