package grandtournament;
import java.util.*;

class Default {
	static Scanner stringInput = new Scanner(System.in);
	static Scanner intInput = new Scanner(System.in);
	static Admin a1 = new Admin();
	static Admin z1 = new Admin();
	static Admin z2 = new Admin();
	static int max = 32;
	public static void autoGen() {
		a1.reg("Rockman","MegaBuster");
		a1.reg("Bombman","Kaboom");
		a1.reg("Elecman","Thunter");
		a1.reg("Fireman","Burning");
		a1.reg("Heatman","HotPot");
		a1.reg("Dustman","Clearner");
		a1.reg("Iceman","SnowBall");
		a1.reg("Airman","Tornado");
		a1.reg("Woodman","Forest");
		a1.reg("Plugman","TurnOn");
	}
	public static void printList(Admin a) {
		for(int i=0;i<a.register.size();i++) 
			System.out.println("ID: "+(i+1)+"\t"+a.register.get(i).username);
		System.out.println();
	}
	public static void printListScore() {
		System.out.println("Result: ");
		for(int i=0;i<a1.register.size();i++) 
			System.out.println("ID: "+(i+1)+"\t"+a1.register.get(i).username+"\t   Score: "+a1.register.get(i).score);
		System.out.println();
	}
	public static void printTable(int n) {
		z1.register.clear();
		int round=1, down=1, up=1, number=0;
		while(down<n) {
			down*=2;
			round++;
		}
		
		int m = down;
		int winbye = m-n;
		int extra;
		try {
			extra = m/winbye;
		} catch (Exception e) { extra = m; }
		int ex = extra;
		
		int id[] = new int[m];
		for(int i=0;i<m;i++) {
			if(i==extra-1&&winbye>0) {
				id[i]=0;
				z1.reg("----","Free");
				extra+=ex;
				winbye--;
			} 
			else { 
				id[i]=i+1;
				z1.reg(a1.register.get(number).username,a1.register.get(number).password);
				number++; }	
		}
		
		System.out.println();
		for(int i=1;i<=round;i++) {
			for(int j=down/2;j-1>=0;j--) {
				System.out.print("  ");
			}			
			if(down==1) for(int j=0;j<m;j++) {
				if(id[j]==0) System.out.print("--  "); 
				else if(id[j]>=10) System.out.print(id[j]+"  "); 
				else System.out.print("0"+id[j]+"  ");
			} 
			else {
				for(int j=1;j<=up;j++) {
					for(int k=down/2;k-1>0;k--) System.out.print("__");
					System.out.print("XX");
					for(int k=down/2;k-1>0;k--) System.out.print("__");
					if(j!=up) for(int k=down;k+1>0;k--) System.out.print("  ");
				}	
			}
			up*=2; 
			down/=2;
			System.out.println();
		} 
		System.out.println();
	}
}

class Admin {
	ArrayList<Player> register;

public Admin() {
    register = new ArrayList<Player>();
	}
    public void reg(String username, String password) {
    	Player regPlayer = new Player(username,password);
        register.add(regPlayer);
    }
}

public class Player extends Default {
	String username;
	String password;
	int score;
	
	public Player(String aName,String aPass) {
		this.username = aName;
		this.password = aPass;
		this.score = 0;
		if(aName.equals("----")&&aPass.equals("Free")) this.score = -1;
	}
	public String setName(String newName) {
		this.username = newName;
		return username;
	}
	public String setPass(String newPass) {
		this.password = newPass;
		return password;	
	}
	public String setPass() {
		this.password = "FreePass";
		return password;
	}
	public int win() {
		this.score++;
		return score;
	}
	public int drop() {
		this.score-- ;
		return score;
	}	
	public static void main(String arge[]) {
		String sc1,sc2; int i;
		int start = 0;				
		System.out.println("Please Choose Number of Option\n1.CheckPlayerList\n2.RegisterAccount");
		System.out.println("3.SetUsername\n4.SetPassword\n5.ForgotPassword\n0.StartTournament\n");		
		do {
			System.out.print("Choose Option: ");
			String nme, pas;
			try {
				sc1 = stringInput.nextLine();
			} catch (Exception e) { System.out.println(e); sc1="0"; };
			String sc=sc1.toLowerCase(); 			
			try {
				switch(sc) {
				case("1"): 
					 if(a1.register.size()==0) System.out.println("Nobody registered"); printList(a1); break;
				
				case("2"): 
					if(a1.register.size()<max) {
						System.out.print("Your Username: "); nme = stringInput.nextLine(); 
						System.out.print("Your Password: "); pas = stringInput.nextLine();
						a1.reg(nme,pas);
						System.out.println("Good Luck Have Fun\n");
					} else System.out.println("Register System has been closed.\n"); break;
			
				case("3"):
					System.out.print("Your ID: "); i = intInput.nextInt();
					System.out.print("Your Password: "); pas = stringInput.nextLine();
					if(pas.equals(a1.register.get(i-1).password)) {
						System.out.print("Your New Name: "); nme = stringInput.nextLine();
						a1.register.get(i-1).setName(nme);
					} else System.out.print("Your Password is wrong."); break;
			
				case("4"):
					System.out.print("Your ID: "); i = intInput.nextInt();
					System.out.print("Your Old Password: "); pas = stringInput.nextLine();
					if(pas.equals(a1.register.get(i-1).password)) {
						System.out.print("Your New Password: "); pas = stringInput.nextLine();
						a1.register.get(i-1).setPass(pas);
					} else System.out.print("Your Old Password is wrong."); break;
			
				case("5"): 
					System.out.print("Your ID: "); i = intInput.nextInt();
					a1.register.get(i-1).setPass();
					System.out.println("Your New Password: "+a1.register.get(i-1).password); break;
			
				case("secret"):
					if(a1.register.size()<=max-10) autoGen(); break;
				
				case("0"):
					if(a1.register.size()>1) start = 1; break;
				
				default:
					System.out.println("Invalid Option\nPlease Choose Number of Option\n1.CheckPlayersList\n2.RegisterAccount");
					System.out.println("3.SetUsername\n4.SetPassword\n5.ForgotPassword\n0.StartTournament\n"); break;
				}				
			} catch (Exception e) { System.out.println(e); };			
		} while(start==0);		
		System.out.println("Tournament Begin");
		printTable(a1.register.size());
		printList(z1);

		int m = max;
		while(a1.register.size()<=m/2) m/=2;
		for(int r=2,a=1;r<=m;r*=2,a++) {
			System.out.println("Round "+a+" Start");
			int check=1;
			System.out.println("Please Choose Number of Option\n1.SubmitScore\n2.Drop\n0.EndRound");
			do {
				System.out.print("\nChoose Option: ");
				String pas;
				try {
					sc2 = stringInput.nextLine();
				} catch (Exception e) { System.out.println(e); sc2="0"; };
				String sc=sc2.toLowerCase(); 		
				try {
					switch(sc) {
					case("1"):
						System.out.print("Your R"+start+" ID: "); i = intInput.nextInt();
						if(z1.register.get(i-1).score>=start) System.out.println("You had submitted score already.");
						else {
							z1.register.get(i-1).win(); 
							System.out.println("Good Game"); 
						} break;
					
					case("2"):
						System.out.print("Your R"+start+" ID: "); i = intInput.nextInt();
						System.out.print("Your Password: "); pas = stringInput.nextLine();
						if(pas.equals(z1.register.get(i-1).password)) z1.register.get(i-1).drop();
						else System.out.println("Your Password is wrong."); break;
					
					case("0"):
						z2.register.clear();
						for(int j=0;j<z1.register.size();j+=2) {
							if(z1.register.get(j).score==-1&&z1.register.get(j+1).score<start) z1.register.get(j+1).win();
							if(z1.register.get(j+1).score==-1&&z1.register.get(j).score<start) z1.register.get(j).win();
							if(z1.register.get(j).score==z1.register.get(j+1).score) {
								System.out.println("ID:"+(j+1)+" and ID:"+(j+2)+" have same score "+z1.register.get(j).score); 
								check=0;
							}
							else if(z1.register.get(j).score>z1.register.get(j+1).score) {
								System.out.println(z1.register.get(j).username+" Won");
								z2.reg(z1.register.get(j).username,z1.register.get(j).password);
								
							}
							else {
								System.out.println(z1.register.get(j+1).username+" Won");
								z2.reg(z1.register.get(j+1).username,z1.register.get(j+1).password);
							}
						}
						if(check==1) start++; break;
					
					default:
						System.out.println("Invalid Option\nPlease Choose Number of Option\n1.SubmitScore\n2.Drop\\n0.EndRound"); break;
					}
					check=1;
				} catch (Exception e) { System.out.println(e); }
			} while(start==a);			
			
			if(z2.register.size()!=1) {
				try{
					printTable(z2.register.size());
				} catch (Exception e) { System.out.println(e); }
			} else System.out.println();
			
			z1.register.clear();
			for(int j=0;j<z2.register.size();j++) {
				z1.reg(z2.register.get(j).username,z2.register.get(j).password);
				try {
					for(int p=0;p<a1.register.size();p++) {
						if(z1.register.get(j).username.equals(a1.register.get(p).username)) a1.register.get(p).win();
					}
				} catch (Exception e) { System.out.println(e); }
			}
			if(z2.register.size()!=1)
				printList(z2); 
			else System.out.println("Congratulation\n"+z2.register.get(0).username+" is a Champion.\n");
		}
		printListScore();
	}
}