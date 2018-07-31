package us.cownet;

public class VonNeumannArchitecture implements Architecture {
	private int ip;
	private int dp;
	private int rp;
	private int memory[];
	private int dataStack[];
	private int returnStack[];

	public VonNeumannArchitecture(int memSize, int dataSize, int returnSize) {
		memory = new int[memSize];
		dataStack = new int[dataSize];
		returnStack = new int[returnSize];
	}

	@Override
	public int getIP() {
		return ip;
	}

	@Override
	public void setIP(int newIP) {
		ip = newIP;
	}

	@Override
	public int getNextInstruction() {
		return memory[ip++];
	}

	@Override
	public void pushReturn(int value) {
		returnStack[rp++] = value;
	}

	@Override
	public int popReturn() {
		return returnStack[--rp];
	}

	@Override
	public int getWord(int location) {
		return memory[location];
	}

	@Override
	public void setWord(int location, int value) {
		memory[location] = value;
	}

	@Override
	public void pushData(int value) {
		dataStack[dp++] = value;
	}

	@Override
	public int popData() {
		return dataStack[--dp];
	}

	@Override
	public int peekData(int location) {
		return dataStack[dp - 1];
	}
}
