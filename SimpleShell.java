package com.rajendra.bhagroo;

import java.io.*;
import java.util.*;

public class SimpleShell {

    public static void main(String[] args) throws IOException {

        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ProcessBuilder pb = new ProcessBuilder();

        //Start Shell, Break Out With <Control><C>
        while(true){
            //Read User Input
            System.out.print("jsh>");
            commandLine = console.readLine();

            //If User Entered A Return, Loop Again
            if(commandLine.equals(""))
                continue;

            //Separate Commands Within commandLine variable
            StringTokenizer commands = new StringTokenizer(commandLine);

            //Adding Commands To ArrayList
            ArrayList<String> userCommands = new ArrayList<String>();

            while(commands.hasMoreTokens()){
                userCommands.add(commands.nextToken());
            }


                    //Handles "cd" Command
                    //Go To Home Directory
                    if( (userCommands.size() == 1 && userCommands.get(0).equalsIgnoreCase("cd")) |
                        (userCommands.size() == 2 && userCommands.get(0).equalsIgnoreCase("cd") &&
                         userCommands.get(1).equalsIgnoreCase("~")) ){

                        System.out.println(userCommands);
                        File home_dir = new File(System.getProperty("user.home"));
                        System.out.println("Home Directory:  " + home_dir);
                        pb.directory(home_dir);
                        continue;

                    //Stay At Same Directory
                    }else if(false){


                        //Code for "cd ."


                    //Go Up 1 Level In Directory Tree
                    }else if(false){


                        //code for "cd .."


                    //Go Up 2 Levels In Directory Tree ( or n levels )
                    }else if(false){


                        //code for "cd ../.."


                    //Go To Root Directory
                    }else if(false){


                        //code for "cd /"


                    }



                    /* CODE FOR HISTORY COMMAND GOES HERE*/



            //Start Process from ProcessBuilder
            //Add A Try Catch Finally Block! For Unknown Commands
            pb.command(userCommands);
            Process process = pb.start();

            //Establish Link To Read Process Output
            InputStream iStream = process.getInputStream();
            InputStreamReader iStreamReader = new InputStreamReader(iStream);
            BufferedReader br = new BufferedReader(iStreamReader);

            //Reading Output Of Process
            String line;

            while( (line = br.readLine()) != null )
                System.out.println(line);

            br.close();



            /** The Steps Are...
             * (1) Parse the input to obtain the command and any parameters <DONE>
             * (2) Create a ProcessBuilder object <DONE>
             * (3) Start the process <DONE>
             * (4) Obtain the output stream <DONE>
             * (5) Output the contents returned by the command <WORKING ON IT>
             */

        }//END SHELL
    }//END MAIN
}//END CLASS