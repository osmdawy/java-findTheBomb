/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package findthebomb;

/**
 *
 * @author Eng Omnia
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HighScore{
static int [] highs=new int [5];
public void setHighs(int []highs){
    this.highs=highs;
}
public int []  getHighs(){
    return highs;
}
	public static int[] sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			// find the insertion location and move all larger elements up
			int pos = i;
			while (pos > 0 && temp < array[pos - 1]) {
				array[pos] = array[pos - 1];
				pos--;
			}
			// insert the element
			array[pos] = temp;
		}
		return array;
	}

	public static void high(int length, int score) throws IOException {
//getting length from the configuration file
	//	int[] highs = new int[length];

		BufferedReader inputStream = null;
		inputStream = new BufferedReader(new FileReader("HighScore"));
		int x = 0;
		int pos = 0;
		while ((x = inputStream.read()) != -1) {
			highs[pos] = x;
			pos++;
		}



		if (inputStream.read() == -1) {
			inputStream.close();
		}
		if (score > highs[0]) {
			highs[0] = score;
		}
		highs = sort(highs);
		new File("HighScore").delete();
		highs = sort(highs);BufferedWriter in = new BufferedWriter(new FileWriter("HighScore"));

		for (int i = 0; i < highs.length; i++) {
			in.write(highs[i]);
		}
		in.close();

		inputStream = new BufferedReader(new FileReader("HighScore"));
int i=0;
		int z = 0;
		//int[] array = new int[length];

			while ((z = inputStream.read()) != -1) {
				highs[i] = z;
			i++;
		}
			inputStream.close();
		
	}
        public static void display(){
            JFrame frame = new JFrame ("High Score");
            frame.setSize(400, 400);
            frame.setVisible(true);
            JPanel panel = new JPanel();
            panel.setVisible(true);
            frame.add(panel);
            for(int l=highs.length-1;l>=0;l--){
                JLabel label = new JLabel("player"+(highs.length-l)+")"+highs[l]);
                 label.getParent();
                    label.setSize(40, 40);
                label.setVisible(true);
                    label.setLocation(10+(l*40),10+(l*40));
                   // label.setBounds(10+(l*40), 10+(l*40), l, l)
                    label.setBounds(10+(l*40),10+(l*40),10+(l*40),10+(l*40));
                    label.setEnabled(true);
                    label.setVisible(true);
                panel.add(label);
            }
        }
}