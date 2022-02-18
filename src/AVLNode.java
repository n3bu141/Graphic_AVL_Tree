public class AVLNode extends BinaryNode {
    public AVLNode(Integer x) {
        super(x);
    }

    public int balanceFactor() {
        return getHeight(left()) - getHeight(right());
    }

    private int getHeight(BinaryNode k) {
        if (k == null)
            return -1;
        return 1 + Math.max(getHeight(k.left()),
                getHeight(k.right()));
    }
}