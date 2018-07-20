package us.cownet;

/*





 */

public class YAForth implements ForthThread.ThreadContext, ForthThread.ThreadMemory {
	private int data[];
	private boolean isRunning;
	private ForthThread thread;

	@Override
	public void terminate(ForthThread thread) {
	}

	public YAForth(int size) {
		this.data = new int[size];
		isRunning = true;
	}

	@Override
	public int getWord(int location) {
		return data[location];
	}

	@Override
	public void setWord(int location, int value) {
		data[location] = value;
	}

	public void buildMemory() {

	}

	public static int main(String args[]) {
		YAForth instance = new YAForth(64 * 1024);
		instance.buildMemory();
		while (instance.isRunning) {
			instance.thread.execute();
		}
		return 0;
	}
}
