package findthebomb;
public class TermPQ 
{
	int positionI;
	int positionJ;
	int level;
	TermPQ parent;
	int keyValue;
	
	
	public TermPQ (int newPosI  ,int  newPosJ  )
	{
		positionI = newPosI;
		positionJ = newPosJ;
		level = 0;
		parent = null;
		keyValue = 0;
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
	public TermPQ getParent() 
	{
		return parent;
	}
	public void setParent(TermPQ parent) 
	{
		this.parent = parent;
	}
	public int getKeyValue() 
	{
		return keyValue;
	}
	public void setKeyValue(int keyValue) 
	{
		this.keyValue = keyValue;
	}
	
	

}
