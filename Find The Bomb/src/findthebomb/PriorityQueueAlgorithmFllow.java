package findthebomb;
public class PriorityQueueAlgorithmFllow 
{
	private PriorityQueue path ;
	private boolean[][][] validation = new boolean [10][][] ;
	
    public PriorityQueueAlgorithmFllow() 
    {
    	path = new PriorityQueue ();
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

	private void enqueueFreeElements(boolean[][] checkValidation , byte[] limites , TermPQ currentPoint , char[][]map)
	{
		for(int i= limites[0]; i< limites[2];i++)
		{
			for(int j=limites[1] ; j<limites[3];j++)
			{
				if(checkValidation[i][j])
				{
					TermPQ newTerm = new TermPQ(i,j);
					newTerm.setLevel(currentPoint.level);
					newTerm.setParent(currentPoint);
					int x = costValue(currentPoint, map)+currentPoint.getKeyValue();
					newTerm.setKeyValue(x); 
					checkValidation[i][j]=false;
					path.enqueue(newTerm);
				}
			}
		}
	}

	private byte checkPositionChar(char[][]map , TermPQ currentPoint)
	{
		int positionI =currentPoint.positionI;
		int positionJ =currentPoint.positionJ;
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

	private void creatBooleanMap(TermPQ startPoint , char[][]map)
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
		valid[startPoint.positionI][startPoint.positionJ]=false;
		validation[startPoint.level]=valid;
	}

	private int costValue(TermPQ currentPoint , char[][]map)
	{
		int cost =0 ;
		try
		{
			Integer.parseInt(""+ map[currentPoint.getPositionI()][currentPoint.getPositionJ()]);
			cost = 2 ;
		}
		catch(Exception e)
		{
			char element= map[currentPoint.getPositionI()][currentPoint.getPositionJ()];
			switch(element)
			{
			case 'r' : cost =5; break ;
			case ' ' : cost =1; break;
			case 'B' : cost =1; break;
			case 'V' : cost =1; break;
		    }
		}
		return cost ;
	}
	
	public TermPQ QueueAlgoPath(Level [] maze , TermPQ startPoint)
	{
		creatBooleanMap(startPoint,maze[startPoint.level].getLevel());
		path.enqueue(startPoint);
		boolean found =false;
		TermPQ wanted =null;
		System.out.println(path.size());
		while(!path.isEmpty() && !found )
		{
			TermPQ currentPoint = (TermPQ) path.dequeue();
			System.out.println(currentPoint.getPositionI()+ " "+ currentPoint.getPositionJ());
			char[][]map =maze[currentPoint.level].getLevel();
			byte checkCharacterType = checkPositionChar(map,currentPoint);
			if(checkCharacterType==1)
			{
				found =true;
				System.out.println("Winner Reach the target");
				wanted =currentPoint;
			}
			else 
			{
				if(validation[currentPoint.level]==null)
				{
					creatBooleanMap(currentPoint, map);
				}
				byte[]limites = getTheLimites(map.length, currentPoint.positionI, currentPoint.positionJ);
				enqueueFreeElements(validation[currentPoint.level], limites, currentPoint , map);
				if(checkCharacterType==0)
				{
					int nextLevel =Integer.parseInt(""+map[currentPoint.positionI][currentPoint.positionJ]);
					try
					{
						String[] stairs=maze[nextLevel].getStairs();
						String positions=stairs[currentPoint.level];
						int posI=Integer.parseInt(positions.substring(0,positions.indexOf(' ')));
						int posJ=Integer.parseInt(positions.substring(positions.indexOf(' ')+1,positions.length()));
						TermPQ newTerm = new TermPQ (posI , posJ);
						newTerm.setParent(currentPoint);
						newTerm.setLevel(nextLevel);
						int x =2+Math.abs(currentPoint.level-nextLevel);
						newTerm.setKeyValue(x);
						path.enqueue(newTerm);
					}
					catch(Exception e)
					{
						System.out.println("dfsuhoiufhfhdfsihfi");
					}
				}
			}
		}
		return wanted;
	}

	
}
