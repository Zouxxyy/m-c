import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    private static final int M = 3; // 修道士人数
    private static final int C = 3; // 野人人数
    // 1 艘船最多载两人，有5种载法
    private static final int[][] actions = {{1, 1}, {0, 2}, {2, 0}, {1, 0}, {0, 1}};

    public static void main(String[] args) {

        ArrayList<AStarNode> nodes = new ArrayList<>();
        PriorityQueue<AStarNode> priorityQueue = new PriorityQueue<>();
        int[][][] arrived = new int[50][50][2]; // 记录到达过的状态（m, c, b）
        AStarNode node;

        // 起始状态
        node = new AStarNode(-1, M, C, 1, 0);
        nodes.add(node);
        priorityQueue.add(node);

        while (!priorityQueue.isEmpty()) {

            AStarNode aStarNode = priorityQueue.poll();

            if (aStarNode.m == 0 && aStarNode.c == 0) {
                System.out.println("方案为：");
                printResult(aStarNode.id, nodes);
                return;
            }

            for (int[] action : actions) {
                int add = aStarNode.b == 1 ? -1 : 1;
                int m = aStarNode.m + add * action[0];
                int c = aStarNode.c + add * action[1];
                int b = 1 - aStarNode.b;

                if (!(m > M || m < 0 || c > C || c < 0
                        || (m != 0 && m < c) || ((M - m) != 0 && (M - m) < (C - c)))
                        && arrived[m][c][b] == 0) {
                    arrived[m][c][b] = aStarNode.g + 1;
                    node = new AStarNode(aStarNode.id, m, c, b, aStarNode.g + 1);
                    nodes.add(node);
                    priorityQueue.add(node);
                }
            }
        }
    }

    public static void printResult(int id, ArrayList<AStarNode> pathNode) {
        if (id == -1)
            return;
        printResult(pathNode.get(id).pid, pathNode);
        System.out.println(pathNode.get(id));
    }
}
