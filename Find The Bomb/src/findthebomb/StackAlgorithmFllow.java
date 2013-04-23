package findthebomb;

public class StackAlgorithmFllow 
{
	NodeStack path ;
	private boolean[][][] validation = new boolean [10][][] ;
	
	public StackAlgorithmFllow()
	{
		 path =new NodeStack();
	}
	
	private byte[] getTheLimites(int mapLength , int currentPosI , int currentPosJ)
	{
		byte startPosI= (byte)currentPosI;
		byte startPosJ=(byte) currentPosJ;
		byte endPosI = (byte) currentPosI;
		byte endPosJ = (byte) currentPosJ;
		if(currentPosI-1 >=0)
		{
			startPosI= (byte) (currentPosI-1) ;
		}
		if(currentPosJ-1 >=0)
		{
			startPosJ= (byte) (currentPosJ-1) ;
		}
		if(currentPosI+1 < mapLength)
		{
			endPosI = (byte) (currentPosI+1);
		}
		if(currentPosJ+1 < mapLength)
		{
			endPosJ = (byte) (currentPosJ+1);
		}
		byte [] limites = {startPosI , startPosJ , (byte)(endPosI+1) ,(byte) (endPosJ +1)};
		
		for(int i=0;i<limites.length;i++)
		{
			System.out.print(limites[i]);
			System.out.print(" ");
		}
		System.out.println("");
		
		return limites;
	}
	
	private void creatBooleanMap(TermS startPoint , char[][]map)
	{
		int length= map.length;
		boolean[][] valid =new boolean[length][length];
		for (int i=0 ; i<length ; i++)
		{
			for(int j=0 ;j <length ;j++)
			{
				valid[i][j]=true;
			}
		}
		for (int i=0 ; i<length ; i++)
		{
			for(int j=0 ;j <length ;j++)
			{
				if(map[i][j]=='x')
				{
					valid[i][j]=false;
				}
			}
		}
		valid[startPoint.getPositionI()][startPoint.getPositionJ()]=false;
		for(int k=0 ; k<valid.length;k++)
		{
		    for(int q=0 ; q<valid.length ; q++)
			{
				System.out.print(valid[k][q]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		validation[startPoint.getLevel()]=valid;
	}

	private byte checkPositionChar(char[][]map , TermS currentPoint)
	{
		int positionI =currentPoint.getPositionI();
		int positionJ =currentPoint.getPositionJ();
		char element = map[positionI][positionJ];
		byte answer=-1;
		try
		{
			Integer.parseInt(""+element);
			answer=0;
		}
		catch(Exception e )
		{
			switch (element)
			{
			case 'B' :answer=1 ; 
			case 'V' :answer=1 ; 
			}
		}
		return answer;
	}

	private boolean chooseTheCell(byte[]limites , boolean[][]valid ,TermS currentTerm )
	{
		boolean check =false;
		boolean answer =false;
		char x = 97;
		for(int i=limites[0];i<limites[2];i++)
		{
			for(int j=limites[1];j<limites[3];j++)
			{
				if(valid[i][j])
				{
					TermS newTerm = new TermS(i,j);
					newTerm.setLevel(currentTerm.getLevel());
					//newTerm.setDirection(x);
					currentTerm.setDirection(x);
					System.out.println(newTerm.getLevel());
					System.out.println(newTerm.getPositionI());
					System.out.println(newTerm.getPositionJ());
					valid[i][j]=false;
					path.push(newTerm);
					check=true;
				    answer=true;
				    break;
				}
				x++;
			}
			if(answer)
				break;
		}
		return check;
	}
	
	private boolean chooseTheRigthStair(int currentLevel , int nextLevel, String[]stair)
	{
		boolean found=false;;
		try
		{
			String positions=stair[currentLevel];
			int posI=Integer.parseInt(positions.substring(0,positions.indexOf(' ')));
			int posJ=Integer.parseInt(positions.substring(positions.indexOf(' ')+1,positions.length()));
			TermS newTerm = new TermS (posI , posJ);
		    newTerm.setLevel(nextLevel);
		    System.out.println(newTerm.getLevel());
			System.out.println(newTerm.getPositionI());
			System.out.println(newTerm.getPositionJ());
		    path.push(newTerm);
		    found=true;
		}
		catch(Exception e)
		{
			
		}
		return found;
	}
	
	private void stackAlgoPath(Level[] maze, TermS currentPoint)
	{
		if(maze[currentPoint.getLevel()]==null)
		{
			
		}
		else
		{
			char[][] map =maze[currentPoint.getLevel()].getLevel();
			byte[]limites=getTheLimites(map.length,currentPoint.getPositionI(),currentPoint.getPositionJ());
			if( validation[currentPoint.getLevel()]==null)
			{
				creatBooleanMap(currentPoint, map);
			}
			
			//for(int k=0 ; k< validation[currentPoint.getLevel()].length;k++)
			{
				//for(int q=0 ; q< validation[currentPoint.getLevel()].length ; q++)
				{
					//System.out.print( validation[currentPoint.getLevel()][k][q]);
					//System.out.print(" ");
				}
				//System.out.println("");
			}
			
			if(chooseTheCell(limites, validation[currentPoint.getLevel()],currentPoint))
			{
				TermS newTerm=(TermS)path.top();
				if(checkPositionChar(map, newTerm)!=1)
				{
					stackAlgoPath(maze , newTerm);
				}
				else{System.out.println("frsfsfer");}
			}
			else if(checkPositionChar(map, currentPoint)==0)
			{
				int nextLevel =Integer.parseInt(""+map[currentPoint.getPositionI()][currentPoint.getPositionJ()]);
				System.out.println(currentPoint.getLevel());
				System.out.println(nextLevel);
				if(maze[nextLevel]!=null)
				{
					chooseTheRigthStair(currentPoint.getLevel(), nextLevel, maze[nextLevel].getStairs());
					currentPoint.setDirection(' ');
					TermS newTerm =(TermS)path.top();
					map[newTerm.getPositionI()][newTerm.getPositionJ()]='x';    
					stackAlgoPath(maze, newTerm);
				}
				else
				{
					System.out.println("pop");
					TermS term =(TermS) path.pop();
					System.out.println(map[term.getPositionI()][term.getPositionJ()]);
					TermS term1 =(TermS) path.top();
					System.out.println(map[term1.getPositionI()][term1.getPositionJ()]);
					map[term.getPositionI()][term.getPositionJ()]='x';
					stackAlgoPath(maze, term1);
				}
			}
			else
			{
				path.pop();
				if(!path.isEmpty())
				{
					System.out.println("dsddsiu");
					TermS nextTerm =(TermS)path.top();
					System.out.println(nextTerm.getLevel());
					System.out.println(nextTerm.getPositionI());
					System.out.println(nextTerm.getPositionJ());
					stackAlgoPath(maze, nextTerm);
				}
			}
		}
		
	}

	public NodeStack stackAlgoFllow(Level[]maze ,TermS startPoint)
	{
		path.push(startPoint);
		stackAlgoPath(maze,startPoint);
		return path;
	}
	
}
