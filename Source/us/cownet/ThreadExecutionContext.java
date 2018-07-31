package us.cownet;

public interface ThreadExecutionContext {
	int getIP();

	void setIP(int newIP);

	int getNextInstruction();
}
