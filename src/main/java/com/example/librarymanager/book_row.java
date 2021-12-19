package com.example.librarymanager;
import java.util.ArrayList;
import java.util.List;

class book_row {
    public static void main(String[] args) {
        int n = 2;
        List<String> list = new ArrayList<>();
        solve(n,n,"",list);
    }

    static void solve(int open, int close, String op, List<String> list){

        if(open == 0 && close == 0){
            list.add(op);
            return;
        }
        if(open != 0){
            String op1 = op;
            op1 += "(";
            solve(open-1, close, op1, list);
        }

        if(close > open){
            String op2 = op;
            op2 += ")";
            solve(open, close-1, op2, list);
        }
    }
}