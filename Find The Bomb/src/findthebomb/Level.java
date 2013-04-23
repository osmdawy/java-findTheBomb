package findthebomb;

public class Level {
	char [][]level;
	String [] stairs ;
	int levelNo;
	int staX;
	int staY;
        public int getStaX() {
		return staX;
	}
	public void setStaX(int staX) {
		this.staX = staX;
	}
	public int getStaY() {
		return staY;
	}
	public void setStaY(int staY) {
		this.staY = staY;
	}
		boolean hasS;
        public char [][] getLevel() {
        	return level;
        }
        public void setLevel(char [][] level) {
        	this.level = level;
        }
        public String[] getStairs() {
        	return stairs;
        }
        public void setStairs(String[] stairs) {
        	this.stairs = stairs;
        }
        public boolean hasStart(){
            return hasS;
        }
        public void hasSta (boolean v){
            hasS= v;
        }
        public Level (char [][] floor){
        	level = floor;
                hasS=false;

              //  timer = new Timer(15, this);
              //addKeyListener(new TAdapter());
        }
		public int getLevelNo() {
			return levelNo;
		}
		public void setLevelNo(int levelNo) {
			this.levelNo = levelNo;
		}
		public boolean isHasS() {
			return hasS;
		}
		public void setHasS(boolean hasS) {
			this.hasS = hasS;
		}
}
