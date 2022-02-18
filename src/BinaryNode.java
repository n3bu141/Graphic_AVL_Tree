public class BinaryNode {
    private BinaryNode left, right;
    private int offset, level;
    private Integer val;

    public BinaryNode(Integer x){
        val = x;
        left = right = null;
        offset = level = 0;
    }

    public BinaryNode(Integer x, BinaryNode newLeft, BinaryNode newRight){
        val = x;
        left = newLeft;
        right = newRight;
    }

    public void setLeft(BinaryNode newLeft){
        left = newLeft;
    }

    public void setRight(BinaryNode newRight){
        right = newRight;
    }

    public void setValue(Integer newVal){
        val = newVal;
    }

    public void setOffset(int newOffset){
        offset = newOffset;
    }

    public void setLevel(int newLevel){
        level = newLevel;
    }

    public int getLevel(){
        return level;
    }

    public int getOffset(){
        return offset;
    }

    public BinaryNode left(){
        return left;
    }

    public BinaryNode right(){
        return right;
    }

    public Integer getValue(){
        return val;
    }
}
