public class AVLNode {
    User data;
    int height;
    AVLNode left, right;

    public AVLNode(User data) {
        this.data = data;
        this.height = 1;
    }
}
