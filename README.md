# OS_Java_Shell
Operating Systems Course Project: Creating A Shell Interface Using Java
________________________________________________________________________

Part 1
___________
* Implement SimpleShell
* Use ProcessBuilder Class To Create External Processes


Part 2
___________

* Implement [cd] Command -> Change Directory

	[cd] Command MUST Include...

		[cd]
		-> Change To Home Directory

		[cd ~]
		-> Change To Home Directory

		[cd .]
		-> Stay At Current Directory

		[cd ..]
		-> Ascend One Directory

		[cd ../../..]
		-> Ascend Multiple (nth) Directories

		[cd /]
		-> Change To Root Directory 

* ArrayList Data Structure Used To Decompose And Pass Commands To ProcessBuilder
        
Part 3
___________

* Implement History Feature
	
	[history]
	-> Displays Commmand History In Numerical Order

	[!!]
	-> Runs Previous Command

	[!i]
	-> Runs ith Command In History (Ex. !3 Runs 3rd Command)



Extra Commands Added For Fun
* [ls] -> List All Files And Directories Within Current Directory
* [cp] -> Copy One File To Another. If File Recipient Does Not Exist, Creates File 
* [cat] -> Reads File To Console
* [cd] -> "cd" Is A Requirement, However I Added The Ability To Change Into Any Valid Directory
