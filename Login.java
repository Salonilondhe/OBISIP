import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    private int id;
    private String loginId;
    private String password;

    public User(int id, String loginId, String password) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public static User getUser(int id, List<User> users) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}

class Reservation {
    private int id;
    private String name;
    private String date;
    private int numberOfGuests;
    private String trainNumber;
    private String classType;
    private String from;
    private String to;

    public Reservation(int id, String name, String date, int numberOfGuests, String trainNumber, String classType, String from, String to) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getClassType() {
        return classType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

class ReservationSystem {
    private List<Reservation> reservations = new ArrayList<>();
    private int nextId = 1;

    public Reservation makeReservation(String name, String date, int numberOfGuests, String trainNumber, String classType, String from, String to) {
        Reservation reservation = new Reservation(nextId++, name, date, numberOfGuests, trainNumber, classType, from, to);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation getReservationById(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public boolean cancelReservation(int id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservations.remove(reservation);
            return true;
        }
        return false;
    }
}

class ReservationSystemUI {
    private ReservationSystem reservationSystem = new ReservationSystem();
    private List<User> users = new ArrayList<>();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Add users
        users.add(new User(1, "user1", "password1"));
        users.add(new User(2, "user2", "password2"));

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Login ID: ");
                    String loginId = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    User user = null;
                    for (User u : users) {
                        if (u.getLoginId().equals(loginId) && u.getPassword().equals(password)) {
                            user = u;
                            break;
                        }
                    }

                    if (user != null) {
                        System.out.println("Login successful");
                        mainMenu(scanner);
                    } else {
                        System.out.println("Invalid login ID or password");
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println();
        }
    }

    private void mainMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Number of guests: ");
                    int numberOfGuests = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Train number: ");
                    String trainNumber = scanner.nextLine();
                    System.out.print("Class type: ");
                    String classType = scanner.nextLine();
                    System.out.print("From: ");
                    String from = scanner.nextLine();
                    System.out.print("To: ");
                    String to = scanner.nextLine();

                    Reservation reservation = reservationSystem.makeReservation(name, date, numberOfGuests, trainNumber, classType, from, to);
                    System.out.println("Reservation made with ID " + reservation.getId());
                    break;
                case 2:
                    System.out.println("Reservations:");
                    for (Reservation r : reservationSystem.getReservations()) {
                        System.out.println(r.getId() + " - " + r.getName() + " - " + r.getDate() + " - " + r.getNumberOfGuests() + " - " + r.getTrainNumber() + " - " + r.getClassType() + " - " + r.getFrom() + " - " + r.getTo());
                    }
                    break;
                case 3:
                    System.out.print("Reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (reservationSystem.cancelReservation(id)) {
                        System.out.println("Reservation canceled");
                    } else {
                        System.out.println("Reservation not found");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        ReservationSystemUI obj = new ReservationSystemUI();
        obj.start();
    }
}