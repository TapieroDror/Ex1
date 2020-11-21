package ex1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import ex1.WGraph_DS.NodeInfo;;
public class WGraphAlgo implements weighted_graph_algorithms {
	private weighted_graph graph;

	/**
	 * this function build a new WGraphAlgo graph
	 * @param g
	 * 
	 */
	
	public WGraphAlgo(weighted_graph g) {
		super();
		init(g);
	}

	/**
	 * this function build a new WGraphAlgo graph
	 */
	public WGraphAlgo() {
		super();
		this.graph = new WGraph_DS();
	}

	/**
	 * this fanction initialise g to this graph
	 * @param g
	 */
	@Override
	public void init(weighted_graph g) {
		this.graph = g;
	}

	/**
	 * this function return this WGraphAlgo
	 * @return graph
	 */
	@Override
	public weighted_graph getGraph() {
		return this.graph;
	}

	/**
	 * this function make a new copy to this graph
	 * @return clone
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph clone = new WGraph_DS();
		for (node_info runner : graph.getV()) {
			clone.addNode(runner.getKey());
		}
		for (int p1 : ((WGraph_DS) graph).getEdges().keySet()) {
			for (ex1.WGraph_DS.Edge neis : ((WGraph_DS) graph).getEdges().values()) {
				for (node_info p2 : neis.getNi()) {
					if (((WGraph_DS) graph).hasEdge(p2.getKey(), p1)) {
						clone.connect(p2.getKey(), p1, graph.getEdge(p2.getKey(), p1));
					}
				}
			}
		}
		return clone;
	}

	/**
	 * this function return true if this graph is connected 
	 * otherwise false
	 * @return true or false
	 */
	@Override
	public boolean isConnected() {
		if (graph.nodeSize() > graph.edgeSize() + 1) {
			return false;
		}
		// weighted_graph clone = copy();
		Queue<node_info> q = new LinkedList<>();
		for (node_info runner : graph.getV())// O(1)
		{
			q.add(runner);
			q.peek().setInfo("grey");
			break;
		}
		while (!q.isEmpty()) {
			for (node_info runner : ((WGraph_DS) graph).getEdges().get(q.peek().getKey()).getNi()) {
				if (runner.getInfo().equals("white")) {
					runner.setInfo("grey");
					q.add(runner);
				}
			}
			q.peek().setInfo("black");
			q.poll();
		}
		boolean flag = true;
		for (node_info runner : graph.getV())
			if (runner.getInfo().equals("white"))
				flag = false;
		return flag;
	}

	/**
	 * this function return the cheapest price from the node src to the node dest
	 * @param src, dest
	 * @return ans or -1
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if (graph.getNode(src) == null || graph.getNode(dest) == null)
			return -1;
		PriorityQueue<node_info> q = new PriorityQueue<>(new TagCompare());
		// weighted_graph clone = copy();
		q.add(graph.getNode(src));
		q.peek().setInfo("grey");
		q.peek().setTag(0);
		while (!q.isEmpty() && q.peek().getKey() != dest) {
			for (node_info runner : ((WGraph_DS) graph).getEdges().get(q.peek().getKey()).getNi()) {
				if (!runner.getInfo().equals("grey") && !runner.getInfo().equals("black")
						|| ((WGraph_DS) graph).getEdges().get(q.peek().getKey()).getWeight(runner)
								+ (q.peek().getTag()) < runner.getTag()) {
					runner.setTag(((WGraph_DS) graph).getEdges().get(q.peek().getKey()).getWeight(runner)
							+ q.peek().getTag());
					runner.setInfo("grey");
					q.add(runner);
				}
			}
			q.peek().setInfo("black");
			q.poll();
		}
		if (q.isEmpty()) {
			setWhite(graph);
			setTag(graph);
			return -1;
		} else {
			double ans = q.peek().getTag();
			setWhite(graph);
			setTag(graph);
			return ans;
		}
	}

	/**
	 * this function return the shortest path (list) from the node src to the node dest
	 * @param src, dest
	 * @return list or null
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		if (graph.getNode(src) == null || graph.getNode(dest) == null)
			return null;
		PriorityQueue<node_info> q = new PriorityQueue<>(new TagCompare());
		// weighted_graph clone = copy();
		q.add(graph.getNode(src));
		q.peek().setInfo("grey");
		q.peek().setTag(0);
		while (!q.isEmpty() && q.peek().getKey() != dest) {
			for (node_info runner : ((WGraph_DS) graph).getEdges().get(q.peek().getKey()).getNi()) {
				if (!runner.getInfo().equals("black")) {
					if (((WGraph_DS) graph).getEdges().get(q.peek().getKey()).getWeight(runner)
							+ (q.peek().getTag()) < runner.getTag()) {
						((NodeInfo) runner).setParent(q.peek().getKey());
						runner.setTag(((WGraph_DS) graph).getEdges().get(q.peek().getKey()).getWeight(runner)
								+ q.peek().getTag());
					}
					if (runner.getInfo() == "white") {
						runner.setInfo("grey");
						((NodeInfo) runner).setParent(q.peek().getKey());
					}
					q.add(runner);
				}
			}
			q.peek().setInfo("black");
			q.poll();
		}
		if (q.isEmpty()) {
			setWhite(graph);
			setTag(graph);
			return null;
		} else {
			LinkedList<node_info> list = new LinkedList<>();
			list.addFirst(q.peek());
			while (list.getFirst().getKey() != src) {
				list.addFirst(graph.getNode(((NodeInfo) list.getFirst()).getParent()));
			}
			setWhite(graph);
			setTag(graph);
			return list;
		}

	}

	/**this function write to a txt file with the name fileName the details of the graph
	 * this function save the txt file in the package project
	 * @param file
	 * @return true
	 */
	@Override
	public boolean save(String file) {
		((WGraph_DS) graph).writeFile(file);
		return true;
	}

	/**
	 * this function read the details from a txt file with the name fileName and make a new graph from that details
	 * this function load a graph from the folder of the package
	 * @param file
	 * @return true
	 */
	@Override
	public boolean load(String file) {
		((WGraph_DS) graph).readFile(file);
		return true;
	}

	/**
	 * this function set's all the nodes in the graph to be with the info white
	 * @param G
	 * 
	 */
	public static void setWhite(weighted_graph G) {
		for (node_info runner : G.getV()) {
			runner.setInfo("white");
		}
	}

	/**
	 * this function set's all the nodes in the graph to be with the tag inf
	 * @param G
	 * 
	 */
	public static void setTag(weighted_graph G) {
		for (node_info runner : G.getV()) {
			runner.setTag(Double.MAX_VALUE);
		}
	}

	private class TagCompare implements Comparator<node_info> {

		@Override
		public int compare(node_info o1, node_info o2) {
			if (o1.getTag() > o2.getTag())
				return 1;
			else if (o1.getTag() == o2.getTag())
				return 0;
			else
				return -1;
		}

	}

	/**
	 * this function print the WGraphAlgo graph
	 * @param
	 * @return
	 */
	public void printGraph() {
		((WGraph_DS) graph).myToString();
	}

	public static void main(String[] args) {
		weighted_graph g = new WGraph_DS();
		g.addNode(1);
		g.addNode(7);
		g.addNode(6);
		g.addNode(5);
		g.addNode(9);
		g.addNode(13);
		g.addNode(8);
		g.connect(1, 6, 3);
		g.connect(1, 7, 1);
		g.connect(7, 5, 1);
		g.connect(7, 9, 4);
		g.connect(5, 13, 2);
		g.connect(6, 13, 0);
		g.connect(6, 8, 7);
		g.connect(8, 13, 5);

		weighted_graph_algorithms ga = new WGraphAlgo();
		ga.init(g);
		ga.save("myGraph");
		ga.load("myGraph");
		((WGraphAlgo) ga).printGraph();
	}
}
