
// Doubly Linked List for Action History
public class DNode {
    String data;
    DNode prev, next;

    public DNode(String data) {
        this.data = data;
        this.prev = this.next = null;
    }
}
