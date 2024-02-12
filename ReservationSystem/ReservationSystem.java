import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

class User {
    private String username;
    private String password;
    public User(String username, String password) {
        this.username=username;
        this.password= password;    
    }
    public String getUsername() {
        return username;
    }
    public String getpassword() {
        return password;
    }
}   
class Reservation {
    private String pnr;
    private String username;
    private String trainNumber;
    private String classtype;
    private String journeyDate;
    private String source;
    private String destination;

    public Reservation(String pnr, String username, String trainNumber,String classtype,String journeyDate,String source, String destination ) {
        this.pnr=pnr;
        this.username=username;
        this.trainNumber=trainNumber;
        this.classtype=classtype;
        this.journeyDate=journeyDate;
        this.source=source;
        this.destination=destination;
    }
    public String getpnr() {
        return pnr;
    }
    public String getusername() {
        return username;
    }
    public String gettrainNumber() {
        return trainNumber;
    }
    public String getclasstype() {
        return classtype;
    }
    public String getjourneyDate() {
        return journeyDate;
    }
    public String getsource() {
        return source;
    }   
    public String getdestination() {
        return destination;
    }
}       


public class ReservationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int pnrCounter = 1;
      
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                register(scanner);
                break;
                case 2:
                login(scanner);
                break;
                case 3:
                System.out.println("Exiting the system.Goodby!");
                System.exit(0);
                default:
                System.out.println("Invalid choice. please try again.");   

            }
        }
    } 
    private static void register(Scanner scanner) {
        System.out.println("Enter a new username: ");
        String username  = scanner.nextLine();
        
        if (users.containsKey(username)) {
            System.err.println("Username already exists . Choose a disfferent one: ");
            return;
        }
        System.out.println("Enter a password: ");
        String password = scanner.nextLine();
          User user= new User(username , password);
          users.put(username, user);
          System.out.println("Registration Succesful!");

        } 
    private static void login(Scanner scanner){
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password :");
        String password = scanner.nextLine();

        User user = users.get(username);
        if(user != null && user.getpassword().equals(password)) {
            System.out.println("Login succesfuly!");
             while(true) {
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel Reservation ");
                System.out.println("3. Logout");
                System.out.println("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice)
                {
                    case 1: 
                    makeReservation(scanner, username);
                    break;
                    case 2:
                    cancelReservation(scanner, username);
                    break;
                    case 3:
                    System.out.println("Logging out.");
                    return;
                    default:
                    System.out.println("Invalid choice.please try again.");
                }
            }
        }  
        else {
            System.out.println("Login failed. Invalid username or password");
        }      
    }  
    private static  void makeReservation(Scanner scanner , String username) {
        System.out.println("Enter train number: ");
        String trainnumber = scanner.nextLine();
        System.out.println("Enter class type: (1/2/General)");
        String classtype= scanner.nextLine();
        System.out.println("Enter journey date: (DD/MM/YY)");
        String journeyDate = scanner.nextLine();
        System.out.println("Enter Leaving From: ");
        String source = scanner.nextLine();
        System.out.println("Enter Going To: ");
        String destination = scanner.nextLine();
        
        String pnr = "PNR"+trainnumber +" "+pnrCounter++;
        Reservation reservation = new Reservation(pnr, username, trainnumber, classtype, journeyDate, source, destination);
        reservations.add(reservation);

        System.out.println("Reservation succesful!");
        System.out.println("PNR:" +pnr);
    }
    private static void cancelReservation(Scanner scanner, String username) {
        System.out.println("Enter PNR number to cancel:");
        String pnr = scanner.nextLine();
        
        for(Reservation reservation : reservations) {
            if(reservation.getusername().equals(username) && reservation.getpnr().equals(pnr)) {
                reservations.remove(reservation);
                System.err.println("Reservation canceled succesfully!");
                return;
            }
        }System.out.println("Reservation not found or you do not have permission to cancel.");
    }            
}

