package io.th0rgal.dpg;

public class Core {

    public static void main (String[] args) {

        String[] salts = {"website.com","thomas","","ex@mple"};
        System.out.println(DPG.generate(salts, 40, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()));

    }

}
