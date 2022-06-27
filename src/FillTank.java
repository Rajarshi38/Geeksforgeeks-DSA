import java.util.*;

public class FillTank {
  long minimum_amount(int[][] Edges, int N, int S, int[] Cap) {
    // Code here
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] e : Edges) {
      int tank1 = e[0];
      int tank2 = e[1];
      adj.get(tank1).add(tank2);
      adj.get(tank2).add(tank1);
    }

    long req = dfs(adj, S, Cap, -1);

    return req;
  }

  private long dfs(List<List<Integer>> adj, int src, int[] cap, int parent) {
    long req = 0;
    int child = 0;

    for (int i : adj.get(src)) {
      if (i != parent) {
        child++;
        long childReq = dfs(adj, i, cap, src);
        if (childReq == -1)
          return -1;
        req = Math.max(req, childReq);
      }
    }

    if ((child * req) + cap[src - 1] > 1e18)
      return -1;

    return (child * req) + cap[src - 1];
  }

  public static void main(String[] args) {
    int[][] Edges = {
        { 1, 2 }, { 1, 3 }, { 2, 4 }
    };
    int[] cap = { 1, 1, 1, 1 };
    int src = 1;
    FillTank ft = new FillTank();
    long res = ft.minimum_amount(Edges, cap.length, src, cap);
    System.out.println(res);
  }

}
