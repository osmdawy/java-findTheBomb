package findthebomb;
public class Stack 
{
	NodeStack path ;
	private boolean[][][] validation = new boolean [10][][] ;
	
	public Stack()
	{
		path =new NodeStack();
	}
	
	
	private byte[] getTheLimites(int mapLength , int currentPosI , int currentPosJ )
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
		return limites;
	}

	private byte checkPositionChar(char[][]map , TermS currentPoint)
	{
		int positionI =currentPoint.getPositionI();
		int positionJ =currentPoint.getPositionJ();
		//System.out.println(positionI);
		//System.out.println(positionJ);
		//int x=map[positionI].length;
		//System.out.println(x);
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
		validation[startPoint.getLevel()]=valid;
	}
	
	private boolean chooseTheCell(byte[]limites , boolean[][]valid ,TermS currentTerm )
	{
		boolean check =false;
		boolean answer =false;
                        boolean here =false;
		char x = 97;
                if(limites[0]==currentTerm.getPositionI()){
                    x='d';
                }
                if(limites[1]==currentTerm.getPositionJ()){
                     x='b';
                     here=true;
                }
		for(int i=limites[0];i<limites[2];i++)
		{
			for(int j=limites[1];j<limites[3];j++)
			{


				if(valid[i][j])
				{
					TermS newTerm = new TermS(i,j);
					newTerm.setLevel(currentTerm.getLevel());
					currentTerm.setDirection(x);
					valid[i][j]=false;
					path.push(newTerm);
					check=true;
				    answer=true;
				    break;
				}
                                if((x=='d'||x=='g')&&here){
                                    x++;
                                }
				x++;
			}
			if(answer)
				break;
		}
		return check;
	}
	
	public void StackFllow(Level[] maze, TermS startPoint)
	{
		boolean found=false;
		path.push(startPoint);
		while(!path.isEmpty() && !found)
		{
			    TermS term=(TermS)path.top();
		//	    System.out.println(term.getPositionI());
		//	    System.out.println(term.getPositionJ());
		//	    System.out.println(term.getLevel());
				char[][]map=maze[term.getLevel()].getLevel();
				if(checkPositionChar(map, term)==1)
				{
					found=true;
				}
				else
				{
					if(validation[term.getLevel()]==null)
					{
						creatBooleanMap(term, map);
					}
					byte[] limites =getTheLimites(map.length,term.getPositionI(), term.getPositionJ());
					if(chooseTheCell(limites,validation[term.getLevel()] ,term))
					{
						//for(int i=0;i<validation[term.getLevel()].length;i++)
						{
							//for(int j= 0 ;j <validation[term.getLevel()].length;j++)
							{
								//System.out.print(validation[term.getLevel()][i][j]);
								//System.out.print(" ");
							}
							//System.out.println();
						}
						
						//System.out.println("found");
						//TermS term1=(TermS)path.top();
						//System.out.println(term1.getLevel());
						//System.out.println(term1.getPositionI()+"    "+ term1.getPositionJ());
					}
					else if(!chooseTheCell(limites,validation[term.getLevel()] ,term))
					{
						if(checkPositionChar(map,term) == 0)
						{
							int nextLevel =Integer.parseInt(""+map[term.getPositionI()][term.getPositionJ()]);
							try
							{
								if(maze[nextLevel]!=null)
								{
									
									System.out.println("stair");
									String[] stairs=maze[nextLevel].getStairs();
									String positions=stairs[term.getLevel()];
									int posI=Integer.parseInt(positions.substring(0,positions.indexOf(' ')));
									int posJ=Integer.parseInt(positions.substring(positions.indexOf(' ')+1,positions.length()));
									TermS newTerm = new TermS (posI , posJ);
									term.setDirection(' ');
									newTerm.setLevel(nextLevel);
									map[term.getPositionI()][term.getPositionJ()]='x';
									//maze[nextLevel].getLevel()[posI][posJ]='x';
									path.push(newTerm);
									TermS term1=(TermS)path.top();
									System.out.println(term1.getLevel());
									System.out.println(term1.getPositionI()+"    "+ term1.getPositionJ());
									String x=term1.getPositionI()+"    "+ term1.getPositionJ();
									//if(x.equalsIgnoreCase("18    8"))
									//{
									//	System.out.println("sdjjods");
									//}
								}
								else
								{
								//	System.out.println("pop");
									path.pop();
								}
							}
						   catch(Exception e)
						   {
							System.out.println("error");
						   }
						}
						else
						{
							path.pop();
						}
					}
					else
					{
						path.pop();
					}
				}
			
		}
	
	}


	
	
}
