package ex1;

import java.util.Collection;

/**
 * @author Tapiero Dror
 *
 */
public interface edge_data {
	public void addNi(node_info Ni, double weight);

	public boolean hasNi(node_info Ni);

	public double getWeight(node_info Ni);

	public Collection<node_info> getNi();

	public void removeNi(node_info Ni);
}
