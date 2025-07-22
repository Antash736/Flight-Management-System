package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

 abstract class User{
	private String UserID;
	private String UserName;
	private String Password;
	private String Name;
	private String Email;
	private String ContactInfo;
	
	public User(String UserID ,String UserName, String Password , String Name , String Email ,  String ContactInfo) {
		this.setContactInfo(ContactInfo);;
		this.setEmail(Email);
		this.setName(Name);
		this.setPassword(Password);
		this.setUserID(UserID);
		this.setUserName(UserName);
	}

	boolean islogin = false;

	public String getUserID() {
		return UserID;
	}
	
	public String getUserName () {
		return UserName;
	}
	
	public String getName () {
		return Name;
	}
	
	public String getPassword () {
		return Password;
	}
	
	public String getEmail () {
		return Email;
	}
	
	public String getContactInfo () {
		return ContactInfo;
	}
	
	public void setName(String Name) {
		if(Name.trim().isEmpty()) {
			System.out.println("Please Enter Your Name Agein !");
		}else {
			this.Name = Name;
		}
	}
	
	public void setEmail(String Email) {
		boolean hasdot = false;
		boolean hasat=false;
		for(int i=0 ; i<Email.length() ; i++) {
			if(Email.charAt(i) == '@' ) {
				hasat = true;
			}else if(Email.charAt(i) == '.') {
				hasdot = true;
			}
		}
		if(hasdot && hasat) {
			this.Email = Email;
		}else {
			System.out.println("Please Enter Your Email Agein !");
		}	
	}
	
	public void setUserName(String UserName) {
		if(UserName.trim().isEmpty() || UserName ==null) {
			System.out.println("Please Enter Your UserName Agein !");
		}else {
			this.UserName = UserName;
		}
	}
	
	public void setUserID(String UserID) {
	    boolean hasDigit = false;
	    if (UserID != null && !UserID.trim().isEmpty()) {
	        for (int i = 0; i < UserID.length(); i++) {
	            if (Character.isDigit(UserID.charAt(i))) {
	                hasDigit = true;
	                break;
	            }
	        }
	    }
	    if (hasDigit) {
	        this.UserID = UserID;
	    } else {
	        System.out.println("UserID must contain at least one digit!");
	    }
	}
	
	public void setPassword(String Password) {
	        boolean hasDigit = false;
	        boolean hasLetter = false;

	        if(Password.length()>=6 && Password != null) {
				for(int i=0 ; i<Password.length() ; i++) {
					if(Password.charAt(i) >= '0' && Password.charAt(i) <= '9') {
						hasDigit=true;
					}
					
					else if(Password.charAt(i) >= 'A' && Password.charAt(i) <= 'Z') {
						hasLetter = true;
					}
					
					else if(Password.charAt(i) >= 'a' && Password.charAt(i) <= 'z') {
						hasLetter = true;
					}
				}

	        if (hasDigit && hasLetter) {
	            this.Password = Password;
	        } else {
	            System.out.println("Password must contain at least one letter and one digit !");
	        }
	    } 
}
	
	public void setContactInfo(String ContactInfo) {
		boolean okcontactinfo1 = false;
		boolean okcontactinfo2 = false;
		if(ContactInfo.length()>=10 && ContactInfo.length()<=15) {
			okcontactinfo2 = true;
		}
		for(int i=0 ; i<ContactInfo.length();i++) {
			if(ContactInfo.charAt(i)>='0' && ContactInfo.charAt(i)<='9') {
				okcontactinfo1 = true;
			}
			
		}	
		if(okcontactinfo1 && okcontactinfo2) {
			this.ContactInfo = ContactInfo;
		}else {	
			System.out.println("Please Enter The ContactInfo Agein !");
		}
	}
	
	public boolean login(String username, String Password) {
        if( this.UserName.equals(username) && this.Password.equals(Password)) {
        	islogin = true;
        	return true;
        }
        return false;
    }

    public void logout() {
        System.out.println(Name + " has logged out.");
        islogin = false;
    }

    public abstract void updateProfile(Customer customer , Agent agent , Administrator admin) ;
        
   }
 
 class Customer extends User {
     private String customerId;
     private String address;
     private ArrayList<String> bookingHistory; 
     private ArrayList<String> bookings; 

     public Customer(String userID, String userName, String password, String name, String email, String contactInfo,
             String customerId, String address) {
         super(userID, userName, password, name, email, contactInfo);
         this.setCustomerId(customerId);
         this.setAddress(address);
         this.bookingHistory = new ArrayList<>();
         this.bookings = new ArrayList<>();
     }

     public String getCustomerId() {
         return customerId;
     }

     public String getAddress() {
         return address;
     }
     
     public void setCustomerId(String customerId) {
    	 boolean okcID = false;
    	 for(int i=0 ; i<customerId.length();i++) {
    		 if(customerId.charAt(i)>='0'&&customerId.charAt(i)<='9') {
    			 okcID = true;
    		 }
    	 }
    	 if(!okcID) {
    		 System.out.println("Please Enter The Customer ID Agein !");
    	 }else {
    		 this.customerId = customerId;
    	 }
     }
     
     public void setAddress(String address) {
    	 if(address == null) {
    		 System.out.println("Please Enter The Address Agien !");
    	 }else {
    		 this.address = address;
    	 }
     }

     @Override
     public boolean login(String username, String password) {
         boolean islogin = super.login(username, password);
         if (islogin) {
             System.out.println("Customer " + getName() + " logged in successfully");
         } else {
             System.out.println("Login failed for customer " + username);
         }
         return islogin;
     }

     @Override
     public void logout() {
    	 if(islogin) {
    		 super.logout();
    	 }
     }
     
     @Override
     public void updateProfile(Customer customer, Agent agent, Administrator admin) {
    	 if(islogin) {
    		 if (customer != null) {
                 setName(customer.getName());
                 setEmail(customer.getEmail());
                 setPassword(customer.getUserName());
                 setContactInfo(customer.getContactInfo());
                 setUserID(customer.getUserID());
                 setAddress(customer.getAddress());
                 System.out.println(getName() + " Your profile has been updated successfully (Customer)");
             } else {
                 System.out.println("No customer data provided for profile update.");
                 }
             }else {
            	 System.out.println("Please Login Agein!");
             }
     }          
          
     public void searchFlights(Flight[] flights, String origin, String destination, String date) {
    	    if (islogin) {
    	        System.out.println("Searching flights from " + origin + " to " + destination + " on " + date);
    	        boolean found = false;
    	        for (int i = 0; i < flights.length; i++) {
    	            if (flights[i] != null && 
    	                flights[i].getOrigin().equalsIgnoreCase(origin) && 
    	                flights[i].getDestination().equalsIgnoreCase(destination) &&
    	                flights[i].getDepartureTime().startsWith(date)) {
    	                flights[i].displayFlightInfo();
    	                System.out.println("Available Seats: " + flights[i].getAvailableSeates());
    	                System.out.println("------------");
    	                found = true;
    	            }
    	        }
    	        if (!found) {
    	            System.out.println("No matching flights found.");
    	        }
    	    } else {
    	        System.out.println("Please Login Again!");
    	    }
    	}
    
    	public void cancelBooking(String booking, Flight flight) {
    	    if (islogin) {
    	        if (booking == null || booking.trim().isEmpty()) {
    	            System.out.println("Booking cannot be empty!");
    	            return;
    	        }
    	        if (bookingHistory.contains(booking)) {
    	            bookingHistory.remove(booking);
    	            if (flight != null) {
    	                String[] parts = booking.split(" ");
    	                if (parts.length >= 2) {
    	                    String flightNumber = parts[1]; 
    	                    if (flight.getFlightNumber() == Integer.parseInt(flightNumber)) {
    	                        for (int i = 0; i < flight.getSeats().length; i++) {
    	                            if (flight.getSeats()[i] != null && flight.getSeats()[i].equals(getCustomerId())) {
    	                                flight.getSeats()[i] = null;
    	                                flight.setAvailableSeates(flight.getAvailableSeates() + 1);
    	                                System.out.println("Seat " + i + " has been released for flight " + flightNumber);
    	                                break;
    	                            }
    	                        }
    	                    }
    	                }
    	            }
    	            System.out.println("Booking cancelled: " + booking);
    	        } else {
    	            System.out.println("Booking not found: " + booking);
    	        }
    	    } else {
    	        System.out.println("Please Login Again!");
    	    }
    	}

    	public void viewBookings() {
    	    if (islogin) {
    	        System.out.println("Booking History for " + getName() + ":");
    	        if (bookingHistory.isEmpty()) {
    	            System.out.println("No bookings found.");
    	        } else {
    	            for (String booking : bookingHistory) {
    	                System.out.println("- " + booking);
    	            }
    	        }
    	    } else {
    	        System.out.println("Please Login Again!");
    	    }
    	}

        public void addToBookingHistory(String booking) {
           if(islogin) {
        	   bookingHistory.add(booking);
           }else {
        	   System.out.println("Please Login Agein!");
           }
        }

        public void createBooking(Flight flight, String seatClass, int seatIndex) {
            if (islogin) {
                if (flight == null) {
                    System.out.println("Flight cannot be null!");
                    return;
                }
                if (seatClass == null || seatClass.trim().isEmpty() || 
                    (!seatClass.equalsIgnoreCase("Economy") && !seatClass.equalsIgnoreCase("Business") && !seatClass.equalsIgnoreCase("First Class"))) {
                    System.out.println("Invalid seat class!");
                    return;
                }
                if (flight.getAvailableSeates() <= 0) {
                    System.out.println("No available seats on this flight!");
                    return;
                }
                if (flight.reserveSeat(seatIndex, this)) {
                    String booking = "Flight " + flight.getFlightNumber() + " (" + seatClass + ")";
                    bookingHistory.add(booking);
                    bookings.add(booking);
                    System.out.println("Booking created successfully: " + booking);
                } else {
                    System.out.println("Failed to reserve seat!");
                }
            } else {
                System.out.println("Please Login Again!");
            }
        }
    }
    	 
  class Administrator extends User {
	    private String adminId;
	    private int securityLevel;
	    private ArrayList<User> managedUsers;
	    private ArrayList<String> systemLogs;
	    private List<String> paymentMethods; 
	    private int maxPassengers; 

	    public Administrator(String userID, String userName, String password, String name, String email, String contactInfo,
	                        String adminId, int securityLevel) {
	        super(userID, userName, password, name, email, contactInfo);
	        this.setAdminId(adminId);
	        this.securityLevel = securityLevel;
	        this.managedUsers = new ArrayList<>();
	        this.systemLogs = new ArrayList<>();
	        this.paymentMethods = new ArrayList<>(Arrays.asList("Credit Card", "Bank Transfer", "PayPal", "Cash"));
	        this.maxPassengers = 5;
	    }
	    
	    public void setAdminId(String adminId) {
	    	 boolean trueadminid = false;
	    	for(int i=0 ; i<adminId.length() ; i++) {
				if(adminId.charAt(i) >= '0' && adminId.charAt(i) <= '9') {
					trueadminid=true;
				}
	    	}
	    	 if(!trueadminid) {
	    		 System.out.println("Please Enter The Admin ID Agein !");
	    	 }
	    	 else {
	    		 this.adminId = adminId;
	    	 }
	    }

	    public String getAdminId() {
	        return adminId;
	    }

	    public int getSecurityLevel() {
	        return securityLevel;
	    }
	    
	    public void setSecurityLevel(int securityLevel) {
	    	this.securityLevel = securityLevel;
	    }

	    public List<String> getPaymentMethods() {
	        return paymentMethods;
	    }

	    public int getMaxPassengers() {
	        return maxPassengers;
	    }
	    
	    public void setmaxPassengers(int maxPassengers) {
	    	this.maxPassengers = maxPassengers;
	    }

	    @Override
	    public boolean login(String username, String password) {
	        boolean islogin = super.login(username, password);
	        if (islogin) {
	            System.out.println("Administrator " + getName() + " logged in successfully");
	        } else {
	            System.out.println("Login failed for Administrator " + username);
	        }
	        return islogin;
	    }

	    @Override
	    public void logout() {
	    	if(islogin) {
	    		super.logout();
	    	}else {
	    		System.out.println("Please Login Agein!");
	    	}
	    }

	    @Override
	     public void updateProfile(Customer customer, Agent agent, Administrator admin) {
	         if(islogin) {
	        	 if (admin != null) {
		             setName(admin.getName());
		             setEmail(admin.getEmail());
		             setPassword(admin.getUserName());
		             setContactInfo(admin.getContactInfo());
		             setUserID(admin.getUserID());
		             setSecurityLevel(admin.getSecurityLevel());
		             setAdminId(admin.getAdminId());
		             setmaxPassengers(admin.getMaxPassengers());
		             System.out.println(getName() + " Your profile has been updated successfully (Administrator)");
		         } else {
		             System.out.println("No customer data provided for profile update.");
		             }
	         }else {
	        	 System.out.println("Please Login Agein!");
	         }
	      }

	    private boolean isUserIdDuplicate(String userID) {
	        for (User user : managedUsers) {
	            if (user.getUserID().equals(userID)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    public void createUser(String userType, String userID, String userName, String password, String name, String email,
	                           String contactInfo, String additionalId, String department) {
	       if(islogin) {
	    	   if (userType == null || userID == null || userName == null || password == null || name == null ||
	   	            email == null || contactInfo == null || additionalId == null || department == null) {
	   	            System.out.println("Invalid user data!");
	   	            return;
	   	        }

	   	        if (isUserIdDuplicate(userID)) {
	   	            System.out.println("User ID " + userID + " already exists!");
	   	            return;
	   	        }

	   	        User newUser;
	   	        if (userType.equalsIgnoreCase("Agent")) {
	   	            newUser = new Agent(userID, userName, password, name, email, contactInfo, additionalId, department, 0.0);
	   	        } else if (userType.equalsIgnoreCase("Customer")) {
	   	            newUser = new Customer(userID, userName, password, name, email, contactInfo, additionalId, department);
	   	        } else {
	   	            System.out.println("Invalid user type! Use 'Agent' or 'Customer'.");
	   	            return;
	   	        }

	   	        managedUsers.add(newUser);
	   	        systemLogs.add("User created: " + userType + " ID: " + userID + " by Administrator: " + getName());
	   	        System.out.println(userType + " " + name + " created successfully.");
	   	    }else {
	   	    	System.out.println("Please Login Agein!");
	   	    }
	  }
	      
	    public void viewSystemLogs() {
	    	if(islogin) {
	    		if (systemLogs.isEmpty()) {
	   	            System.out.println("No Logs Available!");
	   	        } else {
	   	            for (String log : systemLogs) {
	   	                System.out.println(log);
	   	            }
	   	        }
	    	}else {
	    		System.out.println("Please Login Agein!");
	    	}
	    }

	    public void manageUserAccess(String userID, String action) {
	        if(islogin) {
	        	if (userID == null || userID.trim().isEmpty() || action == null || action.trim().isEmpty()) {
		            System.out.println("Invalid user ID or action!");
		            return;
		        }

		        for (User user : managedUsers) {
		            if (user.getUserID().equals(userID)) {
		                if (action.equalsIgnoreCase("Suspend")) {
		                    systemLogs.add("User " + userID + " suspended by Administrator: " + getName());
		                    System.out.println("User " + userID + " suspended successfully.");
		                } else if (action.equalsIgnoreCase("Reactivate")) {
		                    systemLogs.add("User " + userID + " reactivated by Administrator: " + getName());
		                    System.out.println("User " + userID + " reactivated successfully.");
		                } else if (action.equalsIgnoreCase("Delete")) {
		                    managedUsers.remove(user);
		                    systemLogs.add("User " + userID + " deleted by Administrator: " + getName());
		                    System.out.println("User " + userID + " deleted successfully.");
		                } else {
		                    System.out.println("Invalid action! Use Suspend, Reactivate, or Delete.");
		                }
		                return;
		            }
		        }
		        System.out.println("User " + userID + " not found!");
	        }else {
	        	System.out.println("Please Login Agein!");
	        }
	    }

	    public void ModifySystemSetting() {
	    	Scanner scanner = new Scanner(System.in);
	    	if(islogin) {
	    		while (true) {
		            System.out.println("\n=== Modify System Settings ===");
		            System.out.println("1. Add Payment Method");
		            System.out.println("2. Remove Payment Method");
		            System.out.println("3. Set Maximum Passengers");
		            System.out.println("4. View Current Settings");
		            System.out.println("5. Exit");
		            System.out.print("Choose: ");
		            int choice = scanner.nextInt();
		            scanner.nextLine();

		            if (choice == 5) break;

		            if (choice == 1) {
		                System.out.print("Enter new payment method: ");
		                String newMethod = scanner.nextLine();
		                if (newMethod != null && !newMethod.trim().isEmpty() && !paymentMethods.contains(newMethod)) {
		                    paymentMethods.add(newMethod);
		                    systemLogs.add("Payment method added: " + newMethod + " by Administrator: " + getName());
		                    System.out.println("Payment method " + newMethod + " added successfully.");
		                } else {
		                    System.out.println("Invalid or duplicate payment method!");
		                }
		            } else if (choice == 2) {
		                System.out.print("Enter payment method to remove: ");
		                String method = scanner.nextLine();
		                if (paymentMethods.remove(method)) {
		                    systemLogs.add("Payment method removed: " + method + " by Administrator: " + getName());
		                    System.out.println("Payment method " + method + " removed successfully.");
		                } else {
		                    System.out.println("Payment method not found!");
		                }
		            } else if (choice == 3) {
		                System.out.print("Enter maximum number of passengers: ");
		                int max = scanner.nextInt();
		                scanner.nextLine();
		                if (max > 0) {
		                    maxPassengers = max;
		                    systemLogs.add("Maximum passengers set to " + max + " by Administrator: " + getName());
		                    System.out.println("Maximum passengers set to " + max + " successfully.");
		                } else {
		                    System.out.println("Invalid number of passengers!");
		                }
		            } else if (choice == 4) {
		                System.out.println("Current Payment Methods: " + String.join(", ", paymentMethods));
		                System.out.println("Current Maximum Passengers: " + maxPassengers);
		            } else {
		                System.out.println("Invalid choice!");
		            }
		        }
		    }else {
		    	System.out.println("Please Login Agein!");
		    }
	    }     
	}
 
 class Agent extends User{
	 private String agentId;
	 private String department;
	 private double commission;
	 private ArrayList<Flight> managedFlights = new ArrayList<>();
	 private ArrayList<Booking> managedBookings = new ArrayList<>(); 
	 
	    public Agent(String userID, String userName, String password, String name, String email, String contactInfo,
	                 String agentId, String department, double commission) {
	        super(userID, userName, password, name, email, contactInfo);
	        this.setAgentId(agentId);
	        this.setDepartment(department);
	        this.commission = commission;
	    }

	    public String getAgentId() {
	        return agentId;
	    }

	    public String getDepartment() {
	        return department;
	    }

	    public double getCommission() {
	        return commission;
	    }

	    public void setAgentId (String agentId) {
	    	boolean okagentid=false;
	    	if(agentId==null || agentId.isEmpty()||agentId.trim().isEmpty()) {
	    		System.out.println("Please Enter The Agent ID Agein !");
	    	}else {
	    		for(int i=0 ; i<agentId.length();i++) {
	    			if(agentId.charAt(i)>='0' && agentId.charAt(i)<='9') {
	    				okagentid = true;
	    			}
	    		}
	    	}
	    	if(!okagentid) {
	    		System.out.println("Please Enter The Agent ID Agein !");
	    	}else {
	    		this.agentId = agentId;
	    	}
	    }
	    
	    public void setDepartment (String department) {
	    	if(department==null || department.isEmpty()||department.trim().isEmpty()) {
	    		System.out.println("Please Enter The Agent Department Of Agein !");
	    	}else {
	    		this.department = department;
	    	}	
	    }
	    
	    public void setcommission (double commission) {
	    	this.commission = commission;
	    }
	    
	    private boolean isFlightNumberDuplicate(int flightNumber) {
	        for (Flight f : managedFlights) {
	            if (f.getFlightNumber() == flightNumber) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    @Override
	    public boolean login(String username, String password) {
	    	islogin = super.login(username, password);
	    	if (islogin) {
	    	    System.out.println("Agent " + getName() + " logged in successfully");
	    	} else {
	    	    System.out.println("Login failed for Agent " + username);
	    	}
	    	return islogin;
	    }
	    
	    @Override
	    public void logout() {
	        if (islogin) {
	            super.logout();
	        }
	    }
	    
	    @Override
	    public void updateProfile(Customer customer, Agent agent, Administrator admin) {
	        if (islogin) {
	            if (agent != null) {
	                setName(agent.getName());
	                setEmail(agent.getEmail());
	                setPassword(agent.getPassword());
	                setContactInfo(agent.getContactInfo());
	                setUserID(agent.getUserID());
	                setDepartment(agent.getDepartment());
	                System.out.println(getName() + " Your profile has been updated successfully (Agent)");
	            } else {
	                System.out.println("No customer data provided for profile update.");
	            }
	        } else {
	            System.out.println("Please Login Again!");
	        }
	    }

	    private boolean isValidAirport(String airport) {
	        return airport != null && !airport.trim().isEmpty();
	    }

	    private boolean isValidSchedule(String departureTime, String arrivalTime) {
	        return departureTime != null && arrivalTime != null && !departureTime.equals(arrivalTime);
	    }

	    public void ManageFlights(Flight flight , String Action) {
	    	if(islogin) {
	    		if(flight == null) {
		    		System.out.println("The Data Is Valid !");
		    	}
		    	if (Action == null || Action.trim().isEmpty()) {
		            System.out.println("Please Choose What Is The Operation Do You Want To Do !");
		        }
		    	
		    	if (Action .equalsIgnoreCase("Add")) {
		    		 if (isFlightNumberDuplicate(flight.getFlightNumber())) { 
		    			 System.out.println("The Flight Number Is Already exists !");
		             }
		             if (!isValidAirport(flight.getOrigin()) || !isValidAirport(flight.getDestination())) {
		            	 System.out.println("Invalid airport !");
		             }
		             if (!isValidSchedule(flight.getDepartureTime(), flight.getArrivalTime())) {
		                 System.out.println("Invalid flight schedule !");
		             }
		             if (flight.getAvailableSeates() <= 0) {
		                System.out.println("The Number Of Seats Should Be Bigger Than Or Equal 1");
		             }
		             Flight newFlight = new Flight(
		                     flight.getFlightNumber(),
		                     flight.getbasePrice(),
		                     flight.getAirLine(),
		                     flight.getOrigin(),
		                     flight.getDestination(),
		                     flight.getAvailableSeates(),
		                     flight.getDepartureTime(),
		                     flight.getArrivalTime());
			        managedFlights.add(newFlight);
			        System.out.println("Flight : " + flight.getFlightNumber() + " added by Agent: " + getName());
			        
		    	}else if(Action.equalsIgnoreCase("Delete")) {
		    		for (int i = 0; i < managedFlights.size(); i++) {
		                if (managedFlights.get(i).getFlightNumber() == flight.getFlightNumber()) {
		                    managedFlights.remove(i);
		                    System.out.println("Flight : " + flight.getFlightNumber() + " deleted successfully.");
		                }else {
		                	System.out.println("This Flight Number : "+flight.getFlightNumber()+" Is Not Founde !");
		                }
		    		}
		    		
		    	}else if (Action.equalsIgnoreCase("Update")) {
		    		 for (int i = 0; i < managedFlights.size(); i++) {
		                 if (managedFlights.get(i).getFlightNumber() == flight.getFlightNumber()) {
		                     managedFlights.get(i).setAirLine(flight.getAirLine());
		                     managedFlights.get(i).setOrigin(flight.getOrigin());
		                     managedFlights.get(i).setDestination(flight.getDestination());
		                     managedFlights.get(i).setAvailableSeates(flight.getAvailableSeates());
		                     managedFlights.get(i).setPrices(flight.getbasePrice());
		                     managedFlights.get(i).setDepartureTime(flight.getDepartureTime());
		                     managedFlights.get(i).setArrivalTime(flight.getArrivalTime());
		                     System.out.println("Flight : " + flight.getFlightNumber() + " updated successfully."); 
		                 }
		             }
		             System.out.println("Flight : " + flight.getFlightNumber() + " not found."); 
		    	}
	    	}
	    }
	    
	    public void createBookingForCustomer(Customer customer, Flight flight, ArrayList<Passenger> passengers, String seatClass, ArrayList<Integer> seatIndexes, double commissionPercentage) {
	        if (islogin) {
	            if (customer == null) {
	                System.out.println("Invalid customer data!");
	                return;
	            }
	            if (flight == null) {
	                System.out.println("Invalid flight data!");
	                return;
	            }
	            if (passengers == null || passengers.isEmpty()) {
	                System.out.println("At least one passenger is required!");
	                return;
	            }
	            if (seatClass == null || seatClass.trim().isEmpty()) {
	                System.out.println("Seat class is required!");
	                return;
	            }
	            if (seatIndexes == null || seatIndexes.isEmpty()) {
	                System.out.println("At least one seat index is required!");
	                return;
	            }

	            boolean flightExists = false;
	            for (Flight f : managedFlights) {
	                if (f.getFlightNumber() == flight.getFlightNumber()) {
	                    flightExists = true;
	                    break;
	                }
	            }
	            if (!flightExists) {
	                System.out.println("Flight " + flight.getFlightNumber() + " not found.");
	                return;
	            }

	            if (passengers.size() > flight.getAvailableSeates()) {
	                System.out.println("Not enough seats available on flight " + flight.getFlightNumber() + "!");
	                return;
	            }
	            if (passengers.size() != seatIndexes.size()) {
	                System.out.println("Number of passengers must match number of seat indexes!");
	                return;
	            }

	            for (int seatIndex : seatIndexes) {
	                if (!flight.reserveSeat(seatIndex, customer)) {
	                    for (int i = 0; i < seatIndexes.indexOf(seatIndex); i++) {
	                        flight.getSeats()[seatIndexes.get(i)] = null;
	                    }
	                    System.out.println("Seat " + seatIndex + " is not available or invalid!");
	                    return;
	                }
	            }

	            String bookingReference = "BK" + (managedBookings.size() + 1);
	            Booking booking = new Booking(bookingReference, customer, flight, passengers);

	            ArrayList<String> seatSelections = new ArrayList<>();
	            for (int seatIndex : seatIndexes) {
	                seatSelections.add(seatClass + "-Seat" + seatIndex);
	            }
	            booking.getSeatSelections().addAll(seatSelections);

	            for (Booking b : managedBookings) {
	                if (b.getBookingReference().equals(bookingReference)) {
	                    System.out.println("Booking reference " + bookingReference + " already exists!");
	                    return;
	                }
	            }
	            managedBookings.add(booking);

	            customer.addToBookingHistory("Flight " + flight.getFlightNumber() + " (" + seatClass + ")");

	            flight.setAvailableSeates(flight.getAvailableSeates() - passengers.size());

	            double totalPrice = flight.calculatePrice(seatClass) * passengers.size();
	            this.commission += totalPrice * commissionPercentage;

	            System.out.println("Booking " + bookingReference + " created successfully for customer: " + customer.getName());
	        } else {
	            System.out.println("Please Login Again!");
	        }
	    }
	    
	    public void modifyBooking(String bookingReference, ArrayList<Integer> newSeatIndexes, String newSeatClass, double commissionPercentage , ArrayList<Passenger> newPassengers) {
	    	if(islogin) {
	    		if (bookingReference == null || bookingReference.trim().isEmpty()) {
		            System.out.println("Invalid booking reference!");
		            return;
		        }
		        if (newSeatIndexes == null || newSeatIndexes.isEmpty()) {
		            System.out.println("At least one seat index is required!");
		            return;
		        }
		        if (newSeatClass == null || newSeatClass.trim().isEmpty()) {
		            System.out.println("Seat class is required!");
		            return;
		        }
		        if (newPassengers == null || newPassengers.isEmpty()) {
		            System.out.println("At least one passenger is required!");
		            return;
		        }

		        for (Booking booking : managedBookings) {
		            if (booking.getBookingReference().equals(bookingReference)) {
		                Flight flight = booking.getFlight();
		                Customer customer = booking.getCustomer();

		                if (newSeatIndexes.size() != newPassengers.size()) {
		                    System.out.println("Number of seats must Equal number of passengers!");
		                    return;
		                }

		                for (int seatIndex : newSeatIndexes) {
		                    if (seatIndex < 0 || seatIndex >= flight.getSeats().length) {
		                        System.out.println("Invalid seat index: " + seatIndex);
		                        return;
		                    }
		                    if (flight.getSeats()[seatIndex] != null && !flight.getSeats()[seatIndex].equals(customer.getCustomerId())) {
		                        System.out.println("Seat " + seatIndex + " is already taken!");
		                        return;
		                    }
		                }

		                ArrayList<String> oldSeatSelections = booking.getSeatSelections();
		                int oldPassengerCount = booking.getPassengers().size();
		                for (String seat : oldSeatSelections) {
		                    String[] parts = seat.split("-Seat");
		                    if (parts.length == 2) {
		                        int oldSeatIndex = Integer.parseInt(parts[1]);
		                        flight.getSeats()[oldSeatIndex] = null;
		                    }
		                }

		                ArrayList<String> newSeatSelections = new ArrayList<>();
		                for (int seatIndex : newSeatIndexes) {
		                    flight.getSeats()[seatIndex] = customer.getCustomerId();
		                    newSeatSelections.add(newSeatClass + "-Seat" + seatIndex);
		                }

		                booking.getSeatSelections().clear();
		                booking.getSeatSelections().addAll(newSeatSelections);
		                booking.getPassengers().clear();
		                booking.getPassengers().addAll(newPassengers);

		                int newPassengerCount = newPassengers.size();
		                flight.setAvailableSeates(flight.getAvailableSeates() + oldPassengerCount - newPassengerCount);

		                customer.addToBookingHistory("Flight " + flight.getFlightNumber() + " (" + newSeatClass + ")");

		                String oldSeatClass = oldSeatSelections.isEmpty() ? "Economy" : oldSeatSelections.get(0).split("-")[0];
		                double oldPrice = flight.calculatePrice(oldSeatClass) * oldPassengerCount;
		                double newPrice = flight.calculatePrice(newSeatClass) * newPassengerCount;
		                this.commission += (newPrice - oldPrice) * commissionPercentage;

		                System.out.println("Booking " + bookingReference + " modified successfully.");
		                return;
		            }
		        }
		        System.out.println("Booking " + bookingReference + " not found!");
		    }
	   	}
	    	
	    
	    public void generateReports() {
	    	if(islogin) {
	    		System.out.println("Bookings for Agent: " + getName());
		         if (managedBookings.isEmpty()) {
		             System.out.println("No bookings available.");
		         } else {
		             for (Booking booking : managedBookings) {
		                 System.out.println("Booking: " + booking.getBookingReference() + 
		                                    ", Customer: " + booking.getCustomer().getName() + 
		                                    ", Flight: " + booking.getFlight().getFlightNumber());
		             }
		         }
		         System.out.println("Total Commission: " + commission+" $");
	    	} 
	    }
 }
 
  class Flight{ 
	 
 private int FlightNumber;
 private double basePrice;
 private String AirLine;
 private String Origin;
 private String Destination;
 private int AvailableSeates;
 private String DepartureTime;
 private String ArrivalTime;
 String[] seats;
 
 public Flight(int FlightNumber, double basePrice, String AirLine, String Origin, String Destination, int AvailableSeates, String DepartureTime, String ArrivalTime) {
     this.FlightNumber = FlightNumber;
     this.setAirLine(AirLine);
     this.ArrivalTime = ArrivalTime;
     this.AvailableSeates = AvailableSeates;
     this.DepartureTime = DepartureTime;
     this.setDestination(Destination);
     this.setOrigin(Origin);
     this.basePrice=basePrice;
     this.seats = new String[AvailableSeates]; 
     }

 public String[] getSeats() {
     return seats;
 }
 
 public int getFlightNumber() {
     return FlightNumber;
 }

 public double getbasePrice() {
     return basePrice;
 }

 public String getAirLine() {
     return AirLine;
 }

 public String getOrigin() {
     return Origin;
 }

 public String getDestination() {
     return Destination;
 }

 public int getAvailableSeates() {
     return AvailableSeates;
 }

 public String getDepartureTime() {
     return DepartureTime;
 }

 public String getArrivalTime() {
     return ArrivalTime;
 }

 public void setFlightNumber(int FlightNumber) {
     this.FlightNumber = FlightNumber;
 }

 public void setPrices(double Prices) {
	 if(Prices>0) {
		 this.basePrice = Prices;
	 }else {
		 System.out.println("Please Enter The Price Agein !");
	 } 
 }

 public void setAirLine(String AirLine) {
	 boolean Letter = false;
	 for(int i=0 ; i<AirLine.length(); i++) {
		 
	  if(AirLine.charAt(i) >= 'A' && AirLine.charAt(i) <= 'Z') {
			Letter = true;
		}else if(AirLine.charAt(i) >= 'a' && AirLine.charAt(i) <= 'z') {
			Letter = true;
		}
	}
	 if(Letter) {
		 this.AirLine = AirLine;
	 }else {
		 System.out.println("Please Enter The AirLine Agein !");
	 }
     
 }

 public void setOrigin(String Origin) {
	 boolean Letter = false;
	 for(int i=0 ; i<Origin.length(); i++) {
		 
	  if(Origin.charAt(i) >= 'A' && Origin.charAt(i) <= 'Z') {
			Letter = true;
		}else if(Origin.charAt(i) >= 'a' && Origin.charAt(i) <= 'z') {
			Letter = true;
		}
	}
	 if(Letter) {
		 this.Origin = Origin;
	 }else {
		 System.out.println("Please Enter The Origin Agein !");
	 }
 }

 public void setDestination(String Destination) {
	 boolean Letter = false;
	 for(int i=0 ; i<Destination.length(); i++) {
		 
	  if(Destination.charAt(i) >= 'A' && Destination.charAt(i) <= 'Z') {
			Letter = true;
		}else if(Destination.charAt(i) >= 'a' && Destination.charAt(i) <= 'z') {
			Letter = true;
		}
	}
	 if(Letter) {
		 this.Destination = Destination;
	 }else {
		 System.out.println("Please Enter The Destination Agein !");
	 }
 }

 public void setAvailableSeates(int AvailableSeates) {
     this.AvailableSeates = AvailableSeates;
 }

 public void setDepartureTime(String DepartureTime) {
     this.DepartureTime = DepartureTime;
 }

 public void setArrivalTime(String ArrivalTime) {
     this.ArrivalTime = ArrivalTime;
 }


 public void displayFlightInfo() {
     System.out.println("Flight number: "+getFlightNumber());
     System.out.println("Airline: "+getAirLine());
     System.out.println("From: "+getOrigin()+"To: "+getDestination());

 }

 public boolean reserveSeat(int seatIndex, Customer customer) {
	    if (seatIndex < 0 || seatIndex >= seats.length) {
	        System.out.println("Invalid seat number: " + seatIndex);
	        return false;
	    }
	    for (int i = 0; i < seats.length; i++) {
	        if (seats[i] != null && seats[i].equals(customer.getCustomerId())) {
	            System.out.println("Customer " + customer.getName() + " already has a reserved seat (Seat " + i + ").");
	            return false;
	        }
	    }
	    if (seats[seatIndex] == null) {
	        seats[seatIndex] = customer.getCustomerId();
	        System.out.println("Seat " + seatIndex + " reserved for " + customer.getName());
	        return true;
	    } else {
	        System.out.println("Seat " + seatIndex + " is already taken.");
	        return false;
	    }
	}

         public void checkAvailability ( int flightNumber){
             System.out.println("Available seats on flight " + flightNumber + ":");
             for (int i = 0; i < seats.length; i++) {
                 if (seats[i]==null){
                     System.out.println("Seat number: " + i );
                 }
             }
         }
 public void updateSchedule(String newDeparture, String newArrival) {
     this.DepartureTime = newDeparture;
     this.ArrivalTime = newArrival;
     System.out.println("Schedule updated for flight " + FlightNumber);
 }
 public void showSchedule() {
     System.out.println("Flight " + FlightNumber + ":");
     System.out.println("Departure: " + DepartureTime);
     System.out.println("Arrival: " + ArrivalTime);
 }


 public double calculatePrice(String seatClass) {
     if (seatClass.equalsIgnoreCase("Economy")) {
         return basePrice;
     } else if (seatClass.equalsIgnoreCase("Business")) {
         return basePrice * 1.5;
     } else if (seatClass.equalsIgnoreCase("First Class")) {
         return basePrice * 2.0;
     } else {
         System.out.println("Unknown seat class. Using base price.");
          return basePrice;
     }
   }
 }
  
  class Booking{

      private String bookingReference;
      private Customer customer;
      private Flight flight;
      private ArrayList<Passenger> passengers;
      private ArrayList<String> seatClasses;
      private String status;
      private String paymentStatus;

      public Booking(String bookingReference, Customer customer, Flight flight, ArrayList<Passenger> passengers) {
          this.bookingReference = bookingReference;
          this.customer = customer;
          this.flight = flight;
          this.passengers = passengers != null ? passengers : new ArrayList<>();
          this.seatClasses = new ArrayList<>();
          this.status = "Reserved";
          this.paymentStatus = "Pending";

      }

      public String getBookingReference() {
          return bookingReference;
      }

      public Customer getCustomer() {
          return customer;
      }

      public Flight getFlight() {
          return flight;
      }

      public ArrayList<Passenger> getPassengers() {
          return passengers;
      }

      public ArrayList<String> getSeatClasses() {
          return seatClasses;
      }
      
      public ArrayList<String> getSeatSelections() {
    	    if (seatClasses == null) {
    	        seatClasses = new ArrayList<>();
    	    }
    	    return seatClasses;
    	}

      public String getStatus() {
          return status;
      }

      public String getPaymentStatus() {
          return paymentStatus;
      }

      public void setStatus(String status) {
          this.status = status;
      }

      public void setPaymentStatus(String paymentStatus) {
          this.paymentStatus = paymentStatus;
      }

      public void AddPassenger(Passenger passenger) {
          if (passenger != null) {
              passengers.add(passenger);
          }
      }

      public double calculateTotalPrice() {
          double total = 0.0;
          for (int i = 0; i < passengers.size(); i++) {
              String seatClass = seatClasses.get(i);
              total += flight.calculatePrice(seatClass);
          }
          return total;
      }

      public void confirmBooking() {
          if (passengers.isEmpty()) {
              System.out.println("Cannot confirm booking: no passengers added.");
              return;
          }

          for (int i = 0; i < passengers.size(); i++) {
              int seatIndex = i; 
              boolean reserved = flight.reserveSeat(seatIndex, customer);
              if (reserved) {
                  seatClasses.add("Seat " + seatIndex);
              } else {
                  System.out.println("Failed to reserve seat for passenger " + passengers.get(i).getName());
              }
          }

          this.status = "Confirmed";
          System.out.println("Booking confirmed for " + customer.getName());
      }


      public void cancelBooking() {
          this.status = "Cancelled";
          System.out.println("Booking has been cancelled !");
      }

      public void generateItinerary() {
          System.out.println("Itinerary for Booking: " + bookingReference);
          System.out.println("Customer: " + customer.getName());
          System.out.println("Flight: " + flight.getFlightNumber() + " (" + flight.getOrigin() + " → " + flight.getDestination() + ")");
          System.out.println("Passengers:");
          for (Passenger p : passengers) {
              System.out.println("- " + p.getName() + " | Passport: " + p.getPassportNumber());
          }
          System.out.println("Status: " + status + " | Payment: " + paymentStatus);
          System.out.println("Total Price: " +calculateTotalPrice()+" $");
          }
  }
   
class Passenger{
	private String Name;
	private String PassengerId;
	private String PassportNumber;
	private String SpecialRequest;
	private String DateOfBirth;
	
	public Passenger(String Name , String PassengerId , String PassportNumber , String SpecialRequest , String DateOfBirth) {
		this.DateOfBirth = DateOfBirth;
		this.setName(Name);
		this.setPassengerId(PassengerId);
		this.setPassportNumber(PassportNumber);
		this.setSpecialRequest(SpecialRequest);	
	}
	
	public String getName() {
		return Name;
	}
	
	public String getPassengerId() {
		return PassengerId;
	}
	
	public String getPassportNumber() {
		return PassportNumber;
	}
	
	public String getSpecialRequest() {
		return SpecialRequest;
	}
	
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	
	public void setName(String Name) {
		boolean Letter = false;
		 for(int i=0 ; i<Name.length(); i++) {
			 
		  if(Name.charAt(i) >= 'A' && Name.charAt(i) <= 'Z') {
				Letter = true;
			}else if(Name.charAt(i) >= 'a' && Name.charAt(i) <= 'z') {
				Letter = true;
			}
		}
		 if(Letter) {
			 this.Name = Name;
		 }else {
			 System.out.println("Please Enter Your Name Agein !");
		 }
	}
	
	public void setPassengerId(String PassengerId) {
		boolean digite = false;
		 for(int i=0 ; i<PassengerId.length(); i++) {
			 
		  if(PassengerId.charAt(i) >= '0' && PassengerId.charAt(i) <= '9') {
			  digite = true;
			  return;
		}
		 if(digite) {
			 this.PassengerId = PassengerId;
		 }else {
			 System.out.println("Please Enter The PassengerId Agein !");
		 }
	}
}
	public void setPassportNumber(String PassportNumber) {
		boolean digite = false;
		 for(int i=0 ; i<PassportNumber.length(); i++) {
			 
		  if(PassportNumber.charAt(i) >= '0' && PassportNumber.charAt(i) <= '9') {
			  digite = true;
			}
		}
		 if(digite) {
			 this.PassportNumber = PassportNumber;
		 }else {
			 System.out.println("Please Enter The PassportNumber Agein !");
		 }
	}
	
	public void setSpecialRequest(String SpecialRequest) {
		boolean Letter = false;
		 for(int i=0 ; i<SpecialRequest.length(); i++) {
			 
		  if(SpecialRequest.charAt(i) >= 'A' && SpecialRequest.charAt(i) <= 'Z') {
				Letter = true;
			}else if(SpecialRequest.charAt(i) >= 'a' && SpecialRequest.charAt(i) <= 'z') {
				Letter = true;
			}
		}
		 if(Letter) {
			 this.SpecialRequest = SpecialRequest;
		 }else {
			 System.out.println("Please Enter Your SpecialRequest Agein !");
		 }
	}
	
	public void setDateOfBirth(String DateOfBirth) {
		this.DateOfBirth = DateOfBirth;
	}
	
	public void updateInfo(String Name, String PassportNumber, String SpecialRequest, String DateOfBirth) {
        this.setName(Name);
        this.setPassportNumber(PassportNumber);
        this.setSpecialRequest(SpecialRequest);
        this.DateOfBirth = DateOfBirth;
        System.out.println("Passenger information updated successfully.");
    }
	
	public void getPassengerDetails() {
		System.out.println("  The Passenger  ");
		System.out.println("Passenger Name : "+Name);
		System.out.println("Passenger ID: "+PassengerId);
		System.out.println("Date Of Birth : "+DateOfBirth);
		System.out.println("Passport Number : "+PassportNumber);
		if(SpecialRequest ==null || SpecialRequest.isEmpty()) {
			System.out.println("Special Request : Not Founde!");
		}else {
			System.out.println("Special Request : "+SpecialRequest);
		}
	}
}

class Payment{
	private String PaymentId;
    private String BookingReference;
    private double Amount;
    private String Method;
    private String Status;
    private String TransactionDate;
    String[] acceptedMethods = {"Credit Card", "Bank Transfer", "PayPal", "Cash"};

    public Payment(String PaymentId, String BookingReference, double Amount, String Method, String TransactionDate) {
        this.PaymentId = PaymentId;
        this.BookingReference = BookingReference;
        this.Amount = Amount;
        this.Method = Method;
        this.Status = "Pending";
        this.TransactionDate = TransactionDate;
    }
    
    public String getPaymentId() {
    	return PaymentId;
    }
    
    public String getBookingReference() {
    	return BookingReference;
    }
    
    public double getAmount() {
    	return Amount;
    }
    
    public String getMethod() {
    	return Method;
    }
    
    public String getStatus() {
    	return Status;
    }
    
    public String getTransactionDate() {
    	return TransactionDate;
    }
    
    public void setPaymentId(String PaymentId) {
    	this.PaymentId = PaymentId;
    }
    
    public void setBookingReference(String BookingReference) {
    	 this.BookingReference = BookingReference;
    }
    
    public void setAmount(double Amount) {
    	this.Amount = Amount;
    }
    
    public void setMethod(String Method) {
    	this.Method = Method;
    }
    
    public void setStatus(String Status) {
    	this.Status = Status;
    }
    
    public void setTransactionDate(String TransactionDate) {
    	this.TransactionDate = TransactionDate;
    }
    
    public boolean ValidatePaymentDetails() {
    	if(Amount > 0 && Method != null && !Method.trim().isEmpty()) {
    		return true;
    	}else {
    		return false;
    	}
    } 
    
    public boolean processPayment() {
        if (!ValidatePaymentDetails()) {
            Status = "Failed";
            return false;
        }

        boolean methodAccepted = false;

          for (String m : acceptedMethods) {
            if (Method.equalsIgnoreCase(m)) {
                methodAccepted = true;
                break;
            }
        }

        if (!methodAccepted) {
            Status = "Rejected";
            return false;
        }

        Status = "Confirmed";
        return true;
    }
 
    public void UpdateStatus(String NewStatus) {
    	this.Status = NewStatus;
    }
}

 class Bookingsystem {
    private ArrayList<Flight> flights;
    private ArrayList<Booking> bookings;

    public Bookingsystem() {
        flights = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public void searchFlights(String origin, String destination) {
        boolean found = false;
        System.out.println("Searching for flights from " + origin + " to " + destination + ":");

        for (Flight flight : flights) {
            if (flight.getOrigin().equalsIgnoreCase(origin) &&
                    flight.getDestination().equalsIgnoreCase(destination)) {
                flight.displayFlightInfo(); 
                System.out.println("------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching flights found.");
        }}
    public void createBooking(Customer customer, Flight flight, ArrayList<Passenger> passengers, int[] seatIndexes, ArrayList<String> seatClasses) {
        if (passengers.size() != seatIndexes.length || passengers.size() != seatClasses.size()) {
            System.out.println("Mismatch in number of passengers, seats, or classes.");
            return;
        }

        for (int i = 0; i < passengers.size(); i++) {
            boolean reserved = flight.reserveSeat(seatIndexes[i], customer);
            if (!reserved) {
                System.out.println("Failed to reserve seat " + seatIndexes[i] + " for " + passengers.get(i).getName());
                return;
            }
        }
        String bookingRef = "BK-" + UUID.randomUUID().toString().substring(0, 6);

        Booking newBooking = new Booking(bookingRef, customer, flight, passengers);

        for (int i = 0; i < seatIndexes.length; i++) {
            newBooking.getSeatClasses().add("Seat " + seatIndexes[i] + " - " + seatClasses.get(i));
        }

        newBooking.confirmBooking();

        bookings.add(newBooking);

        customer.createBooking(flight, seatClasses.get(0), seatIndexes[0]); 
    }
    public void processPayment(String bookingReference, String paymentMethod) {
        for (Booking booking : bookings) {
            if (booking.getBookingReference().equals(bookingReference)) {
                if (booking.getPaymentStatus().equalsIgnoreCase("Paid")) {
                    System.out.println("Payment already processed for this booking.");
                    return;
                }

                double amount = booking.calculateTotalPrice();
                System.out.println("Processing payment of $" + amount + " via " + paymentMethod + "...");

                booking.setPaymentStatus("Paid");
                booking.setStatus("Confirmed");
                System.out.println("Payment successful. Booking confirmed!");
                return;
            }
        }

        System.out.println("Booking not found with reference: " + bookingReference);
    }

    public void generateTicket(String bookingReference) {
        for (Booking booking : bookings) {
            if (booking.getBookingReference().equalsIgnoreCase(bookingReference)) {

                if (!booking.getPaymentStatus().equalsIgnoreCase("Paid")) {
                    System.out.println("Cannot generate ticket: Payment is not completed.");
                    return;
                }

                Flight flight = booking.getFlight();
                Customer customer = booking.getCustomer();

                System.out.println("========== TICKET ==========");
                System.out.println("Booking Reference: " + booking.getBookingReference());
                System.out.println("Customer: " + customer.getName());
                System.out.println("Flight: " + flight.getFlightNumber());
                System.out.println("Airline: " + flight.getAirLine());
                System.out.println("Route: " + flight.getOrigin() + " → " + flight.getDestination());
                System.out.println("Departure: " + flight.getDepartureTime());
                System.out.println("Arrival: " + flight.getArrivalTime());
                System.out.println("Passengers & Seats:");

                ArrayList<Passenger> passengers = booking.getPassengers();
                ArrayList<String> seats = booking.getSeatSelections();

                for (int i = 0; i < passengers.size(); i++) {
                    String seat = (i < seats.size()) ? seats.get(i) : "Unassigned";
                    Passenger p = passengers.get(i);
                    System.out.println("- " + p.getName() + " | Passport: " + p.getPassportNumber() + " | " + seat);
                }

                System.out.println("Status: " + booking.getStatus());
                System.out.println("Payment: " + booking.getPaymentStatus());
                System.out.println("============================");
                return;
            }
        }

        System.out.println("No booking found with reference: " + bookingReference);
        }
 }
 class  FileManager{
	 private static final String USERS_FILE = "users.csv";
	 private static final String FLIGHTS_FILE = "flights.csv";
	 private static final String BOOKINGS_FILE = "bookings.csv";
	   
	 public void saveUsers(List<User> users) throws IOException {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
	            for (User user : users) {
	                if (user instanceof Customer customer) {
	                    writer.println("Customer," + customer.getUserID() + "," + customer.getUserName() + "," +
	                                  customer.getPassword() + "," + customer.getName() + "," + customer.getEmail() + "," +
	                                  customer.getContactInfo() + "," + customer.getCustomerId() + "," + customer.getAddress());
	                } else if (user instanceof Agent agent) {
	                    writer.println("Agent," + agent.getUserID() + "," + agent.getUserName() + "," +
	                                  agent.getPassword() + "," + agent.getName() + "," + agent.getEmail() + "," +
	                                  agent.getContactInfo() + "," + agent.getAgentId() + "," + agent.getDepartment() + "," +
	                                  agent.getCommission());
	                } else if (user instanceof Administrator admin) {
	                    writer.println("Administrator," + admin.getUserID() + "," + admin.getUserName() + "," +
	                                  admin.getPassword() + "," + admin.getName() + "," + admin.getEmail() + "," +
	                                  admin.getContactInfo() + "," + admin.getAdminId() + "," + admin.getSecurityLevel());
	                }
	            }
	            System.out.println("Users saved successfully.");
	        }
	    }

	    public ArrayList<User> loadUsers() throws IOException {
	        ArrayList<User> users = new ArrayList<>();
	        File file = new File(USERS_FILE);
	        if (!file.exists()) return users;
	        try (Scanner reader = new Scanner(file)) {
	            while (reader.hasNextLine()) {
	                String[] data = reader.nextLine().split(",");
	                if (data.length >= 8) {
	                    String userType = data[0];
	                    if (userType.equals("Customer") && data.length == 9) {
	                        users.add(new Customer(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
	                    } else if (userType.equals("Agent") && data.length == 10) {
	                        users.add(new Agent(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], Double.parseDouble(data[9])));
	                    } else if (userType.equals("Administrator") && data.length == 9) {
	                        users.add(new Administrator(data[1], data[2], data[3], data[4], data[5], data[6], data[7], Integer.parseInt(data[8])));
	                    }
	                }
	            }
	        }
	        return users;
	    }

	    public void saveFlights(List<Flight> flights) throws IOException {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(FLIGHTS_FILE))) {
	            for (Flight flight : flights) {
	                if (flight != null) {
	                    writer.println(flight.getFlightNumber() + "," + flight.getbasePrice() + "," + flight.getAirLine() + "," +
	                                  flight.getOrigin() + "," + flight.getDestination() + "," + flight.getAvailableSeates() + "," +
	                                  flight.getDepartureTime() + "," + flight.getArrivalTime());
	                }
	            }
	            System.out.println("Flights saved successfully.");
	        }
	    }

	    public ArrayList<Flight> loadFlights() throws IOException {
	        ArrayList<Flight> flights = new ArrayList<>();
	        File file = new File(FLIGHTS_FILE);
	        if (!file.exists()) return flights;
	        try (Scanner reader = new Scanner(file)) {
	            while (reader.hasNextLine()) {
	                String line = reader.nextLine();
	                String[] data = line.split(",");
	                if (data.length == 8) {
	                    int flightNumber = Integer.parseInt(data[0]);
	                    double basePrice = Double.parseDouble(data[1]);
	                    String airLine = data[2];
	                    String origin = data[3];
	                    String destination = data[4];
	                    int availableSeats = Integer.parseInt(data[5]);
	                    String departureTime = data[6];
	                    String arrivalTime = data[7];
	                    flights.add(new Flight(flightNumber, basePrice, airLine, origin, destination, availableSeats, departureTime, arrivalTime));
	                }
	            }
	        }
	        return flights;
	    }

	    public void saveBookings(List<Booking> bookings) throws IOException {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKINGS_FILE))) {
	            for (Booking booking : bookings) {
	                if (booking != null) {
	                    StringBuilder passengersData = new StringBuilder();
	                    for (Passenger p : booking.getPassengers()) {
	                        passengersData.append(p.getPassengerId()).append("|").append(p.getName()).append(";");
	                    }
	                    String passengersStr = passengersData.length() > 0 ? passengersData.substring(0, passengersData.length() - 1) : "";
	                    String seatSelections = String.join(";", booking.getSeatSelections());
	                    writer.println(booking.getBookingReference() + "," + booking.getCustomer().getCustomerId() + "," +
	                                  booking.getFlight().getFlightNumber() + "," + passengersStr + "," + seatSelections + "," +
	                                  booking.getStatus() + "," + booking.getPaymentStatus());
	                }
	            }
	            System.out.println("Bookings saved successfully.");
	        }
	    }

	    public ArrayList<Booking> loadBookings(ArrayList<Customer> customers, ArrayList<Flight> flights) throws IOException {
	        ArrayList<Booking> bookings = new ArrayList<>();
	        File file = new File(BOOKINGS_FILE);
	        if (!file.exists()) return bookings;
	        try (Scanner reader = new Scanner(file)) {
	            while (reader.hasNextLine()) {
	                String line = reader.nextLine();
	                String[] data = line.split(",", -1);
	                if (data.length == 7) {
	                    String bookingReference = data[0];
	                    String customerId = data[1];
	                    int flightNumber = Integer.parseInt(data[2]);
	                    String[] passengerData = data[3].split(";");
	                    ArrayList<Passenger> passengers = new ArrayList<>();
	                    for (String p : passengerData) {
	                        if (!p.isEmpty()) {
	                            String[] pData = p.split("\\|");
	                            passengers.add(new Passenger(pData[1], pData[0], "Unknown", "", "Unknown"));
	                        }
	                    }
	                    String[] seatSelections = data[4].split(";");
	                    String status = data[5];
	                    String paymentStatus = data[6];

	                    Customer customer = customers.stream()
	                        .filter(c -> c.getCustomerId().equals(customerId))
	                        .findFirst().orElse(null);
	                    Flight flight = flights.stream()
	                        .filter(f -> f.getFlightNumber() == flightNumber)
	                        .findFirst().orElse(null);

	                    if (customer != null && flight != null) {
	                        Booking booking = new Booking(bookingReference, customer, flight, passengers);
	                        booking.setStatus(status);
	                        booking.setPaymentStatus(paymentStatus);
	                        booking.getSeatSelections().addAll(Arrays.asList(seatSelections));
	                        bookings.add(booking);
	                    }
	                }
	            }
	        }
	        return bookings;
	    }
	}

 public class App {
     public static void main(String[] args) {
         Scanner input = new Scanner(System.in);

         Customer c = new Customer("123456", "username2", "pass123", "name2", "test@gmail.com", "01234567890", "5254", "address123");
         Administrator a = new Administrator("789", "username3", "pass123", "name3", "admin@gmail.com", "01234567890", "528", 5);
         Agent aa = new Agent("456", "username1", "pass123", "name1", "agent@gmail.com", "01234567890", "1234", "dept123", 0.5);
         Flight[] flights = new Flight[2];
         flights[0] = new Flight(101, 200.0, "EgyptAir", "Cairo", "London", 50, "2025-05-12 10:00", "2025-05-12 14:00");
         flights[1] = new Flight(102, 150.0, "Emirates", "Cairo", "Dubai", 51, "2025-05-13 08:00", "2025-05-13 12:00");

         while (true) {
             System.out.println("_____Welcome_____");
             System.out.println("Choose Your Type Number :");
             System.out.println("1. Customer\n2. Agent\n3. Administrator");

             String typeInput = input.nextLine();
             int x = 0;
             if (typeInput.equals("1") || typeInput.equals("2") || typeInput.equals("3")) {
                 x = Integer.parseInt(typeInput);
             } else {
                 System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                 continue;
             }

             String username;
             String password;
             int maxAttempts = 3;
             int attempts = 0;
             boolean loggedIn = false;

             switch (x) {
                 case 1:
                     System.out.println("___Login Please___");
                     while (attempts < maxAttempts && !loggedIn) {
                         System.out.println("Enter Your Username (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                         username = input.nextLine();
                         if (username == null || username.trim().isEmpty()) {
                             System.out.println("Username cannot be empty! Please try again.");
                             attempts++;
                             continue;
                         }

                         System.out.println("Enter Your Password: ");
                         password = input.nextLine();
                         if (password == null || password.trim().isEmpty()) {
                             System.out.println("Password cannot be empty! Please try again.");
                             attempts++;
                             continue;
                         }

                         loggedIn = c.login(username, password);
                         if (!loggedIn) {
                             System.out.println("Login failed! Incorrect username or password. Please try again.");
                             attempts++;
                         }
                     }
                     if (!loggedIn) {
                         System.out.println("Maximum login attempts reached. Returning to main menu...");
                         continue;
                     }
                     boolean customerMenu = true;
                     while (customerMenu) {
                         System.out.println("\nCustomer Menu:");
                         System.out.println("1. Search Flight");
                         System.out.println("2. Create Booking");
                         System.out.println("3. View Bookings");
                         System.out.println("4. Cancel Booking");
                         System.out.println("5. Update Profile");
                         System.out.println("6. Logout");
                         System.out.print("Enter Your Choice: ");
                         String choiceInput = input.nextLine();
                         int choice = 0;
                         if (choiceInput.matches("[1-6]")) {
                             choice = Integer.parseInt(choiceInput);
                         } else {
                             System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                             continue;
                         }

                         switch (choice) {
                             case 1:
                                 System.out.print("Enter Origin: ");
                                 String origin = input.nextLine();
                                 System.out.print("Enter Destination: ");
                                 String destination = input.nextLine();
                                 System.out.println("Enter The Date: ");
                                 String date = input.nextLine();
                                 c.searchFlights(flights, origin, destination,date);
                                 break;

                             case 2:
                                 System.out.println("Available Flights:");
                                 for (Flight f : flights) {
                                     if (f != null) {
                                         f.displayFlightInfo();
                                         System.out.println("Available Seats: " + f.getAvailableSeates());
                                     }
                                 }
                                 System.out.print("Enter Flight Number: ");
                                 String flightNumInput = input.nextLine();
                                 if (!flightNumInput.matches("\\d+")) {
                                     System.out.println("Invalid flight number!");
                                     break;
                                 }
                                 int flightNum = Integer.parseInt(flightNumInput);
                                 Flight selectedFlight = null;
                                 for (Flight f : flights) {
                                     if (f != null && f.getFlightNumber() == flightNum) {
                                         selectedFlight = f;
                                         break;
                                     }
                                 }
                                 if (selectedFlight == null) {
                                     System.out.println("Flight not found!");
                                     break;
                                 }
                                 System.out.print("Enter Seat Class (Economy/Business/First Class): ");
                                 String seatClass = input.nextLine();
                                 if (seatClass == null || seatClass.trim().isEmpty()) {
                                     System.out.println("Seat class cannot be empty!");
                                     break;
                                 }
                                 System.out.print("Enter Seat Index: ");
                                 String seatIndexInput = input.nextLine();
                                 if (!seatIndexInput.matches("\\d+")) {
                                     System.out.println("Invalid seat index!");
                                     break;
                                 }
                                 int seatIndex = Integer.parseInt(seatIndexInput);
                                 c.createBooking(selectedFlight, seatClass, seatIndex);
                                 break;

                             case 3:
                                 c.viewBookings();
                                 break;

                             case 4:
                                 System.out.println("Your Bookings:");
                                 c.viewBookings();
                                 System.out.print("Enter Booking to Cancel (e.g., Flight 101 (economy)): ");
                                 String booking = input.nextLine();
                                 if (booking == null || booking.trim().isEmpty()) {
                                     System.out.println("Booking cannot be empty!");
                                     break;
                                 }
                                 if (!booking.matches("Flight \\d+ \\(Economy|Business|First Class\\)")) {
                                     System.out.println("Invalid booking format! Please enter in the format 'Flight <number> (<class>)'");
                                     break;
                                 }
                                 Flight associatedFlight = null;
                                 for (Flight f : flights) {
                                     if (booking.contains(String.valueOf(f.getFlightNumber()))) {
                                         associatedFlight = f;
                                         break;
                                     }
                                 }
                                 c.cancelBooking(booking, associatedFlight);
                                 break;

                             case 5:
                                 System.out.print("Enter New Name: ");
                                 String newName = input.nextLine();
                                 System.out.print("Enter New Email: ");
                                 String newEmail = input.nextLine();
                                 System.out.print("Enter New Password: ");
                                 String newPassword = input.nextLine();
                                 System.out.print("Enter New Contact Info: ");
                                 String newContact = input.nextLine();
                                 System.out.print("Enter New Address: ");
                                 String newAddress = input.nextLine();
                                 Customer updatedCustomer = new Customer("", "", newPassword, newName, newEmail, newContact, "", newAddress);
                                 c.updateProfile(updatedCustomer, null, null);
                                 break;

                             case 6:
                                 c.logout();
                                 customerMenu = false;
                                 break;
                         }
                     }
                     break;

                 case 2:
                     System.out.println("___Login Please___");
                     while (attempts < maxAttempts && !loggedIn) {
                         System.out.println("Enter Your Username (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                         username = input.nextLine();
                         if (username == null || username.trim().isEmpty()) {
                             System.out.println("Username cannot be empty! Please try again.");
                             attempts++;
                             continue;
                         }

                         System.out.println("Enter Your Password: ");
                         password = input.nextLine();
                         if (password == null || password.trim().isEmpty()) {
                             System.out.println("Password cannot be empty! Please try again.");
                             attempts++;
                             continue;
                         }

                         loggedIn = aa.login(username, password);
                         if (!loggedIn) {
                             System.out.println("Login failed! Incorrect username or password. Please try again.");
                             attempts++;
                         }
                     }
                     if (!loggedIn) {
                         System.out.println("Maximum login attempts reached. Returning to main menu...");
                         continue;
                     }
                     boolean agentMenu = true;
                     while (agentMenu) {
                         System.out.println("\nAgent Menu:");
                         System.out.println("1. Manage Flights");
                         System.out.println("2. Create Booking for Customer");
                         System.out.println("3. Modify Booking");
                         System.out.println("4. Generate Reports");
                         System.out.println("5. Update Profile");
                         System.out.println("6. Logout");
                         System.out.print("Enter Your Choice: ");
                         String choiceInput = input.nextLine();
                         int choice = 0;
                         if (choiceInput.matches("[1-6]")) {
                             choice = Integer.parseInt(choiceInput);
                         } else {
                             System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                             continue;
                         }

                         switch (choice) {
                             case 1:
                                 System.out.print("Enter Action (Add/Delete/Update): ");
                                 String action = input.nextLine();
                                 if (!action.equalsIgnoreCase("Add") && !action.equalsIgnoreCase("Delete") && !action.equalsIgnoreCase("Update")) {
                                     System.out.println("Invalid action! Please enter Add, Delete, or Update.");
                                     break;
                                 }
                                 System.out.print("Enter Flight Number: ");
                                 String flightNumInput = input.nextLine();
                                 if (!flightNumInput.matches("\\d+")) {
                                     System.out.println("Invalid flight number!");
                                     break;
                                 }
                                 int flightNum = Integer.parseInt(flightNumInput);
                                 Flight flight = new Flight(flightNum, 200.0, "EgyptAir", "Cairo", "London", 50, "2025-05-12 10:00", "2025-05-12 14:00");
                                 aa.ManageFlights(flight, action);
                                 break;

                             case 2:
                                 System.out.print("Enter Flight Number: ");
                                 flightNumInput = input.nextLine();
                                 if (!flightNumInput.matches("\\d+")) {
                                     System.out.println("Invalid flight number!");
                                     break;
                                 }
                                 flightNum = Integer.parseInt(flightNumInput);
                                 Flight selectedFlight = null;
                                 for (Flight f : flights) {
                                     if (f != null && f.getFlightNumber() == flightNum) {
                                         selectedFlight = f;
                                         break;
                                     }
                                 }
                                 if (selectedFlight == null) {
                                     System.out.println("Flight not found!");
                                     break;
                                 }
                                 System.out.print("Enter Seat Class (Economy/Business/First Class): ");
                                 String seatClass = input.nextLine();
                                 if (seatClass == null || seatClass.trim().isEmpty()) {
                                     System.out.println("Seat class cannot be empty!");
                                     break;
                                 }
                                 System.out.print("Enter Seat Index: ");
                                 String seatIndexInput = input.nextLine();
                                 if (!seatIndexInput.matches("\\d+")) {
                                     System.out.println("Invalid seat index!");
                                     break;
                                 }
                                 int seatIndex = Integer.parseInt(seatIndexInput);
                                 ArrayList<Passenger> passengers = new ArrayList<>();
                                 passengers.add(new Passenger("Passenger1", "1001", "P123456", "Wheelchair", "1990-01-01"));
                                 ArrayList<Integer> seatIndexes = new ArrayList<>();
                                 seatIndexes.add(seatIndex);
                                 aa.createBookingForCustomer(c, selectedFlight, passengers, seatClass, seatIndexes, 0.1);
                                 break;

                             case 3:
                                 System.out.print("Enter Booking Reference: ");
                                 String bookingRef = input.nextLine();
                                 if (bookingRef == null || bookingRef.trim().isEmpty()) {
                                     System.out.println("Booking reference cannot be empty!");
                                     break;
                                 }
                                 System.out.print("Enter New Seat Index: ");
                                 String newSeatIndexInput = input.nextLine();
                                 if (!newSeatIndexInput.matches("\\d+")) {
                                     System.out.println("Invalid seat index!");
                                     break;
                                 }
                                 int newSeatIndex = Integer.parseInt(newSeatIndexInput);
                                 System.out.print("Enter New Seat Class (Economy/Business/First Class): ");
                                 String newSeatClass = input.nextLine();
                                 if (newSeatClass == null || newSeatClass.trim().isEmpty()) {
                                     System.out.println("Seat class cannot be empty!");
                                     break;
                                 }
                                 ArrayList<Integer> newSeatIndexes = new ArrayList<>();
                                 newSeatIndexes.add(newSeatIndex);
                                 ArrayList<Passenger> newPassengers = new ArrayList<>();
                                 newPassengers.add(new Passenger("Passenger2", "1002", "P654321", "Vegetarian Meal", "1995-01-01"));
                                 aa.modifyBooking(bookingRef, newSeatIndexes, newSeatClass, 0.1, newPassengers);
                                 break;

                             case 4:
                                 aa.generateReports();
                                 break;

                             case 5:
                                 System.out.print("Enter New Name: ");
                                 String newName = input.nextLine();
                                 System.out.print("Enter New Email: ");
                                 String newEmail = input.nextLine();
                                 System.out.print("Enter New Password: ");
                                 String newPassword = input.nextLine();
                                 System.out.print("Enter New Contact Info: ");
                                 String newContact = input.nextLine();
                                 System.out.print("Enter New Department: ");
                                 String newDept = input.nextLine();
                                 Agent updatedAgent = new Agent("", "", newPassword, newName, newEmail, newContact, "", newDept, 0.5);
                                 aa.updateProfile(null, updatedAgent, null);
                                 break;

                             case 6:
                                 aa.logout();
                                 agentMenu = false;
                                 break;
                         }
                     }
                     break;

                 case 3:
                     System.out.println("___Login Please___");
                     while (attempts < maxAttempts && !loggedIn) {
                         System.out.println("Enter Your Username (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                         username = input.nextLine();
                         if (username == null || username.trim().isEmpty()) {
                             System.out.println("Username cannot be empty! Please try again.");
                             attempts++;
                             continue;
                         }

                         System.out.println("Enter Your Password: ");
                         password = input.nextLine();
                         if (password == null || password.trim().isEmpty()) {
                             System.out.println("Password cannot be empty! Please try again.");
                             attempts++;
                             continue;
                         }

                         loggedIn = a.login(username, password);
                         if (!loggedIn) {
                             System.out.println("Login failed! Incorrect username or password. Please try again.");
                             attempts++;
                         }
                     }
                     if (!loggedIn) {
                         System.out.println("Maximum login attempts reached. Returning to main menu...");
                         continue;
                     }
                     boolean adminMenu = true;
                     while (adminMenu) {
                         System.out.println("\nAdministrator Menu:");
                         System.out.println("1. Create User");
                         System.out.println("2. View System Logs");
                         System.out.println("3. Manage User Access");
                         System.out.println("4. Modify System Settings");
                         System.out.println("5. Update Profile");
                         System.out.println("6. Logout");
                         System.out.print("Enter Your Choice: ");
                         String choiceInput = input.nextLine();
                         int choice = 0;
                         if (choiceInput.matches("[1-6]")) {
                             choice = Integer.parseInt(choiceInput);
                         } else {
                             System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                             continue;
                         }

                         switch (choice) {
                             case 1:
                                 System.out.print("Enter User Type (Customer/Agent): ");
                                 String userType = input.nextLine();
                                 if (userType == null || userType.trim().isEmpty() || 
                                     (!userType.equalsIgnoreCase("Customer") && !userType.equalsIgnoreCase("Agent"))) {
                                     System.out.println("Invalid user type! Please enter Customer or Agent.");
                                     break;
                                 }
                                 System.out.print("Enter User ID: ");
                                 String userID = input.nextLine();
                                 System.out.print("Enter Username: ");
                                 String newUsername = input.nextLine();
                                 if (newUsername == null || newUsername.trim().isEmpty()) {
                                     System.out.println("Username cannot be empty!");
                                     break;
                                 }
                                 System.out.print("Enter Password: ");
                                 String newPassword = input.nextLine();
                                 if (newPassword == null || newPassword.trim().isEmpty()) {
                                     System.out.println("Password cannot be empty!");
                                     break;
                                 }
                                 System.out.print("Enter Name: ");
                                 String newName = input.nextLine();
                                 System.out.print("Enter Email: ");
                                 String newEmail = input.nextLine();
                                 if (!newEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                                     System.out.println("Invalid email format!");
                                     break;
                                 }
                                 System.out.print("Enter Contact Info: ");
                                 String newContact = input.nextLine();
                                 if (!newContact.matches("\\d{10,13}")) {
                                     System.out.println("Invalid contact info! Must be 10-13 digits.");
                                     break;
                                 }
                                 System.out.print("Enter Additional ID (Customer ID/Agent ID): ");
                                 String additionalId = input.nextLine();
                                 System.out.print("Enter Address (for Customer) or Department (for Agent): ");
                                 String deptOrAddress = input.nextLine();
                                 a.createUser(userType, userID, newUsername, newPassword, newName, newEmail, newContact, additionalId, deptOrAddress);
                                 System.out.println(userType + " " + newUsername + " created successfully.");
                                 break;

                             case 2:
                                 a.viewSystemLogs();
                                 break;

                             case 3:
                                 System.out.print("Enter User ID: ");
                                 userID = input.nextLine();
                                 System.out.print("Enter Action (Suspend/Reactivate/Delete): ");
                                 String action = input.nextLine();
                                 if (!action.equalsIgnoreCase("Suspend") && !action.equalsIgnoreCase("Reactivate") && !action.equalsIgnoreCase("Delete")) {
                                     System.out.println("Invalid action! Please enter Suspend, Reactivate, or Delete.");
                                     break;
                                 }
                                 a.manageUserAccess(userID, action);
                                 break;

                             case 4:
                                 a.ModifySystemSetting();
                                 break;

                             case 5:
                                 System.out.print("Enter New Name: ");
                                 newName = input.nextLine();
                                 System.out.print("Enter New Email: ");
                                 newEmail = input.nextLine();
                                 System.out.print("Enter New Username: ");
                                 newUsername = input.nextLine();
                                 if (newUsername == null || newUsername.trim().isEmpty()) {
                                     System.out.println("Username cannot be empty!");
                                     break;
                                 }
                                 System.out.print("Enter New Password: ");
                                 newPassword = input.nextLine();
                                 System.out.print("Enter New Contact Info: ");
                                 newContact = input.nextLine();
                                 System.out.print("Enter New Admin ID: ");
                                 String newAdminId = input.nextLine();
                                 Administrator updatedAdmin = new Administrator("", newUsername, newPassword, newName, newEmail, newContact, newAdminId, 5);
                                 a.updateProfile(null, null, updatedAdmin);
                                 break;

                             case 6:
                                 System.out.println("Logged out successfully.");
                                 a.logout();
                                 adminMenu = false;
                                 break;
                         }
                     }
                     break;
             }
         }
     }
 }