package ex1;

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
