package us.cownet;

import java.util.HashMap;

/*
This enum represents primative instructions.  Primitives
have a negative ordinal value whereas composite words have
a strictly positive value.
 */
public enum Instruction {
	ZERO {
		public void execute(Architecture architecture) {
			architecture.pushData(0);
		}
	},
	ONE {
		public void execute(Architecture architecture) {
			architecture.pushData(1);
		}
	},
	NOP {
		public void execute(Architecture architecture) {
			;
		}
	},
	RETURN {
		public void execute(Architecture architecture) {
			architecture.setIP(architecture.popReturn());
		}

		public void trace() {
			--indent;
			super.trace();
		}
	},
	COMPOSITE {
		public void execute(Architecture architecture) {
			architecture.pushReturn(architecture.getIP());
			architecture.setIP(architecture.getNextInstruction());
		}

		public void trace() {
			super.trace();
			indent++;
		}
	},
	HELLO_WORLD {
		public void execute(Architecture architecture) {
			System.out.println("Hello World.");
		}
	},
	TO_RETURN {
		public void execute(Architecture architecture) {
			architecture.pushReturn(architecture.popData());
		}
	},
	FROM_RETURN {
		public void execute(Architecture architecture) {
			architecture.pushData(architecture.popReturn());
		}
	},
	DROP_DATA {
		public void execute(Architecture architecture) {
			architecture.popData();
		}
	},
	FROM_MEMORY {
		// location -- value
		public void execute(Architecture architecture) {
			architecture.pushData(architecture.getWord(architecture.popData()));
		}
	},
	TO_MEMORY {
		// value location --
		public void execute(Architecture architecture) {
			architecture.setWord(architecture.popData(), architecture.popData());
		}
	},
	PUSH_NEXT_INSTRUCTION_WORD_AS_DATA {
		public void execute(Architecture architecture) {
			architecture.pushData(architecture.getNextInstruction());
		}
	},
	JMP_IF_FALSE {
		public void execute(Architecture architecture) {
			int jmpOffset = architecture.getNextInstruction();
			if (architecture.popData() == 0) {
				architecture.bumpIP(jmpOffset);
			}
		}
	},
	JMP {
		public void execute(Architecture architecture) {
			architecture.bumpIP(architecture.getNextInstruction());
		}
	},
	COMPILE {
		public void execute(Architecture architecture) {
			;
		}
	},
	UNKNOWN

			{
				public void execute(Architecture architecture) {
					;
				}
			},;

	public int id;

	private static int indent = 0;

	public void trace() {
		System.out.print(".............................+".substring(0, indent));
		System.out.println(this.toString());
	}

	public void execute(Architecture architecture) {
	}

	public static Instruction fromId(int id) {
		Instruction i = ordinalMap.get(id);
		return (i != null) ? i : COMPOSITE;
	}

	private static HashMap<Integer, Instruction> ordinalMap = new HashMap<>();

	static {
		int i = 0;
		for (Instruction ins : Instruction.values()) {
			ins.id = i--;
			ordinalMap.put(ins.id, ins);
		}
	}
}

