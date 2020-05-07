public class AStarNode implements Comparable<AStarNode> {

    private static int nodeId = 0; // 每次加1

    public final int pid; // 父节点ID
    public final int id;  // 当前节点ID
    public final int m;   // 左侧修道士数量
    public final int c;   // 左侧野人数量
    public final int b;   // 左边船只数量
    public final int g;   // A* 算法 g值
    private final int f;  // A* 算法 f值

    public AStarNode(int pid, int m, int c, int b, int g) {
        this.pid = pid;
        this.id = nodeId++;
        this.m = m;
        this.c = c;
        this.b = b;
        this.g = g;
        int h = m + c - 2 * b;  // h = m + c - 2b
        this.f = this.g + h;    // f = g + h
    }

    @Override
    public String toString() {
        return "{" +
                "m=" + m +
                ", c=" + c +
                ", b=" + b +
                "}";
    }

    @Override
    public int compareTo(AStarNode o) {
        return f - o.f;
    }
}
