package us.cownet;

public interface WordDictionary {

	enum WordExecutionMode {
		PRIMATIVE(1),
		COMPILE_ONLY(2),
		IMMEDIATE(4),
		NORMAL(8);

		public int flag;

		WordExecutionMode(int flag) {
			this.flag = flag;
		}
	}

	public interface WordHeader {
		public String getName();

		public void setName(String name);

		public void setMode(WordExecutionMode mode);

		public WordExecutionMode getMode();

		public int getWord();

		public void setWord(int word);
	}
}
