package com.zjm.test;

public class Main {
    public static void main(String[] args) {
        System.out.println("hahahha");
        Shanghao shanghao = new Shanghao();
        shanghao.readFileToJsonArrayFile(Common.desktop + "100.txt", 50, 2);
    }
}
