public class TreeNode
{
	public TreeNode(Person initValue, TreeNode initLeft, TreeNode initRight, TreeNode parent1)
	{
		value = initValue;
		left = initLeft;
		right = initRight;
		parent = parent1;
	}

	public Person getValue()						{ return value;}
	public TreeNode getLeft()						{ return left;}
	public TreeNode getRight()						{ return right;}
	public TreeNode getParent()						{ return parent;}
	public void setValue(Person theNewValue)			{ value = theNewValue;}
	public void setLeft(TreeNode theNewLeft)		{ left = theNewLeft;}
	public void setRight(TreeNode theNewRight)	{ right = theNewRight;}
	public void setParent(TreeNode theNewParent)	{ parent = theNewParent;}

	private Person value;
	private TreeNode left;
	private TreeNode right;
	private TreeNode parent;
}


