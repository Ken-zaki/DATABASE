public class Display {
    public void getDisplayMenu(){
        System.out.println("+-------------------------------------------------------+");
        System.out.println("| Welcome to the Local Resource Finder (Batangas Area)! |");
        System.out.println("+-------------------------------------------------------+\n");
        System.out.println("1.  Login");
        System.out.println("2.  Register");
        System.out.println("3.  Exit");
        System.out.print("Enter your choice: ");
    }

    public void getDisplayUser(User user){
        System.out.println("+-------------------------------------------------------+");
        System.out.println("| Welcome to the Local Resource Finder (Batangas Area)! |");
        System.out.println("+-------------------------------------------------------+\n");
        System.out.println("1.  View Available Resources");
        System.out.println("2.  Logout");
        System.out.print("Enter your choice: ");
    }

    public void getDisplayAdmin(Admin admin){
        System.out.println("+-------------------------------------------------------+");
        System.out.println("| Welcome to the Local Resource Finder (Batangas Area)! |");
        System.out.println("+-------------------------------------------------------+\n");
        System.out.println("1.  Add Resource");
        System.out.println("2.  Remove Resource");
        System.out.println("3.  View Resources");
        System.out.println("4.  Logout");
        System.out.print("Enter your choice: ");
    }

    public void viewResources(){
        System.out.println("+-------------------------------------------------------+");
        System.out.println("| Select Category Resources (Batangas Area)             |");
        System.out.println("+-------------------------------------------------------+\n");
        System.out.println("1.  Healthcare");
        System.out.println("2.  Educational");        
        System.out.println("3.  Food");        
        System.out.println("4.  Transportation");       
        System.out.println("Enter your choice: ");
    }
}
