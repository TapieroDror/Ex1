package ex1;

import java.util.Collection;
import java.util.HashMap;

public class Edge implements edge_data {
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
	@Override
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
	@Override
	public double getWeight(node_info Ni) {
		if (hasNi(Ni))
			return neighbors.get(Ni);
		return -1;
	}

	/**
	 *this function return all the neighbors
	 *@return neighbors.keySet()
	 */
	@Override
	public Collection<node_info> getNi() {
		return neighbors.keySet();
	}

	/**
	 * this function remove the neighbor Ni
	 *@param Ni
	 *
	 */
	@Override
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
	@Override
	public void addNi(node_info Ni, double weight) {
		neighbors.put(Ni, weight);
	}

}
