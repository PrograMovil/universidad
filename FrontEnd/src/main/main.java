/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import InterfazConsola.InterfazConsola;

/**
 *
 * @author Denis
 */
public class main {

    public main() {
    }
    
    
    
    public static void main(String[] args) throws Exception {

        try {
            
            InterfazConsola interfaz=new InterfazConsola();
            
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }

    }
}
