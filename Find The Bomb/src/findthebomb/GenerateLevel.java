/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package findthebomb;

/**
 *
 * @author Eng Omnia
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */





import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Eng Omnia
 */
public class GenerateLevel {
Level[] levels = new Level[10];

public Level[]getLevels(){
    return levels;
}
	/**
	 * @param args
	 */

	 public   GenerateLevel(String filename) {

	        BufferedReader bufferedReader = null;
	        String line = "";
	        boolean findB = false;
	        boolean findV = false;
	        boolean findS = false;
	       // Level []levels = new Level[9];
	        int noOfLevelsGiven=0; // number of levels given in the file
	        int noOflevels =0; //counter to match number of levels given with number of levels in the mab
	        try {
if(line==null){
    System.out.println("inavlid map ");
    System.exit(0);
}
	            //Construct the BufferedReader object
	            bufferedReader = new BufferedReader(new FileReader(filename));
	            line=bufferedReader.readLine();
                    if(line==null){
    System.out.println("inavlid map number of levels isn't given");
    System.exit(0);
}
	            if(line.substring(0, line.indexOf("=")+1).equals("nlevels=")){
	                noOfLevelsGiven = Integer.parseInt(line.substring(line.indexOf('=')+1,line.length()));
	            }
	            else
	            {
	            	System.out.println("inavlid map number of levels isn't given");
                           System.exit(0);
	            }
	            // count levels to make sure it's valid

	            while (( (line=bufferedReader.readLine())!= null) ){
	                //Process the data, here we just print it out

	            	int stairsNum =0;
	            	int numOfLevel = 0;
	            	int dimension =0;
	            	String x=line.substring(0, line.indexOf("=")+1);
	                if(x.equals("level=")){ //to check level = is written it's also check if the dimension isn't correct if the loop has finished before or after dimension the it will be a part of map
	                	noOflevels++;

	                 numOfLevel =Integer.parseInt(line.substring(line.indexOf("=")+1, line.length()));//the index of the array "leve"

	                	line = bufferedReader.readLine();
	                	if(line.substring(0, line.indexOf("=")+1).equals("dimension=")){
	                	dimension =Integer.parseInt(line.substring(line.indexOf("=")+1, line.length()));//the dimension of 2D array "map"
	                	}
	                	else{
	                		System.out.println("Invalid map dimension isn't given");
	                	}
	                	line = bufferedReader.readLine();
	                	if(line.charAt(0)=='#'){
	                		line=bufferedReader.readLine();
	                	}
	                	//line = bufferedReader.readLine();
	                	if(line.equals("map=")){
	                	char [][] levelMap = new char [dimension][dimension];
	                	Level level = new Level (levelMap);
                               level.setLevelNo(numOfLevel);
	                	String [] stairs =new String [10];
		            	level.setStairs(stairs);
	                	int i=0;
	                	for(i =0 ;i <dimension ;i++){
                                   
	                		line = bufferedReader.readLine();
                                        
	                		if(line==null||line.length()!=dimension){
	                			System.out.println("invalid Map dimension given is greater than it is in the map");
	    	                	System.exit(0);
	                		}
	                		for (int j =0;j<dimension ;j++){
	                			char ele = line.charAt(j);
                                                //Element element = new Element(ele);
	                			levelMap[i][j]=ele;
	                		try{
	                			stairsNum=Integer.parseInt(ele+"");
	                			stairs[stairsNum]=i+" "+j;
	                		}catch(Exception e){

	                		}
	                			if(ele=='B'){
	                				if(findB==false){
	                				findB=true;
	                				}else{
	                					System.out.println("Invalid map 2 B found");
                                                                System.exit(0);
	                				}
	                			}
	                			if(ele=='V'){
	                				if(findV==false){
	                				findV=true;
	                				}else{
	                					System.out.println("Invalid map 2 v found");
                                                                System.exit(0);
	                				}
	                			}

	                			if(ele=='S'){
	                				if(findS==false){
	                				findS=true;

	                				level.hasSta(true);
                                                        level.setStaX(j);
	                				level.setStaY(i);
	                				}else{
	                					System.out.println("2 start found");
                                                                System.exit(0);
	                				}
	                			}

	                		}

	                	}
	                	if(i!=dimension){
	                		System.out.println("invalid Map dimension given is greater than it is in the map");
		                	System.exit(0);
	                	}
	                	levels [numOfLevel]=level; //-1 because it starts from 1 to 10 but the array is from 0 to 9
	                	}else{
	                		System.out.println("invalid Map map isn't M*M or doesn't match the dimensions given");
		                	System.exit(0);
	                	}
	                }else {
	                	System.out.println("invalid Map");
	                	System.exit(0);
	                }

	            }

	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            //Close the BufferedReader
	            try {
	                if (bufferedReader != null)
	                    bufferedReader.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	        if(findB==false||findV==false||findS==false){
	        	System.out.println("invalid Map");
            	System.exit(0);
	        }
	        if(noOflevels!=noOfLevelsGiven){
	        	System.out.println("invalid Map");
            	System.exit(0);
	        }

	    }
	public void checkStairs() {
		levels = this.getLevels();
		for (int level = 0; level < 9; level++) {
			if (levels[level] != null) {
				char[][] currLevel = levels[level].getLevel();
				for (int i = 0; i < currLevel.length; i++) {
					for (int j = 0; j < currLevel.length; j++) {
						char element = currLevel[i][j];
						int stair = -1;
						try {
							stair = Integer.parseInt(element + "");
							if(levels[stair]!=null){
							if(levels[stair].getStairs()[level]==null){
								System.out.println("invalid stairs");
								System.exit(0);
							}
							}
						} catch (Exception e) {

						}
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
BufferedWriter in = new BufferedWriter(new FileWriter("ana.txt"));
in.write("aho");
in.close();
  //          GenerateLevel lev = new GenerateLevel("C:\\My Documents\\map.txt");
//Level[]array = lev.getLevels();
//Level[]array=readFromFile("C:\\Users\\Eng Omnia\\Desktop\\Map.txt");
//	char [][] level = array[0].getLevel();
//	for(int i=0 ;i<level.length;i++){
//		for(int j=0;j<level.length;j++){
//			System.out.print(level[i][j]);
///		}
//		System.out.println("");
//	}
	//String []arr = array[0].getStairs();
	//System.out.println(arr[2]);
		/*JFrame fram = new JFrame ();
		fram.setSize(900, 800);
		fram.setVisible(true);
		ImageIcon image = new ImageIcon("C:\\NetBeansWork\\PostFix\\src\\postfix\\calc.jpg");
		JLabel label1 = new JLabel();
		label1.setIcon(image);
		label1.setLocation(0,0);
		//label1.setHorizontalAlignment(0);
		//label1.setVerticalAlignment(0);

Canvas myCanvas = new Canvas();
fram.add(myCanvas);
		label1.setVisible(true);
		fram.getContentPane().add(label1);

	//String s = "level=2";
	//System.out.println(s.substring(s.indexOf('=')+1,s.length()));
	 * */

	}

}



