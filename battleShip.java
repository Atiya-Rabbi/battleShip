import java.util.*;

class battleShip{
static String grid[][] = new String[12][12];
static int usr_x[] = new int[6],usr_y[] = new int[6], cmp_x[]= new int[5], cmp_y[]= new int[5],nof_cmpship=5, nof_usrship=5;

//MAIN METHOD
public static void main(String ...args){
System.out.println("**** Welcome to Battle Ships game****\n\nRight now, the sea is empty.\n");
//FILLING THE INITIAL VALUES
for(int i=0; i<12;i++){
for(int j=0; j<12;j++){
grid[i][j]=" ";
}
}
for(int m =0; m<12;m++){
grid[m][0]=m+"|";
grid[m][11]="|"+m;
}
//DISPLAY OCEAN'S MAP
oceanMap();
//DEPLOY USER'S SHIPS
deploy();
//DEPLOY COMPUTER'S SHIPS
System.out.println("Computer is deploying ships");
compDeploy();
System.out.println("----------------------------------");
//BATTLE STARTS
userFire();
//who won
if(nof_cmpship==0)
System.out.println("You win");
else if(nof_usrship==0)
System.out.println("Computer win");
//MAIN METHOD ENDS
}

//OCEAN'S MAP
public static void oceanMap(){
int num[] = {0,1,2,3,4,5,6,7,8,9};

System.out.println("  0123456789  ");
for(int i = 0; i<10; i++){
for(int j =0; j<12;j++)
System.out.print(grid[i][j]);
System.out.print("\n");
}
System.out.println("  0123456789  ");
//METHOD ENDS
}

//DEPLOYING USER'S SHIP
public static void deploy(){
int tmp_x, tmp_y,usr_len=0;
Scanner input = new Scanner(System.in);
System.out.println("Deploy your ships:");
int flag =0;
for(int k =0; k<5; k++){
System.out.print("Enter X coordinate for your "+(int)(k+1)+". ship: ");
usr_x[k] = input.nextInt();
System.out.print("Enter Y coordinate for your "+(int)(k+1)+". ship: ");
usr_y[k]= input.nextInt();

if((usr_x[k]<0)||(usr_x[k]>9)||(usr_y[k]<0)||(usr_y[k]>9)){
System.out.println("\nPlease enter the correct coordinates\n");
deploy();
flag =1;
break;
}
else{
for(int z=0; z<usr_len;z++){
if((usr_x[z]==usr_x[k])&&(usr_y[z]==usr_y[k])){
System.out.println("\nCaution: two or more ships cannot be deployed at the same location\n");
deploy();
flag=1;
break;
}
}
}
if(flag==1)
break;
usr_len++;
}
for(int p =0; p<5;p++){
tmp_x= usr_y[p];
tmp_y = usr_x[p]+1;
grid[tmp_x][tmp_y]="@";
}
if(flag==0)
oceanMap();
//METHOD ENDS
}

//DEPLOYING COMPUTER'S SHIP
public static int compDeploy(){
Random rand = new Random();
int cmp_len=0,hat=0,tmp_cx,tmp_cy;
for(int n =0; n<5; n++){
cmp_x[n] = rand.nextInt(10);
cmp_y[n] = rand.nextInt(10);
for(int m =0; m<cmp_len;m++){
if((cmp_x[n]==cmp_x[m])&&(cmp_y[n]==cmp_y[m])){
compDeploy();
hat =1;
break;
}
}
if(hat==0){
for(int s=0; s<5;s++){
if(hat ==0){
for(int z =0; z<=cmp_len;z++){
if((usr_x[s]==cmp_x[z])&&(usr_y[s]==cmp_y[z])){
compDeploy();
hat =1;
break;
}
}
}
}
}
cmp_len++;
if(hat==1)
break;
}

if(hat==0){
for(int q =0; q<5;q++){ 
tmp_cx= cmp_y[q];
tmp_cy = cmp_x[q]+1;
grid[tmp_cx][tmp_cy]=" ";
System.out.println(q+1+". ship DEPLOYED");
}
hat =1;
}
return 0;
//METHOD ENDS
}

static int usr_firex [] = new int[100], usr_firey [] = new int[100], count=0, nexa =0;
//USER FIRES
public static void userFire(){
int ag_x, ag_y,missed =1;
Scanner input = new Scanner(System.in);

while(nof_usrship!=0){
System.out.println("YOUR TURN");
System.out.print("Enter x coordinate: ");
usr_firex[count] = input.nextInt();
System.out.print("Enter y coordinate: ");
usr_firey[count] = input.nextInt();
if(usr_firex[count]<0 || usr_firex[count] >9 || usr_firey[count] <0 || usr_firey[count] >9){
System.out.println("Invalid coordinates");
userFire();
nexa = 1;
break;
}
else{
for(int l =0; l<count; l++){
if((usr_firex[l] == usr_firex[count])&&(usr_firey[l]==usr_firey[count])){
System.out.println("You already fired at this location");
userFire();
nexa =1;
break;
}
}
}
if(nexa ==0){
missed =1;
for(int b =0; b<5; b++){
if((cmp_x[b]==usr_firex[count])&&(cmp_y[b] == usr_firey[count])){
System.out.println("Boom! You sunk the ship!");
ag_y = usr_firex[count]+1;
ag_x = usr_firey[count];
grid[ag_x][ag_y] = "!"; 
nof_cmpship--;
missed =0;
}
else if((usr_x[b]==usr_firex[count])&&(usr_y[b] == usr_firey[count])){
System.out.println("Oh no, you sunk your own ship :(");
ag_y = usr_firex[count]+1;
ag_x = usr_firey[count];
grid[ag_x][ag_y] = "x"; 
nof_usrship--;
missed =0;
}
}
if(missed==1){
System.out.println("Sorry, you missed");
ag_y = usr_firex[count]+1;
ag_x = usr_firey[count];
grid[ag_x][ag_y] = "-"; 
}
count++;
System.out.println("COMPUTER'S TURN");
cmpFire();
if(nexa ==0){
oceanMap();
System.out.println("\nYour ships: "+nof_usrship+" | Computer ships: "+nof_cmpship);
System.out.println("-------------------------------");
}
}
}
//METHOD ENDS
}
static int cmp_firex[] = new int[100], cmp_firey[] = new int[100], cmp_count =0,xon=0;
//COMPUTER FIRES
public static void cmpFire(){
int cmp_missed =1,cm_x, cm_y;
Random rand = new Random();
cmp_firex[cmp_count] = rand.nextInt(10);
cmp_firey[cmp_count] = rand.nextInt(10);

for(int c =0; c<count; c++){
if((usr_firex[c]== cmp_firex[cmp_count])&&(usr_firey[c]== cmp_firey[cmp_count])){
cmpFire();
xon=1;
break;
}
else if((cmp_firex[c] == cmp_firex[cmp_count])&&(cmp_firey[c] == cmp_firey[cmp_count])){
if(c!=cmp_count){
cmpFire();
xon=1;
break;
}
}
}
if(xon==0){
cmp_missed =1;
for(int d=0; d<5; d++){
if((cmp_firex[cmp_count]==usr_x[d])&&(cmp_firey[cmp_count]== usr_y[d])){
System.out.println("The Computer sunk one of your ships!");
cm_y = cmp_firex[cmp_count]+1;
cm_x = cmp_firey[cmp_count];
grid[cm_x][cm_y] = "x"; 
nof_usrship--;
cmp_missed=0;
}
else if((cmp_firex[cmp_count]== cmp_x[d])&&(cmp_firey[cmp_count]==cmp_y[d])){
System.out.println("The Computer sunk one of its own ships");
cm_y = cmp_firex[cmp_count]+1;
cm_x = cmp_firey[cmp_count];
grid[cm_x][cm_y] = "!"; 
nof_cmpship--;
cmp_missed=0;
}
}
if(cmp_missed==1){
System.out.println("Computer missed");
}
cmp_count++;
}
//METHOD ENDS
}
//CLASS ENDS HERE
}