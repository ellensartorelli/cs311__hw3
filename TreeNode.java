
public class TreeNode {
	
	private TreeNode parent;
	private Example[] pos, neg;
	boolean isLeaf, decision;
	int feature;
	TreeNode trueChild;
	TreeNode falseChild;
	boolean[] featuresUsed;
	
	//Sets the decision (true or false) for this node
	public void setDecision(){
		if(pos.length >= neg.length)
			decision = true;
		else
			decision = false;
	}
	
	//Sets the isLeaf variable for this node
	public void setIsLeaf(){
		if(pos.length ==0 || neg.length == 0)
			isLeaf = true;
		else
			isLeaf = false;
		
	}
	
	//Sets the features already used up to this node based on the features
	//used up to the parent node.
	public void setFeaturesUsed(){
		if(parent==null){
			featuresUsed = new boolean[pos[0].getSize()];
			for(int i=0; i<featuresUsed.length; i++)
				featuresUsed[i] = false;
		}else{
			featuresUsed = new boolean[parent.featuresUsed.length];
			for(int i =0; i<featuresUsed.length; i++){
				featuresUsed[i]=parent.featuresUsed[i];
			}
		}
	}
	
	//Records that feature f has been used.
	public void setFeature(int f){
		feature = f;
		featuresUsed[f] = true;
	}
	
	//Returns the feature number of this node.
	public int getFeature(){
		return feature;
	}
	
	//Sets the parent of this node.
	public void setParent(TreeNode p){
		parent = p;
		setFeaturesUsed();
	}
	
	//Sets the positive examples of this node to p
	public void setPos(Example[] p)
	{
		pos = p;
	}
	
	//Sets the negative examples of this node to n
	public void setNeg(Example[] n)
	{
		neg = n;
	}
	
	//Returns the positive examples of this node.
	public Example[] getPos()
	{
		return pos;
	}
	
	//Returns the negative examples of this node.
	public Example[] getNeg()
	{
		return neg;
	}
	
	
}