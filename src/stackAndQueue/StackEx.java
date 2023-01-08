package stackAndQueue;

import java.util.*;

public class StackEx {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(2);
        myStack.push(33);
        myStack.push(11);
        myStack.pop();
        System.out.println("============");
        String ex = "9+(3-1)*3+10/2";
        System.out.println("中缀表达式转后缀表达式:" + ex + "-->" + infixToPostfix(ex));
        System.out.println("计算结果:" + calculate(ex));
    }


    /**
     * 用栈来计算表达式
     * 使用后缀表达式
     *
     * @param expression
     * @return
     * @implNote 从左到右遍历表达式的每个数字和符号，若是数字就进栈，若是符号将栈顶两个数字出栈，运算后入栈，得到最终结果。
     */
    public static double calculate(String expression) {
        List<String> postfix = infixToPostfix(expression);
        System.out.println(postfix);
        Stack<Double> stack = new Stack();
        for (int i = 0; i < postfix.size(); i++) {
            String  tmp = postfix.get(i);
            Double op1=0D,op2=0D;
            switch (tmp.charAt(0)) {
                case '+':
                    stack.push(stack.pop() + stack.pop());
                    break;
                case '-':
                    op2 = stack.pop();
                    op1 = stack.pop();
                    stack.push(op1 - op2);
                    break;
                case '*':
                    stack.push(stack.pop() * stack.pop());
                    break;
                case '/':
                    op2 = stack.pop();
                    op1 = stack.pop();
                    stack.push(op1 / op2);
                    break;
                default:
                    stack.push(Double.parseDouble(tmp));
            }
        }
        return stack.pop();
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param infix
     * @return
     * @implNote 从左到右依次遍历中缀表达式的每个符号和数字，如是数字就输出；如果是符号，则判断与栈顶符号的优先级，若是当前符号优先级低于栈顶符号（若当前符号是右括号，也要出栈），则栈顶元素依次出栈并输出，之后将当前符号进展，直至结束。
     */
    public static List<String> infixToPostfix(String infix) {
        int start = 0, end = 0;
        List<String> result = new LinkedList<>();
        Stack<String> stack = new Stack();
        for (int i = 0; i < infix.length(); i++) {
            start = i;
            end = start + 1;
            char tmp = infix.charAt(i);
            switch (tmp) {
                case '+':
                case '-':
                    String current = stack.isEmpty() ? null : stack.peek();
                    if (current == null) {
                        stack.push(tmp + "");
                    } else {
                        if ((current.equals("*") || current.equals("/"))) {
                            while (current != null && (current.equals("*") || current.equals("/"))) {
                                result.add(stack.pop());
                                current = stack.isEmpty() ? null : stack.peek();
                            }
                            result.add(tmp + "");
                        } else {
                            stack.push(tmp + "");
                        }

                    }
                    break;
                case '(':
                case '/':
                case '*':
                    stack.push(tmp + "");
                    break;
                case ')':
                    while (!Objects.isNull(stack.peek()) && !stack.peek().equals("(")) {
                        result.add(stack.pop());
                    }
                    if (!stack.isEmpty())
                        stack.pop();//"("出栈
                    break;
                default:
                    while (i < infix.length() - 1 && infix.charAt(i + 1) >= '0' && infix.charAt(i + 1) <= '9') {
                        //它的后一个字符不是数字
                        end++;
                        i++;
                    }
                    result.add(infix.substring(start, end));
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}

/**
 * 链栈
 */
class MyStack {
    MyNode node;
    int length;

    public MyStack() {
        length = 0;
    }

    public void push(int value) {
        if (length == 0) {
            node = new MyNode();
            node.setValue(value);
        } else {
            MyNode tmp = new MyNode(node, value);
            node = tmp;
        }
        length++;
    }

    public int pop() {
        int result = node.getValue();
        if (length == 0) {
            return -1;
        } else if (length == 1) {
            node.setNext(null);
        } else {
            node.setNext(node.getNext().getNext());
        }
        return result;
    }

    public int getValue() {
        if (length <= 0) {
            return -1;
        }
        return node.getValue();
    }
}

class MyNode {
    MyNode next;
    int value;

    public MyNode() {
    }

    public MyNode(MyNode next, int value) {
        this.next = next;
        this.value = value;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
