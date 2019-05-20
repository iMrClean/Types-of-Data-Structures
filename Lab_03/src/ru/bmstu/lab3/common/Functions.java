package ru.bmstu.lab3.common;

import java.util.*;

public class Functions {

    private static String operators = "+-*/";

    private static String delimiters = "() " + operators;

    public static boolean flag = true;

    private static boolean isDelimiter(String token) {
        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOperator(String token) {
        if (token.equals("u-")) {
            return true;
        }
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFunction(String token) {
        return token.equals("sqrt") || token.equals("cube") || token.equals("pow10");
    }

    private static int priority(String token) {
        if (token.equals("(")) {
            return 1;
        }
        if (token.equals("+") || token.equals("-")) {
            return 2;
        }
        if (token.equals("*") || token.equals("/")) {
            return 3;
        }
        return 4;
    }

    /**
     * считываем поочерёдно все лексемы, попутно убирая пробелы;
     * если лексема — это число или переменная, то добавляем её в результирующий список;
     * если лексема — это функция, то добавляем её в стэк;
     * если лексема — это открывающая скобка, то добавляем её в стэк;
     * если лексема — это закрывающая скобка, то:
     * помещаем элементы из стэка в результирующую строку пока не встретим открывающую скобку,
     * притом открывающая скобка удаляется из стэка, но в результирующую строку не добавляется;
     * если на вершине стэка оказывается символ функции, то мы помещаем его из стэка в результирующую строку;
     * если открывающая скока не была встречена, то скобки не согласованы.
     * если лексема — это оператор, то:
     * если это последний символ выражения, то выражение некорректно;
     * если это унарный минус, то добавляем его в стэк;
     * иначе:
     * выталкиваем верхние элементы стэка в результирующую строку,пока приоритет текущего
     * оператора меньше либо равен приоритету оператора, находящегося на верине стэка;
     * помещаем текущий оператор в стэк;
     * когда все данные считаны, выталкиваем все элементы стэка в результирующую строку; если в стэке оказались символы не операторов, то скобки не согласованы.
     */
    public static List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                System.out.println("Некорректное выражение.");
                flag = false;
                return postfix;
            }
            if (curr.equals(" ")) {
                continue;
            }
            if (isFunction(curr)) {
                stack.push(curr);
            } else if (isDelimiter(curr)) {
                if (curr.equals("(")) {
                    stack.push(curr);
                } else if (curr.equals(")")) {
                    while (!(stack.peek() != null && stack.peek().equals("("))) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            System.out.println("Скобки не согласованы.");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())) {
                        postfix.add(stack.pop());
                    }
                } else {
                    if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                        // унарный минус
                        curr = "u-";
                    } else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(Objects.requireNonNull(stack.peek())))) {
                            postfix.add(stack.pop());
                        }

                    }
                    stack.push(curr);
                }

            } else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                System.out.println("Скобки не согласованы.");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }

    /**
     * если в записи встретили число, то кладём его в стэк;
     * если в записи встретили функцию или унарный минус, то :
     * вытаскиваем из стэка верхний элемент;
     * применяем функцию к нему;
     * кладём элемент обратно в стэк;
     * если в записи встретили бинарный оператор, то:
     * вытаскиваем из стэка два верхних элемента;
     * выполняем над ними операцию;
     * кладём в стэк результат выполнения операции;
     * выводим последний элемент стэка.
     */
    public static Double calc(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        Double a, b;
        for (String x : postfix) {
            switch (x) {
                case "sqrt":
                    stack.push(Math.sqrt(stack.pop()));
                    break;
                case "cube":
                    Double tmp = stack.pop();
                    stack.push(tmp * tmp * tmp);
                    break;
                case "pow10":
                    stack.push(Math.pow(10, stack.pop()));
                    break;
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    if (b == 0) {
                        throw new RuntimeException("Невозможная операция");
                    }
                    stack.push(a / b);
                    break;
                case "u-":
                    stack.push(-stack.pop());
                    break;
                default:
                    stack.push(Double.valueOf(x));
                    break;
            }
        }
        return stack.pop();
    }
}

