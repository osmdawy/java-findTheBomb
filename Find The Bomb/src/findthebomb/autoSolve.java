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


public class autoSolve {
	NodeStack s=new NodeStack();
	boolean [][][]path;
	public autoSolve(){
		s=new NodeStack();
		path=null;
	}
  public NodeStack getS() {
		return s;
	}
	public void setS(NodeStack s) {
		this.s = s;
	}
	public boolean[][][] getPath() {
		return path;

        }
	public void setPath(boolean[][][] path) {
		this.path = path;
	}

      public   void makePath(Level[]maze,TermQ answer)
        {
	  boolean [][][]lastPath= new boolean[10][][];
    for(int l=0;l<maze.length;l++){
    	if(maze[l]!=null){
    lastPath[l]=new boolean[maze[l].getLevel().length][maze[l].getLevel().length];
    	}
    }
          if(answer!=null){
            while(answer!=null){
       
            	   if(s.isEmpty()||s.top()!=(Integer)answer.level){
    	  s.push(answer.level);
      }
               //    if(maze[answer.level].getLevel()[answer.positionI][answer.positionJ]==' '||maze[answer.level].getLevel()[answer.positionI][answer.positionJ]=='r'){
                lastPath[answer.level][answer.positionI][answer.positionJ]=true;
             //   }


answer=answer.getParent();
            }
            }
          this.setPath(lastPath);
        }
  public static void main (String[]args){
        GenerateLevel lev = new GenerateLevel("C:\\My Documents\\map.txt");
            Level[]array = lev.getLevels();
            TermQ newTerm = new TermQ(2,2);
            newTerm.setLevel(3);
            newTerm.setParent(null);
            QueueAlgorithmFllow x = new QueueAlgorithmFllow();
            TermQ answer = x.QueueAlgoPath(array,newTerm);
            autoSolve solve = new autoSolve();
           solve.makePath(array, answer);
           boolean[][][]ans=solve.path;
           int l=0;
           //while(!solve.s.isEmpty()){
while(l<ans.length){
     //   int rand=(int) (5 * Math.random());
        	  // l=(Integer) solve.s.pop();
                   System.out.println(l);
		if(ans[l]!=null){
       boolean[][]currLevel=ans[l];

        for(int i=0;i<currLevel.length;i++)
        {
            for(int j=0;j<currLevel.length;j++)
            {
       System.out.print(currLevel[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("----------------------------");

	}
l++;
           }
           
    }

      }


  

