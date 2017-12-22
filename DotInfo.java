import java.io.Serializable ;

public class DotInfo implements Serializable, Cloneable {

	private int x;
	private int y;
	private int color;
	private boolean captured = false;


    public DotInfo(int x, int y, int color) {
		this.x= x;
		this.y= y;
		this.color=color;

    }
	
	public DotInfo clone(){
		DotInfo clonedDot=null;
		try{
			clonedDot = (DotInfo) super.clone();
		} catch (CloneNotSupportedException ex){
			System.out.println(ex);
		}
		if(clonedDot==null){
			throw new NullPointerException();
		}
		return clonedDot;
	}

    public int getX() {
		return x;

    }

    public int getY() {
		return y;

    }

    public void setCaptured(boolean captured) {
		this.captured=captured;

    }

    public boolean isCaptured() {
		return captured;

    }

    public int getColor() {
		return color;

    }

}