package lineTable;

public class LineTable {
    public static void main(String[] args) {
        System.out.println("====================顺序结构=====================");
        Sequential listShunXu = new Sequential();
        listShunXu.add(2);
        listShunXu.add(33);
        listShunXu.add(11);
        System.out.println("源：" + listShunXu.getElement(2));
        listShunXu.insert(2, 32);
        System.out.println("增" + listShunXu.getElement(2));
        listShunXu.update(2, 22);
        System.out.println("改" + listShunXu.getElement(2));
        listShunXu.delete(2);
        System.out.println("删" + listShunXu.getElement(2));
        System.out.println("====================链式结构=====================");
        Chain listChain = new Chain();
        System.out.println("源：" + listChain.getValue(2));
        listChain.update(0, 32);
        listChain.add(11);
        listChain.add(819);
        listChain.insert(2, 88);
        System.out.println("增" + listChain.getValue(2));
        listChain.update(2, 22);
        System.out.println("改" + listChain.getValue(2));
        listChain.delete(1);
        System.out.println("删之后的：" + listChain.getValue(2));
    }
}

/**
 * 顺序结构线性表
 */
class Sequential {
    //能快速取元素， 插入和删除麻烦

    //用数组来实现
    Object[] arr;
    int number;//数量

    public Sequential() {
        arr = new Object[16];
        number = 0;
    }

    public int size() {
        return number;
    }

    //查
    public Object getElement(int index) {
        return arr[index];
    }

    public void add(Object o){
        Object[] tmp = arr;
        if (number == arr.length){
            tmp = new Object[number*2];
            for (int i = 0; i < number; i++) {
                tmp[i]= arr[i];
            }
        }
        arr = tmp;
        arr[number++]= o;
    }

    //增
    public void insert(int index, Object o) {
        Object[] tm = arr;
        int num = arr.length;
        if (index >= number - 1) {
            while (index >= number - 1) {
                num = arr.length * 2;
                if (num > index) {
                    tm = new Object[num];
                    break;
                }
            }
            for (int i = 0; i <number; i++) {
                tm[i] = arr[i];
            }
        }
        for (int i = number; i >= index; i--) {
            if (i == index)
                tm[i] = o;
            else tm[i] = arr[i-1];
        }
        arr = tm;
        number++;
    }

    //改
    public void update(int index, Object o) {
        arr[index] = o;
    }

    //删
    public void delete(int index) {
        for (int i = 0; i < number; i++) {
            if (i > index) {
                arr[i - 1] = arr[i];
            }
        }
    }
}


/**
 * 链式结构线性表
 */
class Chain {
    //方便插入和删除

    Node first;
    int number;

    public Chain() {
        first = new Node();
        number = 1;
    }


    public void add(int value) {
        Node tmp = first;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        tmp.setNext(new Node(value));
        number++;
    }


    //插入
    public void insert(int index, int value) {
        int count = 0;
        Node tmp = first;
        while (count < index - 1) {
            count++;
            tmp = tmp.getNext();
        }
        Node now = new Node(value);
        now.setNext(tmp.getNext());
        tmp.setNext(now);
        number++;
    }

    //删
    public void delete(int index) {
        int count = 0;
        Node tmp = first;
        if (index == 0) {
            first = first.getNext();
            return;
        }
        while (count < index - 1) {
            count++;
            tmp = tmp.getNext();
        }
        tmp.setNext(tmp.getNext().getNext());
        number--;
    }

    //改
    public void update(int index, int value) {
        int count = 0;
        Node tmp = first;
        while (count < index) {
            tmp = tmp.getNext();
            count++;
        }

        tmp.setValue(value);
    }

    //查

    public int getValue(int index) {
        if (index >= number)
            return -1;
        int count = 0;
        Node tmp = first;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
            count++;
            if (count == index)
                break;
        }
        return tmp.getValue();
    }
}

/**
 * 链式结构节点
 */
class Node {
    int value;
    Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
