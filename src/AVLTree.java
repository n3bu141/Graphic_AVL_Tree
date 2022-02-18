class AVLTree {
    private AVLNode root;

    //Constructor to set null value to the rootNode
    public AVLTree() {
        root = null;
    }

    public AVLNode getRoot(){
        return root;
    }

    //create removeAll() method to make AVL Tree empty
    public void removeAll() {
        root = null;
    }

    // create checkEmpty() method to check whether the AVL Tree is empty or not
    public boolean checkEmpty() {
        if (root == null)
            return true;
        else
            return false;
    }

    // create insertElement() to insert element to to the AVL Tree
    public void add(int element) {
        root = add(element, root);
    }

    //create getHeight() method to get the height of the AVL Tree
    private int getHeight(AVLNode node) {
        return node == null ? -1 : getHeight(node);
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }


    //create insertElement() method to insert data in the AVL Tree recursively
    private AVLNode add(int element, AVLNode node) {
        //check whether the node is null or not
        if (node == null)
            node = new AVLNode(element);
            //insert a node in case when the given element is lesser than the element of the root node
        else if (element < node.getValue()) {
            node.setLeft(add(element, (AVLNode) node.left()));
            if (node.balanceFactor() > 1)
                if (element < node.left().getValue())
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithLeftChild(node);
        } else if (element > node.getValue()) {
            node.setRight(add(element, (AVLNode) node.right()));
            if (node.balanceFactor() < -1)
                if (element > node.right().getValue())
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithRightChild(node);
        } else ;  // if the element is already present in the tree, we will do nothing

        return node;

    }

    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private AVLNode rotateWithLeftChild(AVLNode node2) {
        AVLNode node1 = (AVLNode) node2.left();
        node2.setLeft(node1.right());
        node1.setRight(node2);
        return node1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private AVLNode rotateWithRightChild(AVLNode node1) {
        AVLNode node2 = (AVLNode) node1.right();
        node1.setRight(node2.left());
        node2.setLeft(node1);
        return node2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private AVLNode doubleWithLeftChild(AVLNode node3) {
        node3.setLeft(rotateWithRightChild((AVLNode) node3.left()));
        return rotateWithLeftChild(node3);
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private AVLNode doubleWithRightChild(AVLNode node1) {
        node1.setRight(rotateWithLeftChild((AVLNode) node1.right()));
        return rotateWithRightChild(node1);
    }

    public void assignOffsets(){
        root.setOffset(0);
        root.setLevel(0);
        assignOffsets(root);
    }

    private void assignOffsets(AVLNode x){
        if(x.getOffset() == 0){
            if(x.left() != null){
                x.left().setOffset(-1);
                x.left().setLevel(x.getLevel() + 1);
                assignOffsets((AVLNode) x.left());

            }if(x.right() != null){
                x.right().setOffset(1);
                x.right().setLevel(x.getLevel() + 1);
                assignOffsets((AVLNode) x.right());

            }
        }

        if(x.getOffset() < 0){
            if(x.left() != null){
                x.left().setOffset(x.getOffset() * 2);
                x.left().setLevel(x.getLevel() + 1);
                assignOffsets((AVLNode) x.left());
            }if(x.right() != null){
                x.right().setOffset(x.getOffset() * 2 + 1);
                x.right().setLevel(x.getLevel() + 1);
                assignOffsets((AVLNode) x.right());
            }
        }

        if(x.getOffset() > 0){
            if(x.left() != null){
                x.left().setOffset(x.getOffset() * 2 - 1);
                x.left().setLevel(x.getLevel() + 1);
                assignOffsets((AVLNode) x.left());
            }if(x.right() != null){
                x.right().setOffset(x.getOffset() * 2);
                x.right().setLevel(x.getLevel() + 1);
                assignOffsets((AVLNode) x.right());
            }
        }
    }
}

