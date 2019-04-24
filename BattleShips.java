import java.util.*;
public class BattleShips {  
    public static String[][] OCEAN_MAP = new String[12][14];
    public static String[][] COMPUTER_MAP = new String[10][10];
    public static int SHIP_NUM;
    public static int FRIENDLY_FIRE = -1;
    public static int PLAYER_HIT = 1;
    public static int PLAYER_MISS = 0;
    public static int COMPUTER_FIRE = -1;
    public static int COMPUTER_HIT = 1;
    public static int COMPUTER_MISS = 0;
    
    public static void sea(){
        System.out.println("**** Welcome to Battle Ships game ****" + "\n");
        System.out.println("Right now, the sea is empty." + "\n");   
        for(int y=0; y<OCEAN_MAP.length; y++){
            for(int x=0; x<OCEAN_MAP[y].length; x++){
                if(y==0 || y==OCEAN_MAP.length-1){
                    if(x<=1 || x>OCEAN_MAP[y].length-3){
                        OCEAN_MAP[y][x] = " ";                        
                    } else {
                       OCEAN_MAP[y][x] = Integer.toString(x-2);
                    }        
                } else{
                    if(x==0 || x==OCEAN_MAP[y].length-1){
                        OCEAN_MAP[y][x] = Integer.toString(y-1);
                    } else if(x==1 || x==OCEAN_MAP[y].length-2){
                        OCEAN_MAP[y][x] = "|";
                    } else{
                        OCEAN_MAP[y][x] = " ";
                    }
                }
            }       
        }
        for(int y=0; y<OCEAN_MAP.length; y++){
            for(int x=0; x<OCEAN_MAP[y].length; x++){
                System.out.print(OCEAN_MAP[y][x]);
            }
            System.out.println(" ");
        }   
    }
   
    public static void computerSea(){
        for(int y=0; y<COMPUTER_MAP.length; y++){
            for(int x=0; x<COMPUTER_MAP[y].length; x++){
                COMPUTER_MAP[y][x] = " ";
            }       
        }
    }
    
    public static void userInput(){
        String shipNumString;
        int inputX, inputY, x, y;
        int counterX = 0;
        int counterY = 0;
        String inputXString, inputYString;       
        Scanner ship = new Scanner(System.in);   
        System.out.print("\nHow many ships do you want to deply? (1-9) ");
        shipNumString = ship.next();
        if(shipNumString.matches("[1-9]+") && shipNumString.length()<2){ 
            SHIP_NUM = Integer.parseInt(shipNumString);
            System.out.println("Deploy your ships:");
            for(int i=0; i<SHIP_NUM; i++){
                ++counterX;
                ++counterY;
                while(true){              
                    System.out.print("Enter X coordinate for your " + (counterX) + ". ship: ");
                    inputXString = ship.next();
                    System.out.print("Enter Y coordinate for your " + (counterY) + ". ship: ");
                    inputYString = ship.next();
                    if(inputXString.matches("[0-9]") && inputYString.matches("[0-9]")){
                        inputX = Integer.parseInt(inputXString);
                        inputY = Integer.parseInt(inputYString);
                        x = inputX + 2;
                        y = inputY + 1;
                        if(OCEAN_MAP[y][x].matches("@")){
                            System.out.println("Can't place ship. Field is occupied by another ship");
                        } else{
                            OCEAN_MAP[y][x] = "@";
                            break;
                        } 
                    } else{
                        System.out.println("That is not a valid integer. You must type positive number in range 0-9");
                    }  
                }
            }
            System.out.println("");
        } else {
            System.out.println("Invalid number. Number must be in range 1-9");
            userInput(); 
        }
    }
    
    public static void computerInput(){
        int counter = 0;
        Random ship = new Random();
        System.out.println("Computer is deploying...");
        for(int i=0; i<SHIP_NUM; i++){ 
            while(true){
                int x = ship.nextInt(10);
                int y = ship.nextInt(10);
                int a = x + 2;
                int b = y + 1;
                if(OCEAN_MAP[b][a].matches(" ")){
                    COMPUTER_MAP[y][x] = "+";
                    System.out.println((++counter) + ". ship DEPLOYED");
                    break;
                }
            }
        }
        System.out.println("----------------------------------------------\n");
    }
    
    public static int playerTurn(int x, int y){
        int a = x + 2;
        int b = y + 1;
        if(COMPUTER_MAP[y][x].equals("+")){
            OCEAN_MAP[b][a] = "!";
            System.out.println("Boom! You sunk the ship! (! mark on map)\n");
            return PLAYER_HIT;
        } else if(OCEAN_MAP[b][a].equals("@")){
            OCEAN_MAP[b][a] = "X";
            System.out.println("Oh no, you sunk your own ship:( (X mark on map)\n");
            return FRIENDLY_FIRE;
        } else{
            OCEAN_MAP[b][a] = "-";
            System.out.println("Sorry you missed (- mark on map)\n");
            return PLAYER_MISS;
        }   
    }
      
    public static int computerTurn(){
        Random missle = new Random();
        int x = missle.nextInt(10);
        int y = missle.nextInt(10);
        int a = x + 2;
        int b = y + 1;
        System.out.println("COMPUTER'S TURN");
        if(OCEAN_MAP[b][a].equals("@")){
            OCEAN_MAP[b][a] = "X";
            System.out.println("The Computer sunk one of your ships! (X mark on map)\n");
            return COMPUTER_HIT;
        } else if(COMPUTER_MAP[y][x].equals("+")){
            OCEAN_MAP[b][a] = "X";
            System.out.println("The Computer sunk one of its own ships (! mark on map)\n");
            return COMPUTER_FIRE;
        } else if(COMPUTER_MAP.equals("-")){
            return computerTurn();
        } else{
            COMPUTER_MAP[y][x] = "-";
            System.out.println("Computer missed");
            System.out.println("");
            return COMPUTER_MISS;
        }
    }
    
    public static void map(){
        for(int y=0; y<OCEAN_MAP.length; y++){
            for(int x=0; x<OCEAN_MAP[y].length; x++){                    
                    System.out.print(OCEAN_MAP[y][x]);
            }
            System.out.println(" ");
        }
        System.out.println("");
    }
    
    ////////////////////// just for testing
    public static void computerMap(){
        for(int a=0; a<COMPUTER_MAP.length; a++){
            for(int b=0; b<COMPUTER_MAP[a].length; b++){                    
                    System.out.print(COMPUTER_MAP[a][b]);
            }
            System.out.println(" ");
        }
    }
    ////////////////////////////////////////
    
    public static void main(String[] args) {
       String stringX, stringY;
       int x, y, point;
       int playerPoints = 0;
       int computerPoints = 0;
       Scanner shoot = new Scanner(System.in);
       computerSea();
       sea();
       userInput();
       computerInput();
       //map after computer turn
       map();
       int playerShips = SHIP_NUM;
       int computerShips = SHIP_NUM;
       while(true){
            System.out.println("\nYOUR TURN");
            System.out.print("Enter X coordinate: ");
            stringX = shoot.next();
            System.out.print("Enter Y coordinate: ");
            stringY = shoot.next();
            if(stringX.matches("[0-9]") && stringY.matches("[0-9]")){
                x = Integer.parseInt(stringX);
                y = Integer.parseInt(stringY);
                /// player turn
                point = playerTurn(x,y); 
                if(point==1){
                    playerPoints++;
                    computerShips--;
                }
                if(point==-1){
                    playerPoints--;
                    playerShips--;
                }
                map();
                // computer turn
                point = computerTurn();
                if(point == 1){
                    computerPoints++;
                    playerShips--;
                }
                if(point == -1){
                    computerPoints--;
                    computerShips--;
                }
                if(computerShips == 0){  
                    map();
                    System.out.println("Your ships: " + playerShips + " | " + "Computer ships: " + computerShips);
                    System.out.println("Horray! You win the battle :)");
                    break;
                }
                if(playerShips == 0){
                   map();
                   System.out.println("Your ships: " + playerShips + " | " + "Computer ships: " + computerShips);
                   System.out.println("Sorry! You are a looser!");
                   break; 
                }
            } else{
                System.out.println("You enter invalid coordinate, try enter in range 0-9");
            }
        }   
    }
}
