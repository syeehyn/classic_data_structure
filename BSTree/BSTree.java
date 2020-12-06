/*
 * NAME: Jackie Yuan
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of a Binary Search Tree.
 * The tree holds Node objects that have key,value structure.
 */

public class BSTree<T> implements IDupTree<T> {
    private BSTNode root;    //BST Tree root
    private int nelems;      //Number of nodes in the trees

    /**
     * Inner class defining the node structure that will be used for the BST Tree
     * */
    protected class BSTNode {
        Integer key;            //The value on which the node will be placed
        ArrayList<T> dataList;  /*The associated data stored in the node.
                                  Since there are duplicates we store as a list*/
        BSTNode leftChild;      //The left child
        BSTNode rightChild;     //The right child


        /**
         * Constructs a leaf node that stores an integer key and it's associated data
         *
         */
        BSTNode(BSTNode leftChild, BSTNode rightChild, Integer key, T data) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.key = key;
            this.dataList = new ArrayList<T>();
            dataList.add(data);
        }
        /**
         * Think about what other methods you might need…
         * accessors (data, key, left, right)
         * mutators (add/remove data, updates children)
         */



        /**
         * Gets the key of the this node
         *
         */
        Integer getKey() {
            return this.key;
        }

        /**
         * sets left node pointer for this node
         *
         */
        void setLeftChild(BSTNode newLeftChild) {
            this.leftChild = newLeftChild;
        }

        /**
         * Returns the left child node of the current node
         *
         */
        BSTNode getLeftChild() {
            return this.rightChild;
        }

        /**
         * sets right node pointer for this node
         *
         */
        void setRightChild(BSTNode newRightChild) {
            this.rightChild = newRightChild;
        }

        /**
         * Returns the right child node of the current node
         *
         */
        BSTNode getRightChild() {
            return this.rightChild;
        }

        /**
         * Returns the list of data associated with this node
         *
         */
        ArrayList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Adds a new value to an existing node
         *
         */
        void addData(T data) {
            this.dataList.add(data);
        }


        /**
         * HELPER METHODS FOR TESTING*/
        private boolean subTreeCompare(BSTNode root) {
            if (nodeCompare(this, root)) {
                boolean left = true;
                boolean right = true;

                if (this.leftChild != null) {
                    left = this.leftChild.subTreeCompare(root.leftChild);
                } else if (root.leftChild != null) {
                    left = false;
                } else {
                    left = true;
                }
                if (this.rightChild != null) {
                    right = this.rightChild.subTreeCompare(root.rightChild);
                } else if (root.rightChild != null) {
                    right = false;
                } else {
                    right = true;
                }

                return (left && right);
            }

            return false;
        }

        /**
         * HELPER METHODS FOR TESTING*/
        private boolean nodeCompare(BSTNode one, BSTNode two) {
            if (one == null && two == null) {
                return true;
            } else if (one == null || two == null) {
                return false;
            } else if (one.getKey() == two.getKey()) {
                List<T> oneData = one.getDataList();
                List<T> twoData = two.getDataList();
                if (oneData.equals(twoData)) {
                    return true;
                }
            }

            return false;
        }

    }


    /**
     * Constructs an empty BST.
     */
    public BSTree() {
        root = null;
        nelems = 0;
    }

    /**
     * Returns the root of this tree. Only used in the treeCompare method
     * (refer to writeup to read more about treeCompare)
     *
     *
     * */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Returns the number of nodes in this tree
     *
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Finds if a Node in the BST holds the key
     *
     */
    public boolean containsKey(Integer key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("The key should be integer.");
        }
        BSTNode toCheck = this.getRoot();
        while (toCheck != null) {
            if (toCheck.key.equals(key)) {
                return true;
            } else if (toCheck.getKey() < key) {
                toCheck = toCheck.rightChild;
            } else if (toCheck.getKey() > key) {
                toCheck = toCheck.leftChild;
            }
        }
        return false;
    }

    /**
     * Finds a Node in the BST based on key and returns it's data
     *
     */
    public List<T> getData(Integer key) throws NullPointerException, NoSuchElementException {
        if (key == null) {
            throw new NullPointerException("key should be integer");
        }
        BSTNode toGet = this.getRoot();
        if (!this.containsKey(key)) {
            throw new NoSuchElementException("key is not existed");
        }
        while (!toGet.getKey().equals(key)) {
            if (toGet.getKey() < key && toGet.rightChild == null) {
                throw new NoSuchElementException("key is not existed");
            } else if (toGet.getKey() > key && toGet.leftChild == null) {
                throw new NoSuchElementException("key is not existed");
            } else if (toGet.getKey() < key) {
                toGet = toGet.rightChild;
            } else if (toGet.getKey() > key) {
                toGet = toGet.leftChild;
            }
        }
        return toGet.getDataList();
    }

    /**
     * Checks to see if a key,value pair exists in the tree
     *
     */
    public boolean findPair(int key, T element) {
        try {
            return this.getData(key).contains(element);
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Inserts the data into the tree at a Node with the appropriate key value.
     * If one does not exist it will create a new node in the tree.
     *
     */
    public boolean insert(Integer key, T data) throws NullPointerException {
        if (key == null || data == null) {
            throw new NullPointerException("Should be a valid input.");
        }
        if (this.containsKey(key)) {
            this.getData(key).add(data);
            return true;
        } else {
            this.nelems++;
            if (this.getRoot() == null) {
                this.root = new BSTNode(null, null, key, data);
            } else {
                BSTNode toSet = this.getRoot();
                while (toSet != null) {
                    if (toSet.getKey() < key) {
                        if (toSet.rightChild == null) {
                            toSet.setRightChild(new BSTNode(null, null, key, data));
                            break;
                        }
                        toSet = toSet.rightChild;
                    } else if (toSet.getKey() > key) {
                        if (toSet.leftChild == null) {
                            toSet.setLeftChild(new BSTNode(null, null, key, data));
                            break;
                        }
                        toSet = toSet.leftChild;
                    }
                }
            }
        }
        return true;
    }


    /**
     * EXTRA CREDIT!
     *
     *  Removes node from tree with given key
     *
     */
    public List<T> remove(Integer key) throws  NoSuchElementException {
        if (!this.containsKey(key)) {
            throw new NoSuchElementException("key is not existed");
        }
        BSTNode parent = null;
        BSTNode node = this.getRoot();
        BSTNode child = this.getRoot();
        while (node != null && node.key != key) {
            parent = node;
            if (key.compareTo(node.key) < 0) {
                node = node.leftChild; //Scan left
            } else {
                node = node.rightChild; //Scan right
            }
        }

        if (node.leftChild == null && node.rightChild == null) {
            if (parent == null) {
                root = null;
            } else if (key.compareTo(parent.key) < 0) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (node.leftChild == null) { //If left child is null, get right child
            child = node.rightChild;
            swapKeys(node, child);
            swapData(node, child);
            node.leftChild = child.leftChild;
            node.rightChild = child.rightChild;
        } else if (node.rightChild == null) { //If right child is null, get left child
            child = node.leftChild;
            swapKeys(node, child);
            swapData(node, child);
            node.leftChild = child.leftChild;
            node.rightChild = child.rightChild;
        } else { // two children case
            child = node.leftChild;
            parent = null;
            while (child.rightChild != null) {
                parent = child;
                child = parent.rightChild;
            }
            if (parent == null) {
                swapKeys(node, child);
                swapData(node, child);
                node.leftChild = child.leftChild;
            } else {
                swapKeys(node, child);
                swapData(node, child);
                parent.rightChild = child.leftChild;
            }
        }
        this.nelems--;
        return null;
    }

    /**
     * helper method to swap keys
     * @param node destination
     * @param child from
     */
    private void swapKeys(BSTNode node, BSTNode child) {
        int temp = node.key;
        node.key = child.key;
        child.key = temp;
    }

    /**
     * helper method to swap data
     * @param node destination
     * @param child from
     */
    private void swapData(BSTNode node, BSTNode child) {
        ArrayList<T> temp = node.dataList;
        node.dataList = child.dataList;
        child.dataList = temp;
    }

    /**
     * HELPER METHODS FOR TESTING. Refer to the writeup and BSTreeTester.java
     * file for info on how to use it*/
    public boolean treeCompare(BSTree<T> other) {
        if (root == null && other.root == null) {
            return true;
        } else if (root == null || other.root == null) {
            return false;
        }
        return root.subTreeCompare(other.root);
    }

    /**
     * EC 3 check if the tree is completed.
     * @return true is bst is complete, false otherwise.
     */
    public boolean isCompleteBT()
    {
        // An empty tree is complete BST
        if(this.root == null)
            return true;

        // Create an empty stack.
        LinkedList stack =new LinkedList();

        // when a non full node is seen set to true.
        boolean full = false;

        stack.add(this.root);
        while(!stack.isEmpty())
        {
            BSTNode toCheck = (BSTNode) stack.remove();

            if(toCheck.leftChild != null)
            {
                if(full == true)
                    return false;

                stack.add(toCheck.leftChild);
            }
            else
                full = true;

            if(toCheck.rightChild != null)
            {
                if(full == true)
                    return false;

                stack.add(toCheck.rightChild);

            }
            else
                full = true;
        }
        return true;
    }
}
