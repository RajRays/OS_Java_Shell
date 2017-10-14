package com.rajendra.bhagroo;

import java.io.*;
import java.util.*;

public class SimpleShell {

    public static void main(String... args) throws IOException {


        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ProcessBuilder pb = new ProcessBuilder();

        File current_dir = new File(new File(".").getAbsolutePath());
        File home_dir = new File(System.getProperty("user.home"));
        ArrayList<String> commandHistory = new ArrayList<String>();


        //START SHELL, Break Out With <Control><C>
        while (true) {
            //Read User Input
            System.out.print("jsh>");
            commandLine = console.readLine();
            commandHistory.add(commandLine);

            //If User Entered A Return, Loop Again
            if (commandLine.equals(""))
                continue;

            //Separate Commands Within "commandLine" Variable
            StringTokenizer commands = new StringTokenizer(commandLine);

            //Adding Commands To ArrayList
            ArrayList<String> userCommands = new ArrayList<String>();

            while (commands.hasMoreTokens()) {
                userCommands.add(commands.nextToken());
            }



            //History Utilities
            //Run Previous Command In History
            if(userCommands.size() == 1 &
               userCommands.get(0).equals("!!")){


                try {
                    String previousCommand = commandHistory.get(commandHistory.size() - 2);
                    commandLine = previousCommand;
                    userCommands.clear();

                    String[] tokens = commandLine.split(" ");
                    for(int i = 0; i < tokens.length; i++) {
                        userCommands.add(tokens[i]);
                    }
                    //"continue" Intentionally Omitted To Allow Previous Command To Run

                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("No Previous History");
                    continue;
                }


            //Run ith Command In History
            } else if ((userCommands.size() == 1) &&
                       (userCommands.get(0).matches("!" + "\\d+"))) {

                try {

                    String fullCommand = userCommands.get(0);
                    String[] splitCommand = fullCommand.split("");
                    ArrayList<String> refinedCommand = new ArrayList<>();

                    //Inputs Every Number EXCEPT "!"
                    for(int i = 1; i < splitCommand.length; i++) {
                       refinedCommand.add(splitCommand[i]);
                    }

                    int focusedCommand = Integer.parseInt(refinedCommand.get(0));

                    String iCommand = commandHistory.get(focusedCommand);
                    commandLine = iCommand;
                    userCommands.clear();

                    String[] tokens = commandLine.split(" ");
                    for(int i = 0; i < tokens.length; i++) {
                        userCommands.add(tokens[i]);
                    }
                    //"continue" Intentionally Omitted To Allow Previous Command To Run

                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Command History Does Not Exist");
                    continue;
                }


            }//END History Utilities




            //Handles "ls" Command -> List
            if (userCommands.contains("ls") & userCommands.size() == 1) {

                //Using Variable "current_dir"
                File[] fileList = current_dir.listFiles();
                for (File file : fileList) {
                    if (file.isFile() | file.isDirectory()) {
                        System.out.println(file.getName());
                    }
                }
                continue;

            }//END "ls" Command



            //Handles "cat" Command -> Reads File To Console
            if (userCommands.contains("cat")) {


                if (userCommands.size() == 2 && userCommands.get(0).equals("cat")) {

                    BufferedReader reader = new BufferedReader(new FileReader(userCommands.get(1)));
                    String fileContents = "";
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        fileContents += line + "\n";
                    }
                    System.out.print(fileContents);
                    continue;
                }


            }//END "cat" Command



            //Handles "cp" Command -> Copy
            //If Second Argument Does Not Exist, It Will Create New File
            if (userCommands.contains("cp")) {


                if (userCommands.size() == 3 && userCommands.get(0).equals("cp")) {

                    FileInputStream in = null;
                    FileOutputStream out = null;

                    try {
                        in = new FileInputStream(userCommands.get(1));
                        out = new FileOutputStream(userCommands.get(2));

                        int character;
                        while ((character = in.read()) != -1) {
                            out.write(character);
                        }
                    } catch (Exception e) {

                        System.out.println("Error Copying File");

                    } finally {
                        if (in != null) {
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                        System.out.println("File Copied Successfully");
                    }
                    continue;
                }


            }//END "cp" Command



            //Handles "cd" Command -> Change Directory
            if (userCommands.contains("cd")) {


                //Go To Home Directory
                if ((userCommands.size() == 1 && userCommands.get(0).equals("cd")) |
                    (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                     userCommands.get(1).equals("~"))) {


                    System.out.println("Now At Home Directory:  " + home_dir);
                    current_dir = home_dir;
                    continue;


                //Stay At Same Directory
                } else if ((userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                            userCommands.get(1).equals("."))) {


                    System.out.println("Current Directory:  " + current_dir);
                    continue;


                //Go Up 1 Level In Directory Tree
                } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                           userCommands.get(1).equals("..")) {

                    if (current_dir.getParentFile() != null) {

                        current_dir = current_dir.getParentFile();
                        System.out.println("Moved To Directory :  " + current_dir);
                    } else {
                        System.out.println("You Are At Root Directory");
                    }
                    continue;


                //Go Up 2 Levels In Directory Tree ( or n levels )
                } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                           userCommands.get(1).startsWith("../")) {


                    String[] levels = userCommands.get(1).split("/");

                    for (int i = 0; i < levels.length; i++) {
                        if (current_dir.getParentFile() != null) {
                            current_dir = current_dir.getParentFile();
                        }
                    }

                    System.out.println("Moved To Directory :  " + current_dir);

                    if (current_dir.getParentFile() == null) {
                        System.out.println("You Are At Root Directory");
                    }

                    continue;


                //Go To Root Directory
                } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd") &&
                           userCommands.get(1).equals("/")) {


                    while ((current_dir.getParentFile() != null)) {
                        current_dir = current_dir.getParentFile();
                    }
                    System.out.println("Now At Root Directory:  " + current_dir);
                    continue;


                //Change Into Any Valid Directory
                } else if (userCommands.size() == 2 && userCommands.get(0).equals("cd")) {


                    File targetFile = new File(current_dir + "\\" + userCommands.get(1));

                    if (targetFile.exists() & (targetFile.isFile() || targetFile.isDirectory())) {
                        current_dir = targetFile;
                        continue;
                    } else {
                        System.out.println("\"" + userCommands.get(1) + "\""
                                + " Is Not A Valid Directory");
                        System.out.println("Use \"ls\" Command To View Valid Directories");
                        continue;
                    }
                }


            }//END "cd" Command



            //Handles "history" Command -> Prints History To Console
            if(userCommands.size() == 1 &
               userCommands.get(0).equals("history")) {


                for (int i = 0; i < commandHistory.size(); i++) {

                        String command = commandHistory.get(i);
                        System.out.println(i + " " + command);

                    }
                    continue;


            }//END "history" command



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

            } catch (Exception e) {
                e.getStackTrace();
            }
            

        }//END SHELL
    }//END MAIN
}//END CLASS