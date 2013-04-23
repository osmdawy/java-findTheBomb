package findthebomb;
public class TermS 
{
	private int positionI;
	private int positionJ;
	private int level;
	private char direction;
	
	

	public TermS(int newPosI , int newPosJ)
	{
		positionI=newPosI;
		positionJ=newPosJ;
		level=0;
	}
	
	public int getPositionI() 
	{
		return positionI;
	}
	
	public void setPositionI(int positionI) 
	{
		this.positionI = positionI;
	}
	
	public int getPositionJ() 
	{
		return positionJ;
	}
	
	public void setPositionJ(int positionJ) 
	{
		this.positionJ = positionJ;
	}
	
	public int getLevel() 
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public char getDirection() 
	{
		return direction;
	}

	public void setDirection(char direction) 
	{
		this.direction = direction;
	}

}
