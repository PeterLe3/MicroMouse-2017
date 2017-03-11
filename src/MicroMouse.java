import java.util.Arrays;

public class MicroMouse {
  
   static int[][] mazeAdjList = new int[256][4];
   static int current = Direction.EAST;
   static int listSize = 4;
   static int board = 16;
   static int row = 0;
   static int col = 0;
   static int[][] goal = {{7,7},{7,8},{8,7},{8,8}};
   static int index = 0;
   static int left = 0;
   static int right = 0;
   static int forward = 0;
   static int weight[] = {6,2,1,8};
   static LoadMaze maze;
   static int[][] wMazeList = new int[256][4];
   public static void main(String[] args) {
      try {
         maze = new LoadMaze();  
         fillArray();
         gatherSensor();
         updateAdjList();
         int choice = makeDecision();
         System.out.println(choice);
      } catch(Exception e) {
         System.out.println(e);
      }
   }
   
   private static void updateAdjList() {
      for(int i = 0; i < 4; ++i) {
         if (mazeAdjList[index][i] != -1) 
            wMazeList[index][i] = weight[i];
         else
            wMazeList[index][i] = 9999;
      }
   }
   
   private static int makeDecision() {
      int choice = 0;
      for(int i = 1; i < 4; ++i)
         if (wMazeList[index][choice] > wMazeList[index][i])
            choice = i;
      return mazeAdjList[index][choice];
   }
   
   private static void fillArray() {
      for(int i = 0; i < 256; ++i) {
         if (i == 0) {                       //Top-left Corner
            mazeAdjList[i][0] = -1;  
            mazeAdjList[i][1] = i+1;
            mazeAdjList[i][2] = i + board;
            mazeAdjList[i][3] = -1;
         } else if (i == 15) {               //Top-right corner
            mazeAdjList[i][0] = -1;  
            mazeAdjList[i][1] = -1;
            mazeAdjList[i][2] = i + board;
            mazeAdjList[i][3] = i - 1;
         } else if (i == 240) {              //Bottom-left corner
            mazeAdjList[i][0] = i - board;  
            mazeAdjList[i][1] = i + 1;
            mazeAdjList[i][2] = -1;
            mazeAdjList[i][3] = -1;
         } else if (i == 255) {              //Bottom-right corner
            mazeAdjList[i][0] = i - board;  
            mazeAdjList[i][1] = -1;
            mazeAdjList[i][2] = -1;
            mazeAdjList[i][3] = i - 1;
         } else if (i / board == 0) {        //Top wall
            mazeAdjList[i][0] = -1;  
            mazeAdjList[i][1] = i + 1;
            mazeAdjList[i][2] = i + board;
            mazeAdjList[i][3] = i - 1;
         } else if (i / board == 15) {       //bottom wall                            
            mazeAdjList[i][0] = i - board;  
            mazeAdjList[i][1] = i + 1;
            mazeAdjList[i][2] = -1;
            mazeAdjList[i][3] = i - 1;
         } else if (i % board == 0) {        //Left wall
            mazeAdjList[i][0] = i - board;  
            mazeAdjList[i][1] = i + 1;
            mazeAdjList[i][2] = i + board;
            mazeAdjList[i][3] = -1;
          } else if (i % board == 15) {      //Right wall
            mazeAdjList[i][0] = i - board;  
            mazeAdjList[i][1] = -1;
            mazeAdjList[i][2] = i + board;
            mazeAdjList[i][3] = i - 1;
         } else {                            //All center pieces
            mazeAdjList[i][0] = i - board;  
            mazeAdjList[i][1] = i + 1;
            mazeAdjList[i][2] = i + board;
            mazeAdjList[i][3] = i - 1;
         }  
      }
   }
   
   private static void printArray() {
      for(int i = 0; i < 256; ++i) 
         System.out.printf("%3d %3d %3d %3d %3d\n", i, mazeAdjList[i][0],mazeAdjList[i][1],mazeAdjList[i][2],mazeAdjList[i][3]);
   }
   
   private static void gatherSensor() {
      for(int i = 0; i < 4; ++i) {
         boolean found = false;
         for(int j = 0; j < 4; ++j) {
            if (mazeAdjList[index][i] == maze.getEdge(index, j))
                found = true;
         }
         if (!found)
         mazeAdjList[index][i] = -1;
      }
   }
  /* public static void gatherSensor() {
		
		 //for the row, index matches up with the predefined directions
		 //if between 0-9, update that valid to turn left
		 int off = 0;
       if(left < 9)  { 
         off = (current + listSize -1)% listSize;
		   mazeAdjList[index][off] = weight[off];
		  
		 }
		 else { 
		     mazeAdjList[index][(current + listSize -1)% listSize] = -1;
		    
		    }
		 // check right and update list
		 if(right < 9)  { 
         off = (current + listSize + 1)% listSize;
		   mazeAdjList[index][off] = weight[off];
		  
		 } else { 
		     mazeAdjList[index][(current + listSize +1)% listSize] = -1;
		
		     
		  }
		 // checks sensor for center
		 if(forward < 9)  { 
         off = (current + listSize)% listSize;
		   mazeAdjList[index][off] = weight[off];
		 } else { 
		     mazeAdjList[index][(current + listSize)% listSize] = -1;
		 } 
   } */
}
            
