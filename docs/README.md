# NIVI User Guide

NIVI is a friendly command-line chatbot that helps you manage your tasks, deadlines, and events. It saves your data automatically so you never lose track!

![NIVI Screenshot](Nivi_image.png)

## Features

---

### Add a Todo task: `todo`

Adds a simple task with no date/time attached.

Format: `todo DESCRIPTION`

Example: `todo poster revision`

```
____________________________________________________________
 Got it. I've added this task:
   [T][ ] poster revision
 Now you have 1 tasks in the list.
____________________________________________________________
```

---

### Add a Deadline task: `deadline`

Adds a task with a specific due date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example: `deadline project submission /by 2025-06-06`

```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] project submission (by: Jun 06 2025)
 Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Add an Event task: `event`

Adds a task with a start time and end time.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example: `event peer tutoring /from Aug 6th 2pm /to 4pm`

```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] peer tutoring (from: Aug 6th 2pm to: 4pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```

---

### List all tasks: `list`

Shows all tasks currently in your list.

Format: `list`

Example: `list`

```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] poster revision
 2.[D][ ] project submission (by: Jun 06 2025)
 3.[E][ ] peer tutoring (from: Aug 6th 2pm to: 4pm)
____________________________________________________________
```

---

### Mark a task as done: `mark`

Marks a task as completed.

Format: `mark TASK_NUMBER`

Example: `mark 1`

```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] poster revision
____________________________________________________________
```

---

### Unmark a task: `unmark`

Marks a completed task as not done yet.

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

```
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] poster revision
____________________________________________________________
```

---

### Delete a task: `delete`

Removes a task from your list permanently.

Format: `delete TASK_NUMBER`

Example: `delete 2`

```
____________________________________________________________
 Okk. I've deleted this task, yaaa:
   [D][ ] project submission (by: Jun 06 2025)
 Now you have 2 tasks in the list.
____________________________________________________________
```

---

### Find tasks by keyword: `find`

Searches for tasks whose description contains the given keyword.

Format: `find KEYWORD`

Example: `find poster`

```
____________________________________________________________
 Matched things I can find:
 1.[T][ ] poster revision
____________________________________________________________
```

---

### Exit NIVI: `bye`

Saves your task list and exits the program.

Format: `bye`

```
____________________________________________________________
 Bye. See you soon little kid! Love u
____________________________________________________________
```

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Todo | `todo DESCRIPTION` | `todo poster revision` |
| Deadline | `deadline DESCRIPTION /by YYYY-MM-DD` | `deadline project submission /by 2025-06-06` |
| Event | `event DESCRIPTION /from START /to END` | `event peer tutoring /from 2pm /to 4pm` |
| List | `list` | `list` |
| Mark | `mark TASK_NUMBER` | `mark 1` |
| Unmark | `unmark TASK_NUMBER` | `unmark 1` |
| Delete | `delete TASK_NUMBER` | `delete 2` |
| Find | `find KEYWORD` | `find poster` |
| Exit | `bye` | `bye` |