package com.rajendra.bhagroo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleShell {

    public static void main(String[] args) throws IOException {

        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        //Break out with <control><C>
        while (true) {
            //Read user input
            System.out.println("jsh>");
            commandLine = console.readLine();

            //If user entered a return, loop again
            if(commandLine.equals(""))
                continue;

            /** The Steps Are...
             * (1) Parse the input to obtain the command and any parameters
             * (2) Create a ProcessBuilder object
             * (3) Start the process
             * (4) Obtain the output stream
             * (5) Output the contents returned by the command
             */
        }
    }
}
