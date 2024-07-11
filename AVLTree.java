public class AVLTree {
    AVLNode root;

    // Calculate height of AVL Node
    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Right rotate subtree rooted with y
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate subtree rooted with x
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of node N
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a user into the AVL tree
    AVLNode insert(AVLNode node, User user) {
        // Perform the normal BST insertion
        if (node == null)
            return (new AVLNode(user));

        if (user.getUserId().compareTo(node.data.getUserId()) < 0)
            node.left = insert(node.left, user);
        else if (user.getUserId().compareTo(node.data.getUserId()) > 0)
            node.right = insert(node.right, user);
        else // Duplicate userIds not allowed
            return node;

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left),
                height(node.right));

        // Get the balance factor of this ancestor node to check whether this node became unbalanced
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && user.getUserId().compareTo(node.left.data.getUserId()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && user.getUserId().compareTo(node.right.data.getUserId()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && user.getUserId().compareTo(node.left.data.getUserId()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && user.getUserId().compareTo(node.right.data.getUserId()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // A utility function to print preorder traversal of the tree
    // The function also prints height of every node
    void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.data.getName() + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}
