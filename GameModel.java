import java.util.Random;
import java.io.* ;

public class GameModel implements Cloneable, Serializable{

    public static final int COLOR_0 = 0;
    public static final int COLOR_1 = 1;
    public static final int COLOR_2 = 2;
    public static final int COLOR_3 = 3;
    public static final int COLOR_4 = 4;
    public static final int COLOR_5 = 5;
    public static final int NUMBER_OF_COLORS = 6;

	private DotInfo[][] game;
	private int steps;
	private int currentColor;
	private int currentCapturedNumber=0;
	private int size;
	private Random rand;
	private boolean torus;
	private boolean diagonal;
	
	
    public GameModel(int size) {
		rand = new Random();
		this.size=size;
		game = new DotInfo[size][size];
		steps=-1;
		currentColor=-1;
		int X,Y;
		for (int j=0;j<size;j++){
			Y=j;
			for (int i=0;i<size;i++){
				X=i;
				int m = rand.nextInt(NUMBER_OF_COLORS);
				game[Y][X]= new DotInfo(X,Y,m);
			}
		}
		
    }
	

	public GameModel clone() {
		GameModel clonedModel=null;
		try{
			clonedModel=(GameModel) super.clone();
		} catch (CloneNotSupportedException e){
			System.out.println(e);
		}
		clonedModel.game = new DotInfo[size][size] ;
		int X,Y;
		for (int j=0;j<size;j++){
			Y=j;
			for (int i=0;i<size;i++){
				X=i;
				clonedModel.game[Y][X] = game[Y][X].clone();
			}
		}
		
		return clonedModel;
	}

 
    public void reset() {
		steps=-1;
		currentCapturedNumber=0;
		currentColor=-1;
		DotInfo[][] newGame = new DotInfo[size][size];
		int X,Y;
		for (int j=0;j<size;j++){
			Y=j;
			for (int i=0;i<size;i++){
				X=i;
				int m = rand.nextInt(NUMBER_OF_COLORS);
				newGame[Y][X]= new DotInfo(X,Y,m);
			}
		}
		game = newGame;
    }


    public int getSize() {
		return size;
		
    }

    public int getColor(int i, int j) {
		DotInfo dot = get(i,j);
		return dot.getColor();

	}

    public boolean isCaptured(int i, int j) {
		DotInfo dot = get(i,j);
		return dot.isCaptured();

    }

    public void capture(int i, int j) {
		if (isCaptured(i,j)==false){
			currentCapturedNumber++;
		}
		game[j][i]=new DotInfo(i,j,currentColor);
		game[j][i].setCaptured(true); 

    }

    public int getNumberOfSteps() {
		return steps;

    }

    public void setCurrentSelectedColor(int val) {
		currentColor=val;

    }

    public int getCurrentSelectedColor() {
		return currentColor;

    }

    public DotInfo get(int i, int j) {
		return game[j][i];

    }

    public void step() {
		steps++;
		
    }
	
	public void stepBack() { 
		steps--;
		
    }
	
	public void setTorus(boolean option){
		torus = option;
		
	}
	
	public void setDiagonal(boolean option){
		diagonal = option;
		
	}
	
	public boolean getTorus(){
		return torus;
		
	}
	
	public boolean getDiagonal(){
		return diagonal;
		
	}

    public boolean isFinished() {		
		return (currentCapturedNumber == size*size);

    }

    public String toString() {
		int r = 1;
		String s = ("Current game: # of Captured cells:"+currentCapturedNumber+", Steps:"+steps+System.lineSeparator());
		for (int b=size-1; b>-1; b--){	
			s += System.lineSeparator();
			for (int a=0; a<size; a++){
				s += ("Cell#"+(r++)+": ("+a+","+b+"), CAPTURED?:"+game[b][a].isCaptured()+", COLOR:"+game[b][a].getColor()+"__");
			}
		}

		return s;

    }
}