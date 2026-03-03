# Nivi project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Nivi_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Nivi.java` file, right-click it, and choose `Run Nivi.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    _   _  _____ __      _____
   | \ | ||_   _|\ \    / /|_   _|
   |  \| |  | |   \ \  / /   | |  
   | . ` |  | |    \ \/ /    | |  
   | |\  | _| |_    \  /    _| |_ 
   |_| \_||_____|    \/    |_____|
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

User Guide

Features
todo - Add a Todo task
Format: todo DESCRIPTION
Example: todo poster revision
Output:
____________________________________________________________
 Got it. I've added this task:
   [T][ ] poster revision
 Now you have 1 tasks in the list.
____________________________________________________________

deadline - Add a Deadline task
Format: deadline DESCRIPTION /by YYYY-MM-DD
Example: deadline project submission /by 2025-06-06
Output:
____________________________________________________________
 Got it. I've added this task:
   [D][ ] project submission (by: Jun 06 2025)
 Now you have 2 tasks in the list.
____________________________________________________________

event - Add an Event task
Format: event DESCRIPTION /from START_TIME /to END_TIME
Example: event peer tutoring /from Aug 6th 2pm /to 4pm
Output:
____________________________________________________________
 Got it. I've added this task:
   [E][ ] peer tutoring (from: Aug 6th 2pm to: 4pm)
 Now you have 3 tasks in the list.
____________________________________________________________

list - List all tasks
Format: list
Output:
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] poster revision
 2.[D][ ] project submission (by: Jun 06 2025)
 3.[E][ ] peer tutoring (from: Aug 6th 2pm to: 4pm)
____________________________________________________________

mark - Mark a task as done
Format: mark TASK_NUMBER
Example: mark 1
Output:
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] poster revision
____________________________________________________________

unmark - Unmark a task
Format: unmark TASK_NUMBER
Example: unmark 1
Output:
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] poster revision
____________________________________________________________

delete - Delete a task
Format: delete TASK_NUMBER
Example: delete 2
Output:
____________________________________________________________
 Okk. I've deleted this task, yaaa:
   [D][ ] project submission (by: Jun 06 2025)
 Now you have 2 tasks in the list.
____________________________________________________________

find - Find tasks by keyword
Format: find KEYWORD
Example: find poster
Output:
____________________________________________________________
 Matched things I can find:
 1.[T][ ] poster revision
____________________________________________________________

bye - Exit NIVI
Format: bye
Output:
____________________________________________________________
 Bye. See you soon little kid! Love u
____________________________________________________________

Data Saving
NIVI automatically saves your task list to data/nivi.txt after every command and reloads it on the next startup. No manual saving needed!