package com.rajendra.bhagroo;

import java.io.*;
import java.util.*;

public class SimpleShell {

    public static void main(String[] args) throws IOException {


        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ProcessBuilder pb = new ProcessBuilder();

        File current_dir = new File(new File(".").getAbsolutePath());
        File home_dir = new File(System.getProperty("user.home"));


        //Start Shell, Break Out With <Control><C>
        while(true){
            //Read User Input
            System.out.print("jsh>");
            commandLine = console.readLine();

            //If User Entered A Return, Loop Again
            if(commandLine.equals(""))
                continue;

            //Separate Commands Within "commandLine" Variable
            StringTokenizer commands = new StringTokenizer(commandLine);

            //Adding Commands To ArrayList
            ArrayList<String> userCommands = new ArrayList<String>();

            while(commands.hasMoreTokens()){
                userCommands.add(commands.nextToken());
            }

                    //Handles "ls" Command -> List
                    if(userCommands.contains("ls")) {

                        //Using Variable "current_dir"
                        File[] fileList = current_dir.listFiles();
                        for (File file : fileList) {
                            if (file.isFile() | file.isDirectory()) {
                                System.out.println(file.getName());
                            }
                        }
                        continue;

                    }//END "ls" Command



                    //Handles "cd" Command -> Change Directory
                    if(userCommands.contains("cd")) {

                        //Go To Home Directory
                        if ((userCommands.size() == 1 && userCommands.get(0).equals("cd")) |
                            (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                             userCommands.get(1).equals("~"))) {


                            System.out.println("Home Directory:  " + home_dir);
                            current_dir = home_dir;
                            continue;


                        //Stay At Same Directory
                        } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                                   userCommands.get(1).equals(".")) {

                            System.out.println("Current Directory: " + current_dir);
                            continue;


                        //Go Up 1 Level In Directory Tree
                        } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                                   userCommands.get(1).equals("..")) {

                            current_dir = current_dir.getParentFile();
                            System.out.println("New Directory : " + current_dir);
                            continue;


                        //Go Up 2 Levels In Directory Tree ( or n levels )
                        } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                                userCommands.get(1).equals("../")) {


                            System.out.println("NOT YET IMPLEMENTED");
                            continue;

                            /*for(int i = [Number of ../ That Appear] ; i > 0 ; i--)
                              current_dir = current_dir.getParentFile(); */

                            //code for "cd ../.."


                        //Go To Root Directory
                        } else if (false) {


                            //code for "cd /"


                        //Change Into Any Valid Directory
                        } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd")) {

                            File targetFile = new File(current_dir + "\\" + userCommands.get(1));

                                if(targetFile.exists() & (targetFile.isFile() || targetFile.isDirectory())) {
                                    current_dir = targetFile;
                                    continue;
                                } else {
                                    System.out.println("Please Enter A Valid Directory");
                                    System.out.println("Use \"ls\" Command To View Valid Directories");
                                    continue;
                                }

                        }

                    }//End "cd" Command





                    /* CODE FOR HISTORY COMMAND GOES HERE*/





            try {
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

                while ((line = br.readLine()) != null)
                    System.out.println(line);

                br.close();

            }catch (Exception e){
                e.getStackTrace();
            }


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