package ai;

public interface ReactToInputAI extends AI{
	public void reactToMouse(int x, int y);
	public void reactToKey(char key, boolean release);
}
