package us.cownet;

public interface ThreadExecutionContext {
	int getIP();

	void setIP(int newIP);

	default void bumpIP(int offset) {
		setIP(getIP() + offset);
	}

	int getNextInstruction();
}
