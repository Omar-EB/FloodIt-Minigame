import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class DotButton extends JButton {

	private int row;
	private int column;
	private int color;
	private int iconSize;
	
	private static final int NUM_COLOURS = 6;
    private ImageIcon[] icons;


	

    public DotButton(int row, int column, int color, int iconSize) {
		char s;
		if(iconSize==-1){
			s='S';
		} else if (iconSize==0){
			s='M';
		} else {
			s='N';
		}
		icons = new ImageIcon[NUM_COLOURS]; 
		for (int i=0; i<NUM_COLOURS; i++) {
			icons[i] = new ImageIcon("data/"+s+"/ball-" + Integer.toString(i) + ".png");
		}
		this.row=row;
		this.column=column;
		this.color=color;
		this.iconSize=iconSize;
		setBackground(Color.WHITE);
		setIcon(icons[color]); 
		Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    	setBorder(emptyBorder);
		setBorderPainted(false);


    }

    public DotButton(int color, int iconSize) {
		char s;
		if(iconSize==-1){
			s='S';
		} else if (iconSize==0){
			s='M';
		} else {
			s='N';
		}
		icons = new ImageIcon[NUM_COLOURS]; 
		for (int i=0; i<NUM_COLOURS; i++) {
			icons[i] = new ImageIcon("data/"+s+"/ball-" + Integer.toString(i) + ".png");
		}
		
		this.row=-1;
		this.column=-1;
		this.color=color;
		this.iconSize=iconSize;
		
		setBackground(Color.WHITE);
		setIcon(icons[color]); 
		setBorderPainted(false);

    }


    public void setColor(int color) {
		this.color=color;
		setIcon(icons[color]); 
		
    }

    public int getColor() {
		return color;

    }


    public int getRow() {
		return row;

    }


    public int getColumn() {
		return column;

    }

}