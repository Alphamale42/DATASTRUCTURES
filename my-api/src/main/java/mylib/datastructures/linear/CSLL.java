/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.1
 * @since 1.0
 */

package mylib.datastructures.linear;

import mylib.datastructures.nodes.SNode;
 

/**
 * CSLL is a circular singly linked list. It is a subclass of SLL.
 * All methods of SLL except clear() are overridden to account for the circular nature of the list.
 */
 public class CSLL extends SLL {
     private SNode head = null;
     private int size;
 
 
 
 
     /**
      * Default CSLL constructor. Calls the default SLL constructor.
      */
     public CSLL() {
         super();
     }
 
     /**
      * CSLL constructor that takes a head node as its input.
      * @param headInput  A node that will be the head of the new CSLL.
      */
     public CSLL(SNode headInput) {
         this.head = headInput;
         this.head.next = this.head;
         this.size = 1;
     }
 
     /**
      * Inserts a new node at the head of the list.
      * @param newNode  The node to be inserted at the head of the list.
      */
     @Override
     public void insertHead(SNode newNode) {
         if(this.head == null) {
             this.head = newNode;
             this.head.next = this.head;
             this.size++;
         } else {
             // must traverse the list to find the tail.
             SNode temporary = this.head;
             while(temporary.next != this.head) {
                 temporary = temporary.next;
             }
             newNode.next = this.head;
             temporary.next = newNode;
             this.head = newNode;
             this.size++;
         }
     }
 
     /**
      * Inserts a new node at the tail of the list.
      * @param newNode  The node to be inserted at the tail of the list.
      */
     @Override
     public void insertTail(SNode newNode) {
         if(this.head == null) {
             this.head = newNode;
             this.head.next = this.head;
             this.size++;
         } else {
             // must traverse the list to find the tail.
             SNode temporary = this.head;
             while(temporary.next != this.head) {
                 temporary = temporary.next;
             }
             newNode.next = this.head;
             temporary.next = newNode;
             this.size++;
         }
     }
 
     /**
      * inserts a new node at a given position in the list.
      * @param newNode   The node to be inserted into the list.
      * @param position  The position at which the node should be inserted.
      */
     @Override
     public void insert(SNode newNode, int position) {
         // One of three cases:
         // 1) Position = 1, which means to insert at the head position
         // 2) Position != 1 AND position != last position.
         // 3) Position = last position of the linked list.
 
         if(position <= 1) {
             this.insertHead(newNode); // changed this to use CSLL insertHead method.
         } else if (position > 1 && position <= this.size) {
             SNode current = this.head;
             SNode prev = this.head;
             int currentNum = 1;
             while(currentNum != position) {
                 prev = current;
                 current = current.next;
                 currentNum += 1;
             }
             prev.next = newNode;
             prev.next.next = current;
             this.size += 1;
         }
         else{
             this.insertTail(newNode);
         }
     }
 
     /**
      * Deletes the head node of the list.
      */
     @Override
     public void deleteHead() {
         if(this.head == null){
             return;
         } else if(this.head.next == this.head) {
             this.head = null;
             this.size--;
         } else {
             // must traverse the list to find the tail.
             SNode temporary = this.head;
             while(temporary.next != this.head) {
                 temporary = temporary.next;
             }
             temporary.next = this.head.next;
             this.head = this.head.next;
             this.size--;
         }
     }
 
     /**
      * Deletes the tail node of the list.
      */
     @Override
     public void deleteTail() {
         if(this.head == null) {
             return;
         } else if(this.head.next == this.head) {
             this.head = null;
             this.size--;
         } else {
             // must traverse the list to find the tail.
             SNode temporary = this.head;
             while(temporary.next.next != this.head) {
                 temporary = temporary.next;
             }
             temporary.next = this.head;
             this.size--;
         }
     }
 
     /**
      * Searches the list for a given node.
      * @param node  The node to be searched for.
      */
     @Override
     public SNode Search(SNode node) {
         if(this.head != null){
             SNode current = this.head;
             do {
                 if(current == node){
                     return node;
                 }
                 current = current.next;
             } while(current != this.head);
         }
         return null;
     }
 
     /**
      * Prints out a detailed description of the list.
      * This includes the length of the list, the sorted status, and the data in each node.
      */
     @Override
     public void print() {
         SNode current = this.head;
         System.out.print("List Information:\n");
         System.out.printf("List length: %d\n", this.size);
         System.out.printf("Sorted status: " + this.isSorted() + "\n");
 
         int i = 1;
         do {
             System.out.printf("Data in list item #%d: %d\n", i, current.data);
             current = current.next;
             i++;
         } while(current != this.head);
     }
 
     /**
      * Checks if the list is sorted.
      * @return  True if the list is sorted, false otherwise.
      */
     @Override
     public boolean isSorted() {
         // Idea here: for every node, check with every other prev. node.
         if(this.head != null) {
             int[] allData = new int[this.size];
             SNode current = this.head;
             int i = 0;
             do{
                 allData[i] = current.data;
                 for(int j = 0; j < i; j++) {
                     if(allData[j] > current.data) {
                         return false;
                     }
                 }
                 current = current.next;
                 i++;
             } while(current != this.head);
             return true;
         } else {
             return true;
         }
     }
 
     /**
      * If the given node is in the list, deletes it.
      */
     @Override
     public void delete(SNode node) {
         if(this.head != null) {
             if(this.head == node) {
                 deleteHead();
                 return;
             }
             SNode current = this.head;
             SNode prev = null;
             do {
                 prev = current;
                 current = current.next;
                 if(current == node) {
                     prev.next = current.next;
                     this.size--;
                     return;
                 }
             } while(current != this.head);
         }
     }
 
     /**
      * Sorts the list using insertion sort.
      */
     @Override
     //insertion sort method for the CSLL
     public void sort() {
         if(this.head == null || this.size == 1) {
             return;
         }
 
         SNode key = this.head.next;
         SNode prev = this.head;
         SNode current;
         SNode originalNode;
         for(int i = 1; i < this.size; i++) {
 
             int j = i;
             current = key;
             while(prev.next != key) {
                 prev = prev.next;
             }
 
             while(j > 0 && prev.data > current.data) {
                 int temp = prev.data; //Should swap the entire node instead of just the data
                 prev.data = current.data; // will fix another time
                 current.data = temp;
                 j--;
 
                 originalNode = prev;
                 while (prev.next != originalNode) {
                     prev = prev.next;
                 }
 
                 current = prev.next;
             }
             key = key.next;
         }
     }
 
     /**
      * Inserts a node into the list in sorted order.
      * If the list is not sorted, it will call sort() first.
      * @param node  The node to be inserted.
      */
     @Override
     public void sortedInsert(SNode node) {
         if(this.head == null) {
             this.insertHead(node);
             return;
         }
         if(!isSorted()) {
             sort();
         }
 
         if(this.size == 1) {
             if(this.head.data > node.data) {
                 insertHead(node);
             } else {
                 insertTail(node);
             }
             return;
         }
 
         int posCurr = 1;
         SNode current = this.head;
         do {
             if(current.data >= node.data) {
                 System.out.println("Reached??" + posCurr);
                 insert(node, posCurr);
                 return;
             }
             current = current.next;
             posCurr++;
         }
         while(current != this.head);
 
         insertTail(node);
     }
 
 }