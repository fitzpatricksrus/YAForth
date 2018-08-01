package us.cownet;

public interface WordCompiler {
	void beginCompile(ThreadMemory memory);

	void setMode(WordDictionary.WordExecutionMode mode);

	void setName(String name);

	void compileValue(int value);

	int endCompile(int previousWordLink);

	void abortCompile();
}
