package us.cownet;

import java.util.HashMap;

public class ForthThread {
	public interface ThreadContext {
		public void terminate(ForthThread thread);
	}

	public interface ThreadMemory {
		public int getWord(int location);
		public void setWord(int location, int value);

		public void pushData(int value);

		public int popData();

		public int peekData(int location);

		public void pushReturn(int value);

		public int popReturn();
	}

	private int instructionPointer;
	private int dataStackPointer;

	private ThreadMemory memory;
	private ThreadContext context;

	/*
	This enum represents primative instructions.  Primitives
	have a negative ordinal value whereas composite words have
	a strictly positive value.
	 */
	public enum INSTRUCTION {
		ZERO,
		ONE,
		NOP,
		RETURN

				{
					public void trace() {
						--indent;
						super.trace();
					}
				},
		COMPOSITE {
			public void trace() {
				super.trace();
				indent++;
			}
		},
		HELLO_WORLD,
		PUSH_RETURN,
		POP_RETURN,
		PUSH_DATA,
		POP_DATA,
		PEEK_DATA,
		PUSH_NEXT_INSTRUCTION_WORD_AS_DATA,
		JMP_IF_FALSE,
		JMP,
		COMPILE,
		UNKNOWN,;

		public int id;

		private static int indent = 0;

		public void trace() {
			System.out.print(".............................+".substring(0, indent));
			System.out.println(this.toString());
		}

		public static INSTRUCTION fromId(int id) {
			INSTRUCTION i = ordinalMap.get(id);
			return (i != null) ? i : COMPOSITE;
		}

		private static HashMap<Integer, INSTRUCTION> ordinalMap = new HashMap<>();

		static {
			int i = 0;
			for (INSTRUCTION ins : INSTRUCTION.values()) {
				ins.id = i--;
				ordinalMap.put(ins.id, ins);
			}
		}
	}

	public ForthThread(ThreadContext context, ThreadMemory memory, int ip, int dp) {
		this.context = context;
		this.memory = memory;
		instructionPointer = ip;
		dataStackPointer = dp;
	}

	public int peekInstruction(int offset) {
		return memory.getWord(instructionPointer + offset);
	}

	public int getNextInstruction() {
		return memory.getWord(instructionPointer++);
	}

	public void execute() {
		int ins = getNextInstruction();
		INSTRUCTION instruction = INSTRUCTION.fromId(ins);
		instruction.trace();
		switch (instruction) {
			case ZERO:
				memory.pushData(0);
				break;
			case ONE:
				memory.pushData(1);
				break;
			case NOP:
				break;
			case RETURN:
				instructionPointer = memory.popReturn();
				break;
			case COMPOSITE:
				// this mush to a composite method since it is not a primitive
				memory.pushReturn(instructionPointer);
				instructionPointer = ins;
				break;
			case HELLO_WORLD:
				System.out.println("Hello World.");
				break;
			case PUSH_RETURN:
				memory.pushReturn(memory.popData());
				break;
			case POP_RETURN:
				memory.pushData(memory.popReturn());
				break;
			case PUSH_DATA:
				memory.pushData(getNextInstruction());
				break;
			case POP_DATA:
				memory.popData();
				break;
			case PEEK_DATA:
				memory.pushData(memory.peekData(memory.popData()));
				break;
			case PUSH_NEXT_INSTRUCTION_WORD_AS_DATA:
				memory.pushData(getNextInstruction());
				break;
			case JMP_IF_FALSE: {
				int jmpOffset = getNextInstruction();
				if (memory.popData() == 0) {
					instructionPointer += jmpOffset;
				}
				break;
			}
			case JMP: {
				int jmpOffset = getNextInstruction();
				instructionPointer += jmpOffset;
				break;
			}
			case COMPILE:

			case UNKNOWN:
			default:
				terminate();
				break;
		}
	}

	public void terminate() {
		context.terminate(this);
	}
}
