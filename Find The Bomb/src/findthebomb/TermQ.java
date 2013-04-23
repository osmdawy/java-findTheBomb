
package findthebomb;

public class TermQ 
{
	int positionI;
	int positionJ;
	int level;
	TermQ parent;
	
	
	public TermQ(int newPositionI , int newPositionJ )
	{
		positionI=newPositionI;
		positionJ=newPositionJ;
		level=0;
		parent=null;
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
	
	public TermQ getParent() 
	{
		return parent;
	}
	
	public void setParent(TermQ parent) 
	{
		this.parent = parent;
	}

}
