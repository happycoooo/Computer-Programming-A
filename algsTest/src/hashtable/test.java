package hashtable;

public class test {
    public static void main(String[] args){
        HashTable hashTable = new HashTable(10);
        Student student1 = new Student(1,"A");
        Student student2 = new Student(2,"B");
        Student student3 = new Student(3,"C");
        Student student4 = new Student(4,"D");
        Student student5 = new Student(4,"E");
        hashTable.add(student1);
        hashTable.add(student2);
        hashTable.add(student3);
        hashTable.add(student4);
        hashTable.add(student5);

        hashTable.list();
        hashTable.findByStudentId(4);




    }
}
