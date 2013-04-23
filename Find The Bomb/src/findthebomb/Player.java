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


import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Player implements KeyListener{
    String Name;
int x;
int y;
Image player;
Board board;
char[][]level;
boolean right;
boolean up;
boolean down;
boolean left;
boolean enterStairs;
int score;
int challange;
boolean showScore;
JDialog dialog;
JTextField field;
JLabel label;
//public Player(char[][]level){

	//ImageIcon i = new ImageIcon("C:\\Users\\Eng Omnia\\Downloads\\bent.png");
	//player=i.getImage();
	//this.level=level;
	//initialize x and y to the start point
//}

public Player(Board board){
	this.board=board;
	this.setLevel(board.getLevel());
score=0;
challange=0;
}
public void setTextField(JTextField newJText){
    field=newJText;
}
public JLabel getLabel(){
    return label;
}
public void setLabel(JLabel newLabel){
   label= newLabel;
}
public void setName(String name){
   Name=name;
   label.setText(name);
}
public String getName(){
   return Name;
}
public JTextField getTextField(){
    return field;
}
public void setChallange(int newChallange){
    challange=newChallange;
}
public void setDialog(JDialog dia){
    dialog=dia;
}
public JDialog getDialog(){
    return dialog;
}
public char[][] getCurrlevel() {
	return level;
}
public void setCurrlevel(char[][] currlevel) {
	this.level = currlevel;
}
//public void setImage(String destination){

//	ImageIcon i = new ImageIcon(destination);
//	player=i.getImage();
//}
public void move(int x , int y){
	int currLevel=-1;
              this.getTextField().setText(score+"");
   if(showScore){
       showScore=false;
       this.getDialog().setSize(400, 400);
       this.getDialog().setVisible(true);
   
   }
	//boolean stairs=false;
	try{

		currLevel=Integer.parseInt(level[y][x]+"");
	if(enterStairs){
		enterStairs=false;
		Level lev =this.getBoard().getLevels()[currLevel];
		char[][] newLevel=lev.getLevel();

		this.getBoard().setLevel(newLevel);
		this.setCurrlevel(newLevel);
	String stair= lev.getStairs()[board.getLevelNo()];
	int posY=Integer.parseInt(stair.substring(0,stair.indexOf(' ')));
	int posX=Integer.parseInt(stair.substring(stair.indexOf(' ')+1,stair.length()));
	setX(posX);
	setY(posY);
	this.board.setLevelNo(currLevel);
return;
	}
	}catch(Exception e){

	}
  if(level[y][x]==' '&&board.hard==3){
      level[y][x]='x';
  }
    else if(level[y][x] == 'r' && board.hard == 3)
    {
            score--;
            
        }
if(left&&up && y-1>=0 && x-1>=0 &&(level[y-1][x - 1] != 'x')){
    this.setX(x-1);
    this.setY(y-1);
}
 else if (left && down && y + 1 <level.length  && x - 1 >= 0 && (level[y + 1][x - 1] != 'x')) {
    this.setX(x-1);
    this.setY(y+1);
}
         else if (right && down && y + 1 <level.length  && x + 1 < level.length && (level[y + 1][x + 1] != 'x')) {
    this.setX(x+1);
    this.setY(y+1);
}
         else if (right && up && y-1 >=0  && x + 1 < level.length && (level[y -1][x + 1] != 'x')) {
    this.setX(x+1);
    this.setY(y-1);
}
         else if(level[y][x]=='*'){
            level[y][x]=' ';
            this.score++;
        }
 else if (left && x - 1 >= 0 && level[y][(x - 1)] != 'x')
    {
		this.setX(x-1);

	}

    else if(right && x + 1 < level.length && level[y][(x + 1)] != 'x')
    {
		this.setX(x+1);

	}
    else if(up && y - 1 >= 0 && level[(y - 1)][x] != 'x')
    {

		this.setY(y-1);
	}
    else if(down && y + 1 < level.length && level[y + 1][x] != 'x')
    {
		this.setY(y+1);
	}
    
}
public Board getBoard() {
	return board;
}

public void setBoard(Board board) {
	this.board = board;
}

public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}

public Image getPlayer() {
	return player;
}
public void setPlayer(ImageIcon player) {
	this.player = player.getImage();
}
public char[][] getLevel() {
	return level;
}
public void setLevel(char[][] level) {
	this.level = level;
}
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	int key = e.getKeyCode();
        if(key==KeyEvent.VK_W){
            up=true;
            left=true;
            right=false;
            down=false;
            enterStairs=false;
            showScore=false;
        }
        if(key==KeyEvent.VK_E){
            up=true;
            right=true;
            left=false;
            down=false;
            enterStairs=false;
            showScore=false;
        }
         if(key==KeyEvent.VK_S){
            up=false;
            right=false;
            left=true;
            down=true;
            enterStairs=false;
            showScore=false;
        }
          if(key==KeyEvent.VK_D){
            up=false;
            right=true;
            left=false;
            down=true;
            enterStairs=false;
            showScore=false;
        }


	if(key== KeyEvent.VK_LEFT){

		left=true;
		right=false;
		up=false;
		down=false;
		enterStairs=false;
showScore=false;
	}
	if(key==KeyEvent.VK_SPACE){
		enterStairs=true;
		left=false;
		right=false;
		up=false;
		down=false;
                showScore=false;

	}
	if(key== KeyEvent.VK_RIGHT){
		left=false;
		right=true;
		up=false;
		down=false;
	showScore=false;
                enterStairs=false;
	}
	if(key== KeyEvent.VK_UP){
		left=false;
		right=false;
		up=true;
		down=false;
		enterStairs=false;
                showScore=false;
	}
        if(key== KeyEvent.VK_ALT){
		left=false;
		right=false;
		up=false;
		down=false;
		enterStairs=false;
                showScore=true;
	}
	if(key== KeyEvent.VK_DOWN){
		left=false;
		right=false;
		up=false;
		down=true;
		enterStairs=false;
                showScore=false;
	}
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	int key = e.getKeyCode();
	if (key == KeyEvent.VK_RIGHT && !left) {
		right = false;
		} else if (left) {
		left = false;
		}
		if (key == KeyEvent.VK_LEFT && !right) {
		left= false;
		} else if (right) {
		right = false;
		}

		if (key == KeyEvent.VK_UP && !down) {
		up= false;
		} else if (down) {
		down = false;
		}

		if (key == KeyEvent.VK_DOWN && !up) {
		down= false;
		} else if (up) {
		up = false;
		}
}

@Override


public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub

}
public boolean isRight() {
	return right;
}
public void setRight(boolean right) {
	this.right = right;
}
public boolean isUp() {
	return up;
}
public void setUp(boolean up) {
	this.up = up;
}
public boolean isDown() {
	return down;
}
public void setDown(boolean down) {
	this.down = down;
}
public boolean isLeft() {
	return left;
}
public void setLeft(boolean left) {
	this.left = left;
}

    void setScore(int i) {
        score = i;
    }
}

