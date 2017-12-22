public class EmptyStackException extends RuntimeException {
		
	public EmptyStackException(){
		super("Tried to Pop or Peek an empty stack.");
	}
	
}