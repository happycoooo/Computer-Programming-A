package hashtable;

public class StudentLinkedList {
    private Student head;

    public void add(Student newStudent){
        if(head == null){
            head = newStudent;
            return;
        }

        Student temp = head;

        while(true){
            if(temp.next  == null){
                break;
            }
            temp = temp.next; //继续向后查找
        }
        temp.next = newStudent;  //添加新结点
    }


    public void list(int no){
        if(head == null){
            System.out.print("第"+no+"链表是空");
            return;
        }

        Student temp = head;
        while(true){
            System.out.printf("id = %d, name = %s\t", temp.id, temp.name);
            if(temp.next == null) break;
            temp = temp.next;
        }
        System.out.println();
    }


    public Student findById(int id){
        if(head == null){
            System.out.println("该链表为空");
            return null;
        }
        Student temp = head;
        while(true){
            if(temp.id == id){
                break;
            }

            if (temp.next == null) {
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

}
