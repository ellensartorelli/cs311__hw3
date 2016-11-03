import java.util.ArrayList;


public class DecisionTree {
	TreeNode root = null;
	
	//Example[] pos/neg are boolean arrays corresponding to whether or not the example
	//has the feature. These arrays are set in the driver TestClassifier.
	public void train(Example[] positive, Example [] negative){
		root = new TreeNode();
		root.setPos(positive);
		root.setNeg(negative);
		root.setParent(null);
		root.setDecision(); 
		root.setIsLeaf(); 
		
		if(!root.isLeaf){		
			train(root);
		}
		
	}
	
	//The recursive train method that builds a tree at node
	private void train(TreeNode node){
		// pos = node.getPos??? same for neg
		//find feature
		int feature = getFeature(node);
		if(feature != -1){
			node.setFeature(feature);
			createSubChildren(node);
			if(!node.trueChild.isLeaf){
				train(node.trueChild);
			}
			if(!node.falseChild.isLeaf){
				train(node.falseChild);
			}
		}else{
			node.setIsLeaf();
			node.setDecision();
		}
	}
	
	
	private int getFeature(TreeNode node){
		double minRInfo = 1;
		int minIndex = -1;
		for(int i = 0; i < node.featuresUsed.length; i++){
			if(!node.featuresUsed[i]){
				double RInfo = getRemainingInfo(i, node);
				if(RInfo < minRInfo){
					minRInfo = RInfo;
					minIndex = i;
				}
			}
		}
		return minIndex;
	}
	
	//Creates the true and false children of node
	private void createSubChildren(TreeNode node){
		
		node.trueChild = new TreeNode();
		node.falseChild = new TreeNode();

		ArrayList<Example> true_pos = new ArrayList<Example>();
		ArrayList<Example> true_neg = new ArrayList<Example>();
		ArrayList<Example> false_pos = new ArrayList<Example>();
		ArrayList<Example> false_neg = new ArrayList<Example>();
		int feature = node.getFeature();
		
		//for true child:
		
		for(Example i : node.getPos()){
			//for every example check feature we are looking at
			//add to positive or negative
			if(i.get(feature)){
				true_pos.add(i);
			}else
			{
				true_neg.add(i);
			}
		};
		
		Example[] true_positive = true_pos.toArray(new Example[true_pos.size()]);
		Example[] true_negative = true_neg.toArray(new Example[true_neg.size()]);
		
		
		//for false child:
		for(Example j : node.getNeg()){
			//for every example check feature we are looking at
			//add to positive or negative
			if(j.get(feature)){
				false_pos.add(j);
			}else
			{
				false_neg.add(j);
			}
		};
		
		Example[] false_positive = false_pos.toArray(new Example[false_pos.size()]);
		Example[] false_negative = false_neg.toArray(new Example[false_neg.size()]);
		
		node.falseChild.setPos(true_negative);
		node.falseChild.setNeg(false_negative);

		
		node.falseChild.setParent(node);
		node.falseChild.setDecision(); 
		node.falseChild.setIsLeaf(); 
		
		node.trueChild.setPos(true_positive);
		node.trueChild.setNeg(false_positive);

		
		node.trueChild.setParent(node);
		node.trueChild.setDecision(); 
		node.trueChild.setIsLeaf(); 
	}
	
	//Computes and returns the remaining info needed if feature is chosen
	//at node.
	private double getRemainingInfo(int feature, TreeNode node){
		//R(feature) = H(true)Pr(true) + H(false)Pr(false) 
		double rInfo = 0;
		int numPosT = 0;
		int numNegT = 0;
		int numPosF = 0;
		int numNegF = 0;

		
		for(int i = 0; i < node.getPos().length; i++){
			if(node.getPos()[i].get(feature)){
				numPosT++;
			}else{
				numNegT++;
			}
		}
		for(int i = 0; i < node.getNeg().length; i++){
			if(node.getNeg()[i].get(feature)){
				numPosF++;
			}else{
				numNegF++;
			}
		}
		
		double hTrue = getEntropy(numPosT, numPosF);
		double hFalse = getEntropy(numNegT, numNegF);
		
		double totalCases = (numPosT+numNegT+numPosF+numNegF);
		double totalSunny = numPosT+numPosF;
		double totalRainy = totalCases - totalSunny;
				
		rInfo = hTrue*(totalSunny/totalCases) + hFalse*(totalRainy/totalCases);
		
		return rInfo;
	}
	
	//Computes and returns the entropy given the number of positive and 
	//negative examples.
	private double getEntropy(double numPos, double numNeg){
		if(numPos==0 || numNeg ==0)
			return 0;
		double q = numPos/(numPos + numNeg);
		
		return -q*log2(q)-(1-q)*log2(1-q);	
	}
	
	//Computes and returns the log (base 2) of d. Used by the getEntropy method.
	private double log2(double d){
		return Math.log(d)/Math.log(2);
	}
	
	//Uses the tree to classify the given example as positive (true) or negative (false).
	public boolean classify(Example e){
		TreeNode current = root;
		
		while (!current.isLeaf){
			int feature = current.getFeature();
			if(e.get(feature)){
				current = current.trueChild;
			}
			else{
				current = current.falseChild;
			}
		}
		
		return current.decision;
	}
	
	
	//Prints the decision tree.
	public void print(){
		printTree(root, 0);
	}
	
	
	//Called by print() to print the decision tree.
	private void printTree(TreeNode node, int indent){
		if(node== null)
			return;
		if(node.isLeaf){
			if(node.decision)
				System.out.println("Positive");
			else
				System.out.println("Negative");
		}
		else{
			System.out.println();
			doIndents(indent);
			System.out.print("Feature "+node.getFeature() + " = True:");
			printTree(node.trueChild, indent+1);
			doIndents(indent);
			System.out.print("Feature "+node.getFeature() + " = False:");
			printTree(node.falseChild, indent+1);
		}
	}
	
	//Called by printTree to print out indentations.
	private void doIndents(int indent){
		for(int i=0; i<indent; i++)
			System.out.print("\t");
	}
}