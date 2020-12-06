/*
 * NAME: Jackie Yuan
 */
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of AVL tree key structure that holds Node objects
 * that stores generics as their key.
 */
public class AVLTree<T> implements IDupTree<T> {

    private AVLNode root; //AVL tree root
    private int nelems;   //Number of nodes in tree
    private final int BALANCE = 2;

    /**
     * Inner class defining the node structure that will be used for the AVL Tree
     * */
    public class AVLNode {

        private Integer key;         //The value on which the node will be placed
        ArrayList<T> dataList;       /*The associated data stored in the node.
                                      Since there are duplicates we store as a list*/
        private int height;          //The height of this node
        private AVLNode leftChild;   //Left child
        private AVLNode rightChild;  //Right child
        private AVLNode parent; //The parent of this node. Using this is OPTIONAL but recommended


        /**
         * Constructs a leaf node that stores an integer key and it's associated data
         *
         */
        AVLNode(Integer key, T data) {
            this.key = key;
            this.dataList = new ArrayList<T>();
            dataList.add(data);
        }

        /**
         * Gets the key of the this node
         *
         */
        Integer getKey() {
            return this.key;
        }

        /**
         * Sets the Node pointer to the left child of the current node and parent
         * pointer of the left child back up
         * to this node
         *
         */
        void setLeftChild(AVLNode leftChild) {
            this.leftChild = leftChild;
            if (leftChild != null) {
                leftChild.setParent(this);
            }
            this.updateHeight();
        }

        /**
         * Returns the left child node of the current node
         *
         */
        AVLNode getLeftChild() {
            return this.leftChild;
        }

        /**
         * Sets the Node pointer to the right child of the current
         * node and parent pointer of the right child back up
         * to this node
         *
         */
        void setRightChild(AVLNode rightChild) {
            this.rightChild = rightChild;
            if (rightChild != null) {
                rightChild.setParent(this);
            }
            this.updateHeight();
        }

        /**
         * Returns the right child node of the current node
         *
         */
        AVLNode getRightChild() {
            return this.rightChild;
        }

        /**
         * Sets the parent pointer of this node to the passed in node
         *
         */
        void setParent(AVLNode parent) {
            this.parent = parent;
        }

        /**
         * Returns the parent node of this node
         *
         */
        AVLNode getParent() {
            return this.parent;
        }

        /**
         * Sets the height value of this node
         *
         */
        void setHeight(int height) {
            this.height = height;
        }

        /**
         * Returns the height of this node
         *
         */
        int getHeight() {
            return this.height;
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
         * Recursively updates the height of this node and it's parent node
         */
        private void updateHeight() {

            AVLNode left = this.getLeftChild();
            AVLNode right = this.getRightChild();
            int leftHeight = 0;
            int rightHeight = 0;

            //Get heights
            if (left != null) {
                leftHeight = left.getHeight();
            }
            if (right != null) {
                rightHeight = right.getHeight();
            }

            //Get max child height
            int maxChildHeight = leftHeight;
            if (rightHeight > leftHeight) {
                maxChildHeight = rightHeight;
            }

            //Set height
            if (left == null && right == null) {
                this.setHeight(0);
            } else {
                this.setHeight(maxChildHeight + 1);
            }
        }

        /**
         * Determines the balance factor of the current node
         * The balance factor is equal to the difference between the
         * height of the left subtree and the right subtree of the current node.
         *
         */
        int getBalanceFactor() {
            int leftChildHeight;
            int rightChildHeight;
            if (this.leftChild == null) {
                leftChildHeight = -1;
            } else {
                leftChildHeight = this.getLeftChild().height;
            }
            if (this.rightChild == null) {
                rightChildHeight = -1;
            } else {
                rightChildHeight = this.getRightChild().height;
            }
            return leftChildHeight - rightChildHeight;
        }



        /**
         * HELPER METHOD FOR TESTING. Refer to the writeup and AVLTreeTest.java
         * file for info on how to use it*/
        private boolean subTreeCompare(BSTree.BSTNode root) {
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
         * HELPER METHOD FOR TESTING. Refer to the writeup and AVLTreeTest.java
         * file for info on how to use it*/
        private boolean nodeCompare(AVLNode one, BSTree.BSTNode two) {
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
     * Constructs an empty AVL tree.
     */
    public AVLTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Constructs an AVL tree, setting root to a new Node that contains
     * the T value, key
     *
     */
    public AVLTree(Integer key, T data) {
        this.root = new AVLNode(key, data);
        this.nelems = 1;
        this.root.height = 0;
    }

    /**
     * Returns the number of nodes in this tree
     *
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Finds if a Node in the AVL tree holds the key
     *
     */
    public boolean containsKey(Integer key) {
        AVLNode toCheck = this.root;
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
     * Finds a Node in the AVL tree based on key and returns it's data
     *
     */
    public List<T> getData(Integer key) {
        AVLNode toGet = this.root;
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
    // To change
    public boolean insert(Integer key, T data) throws  NullPointerException {
        if (key == null || data == null) {
            throw new NullPointerException("Should be valid inputs");
        }
        if (this.containsKey(key)) {
            this.getData(key).add(data);
        } else {
            this.nelems++;
            if (this.root == null) {
                this.root = new AVLNode(key, data);
            } else {
                AVLNode toSet = this.root;
                AVLNode node = new AVLNode(key, data);
                while (toSet != null) {
                    if (toSet.getKey() < key) {
                        if (toSet.rightChild == null) {
                            toSet.setRightChild(node);
                            break;
                        }
                        toSet = toSet.rightChild;
                    } else if (toSet.getKey() > key) {
                        if (toSet.leftChild == null) {
                            toSet.setLeftChild(node);
                            break;
                        }
                        toSet = toSet.leftChild;
                    }
                }
                node = node.parent;
                while (node != null) {
                    rebalance(node);
                    node = node.parent;
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
    public List<T> remove(Integer key) throws NoSuchElementException {

        if (!this.containsKey(key)) {
            throw new NoSuchElementException("key is not existed");
        }
        AVLNode parent = null;
        AVLNode node = this.root;
        AVLNode child = this.root;
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
        node = parent;
        while (node != null) {
            rebalance(node);
            node = node.parent;
        }

        this.nelems--;
        return null;
    }

    /**
     * Helper method to swapkeys
     * @param node destination
     * @param child from
     */
    private void swapKeys(AVLNode node, AVLNode child) {
        int temp = node.key;
        node.key = child.key;
        child.key = temp;
    }

    /**
     * Helper method to swapdata
     * @param node destination
     * @param child from
     */
    private void swapData(AVLNode node, AVLNode child) {
        ArrayList<T> temp = node.dataList;
        node.dataList = child.dataList;
        child.dataList = temp;
    }


    /**
     * OPTIONAL METHOD BUT RECOMMENDED
     * Rebalances the AVL rooted at current
     *
     */
    private void rebalance(AVLNode current) {

        current.updateHeight();
        //Too right heavy
        if (current.getBalanceFactor() == -BALANCE) {

            // Double rotation case.
            if (current.getRightChild().getBalanceFactor() == 1) {
                this.rotateRight(current.rightChild);
            }

            this.rotateLeft(current);

        } else if (current.getBalanceFactor() == BALANCE) {

            // Double rotation case.
            if (current.getLeftChild().getBalanceFactor() == -1) {
                this.rotateLeft(current.leftChild);
            }
            this.rotateRight(current);
        }
    }

    /**
     * OPTIONAL METHOD BUT RECOMMENDED
     * Replaces the child of a node with a different node
     *
     */
    private void replaceChild(AVLNode parent, AVLNode currentChild, AVLNode newChild) {
        if (parent.getLeftChild() == currentChild) {
            parent.setLeftChild(newChild);
        } else if (parent.getRightChild() == currentChild) {
            parent.setRightChild(newChild);
        }
    }

    /**
     * Rotates tree rooted at node, to the right. Based on ZYBooks algorithm
     *
     */
    private void rotateRight(AVLNode node) {
        AVLNode leftRightChild = node.getLeftChild().getRightChild();
        if (node.getParent() != null) {
            replaceChild(node.parent, node, node.leftChild);
        } else {
            this.root = node.leftChild;
            this.root.parent = null;
        }
        node.leftChild.setRightChild(node);
        node.setLeftChild(leftRightChild);

    }

    /**
     * Rotates tree rooted at node, to the left. ZYBooks algorithm
     *
     */
    private void rotateLeft(AVLNode node) {
        AVLNode rightLeftChild = node.getRightChild().getLeftChild();
        if (node.getParent() != null) {
            replaceChild(node.getParent(), node, node.rightChild);
        } else {
            this.root = node.getRightChild();
            this.root.parent = null;
        }
        node.rightChild.setLeftChild(node);
        node.setRightChild(rightLeftChild);

    }


    /**
     * HELPER METHODS FOR TESTING. Refer to the writeup and AVLTreeTest.java file
     * for info on how to use it*/
    public boolean treeCompare(BSTree<T> other) {
        if (root == null && other.getRoot() == null) {
            return true;
        } else if (root == null || other.getRoot() == null) {
            return false;
        }
        return root.subTreeCompare(other.getRoot());
    }

}
