package com.company;

import data.oprate.FileOprator;

public class Main {
    public static void main(String[] args) {
        FileOprator fileOprator = new FileOprator("Bye.txt",1);
        fileOprator.read();
    }
}
