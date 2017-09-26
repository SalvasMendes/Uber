package eds;

public class Stack<E> {

	private int size;
	private int top;
	private E[] stack;

	public Stack() {
		size = 10;
	}

	@SuppressWarnings("unchecked")
	public Stack(int s) {
		size = s > 0 ? s : 10;
		top = -1;

		stack = (E[]) new Object[size];
	}

	public void push(E object) {
		if (!isFull())
			stack[++top] = object;
	}

	public E pop() {
		if (isEmpty())
			return null;
		return stack[top--];
	}

	public E top() {
		return stack[top];
	}

	public int size() {
		return top;
	}

	private boolean isEmpty() {
		return (top == -1);

	}

	private boolean isFull() {
		return (top == size - 1);
	}
	
	/*insert resize*/

}