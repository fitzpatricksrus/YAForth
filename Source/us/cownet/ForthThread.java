package us.cownet;

public class ForthThread {
	public interface ThreadContext {
		public void terminate(ForthThread thread);
	}

	public interface ThreadMemory {
		public int getWord(int location);

		public void setWord(int location, int value);
	}

	private int instructionPointer;
	private int returnStackPointer;
	private int dataStackPointer;

	private ThreadMemory memory;
	private ThreadContext context;

	public enum INSTRUCTION {
		INSTRUCTION_PTR(0),
		RETURN_STACK_PTR(1),
		DATA_STACK_PTR(2);

		public final int id;

		INSTRUCTION(int id) {
			this.id = id;
		}
	}

	public ForthThread(ThreadContext context, ThreadMemory memory, int ip, int rp, int dp) {
		this.context = context;
		this.memory = memory;
		instructionPointer = ip;
		returnStackPointer = rp;
		dataStackPointer = dp;
	}

	public int nextInstruction() {
		return memory.getWord(instructionPointer++);
	}

	public void execute() {
		int ins = nextInstruction();
		switch (ins) {
			case 0: // INSTRUCTION_PTR
			case 1: // RETURN_STACK_PTR
			case 2: // DATA_STACK_PTR
				terminate();
				break;
			default:

		}
	}

	public void terminate() {
		context.terminate(this);
	}
}
