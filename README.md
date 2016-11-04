CS 311 Homework 3
- All members were present and contributing during all work on this project.
- We did not give nor receive unauthorized aid on this assignment.

1. **Ellen Sartorelli, Joy Wood**
2. **Use TestClassifier.java to run our decision tree from DecisionTree.java that
    uses code from TreeNode.java, Example.java. (Our program does not include an
    extra files besides those initially provided)**
3. **No known bugs.**
4. **Part 2 Discussion:**
  * The decision tree shows the most effective feature to begin predicting a hepatitis patient's survival is checking for varices, enlarged viens in the throat often linked to serious liver diseases. If varices were absent, the resulting decision tree branch was quite conclusive, checking only one feature, histology, to detemine whether a patient survived.
  On the other hand, if varices are present, we check out a myriad of features, the next being feature 19, "sgot gt 156," an indicator that levels of SGOT, an enzyme to linked to liver disease, are higher than 156. Normal levels for the enzyme range between 5 and 40. While checking SGOT provided more information than the other features, it wasn't totally conclusive as it branched out leaving multiple features  to be checked.
  
  * Positive examples correct: 38 out of 42 - about 90% correct, 4 false negatives
  - Negative examples correct: 7 out of 10 - about 70% correct, 3 false positives
5. **Part 3 Discussion:**
  1. Zoo Data Set
  2. Data from University of California Irvine Machine Learning Repository
  3. This dataset describes basic information about animals. The features are binary and are as follow: hair, feathers, milk, airbone, aquatic, predator, toothed, backbone, breathes, venom, fins, tail and domestic. We are predicting whether or not an animal lays eggs.
  4. 101 examples in the data set - 50 devoted to training, 51 devoted to testing
  5. 12 features

  * In classifying whether or not a given animal lays eggs, our decision tree used the feature "milk" to most efficiently predict whether or not an animal lays eggs. The tree decided on "milk" as the root feature because it would provide the greatest information gain. In reality, all of the information that our tree needed to decide whether or not an animal layed eggs was provided in the "milk" feature during the training set, therefore our tree only had one branch. When we ran our tree on testing set it failed to perform with complete certainty because none of the animals that both lay eggs and produce milk (ie: playtus) were in the training set. Had we had a larger dataset with a full range of animals, we may have been able to increase the accurary of the tree.
  * Positive examples correct: 31 out of 32 - about 97% correct, 1 false negatives
  - Negative examples correct: 17 out of 19 - about 90% correct, 2 false positives
