// Circular Linked List for Watering Schedule
public class CNode {
    Plant data;
    CNode next;

    public CNode(Plant data) {
        this.data = data;
        this.next = this;
    }
}
