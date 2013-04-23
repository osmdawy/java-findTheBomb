/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package findthebomb;

/**
 *
 * @author Eng Omnia
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Board extends JPanel implements ActionListener {
int hard;
    private Player p;
    Image sand, green, stone, door, bomb, victim, stairs, n, s, w, e, nw, sw, k, l,coins,light;
    Timer time;
    char[][] level;
    Level[] levels;
    int levelNo;
    JFrame frame;
    JDialog dialog;
    boolean[][][] solve;
    int type;
    boolean enter = false;
JPanel panel ;
    public Board() {
        //0 for interactive
        //1 for queue
        // 2 forstack

        GenerateLevel allLevels = new GenerateLevel("map.txt");
        allLevels.checkStairs();
        this.levels = allLevels.getLevels();

        int start = 0;
        for (start = 0; start < 9; start++) {
            if (levels[start] != null) {
                if (levels[start].hasS) {
                    break;
                }
            }
        }
        levelNo = start;
        this.level = levels[start].getLevel();
        //frame.setSize(this.level.length*40+40, this.level.length*40+40);
        addKeyListener(new AL());
        p = new Player(this);
        p.setX(levels[start].getStaX());
        p.setY(levels[start].getStaY());
        setFocusable(true);

        ImageIcon sandA = new ImageIcon(("images\\sand.png"));
        ImageIcon greenA = new ImageIcon("images\\green.png");
        ImageIcon stoneA = new ImageIcon("images\\stone.png");
        ImageIcon Vict = new ImageIcon("images\\victim.png");
        ImageIcon bom = new ImageIcon("images\\bomb.png");
        ImageIcon doo = new ImageIcon("images\\gate.png");
        ImageIcon stai = new ImageIcon("images\\stairs.png");
        ImageIcon coin = new ImageIcon("images\\coinss.png");
        stone = stoneA.getImage();
        green = greenA.getImage();
        sand = sandA.getImage();
        victim = Vict.getImage();
        door = doo.getImage();
        bomb = bom.getImage();
        stairs = stai.getImage();
        coins=coin.getImage();
         ImageIcon up = new ImageIcon("images\\n.png");
     ImageIcon down = new ImageIcon("images\\s.png");

        ImageIcon left = new ImageIcon("images\\w.png");
        ImageIcon right = new ImageIcon("images\\e.png");
        ImageIcon upRight = new ImageIcon("images\\k.png");
        ImageIcon upLeft = new ImageIcon("images\\h.png");
        ImageIcon downRight = new ImageIcon("images\\l.png");
        ImageIcon downLeft = new ImageIcon("images\\j.png");
             ImageIcon ligh = new ImageIcon("images\\light2.png");
             light=ligh.getImage();
        n = up.getImage();
        s = down.getImage();
        e = right.getImage();
        w = left.getImage();
        nw = upLeft.getImage();
        sw = downLeft.getImage();
        k = upRight.getImage();
        l = downRight.getImage();


        time = new Timer(110, this);
        time.start();
        
    }
public void setJPanel(JPanel newJpanel){
    panel = newJpanel;
}
public JPanel getPaenl(){
    return panel;
}
    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
    public void setForStack(){
        Level [] temps =new Level[10];

        NodeStack arrange = new NodeStack();
        Stack stack = new Stack();
        TermS start = new TermS(levels[levelNo].getStaY(), levels[levelNo].getStaX());
        start.setLevel(levelNo);
        stack.StackFllow(levels, start);
        NodeStack finalPath = stack.path;
        int pathCost = finalPath.size();
        System.out.println("Path cost ="+pathCost);
        while(!finalPath.isEmpty()){
            TermS current=(TermS) finalPath.pop();
            if(arrange.isEmpty()){
                arrange.push(current.getLevel());
            }else{
//                if(current.getLevel()!=arrange.top()){
//                     arrange.push(current.getLevel());
//                }
            }if(levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]==' '||levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]=='r'){
            if(current.getDirection()=='a'){

                levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='h';
            }else if(current.getDirection()=='b'){
                 levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='n';
            }
            else if(current.getDirection()=='c'){
                 levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='k';
            }
            else if(current.getDirection()=='f'){
                 levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='e';
            }
            else if(current.getDirection()=='i'){
                 levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='l';
            }
            else if(current.getDirection()=='h'){
                 levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='s';
            }else if(current.getDirection()=='g'){
                 levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='j';
            }else if(current.getDirection()=='d'){
                 levels[current.getLevel()].getLevel()[current.getPositionI()][current.getPositionJ()]='w';
            }
            }
        }
         for(int index=0;index<levels.length&&!arrange.isEmpty();index++){
                int newPlace = (Integer) arrange.pop();
               
temps[index]=levels[newPlace];



        }

levelNo=0;

        levels=temps;

    }
public void setForQ() {
       Level[]temps= new Level[10];
this.setLevel(levels[levelNo].getLevel());
        TermQ Start = new TermQ(levels[levelNo].getStaY(), levels[levelNo].getStaX());

        Start.setLevel(levelNo);
        QueueAlgorithmFllow x = new QueueAlgorithmFllow();
        TermQ answer = x.QueueAlgoPath(levels, Start);
        autoSolve solveQ = new autoSolve();
        solveQ.makePath(levels, answer);
        this.solve = solveQ.path;
        int index = 0;
       int lastI=0;
        int lastJ=0;
        if(answer!=null){

         lastI=answer.positionI;
             lastJ=answer.positionI;
    }
        int count=0;
            if(answer!=null){
        while(answer.getParent()!=null){
            int i=answer.positionI;
            int j=answer.positionJ;
            int lev=answer.level;
            
           boolean[][]currentBoolean=solve[lev];
           char[][]currentChar=levels[lev].getLevel();
            char curr= currentChar[i][j];
                        if ((curr == ' ' ||curr=='r'||curr=='S')) {
                       
                            if (i - 1 >= 0 && currentBoolean[i - 1][j]) {

                                levels[lev].getLevel()[i][j] = 'q';
                            } else if (j - 1 >= 0 && currentBoolean[i][j - 1]) {
                               levels[lev].getLevel()[i][j] = 'q';
                            } else if (i + 1 < currentBoolean.length && currentBoolean[i + 1][j]) {
                              levels[lev].getLevel()[i][j] = 'q';

                            } else if (j + 1 < currentBoolean.length && currentBoolean[i][j + 1]) {
                                levels[lev].getLevel()[i][j] = 'q';

                            } else if (i - 1 >= 0 && j - 1 >= 0 && currentBoolean[i - 1][j - 1]) {
                                levels[lev].getLevel()[i][j] = 'q';

                            } else if (i - 1 >= 0 && j + 1 < currentBoolean.length && currentBoolean[i - 1][j + 1]) {
                            levels[lev].getLevel()[i][j]= 'q';

                            } //else if (i - 1 >= 0 && j - 1 >= 0 && currentBoolean[i - 1][j - 1]) {
                                //levels[lev].getLevel()[i][j] = 'h';

                        //    }
                        else if (i + 1 < currentBoolean.length && j - 1 >= 0 && currentBoolean[i + 1][j - 1]) {
                              levels[lev].getLevel()[i][j]= 'q';

                            } else if (i + 1 < currentBoolean.length && j + 1 < currentBoolean.length && currentBoolean[i + 1][j + 1]) {
                                levels[lev].getLevel()[i][j]= 'q';

                            }
                               currentBoolean[i][j]=false;

                        }
            lastI=i;
lastJ=j;
            answer=answer.getParent();
            count++;
    }
        System.out.println("pathCost ="+count);
    }
          for(index=0;index<levels.length&&!solveQ.s.isEmpty();index++){
                int newPlace = (Integer) solveQ.s.pop();
              
temps[index]=levels[newPlace];



        }

levelNo=0;

        levels=temps;


            }


        
      


    
 

 /*   public void setArrow() {
        ImageIcon up = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\up.png");
        ImageIcon down = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\down.png");

        ImageIcon left = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\left.png");
        ImageIcon right = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\right.png");
        ImageIcon upRight = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\upright.png");
        ImageIcon upLeft = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\upleft.png");
        ImageIcon downRight = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\downright.png");
        ImageIcon downLeft = new ImageIcon("C:\\Users\\Eng Omnia\\Pictures\\project\\downleft.png");
        n = up.getImage();
        s = down.getImage();
        e = right.getImage();
        w = left.getImage();
        nw = upLeft.getImage();
        sw= downLeft.getImage();
        k = upRight.getImage();
        l = downRight.getImage();
    }
  * */

public void shortestPath(){
    this.setForQ();
    this.setType(1);
    //this.setArrow();
}
    public void prepareNormal() {
    
for(int num= 0 ;num<levels.length;num++){
      int rand=(int) (p.challange * Math.random());
    if(levels[num]!=null){
       int count=0;
        char[][]curr= levels[num].getLevel();
        int length=curr.length;

        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                if(curr[i][j]==' '&&(i==rand||j==rand)){
                    curr[i][j]='*';
                    count++;
                }
            }
        }
    
    }
}
    }

    public void setFrame(JFrame newFrame) {
        frame = newFrame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Player getPlayer() {
        return p;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public char[][] getLevel() {
        return level;
    }

    public void setLevel(char[][] newLevel) {
        level = newLevel;
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setLevels(Level[] all) {
        levels = all;
    }

    public void setDialog(JDialog newDialog) {
        dialog = newDialog;
    }

    public JDialog getDialog() {
        return dialog;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        if (type == 0) {
            p.move(p.getX(), p.getY());
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
     
if(enter){
    enter=false;
    levelNo++;
    if(levelNo<levels.length&&levels[levelNo]!=null){
    this.setLevel(levels[levelNo].getLevel());
    }
}
        //  this.frame.getContentPane().setBackground(Color.yellow);
        super.paint(g);
if(type==0){
        if ((level[p.getY()][p.getX()] == 'B' || level[p.getY()][p.getX()] == 'V')&&(p.score>=p.challange )) {
dialog.setSize(600,600);
            dialog.setVisible(true);
            frame.setVisible(false);
         
        }
        }
        int ratio =40;
        if(level.length>15){
            ratio = 30;
        }
        if(level.length>30){
            ratio=15;
        }
        if(level.length>40){
            ratio =10;
        }if(level.length>60){
            ratio=6;
        }
        this.getFrame().setSize(this.getLevel().length * ratio + 40, this.getLevel().length * ratio + 40);
        Graphics2D g2D = (Graphics2D) g;

        try {

            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            AffineTransform aTran = new AffineTransform();
            aTran.translate(1.0f, 1.0f);
            g2D.transform(aTran);

            for (int i = 0; i < level.length; i++) {
                for (int j = 0; j < level.length; j++) {
                    char element=level[i][j];
                      if(element == 'n') {
                            g2D.drawImage(n, (int) ratio * j, (int) ratio * i,
                                    (int) ratio, (int) ratio, this);
                        } else if (element== 's') {
                            g2D.drawImage(s, (int) ratio * j, (int) ratio * i,
                                    (int) ratio, (int) ratio, this);

                        }else if (element== 'e') {
                            g2D.drawImage(e, (int) ratio * j, (int) ratio * i,
                                    (int) ratio, (int) ratio, this);
                        }else if (element == 'w') {
                            g2D.drawImage(w, (int) ratio * j, (int) ratio* i,
                                    (int) ratio, (int) ratio, this);
                        } else if (element == 'h') {
                            g2D.drawImage(nw, (int) ratio*j, (int) ratio*i,
                                    (int) ratio, (int) ratio, this);
                        } else if (element == 'j') {
                            g2D.drawImage(sw, (int)  ratio * j, (int) ratio* i,
                                    (int) ratio, (int) ratio, this);
                        } else if (element == 'k') {
                            g2D.drawImage(k, (int) ratio * j, (int) ratio * i,
                                    (int) ratio, (int) ratio, this);
                        } else if (element == 'l') {
                            g2D.drawImage(l, (int) ratio * j, (int) ratio * i,
                                    (int) ratio, (int) ratio, this);
                        }






    else  if (element == 'x') {
                        g2D.drawImage(stone, (int) ratio * j, (int) ratio * i,
                                ratio, ratio, this);
                    } else if (element == 'r') {
                        g2D.drawImage(green, (int) ratio * j, (int) ratio * i,
                                (int) ratio, (int) ratio, this);
                    } else if (element == ' ') {
                        g2D.drawImage(sand, (int) ratio* j, (int) ratio * i,
                                (int) ratio, (int) ratio, this);
                    } else if (element == 'V') {
                        g2D.drawImage(victim, (int) ratio * j, (int) ratio * i,
                                (int) ratio, (int) ratio, this);
                    } else if (element == 'B') {
                        g2D.drawImage(bomb, (int) ratio * j, (int) ratio* i,
                                (int) ratio, (int) ratio, this);
                    } else if (element == 'S') {
                        g2D.drawImage(door, (int) ratio * j, (int) ratio * i,
                                (int) ratio, (int) ratio, this);
                    }else if(element=='q'){
                        g2D.drawImage(light, (int) ratio * j, (int) ratio * i,
                                (int) ratio, (int) ratio, this);
                    }

                   
                     else if(element=='*'){
     g2D.drawImage(coins, (int) ratio* j, (int) ratio * i,
                                (int) ratio, (int) ratio, this);
 }
                    else {

                        g2D.drawImage(stairs, (int) ratio * j, (int) ratio * i,
                                (int) ratio, (int) ratio, this);
                    }
                    

                }
            }
            if (type == 0) {
                g2D.drawImage(p.getPlayer(), p.getX() * ratio, p.getY()* ratio, ratio, ratio, this);
            }
        } catch (Exception e) {
        }

        paintComponents(g);

    }

    private class AL extends KeyAdapter {

        public void keyTyped(KeyEvent e) {
            //   throw new UnsupportedOperationException("Not supported yet.");
        }

        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");

            p.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                enter = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
            p.keyReleased(e);
        }
    }
}
