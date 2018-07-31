package us.cownet;

public interface ThreadMemory {
	public int getWord(int location);

	public void setWord(int location, int value);

	public void pushData(int value);

	public int popData();

	public int peekData(int location);

	public void pushReturn(int value);

	public int popReturn();
}
