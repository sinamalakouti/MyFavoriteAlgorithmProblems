import java.util.Scanner;




class Sat3 {
 int n, m;
 int[][] clauses;
 int[] x, min, clauseValue,max;
 boolean resultFound = false;
 
 Sat3() {
  
  
   Scanner scan = new Scanner( System.in);
   
   n = scan.nextInt();
   m = scan.nextInt();
   

   x = new int[n+1];
   clauses = new int[m][3];

   for(int i = 0;i < m;i++) {
    for(int j=0;j<3;j++) {
     clauses[i][j] = scan.nextInt();
    }
   }
  
  max = new int[m];
  min = new int[m];

  getDepths();
  
  
 }

 void backTrackCompute(int depth) {
  if(clauseValue(depth)==m) {
    resultFound = true;    
   return;
  }

  if(promising(depth) ) {
   x[depth]=1;
   backTrackCompute(depth+1);
   x[depth]=0;
   backTrackCompute(depth+1);
  }
 }



 boolean promising(int varCount) {
  for(int i=0;i<m;i++) {
   if(max[i]==varCount) {
    boolean clauseFalse = true;
    for(int j=0;j<3;j++) {
     if(clauses[i][j]<0) {
      if(x[( -1 * clauses[i][j])-1]==0)
       clauseFalse = false;
     } else {
      if(x[clauses[i][j]-1]==1)
       clauseFalse = false;
     }
    }
    if(clauseFalse) {
     return false;
    }
   }
  }
  return true;
 }


 int clauseValue(int varCount) {
  int countTrue = 0;
  for(int i=0;i<m;i++) {
   if(min[i]<=varCount) {
    for(int j=0;j<3;j++) {
     int c = clauses[i][j];
     if(c<0 && (0-c)<=varCount) {
      if(x[(0-c)-1]==0) {
       countTrue++;
       break;
      }
     } else if(c>0 && c<=varCount) {
      if(x[c-1]==1) {
       countTrue++;
       break;
      }
     }
    }
   }
  }
  return countTrue;
 }

 void getDepths() { 
  for(int i=0;i<m;i++) {
   max[i] = 0;
   min[i] = n;
   for(int j=0;j<3;j++) {
    if(clauses[i][j] < 0) {
     if(min[i] > (0-clauses[i][j])) 
     min[i] = (0-clauses[i][j]);
     if(max[i] < (0-clauses[i][j]))
     max[i] = (0-clauses[i][j]);
    } else {
     if(min[i] > clauses[i][j])
     min[i] = clauses[i][j];
     if(max[i] < clauses[i][j])
     max[i] = clauses[i][j];
    }
   }
  }
 }

 
 

 public static void main(String[] args) {
  String output = "";


  Sat3 sub = new Sat3();

  /*if result is not found*/
  sub.backTrackCompute(0);
  if(!sub.resultFound) {
   output = "NO";

  /*if result is not found with n>5 and m>30*/
  } else if(sub.resultFound ) {
   output = "YES";
  }

  /*calculate running time*/

  /*append the output string to output file*/
  
   System.out.println(output);
  
 }
 

}
