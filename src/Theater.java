import java.util.*;

class Movie {

    Scanner sc = new Scanner(System.in);
    static Movie movie[] = new Movie[20];
    String movie_name;
    double base_price;
    String time;
    boolean seat[][] = new boolean[10][10];
    static int movie_count = 0;

    static {
        // pre-defined data of movie
        movie[0] = new Movie("Pushpa 2", 200, "8:00");
        movie[1] = new Movie("Emergency", 200, "11:00");
        movie[2] = new Movie("Marco", 200, "10:00");
        movie[3] = new Movie("Avengers: Endgame", 200, "2:30");
        movie[4] = new Movie("Stree 2", 200, "6:00");
        movie_count = 5;

        // pre-defined data of seat allocation
        movie[0].seat[4][5] = true;
        movie[0].seat[1][9] = true;
        movie[0].seat[0][2] = true;
        movie[1].seat[1][2] = true;
        movie[1].seat[8][4] = true;
        movie[1].seat[9][9] = true;
        movie[2].seat[3][2] = true;
        movie[2].seat[3][9] = true;
        movie[2].seat[0][1] = true;
        movie[3].seat[8][1] = true;
        movie[3].seat[8][2] = true;
        movie[3].seat[8][3] = true;
        movie[4].seat[9][2] = true;
        movie[4].seat[9][3] = true;
        movie[4].seat[9][4] = true;
    }

    Movie() {
    }

    Movie(String movie_name, double movie_price, String time) {
        this.base_price = movie_price;
        this.movie_name = movie_name;
        this.time = time;
        // this.seat = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.seat[i][j] = false;
            }
        }
    }

    void showAllSeat() {
        System.out.println("_________________________________________");
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", (i + 1));
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", (i + 1));
            for (int j = 0; j < 10; j++) {
                System.out.print(seat[i][j] ? "❌ " : "〇 ");
            }
            System.out.println();
        }
    }

    void showAllMovieTicketSell() {
        if (Movie.movie_count == 0) {
            System.out.println("No movies available to display seats.");
            return;
        }

        for (int i = 0; i < Movie.movie_count; i++) {
            System.out.println(
                    "\nSeating arrangement for " + Movie.movie[i].movie_name + " (" + Movie.movie[i].time + "):");
            Movie.movie[i].showAllSeat();
        }
    }

    final void addMovie() {

        System.out.println("How many movie you want to add : ");
        int ask_movie_count = sc.nextInt();
        if (ask_movie_count + movie_count > movie.length) {
            System.out.println("Theater is full for adding new movie !");
            return;
        }
        for (int i = movie_count; i < movie_count + ask_movie_count; i++) {
            System.out.println("\nEnter Movie  name :");
            if (i == movie_count) {
                sc.nextLine();
            }
            String movie_name = sc.nextLine();
            System.out.println("Enter Movie price :");
            double movie_price = sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter Movie time (0:00-24:00):");
            String time = sc.nextLine();
            if(!timeValidation(time))
            {
                i--;
                continue;
            }
            else{
                movie[i] = new Movie(movie_name, movie_price, time);
            }
        }
        movie_count = movie_count + ask_movie_count;
    }

    void displayAllMovie() {
        for (int i = 0; i < movie_count; i++) {
            System.out.println("\nMovie Name              : " + movie[i].movie_name);
            System.out.println("Movie Ticket Price      : " + movie[i].base_price);
            System.out.println("Movie Time              : " + movie[i].time);
        }
    }

    void deleteMovie(String name) {
        if (movie_count == 0) {
            System.out.println("No movies available to delete.");
            return;
        }
        boolean found = false;
        for (int i = 0; i < movie_count; i++) {
            if (movie[i].movie_name.equalsIgnoreCase(name)) {
                found = true;
                for (int j = i; j < movie_count - 1; j++) {
                    movie[j] = movie[j + 1];
                }
                movie[movie_count - 1] = null;
                movie_count--;
                System.out.println("Movie '" + name + "' deleted successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Movie '" + name + "' not found.");
        }
    }

    void searchMovie(String name) {
        boolean flag = true;
        for (int i = 0; i < movie_count; i++) {
            if (movie[i].movie_name.equalsIgnoreCase(name)) {
                flag = false;
                System.out.println("\nMovie Founded : ");
                System.out.println("Movie name          : " + movie[i].movie_name);
                System.out.println("Movie ticket price  : " + movie[i].base_price);
                System.out.println("Movie time          : " + movie[i].time);
            }
        }

        if (flag) {
            System.out.println("No movie founded !");
        }
    }
    //9:15
    boolean timeValidation(String time) {
        boolean flag = false;
        if (time.length() == 4) {
            if (time.charAt(0) >= '0' && time.charAt(0) <= '9' && time.charAt(2) >= '0' && time.charAt(2) <= '6'
                    && time.charAt(3) >= '0' && time.charAt(3) <= '9' && time.charAt(1) == ':') {
                flag = true;
            } else {
                System.out.println("Wrong input");
                flag = false;
            }
        } else if (time.length() == 5 && time.charAt(2) == ':') {
            if (time.charAt(0) == '1') {
                if (time.charAt(1) >= '0' && time.charAt(1) <= '9') {
                    flag = true;
                } else {
                    System.out.println("Wrong input");
                    flag = false;
                }
            } else if (time.charAt(0) == '2') {
                if (time.charAt(1) >= '0' && time.charAt(1) <= '4') {
                    flag = true;
                } else {
                    System.out.println("Wrong input");
                    flag = false;
                }
            } else {
                System.out.println("Wrong input");
            }
            if(flag)
            {
                if(time.charAt(3) >= '0' && time.charAt(3)<='6' && time.charAt(4)>='0' && time.charAt(4)<='9')
                {
                    flag = true;
                }
                else{
                    flag = false;
                }
            }
        }
        else{
            flag = false;
            System.out.println("Wrong input");
        }
        return flag;
    }

}

class Employee {

    // add employee ID

    Scanner sc = new Scanner(System.in);
    String employee_name;
    int employee_id;
    String employee_post;
    double employee_salary;
    int employee_joiningYear;
    int exp;
    static Employee employee[] = new Employee[25];
    static int employee_count = 0;

    static {
        employee[0] = new Employee("Subhas", 1, "Manager", 35000.00, 2018);
        employee[1] = new Employee("Diya", 2, "Assistant Manager", 30000.00, 2020);
        employee[2] = new Employee("Sandeep", 3, "Cleaner", 12000.00, 2016);
        employee[3] = new Employee("Neha", 4, "Cleaner", 12000.00, 2021);
        employee[4] = new Employee("Maithili", 5, "Ticket Seller", 18000.00, 2021);
        employee[5] = new Employee("Kunal", 6, "Security Guard", 13000.00, 2020);
        employee_count = 6;
    }

    Employee() {
    }

    Employee(String name, int id, String post, double salary, int joiningYear) {
        this.employee_name = name;
        this.employee_id = id;
        this.employee_post = post;
        this.employee_salary = salary;
        this.employee_joiningYear = joiningYear;
        this.exp = 2025 - joiningYear;
    }

    void addEmployee() {

        System.out.println("How many employee you want to add : ");
        int new_employee = sc.nextInt();
        if (new_employee + employee_count > employee.length) {
            System.out.println("Staff space is full for adding new staff !");
            return;
        }
        for (int i = employee_count; i < employee_count + new_employee; i++) {
            int j = 1;
            System.out.println("\nEnter Employee " + j + " name :");
            sc.nextLine();
            String employee_name = sc.nextLine();
            System.out.println("Enter Employee " + j + " post :");
            String employee_post = sc.nextLine();
            System.out.println("Enter Employee " + j + " salary :");
            double salary = sc.nextDouble();
            System.out.println("Enter Employee " + j + " joining year :");
            int joiningYear = sc.nextInt();
            employee[i] = new Employee(employee_name, i + 1, employee_post, salary, joiningYear);
            j++;
        }
        employee_count = employee_count + new_employee;
    }

    void displayAllEmployeeDetails() {
        System.out.println("\nTotal number of Employee : " + employee_count);
        for (int i = 0; i < employee_count; i++) {
            System.out.println("\n---Employee " + (i + 1) + " Details---");
            employee[i].detailsOfEmployee();
        }
    }

    void searchEmployeeId(int id) {
        boolean flag = true;
        for (int i = 0; i < employee_count; i++) {
            if (employee[i].employee_id == id) {
                flag = false;
                employee[i].detailsOfEmployee();
                return;
            }
        }
        if (flag) {
            System.out.println("No employee founded !");
        }
    }

    void searchEmployeeName(String name) {
        boolean flag = true;
        for (int i = 0; i < employee_count; i++) {
            if (employee[i].employee_name.equalsIgnoreCase(name)) {
                flag = false;
                employee[i].detailsOfEmployee();
                return;
            }
        }
        if (flag) {
            System.out.println("No employee founded !");
        }
    }

    void detailsOfEmployee() {
        System.out.println("\nEmployee Name           : " + this.employee_name);
        System.out.println("Employee ID             : " + this.employee_id);
        System.out.println("Employee Post           : " + this.employee_post);
        System.out.println("Employee Salary         : " + this.employee_salary + " ₹");
        System.out.println("Employee Joining year   : " + this.employee_joiningYear);
        System.out.println("Employee Experience     : " + this.exp + " Years");
    }
}

class User extends Movie {
    User() {
    }

    void selectMovie() {
        // displayAllMovie();
        System.out.println("Enter movie name for ticket booking: ");
        String user_movie_choice = sc.nextLine().trim();

        Movie selected_movie = null;
        for (int i = 0; i < movie_count; i++) {
            if (movie[i].movie_name.equalsIgnoreCase(user_movie_choice)) {
                selected_movie = movie[i];
                break;
            }
        }

        if (selected_movie == null) {
            System.out.println("Movie not found!");
            return;
        }

        selected_movie.showAllSeat();

        System.out.println("How many tickets do you want to book? ");
        int ticket_count = sc.nextInt();
        System.out.println("Enter your name : ");
        sc.nextLine();
        String user_Name = sc.nextLine();

        for (int t = 0; t < ticket_count; t++) {
            System.out.println("\nBooking ticket " + (t + 1) + " of " + ticket_count);
            System.out.println("Enter row number (1-10): ");
            int row = sc.nextInt() - 1;
            System.out.println("Enter Column number (1-10): ");
            int seat_number = sc.nextInt() - 1;

            if (row >= 0 && row < 10 && seat_number >= 0 && seat_number < 10) {
                if (!selected_movie.seat[row][seat_number]) {
                    selected_movie.seat[row][seat_number] = true; // Mark the seat as booked
                    System.out.println("Seat booked successfully!");
                } else {
                    System.out.println("Seat is already booked! Please choose another seat.");
                    t--;
                }
            } else {
                System.out.println("Invalid seat selection! Please try again.");
                t--;
            }
        }
        price(ticket_count, selected_movie.base_price, user_Name);

        // Display updated seating arrangement
        System.out.println("\nUpdated seating arrangement:");
        selected_movie.showAllSeat();
    }

    void price(int ticket_count, double base_price, String user_Name) {
        double final_price = base_price * ticket_count * 1.28;
        System.out.println("\n  ---Bill---  ");
        System.out.println("Name                           :       " + user_Name);
        System.out.println("Ticket count                   :       " + ticket_count);
        System.out.println("Ticket base price(per ticket)  :       " + base_price);
        System.out.println("Total                          :       " + base_price * ticket_count);
        System.out.println("TAX                            :       " + base_price * ticket_count * 0.28);
        System.out.println("Final bill                     :       " + final_price);
    }
}

class Food {
    Scanner sc = new Scanner(System.in);
    static Food food[] = new Food[20];
    String food_name;
    double food_price;
    static int food_count = 0;

    static {
        // Pre-defined data of food items
        food[0] = new Food("Popcorn", 150.00);
        food[1] = new Food("Nachos", 200.00);
        food[2] = new Food("Pizza", 300.00);
        food[3] = new Food("Burger", 120.00);
        food[4] = new Food("Soft Drink", 80.00);
        food_count = 5;
    }

    Food() {
    }

    Food(String food_name, double food_price) {
        this.food_name = food_name;
        this.food_price = food_price;
    }

    void displayAllFood() {
        System.out.println("\n------------ Food Menu --------------");
        for (int i = 0; i < food_count; i++) {
            System.out.printf("\n%7d. %10s - ₹%5.2f", (i + 1), food[i].food_name, food[i].food_price);
        }
    }

    void addFood() {
        System.out.println("How many food items you want to add: ");
        int askFoodCount = sc.nextInt();
        sc.nextLine();
        if (askFoodCount + food_count > food.length) {
            System.out.println("Food menu is full for adding new items!");
            return;
        }
        for (int i = food_count; i < food_count + askFoodCount; i++) {
            System.out.println("\nEnter Food " + (i + 1) + " name: ");
            if (i != food_count) {
                sc.nextLine();
            }
            String food_name = sc.nextLine();
            System.out.println("Enter Food price: ");
            double food_price = sc.nextDouble();
            food[i] = new Food(food_name, food_price);
        }
        food_count = food_count + askFoodCount;
    }

    void updateFoodmenu() {
        displayAllFood();
        // sc.hasNextLine();
        System.out.println("\nEnter food name for update it");
        // sc.nextLine();
        String update = sc.nextLine().trim();
        if (update.equals("")) {
            System.out.println("There is a peoblem! please re-enter your input!");
            update = sc.nextLine().trim();
        }
        boolean flag = true;
        // using food_count for avoiding null error
        for (int i = 0; i < food_count; i++) {
            if (food[i].food_name.equalsIgnoreCase(update)) {
                System.out.println("Enter your choice");
                System.out.println("==========================");
                System.out.println("1. Update name");
                System.out.println("2. Update price");
                System.out.println("==========================");
                int updateChoice = sc.nextInt();
                switch (updateChoice) {
                    case 1:
                        System.out.println("Enter Name to edit");
                        sc.nextLine();
                        food[i].food_name = sc.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter price you want to update");
                        food[i].food_price = sc.nextDouble();
                        break;
                    default:
                        System.out.println("Invalid Input!!");
                        break;
                }
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("No food item found");
        }
    }

    void orderFood() {
        double total_food_price = 0;
        displayAllFood();
        System.out.println(
                "\nEnter the number of the food item you want to order (or " + (food_count + 1) + " to Make Bill): \n");
        while (true) {
            int food_choice = sc.nextInt() - 1;
            if (food_choice == food_count) {
                System.out.println("Finaizing your order...");
                break;
            }
            if (food_choice >= 0 && food_choice < food_count) {
                total_food_price += food[food_choice].food_price;
                System.out.println(food[food_choice].food_name + " added to your order.");
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        System.out.println("\n--- Final Food Bill ---");
        System.out.println("Total food bill: ₹" + total_food_price);
    }
}

class Theater {

    int movie_count = 0;
    Movie movie_Obj = new Movie();
    Employee employee_Obj = new Employee();
    User user_Obj = new User();
    Food food_obj = new Food();
    Scanner sc = new Scanner(System.in);

    void theaterDetails() {
        System.out.println("\n--- Theater Details ---");
        System.out.println("Theater Name      : EVOX - 𝔼𝕍𝕆lving e𝕏perience");
        System.out.println("Location          : Ahmedabad, India");
        System.out.println("Number of Screens : 5");
        System.out.println("Seating Capacity  : 100 seats per screen");
        System.out.println("Facilities        : Food Court, Parking, IMAX, Online Booking");
    }

    void adminMain() {

        int choice;
        do {
            System.out.print("\n\nEnter your choice: ");
            System.out.println("\n--- Theater Management System ---");
            System.out.println("1. Movie manage");
            System.out.println("2. Book Ticket");
            System.out.println("3. Food court manage");
            System.out.println("4. Display Theater Details");
            System.out.println("5. Employee manage");
            System.out.println("6. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("1. Add movie");
                    System.out.println("2. Show all movie");
                    System.out.println("3. Search movie");
                    System.out.println("4. delete movie");
                    System.out.println("5. Show all movie ticket booke");
                    int choice1 = sc.nextInt();

                    switch (choice1) {
                        case 1:
                            movie_Obj.addMovie();
                            break;
                        case 2:
                            movie_Obj.displayAllMovie();
                            break;
                        case 3:
                            System.out.println("Enter Movie name");
                            String movie_name_for_search = sc.nextLine();
                            if (movie_name_for_search.equals("")) ; {
                            System.out.println("There is a proble, please re-enter!");
                            movie_name_for_search = sc.nextLine();
                        }
                        movie_Obj.searchMovie(movie_name_for_search);
                        break;
                        case 4:
                            System.out.println("Enter the name of the movie to delete:");
                            sc.nextLine(); // Consume the newline character
                            String movie_name_to_delete = sc.nextLine();
                            movie_Obj.deleteMovie(movie_name_to_delete);
                            break;

                        case 5:
                            movie_Obj.showAllMovieTicketSell();
                            break;

                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("\nEnter your choice : ");
                        System.out.println("=======================================");
                        System.out.println("1. book ticket");
                        System.out.println("2. show all movie`s seats");
                        System.out.println("3. Exit");
                        System.out.println("=======================================");
                        int admin_ticket_choice = sc.nextInt();
                        if (admin_ticket_choice == 3) {
                            break;
                        }
                        switch (admin_ticket_choice) {
                            case 1:
                                user_Obj.selectMovie();
                                break;
                            case 2:
                                movie_Obj.showAllMovieTicketSell();
                                break;
                            default:
                                System.out.println("Invalid input!! please try again");
                                continue;
                        }
                        break;
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("\nEnter your choice");
                        System.out.println("=====================================");
                        System.out.println("1. Add food");
                        System.out.println("2. Display menu");
                        System.out.println("3. Update menu");
                        System.out.println("4. Exit");
                        System.out.println("=====================================");
                        int food_choice = sc.nextInt();
                        if (food_choice == 4) {
                            break;
                        }
                        switch (food_choice) {
                            case 1:
                                food_obj.addFood();
                                break;
                            case 2:
                                food_obj.displayAllFood();
                                break;
                            case 3:
                                food_obj.updateFoodmenu();
                                break;
                            default:
                                System.out.println("Invalid input!!!");
                                break;
                        }
                    }
                    break;
                case 4:
                    theaterDetails();
                    break;
                case 5:
                    System.out.println("\n1.Add Employee");
                    System.out.println("2.Show all Employee`s details");
                    System.out.println("3.Search employee by ID");
                    System.out.println("4.Search employee by Name");
                    int choice5 = sc.nextInt();
                    switch (choice5) {
                        case 1:
                            employee_Obj.addEmployee();
                            break;

                        case 2:
                            employee_Obj.displayAllEmployeeDetails();
                            break;

                        case 3:
                            System.out.println("Enter ID : ");
                            int id = sc.nextInt();
                            employee_Obj.searchEmployeeId(id);
                            break;

                        case 4:
                            System.out.println("Enter Name : ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            employee_Obj.searchEmployeeName(name);
                            break;
                        default:
                            break;
                    }
                    break;

                default:
                    break;
            }
        } while (choice != 6);
    }

    void userMain() {
        int choice;
        do {
            System.out.println("\nEnter your choice ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Movie list");
            System.out.println("2. Book tickets");
            System.out.println("3. Food court");
            System.out.println("4. Theatre details");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------------");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    user_Obj.displayAllMovie();
                    break;

                case 2:
                    user_Obj.selectMovie();
                    break;

                case 3:
                    food_obj.orderFood();
                    break;
                case 4:
                    theaterDetails();
                    break;
                default:
                    System.out.println("Invalid Input please try again!");
                    break;
            }
        } while (choice != 5);
    }
}

class LogIn {
    static Scanner sc = new Scanner(System.in);
    static final int password = 2604;
    static final String username = "Admin";
    static int attempt = 0;
    static LogIn l = new LogIn();
    static Theater t = new Theater();
    static {
    }

    public static void main(String args[]) {

        while (true) {

            if (attempt == 3) {
                break;
            }
            System.out.println("===================================================");
            System.out.println("               Theatre managment system               ");
            System.out.println("===================================================");
            System.out.println("1.Admin");
            System.out.println("2.User");
            System.out.println("3.Exit");
            int choice = sc.nextInt();
            if (choice == 3) {
                break;
            }
            switch (choice) {
                case 1:
                    l.verification();
                    break;
                case 2:
                    t.userMain();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("You enter wrong input !");
            }
        }
    }

    void verification() {

        while (true) {
            System.out.println("Enter Username : ");
            sc.nextLine();
            String login_username = sc.nextLine();
            System.out.println("Enter Password : ");
            int login_password = sc.nextInt();
            if (username.equals(login_username) && login_password == password) {
                t.adminMain();
            } else {
                System.out.println("Wrong inputs");
                attempt++;
            }
            break;
        }
    }
}