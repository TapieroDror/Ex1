package ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class WGraph_DS implements weighted_graph {
	private HashMap<Integer, node_info> graph;
	private HashMap<Integer, Edge> edges;
	private int numOfEdges;
	private int MC;
	public class NodeInfo implements node_info {

		private int key;
		private String info;
		private double tag;
		private int parent;

		/**
		 * this function build a new NodeInfo
		 * @param key
		 */
		public NodeInfo(int key) {
			super();
			this.key = key;
			this.info = "white";
			this.tag = Double.MAX_VALUE;
			this.parent=-1;

		}

		/**
		 * this function build a new NodeInfo
		 */
		public NodeInfo() {
			super();
			this.key = (int) (Math.random() * Integer.MAX_VALUE);
			this.info = "white";
			this.tag = Double.MAX_VALUE;
			this.parent=-1;

		}

		/**
		 * this function build a new NodeInfo
		 * @param node
		 */
		public NodeInfo(node_info node) {
			this.key = node.getKey();
			this.info = node.getInfo();
			this.tag = node.getTag();
			this.parent=-1;
		}

		/**
		 * this function return the key of the node
		 *@return key
		 */
		@Override
		public int getKey() {

			return key;
		}
		/**
		 * this function return the tag of the node
		 *@return tag
		 */
		@Override
		public double getTag() {

			return tag;
		}

		/**
		 * this function set the tag of the node
		 *@param t
		 */
		@Override
		public void setTag(double t) {
			this.tag = t;

		}
		
		/**
		 * this function return the info of the node
		 *@return info
		 */
		@Override
		public String getInfo() {
			return this.info;
		}

		/**
		 * this function set the info of the node
		 *@param s
		 */
		@Override
		public void setInfo(String s) {
			this.info = s;

		}
		/**
		 * this function return the parent of the node
		 * @return parent
		 */
		public int getParent() {
			return this.parent;
		}
		/**
		 * this function set the parent of the node
		 * @param p
		 */
		public void setParent(int p) {
			this.parent = p;

		}

	}
	public class Edge{
		private HashMap<node_info, Double> neighbors;

		/**
		 * this function build a new edge
		 */
		public Edge() {
			super();
			this.neighbors = new HashMap<>();
		}

		/**
		 * this function check if the node have a neighbor Ni
		 * return true if yes false if not.
		 * @param Ni
		 * @return true or false
		 */
		
		public boolean hasNi(node_info Ni) {
			if (neighbors.containsKey(Ni))
				return true;
			return false;
		}

		/**
		 * this function return the weight of the neighbor Ni
		 * if he dose not exist the function return false
		 *@param Ni
		 *@return neighbors.get(Ni) or -1
		 */
		
		public double getWeight(node_info Ni) {
			if (hasNi(Ni))
				return neighbors.get(Ni);
			return -1;
		}

		/**
		 *this function return all the neighbors
		 *@return neighbors.keySet()
		 */
		
		public Collection<node_info> getNi() {
			return neighbors.keySet();
		}

		/**
		 * this function remove the neighbor Ni
		 *@param Ni
		 *
		 */
		
		public void removeNi(node_info Ni) {
			if (!hasNi(Ni))
				return;
			neighbors.remove(Ni);

		}

		/**
		 * this function add a new neighbor Ni
		 *@param Ni, weight
		 *
		 */
		
		public void addNi(node_info Ni, double weight) {
			neighbors.put(Ni, weight);
		}

	}

	/**
	 *this function build a new WGraph_DS
	 *@param
	 *@return
	 */
	public WGraph_DS() {
		graph = new HashMap<>();
		edges = new HashMap<>();
		this.MC = 0;
		this.numOfEdges = 0;
	}
	/**
	 *this function build a new WGraph_DS
	 *@param numOfEdges
	 *@return
	 */
	public WGraph_DS(int numOfEdges) {
		graph = new HashMap<>();
		edges = new HashMap<>();
		this.MC = 0;
		this.numOfEdges = numOfEdges;
	}
	/**
	 * this function return all the edges in the graph
	 *@param
	 *@return edges
	 */
	public HashMap<Integer, Edge> getEdges() {
		return edges;
	}
	/**
	 * this function return the node in the graph with this key
	 *@param key
	 *@return graph.get(key);
	 */
	@Override
	public node_info getNode(int key) {

		return this.graph.get(key);
	}
	/**
	 * this function return true if the is an edge between node1 and node 2
	 * otherwise false
	 *@param node1, node2
	 *@return true or false
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {
		if (graph.containsKey(node1) && graph.containsKey(node2)) {
			return edges.get(node1).hasNi(graph.get(node2));
		}
		return false;
	}
	/**
	 * this function return the weight between node1 and node2
	 *@param node1, node2
	 *@return
	 */
	@Override
	public double getEdge(int node1, int node2) {
		if (hasEdge(node1, node2))
			return edges.get(node1).getWeight(graph.get(node2));
		return -1;
	}
	/**
	 * this function add a new node to WGraph_DS
	 *@param key
	 *@return
	 */
	@Override
	public void addNode(int key) {
		if (graph.containsKey(key)) {
			node_info temp = new NodeInfo();
			while (graph.containsKey(temp.getKey()))
				temp = new NodeInfo();
			graph.put(temp.getKey(), temp);
			edges.put(temp.getKey(), new Edge());
			MC++;
			System.out.println("your new key is "+temp.getKey()+" remember it!");
			return;
		}
		if(key<0)
			return;
		graph.put(key, new NodeInfo(key));
		edges.put(key, new Edge());
		MC++;
	}
	/**
	 * this function build an new edge between node1 and node2 with the weight w
	 *@param node1 node2 w
	 *@return
	 */
	@Override
	public void connect(int node1, int node2, double w) {
		if (!graph.containsKey(node1) || !graph.containsKey(node2) || hasEdge(node1, node2)||node1==node2)
			return;
		edges.get(node1).addNi(graph.get(node2), w);
		edges.get(node2).addNi(graph.get(node1), w);
		numOfEdges++;
	}
	/**
	 *this function return all the nodes in the WGraph_DS
	 *@return V
	 */
	@Override
	public Collection<node_info> getV() {
		Collection<node_info> V = graph.values();
		return V;
	}
	/**
	 * this function return all the neighbors of the node_id
	 *@param node_id
	 *@return V
	 */
	@Override
	public Collection<node_info> getV(int node_id) {
		Collection<node_info> V = edges.get(node_id).getNi();
		return V;
	}
	/**
	 * this function delete the node with this key from the WGraph_DS
	 *@param key
	 *
	 */
	@Override
	public node_info removeNode(int key) {
		if (!this.graph.containsKey(key))
			return null;
		Iterator<node_info> runner = edges.get(key).getNi().iterator();
		while (runner.hasNext()) {
			node_info node = runner.next();
			edges.get(node.getKey()).removeNi(graph.get(key));
			runner.remove();
			numOfEdges--;
			MC++;
		}
		this.MC++;
		edges.remove(key);
		return graph.remove(key);

	}
	/**
	 * this function remove the edge between node1 and node2
	 *@param node1,node2
	 *@return
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		if (node1 == node2)
			return;
		if (hasEdge(node1, node2)) {
			edges.get(node1).removeNi(graph.get(node2));
			edges.get(node2).removeNi(graph.get(node1));
			this.numOfEdges--;
			this.MC++;
		}
	}
	/**
	 *@param this function return the number of the node in the graph
	 *@return graph.size()
	 */
	@Override
	public int nodeSize() {

		return graph.size();
	}
	/**
	 *this function return the number of edges in the graph
	 *@return numOfEdges
	 */
	@Override
	public int edgeSize() {

		return numOfEdges;
	}
	/**
	 *this functions return the number of all the actions in the graph
	 *@return MC
	 */
	@Override
	public int getMC() {

		return MC;
	}
	/**
	 * this function print the graph
	 *@param
	 *@return
	 */
	public void myToString() {
		int counter = 1;
		System.out.println("The vertices are: ");
		for (node_info num : graph.values()) {
			if (counter == graph.values().size())
				System.out.print(num.getKey() + ".");
			else
				System.out.print(num.getKey() + ", ");
			counter++;
		}
		System.out.println();
		for (node_info num : graph.values()) {
			System.out.print("vertix is>" + num.getKey() + ", neighbors are:");
			counter = 1;
			for (node_info num1 : edges.get(num.getKey()).getNi()) {
				System.out.print(num1.getKey() + ", ");
				if (counter == edges.get(num.getKey()).getNi().size())
					System.out.print("and his weight:" + getEdge(num1.getKey(), num.getKey()) + ".");
				else
					System.out.print("and his weight:" + getEdge(num1.getKey(), num.getKey()) + " | ");
				counter++;

			}
			System.out.println();
		}
	}
	/**
	 * this function write to a txt file with the name fileName the details of the graph
	 *@param fileName
	 *@return
	 */
	public void writeFile(String fileName) {
		// try write to the file
		try {
			FileWriter fw = new FileWriter(fileName);
			PrintWriter outs = new PrintWriter(fw);
			int counter = 1;
			outs.println("The vertices are: ");
			for (node_info num : graph.values()) {
				if (counter == graph.values().size())
					outs.print(num.getKey() + ".");
				else
					outs.print(num.getKey() + ", ");
				counter++;
			}
			outs.println();
			for (node_info num : graph.values()) {
				outs.print("vertix is>" + num.getKey() + ", neighbors are:");
				counter = 1;
				for (node_info num1 : edges.get(num.getKey()).getNi()) {
					outs.print(num1.getKey() + ", ");
					if (counter == edges.get(num.getKey()).getNi().size())
						outs.print("and his weight:" + getEdge(num1.getKey(), num.getKey()) + ".");
					else
						outs.print("and his weight:" + getEdge(num1.getKey(), num.getKey()) + " | ");
					counter++;

				}
				outs.println();
			}
			outs.close();
			fw.close();
		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}
	/**
	 * this function read the details from a txt file with the name fileName and make a new graph from that details
	 *@param fileName
	 *
	 */
	public void readFile(String fileName) {
		// try read from the file
		weighted_graph g = new WGraph_DS();
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String str;
			br.readLine();
			str = br.readLine();
			int i = 0;
			while (i < str.length()) {
				if (str.charAt(i) != ',' && str.charAt(i) != ' ') {
					if (str.charAt(i + 1) != ',' && str.charAt(i + 1) != ' ' && str.charAt(i + 1) != '.') {
						String saver = "";
						while (str.charAt(i) != ',' && str.charAt(i) != ' ' && str.charAt(i) != '.') {
							saver += str.charAt(i);
							i++;
						}
						int saver2 = Integer.parseInt(saver);
						g.addNode(saver2);
					} else {
						String saver = "";
						saver += str.charAt(i);
						int saver2 = Integer.parseInt(saver);
						g.addNode(saver2);
					}
				}
				i++;
			}
			str = br.readLine();
			i = 0;
			int key = 0;
			int ni = 0;
			double we = 0;
			while (str != null) {
				while (i < str.length()) {
					if (str.charAt(i) == '>') {
						i++;
						String saver = "";
						while (str.charAt(i) != ',' && str.charAt(i) != ' ' && str.charAt(i) != '.') {
							saver += str.charAt(i);
							i++;
						}
						key = Integer.parseInt(saver);
					}
					if (((str.charAt(i) == ':' && str.charAt(i - 1) == 'e') || str.charAt(i) == '|')
							&& i < str.length()) {
						if (str.charAt(i) == ':') {
							i++;
							String saver = "";
							while (str.charAt(i) != ',' && str.charAt(i) != ' ' && str.charAt(i) != '.') {
								saver += str.charAt(i);
								i++;
							}
							ni = Integer.parseInt(saver);
						} else {
							i += 2;
							String saver = "";
							while (str.charAt(i) != ',' && str.charAt(i) != ' ' && str.charAt(i) != '.') {
								saver += str.charAt(i);
								i++;
							}
							ni = Integer.parseInt(saver);
						}
					}
					if (str.charAt(i) == ':' && str.charAt(i - 1) == 't' && i < str.length()) {
						i++;
						String saver = "";
						while (str.charAt(i) != ',' && str.charAt(i) != ' ' && str.charAt(i) != '.') {
							saver += str.charAt(i);
							i++;
						}
						we = Double.parseDouble(saver);
						g.connect(key, ni, we);
					}
					i++;
				}
				i = 0;
				str = br.readLine();
			}

			((WGraph_DS) g).myToString();
			br.close();
			fr.close();

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}
	public boolean equals(Object e)
	{
		if(!(e instanceof weighted_graph))
			return false;
		weighted_graph g=(WGraph_DS)e;
		if(g.edgeSize()!=this.edgeSize())
			return false;
		if(g.nodeSize()!=this.nodeSize())
			return false;
		for(node_info runner:g.getV())
		{
			if(!graph.containsKey(runner.getKey()))
				return false;
			if(g.getV(runner.getKey())!=null&&g.getV(runner.getKey()).size()!=this.getV(runner.getKey()).size())
				return false;
			if(g.getV(runner.getKey())!=null)
			{
				for(node_info runner2:g.getV(runner.getKey()))
				{
					if(!this.hasEdge(runner.getKey(), runner2.getKey()))
						return false;
					else if(g.getEdge(runner.getKey(), runner.getKey())!=this.getEdge(runner.getKey(), runner2.getKey()))
						return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
