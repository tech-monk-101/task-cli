# Task Tracker CLI (Java)

- A simple command-line application to manage your tasks efficiently. 
- This tool allows you to add, update, delete, and track the progress of your tasks directly from the terminal.
- This is an implementation of the project specified in https://roadmap.sh/projects/task-tracker
---

## Usage

### Add a Task

```bash
task-cli add "Buy groceries"
```

---

### Update a Task

```bash
task-cli update 1 "Buy groceries and cook dinner"
```

---

### Delete a Task

```bash
task-cli delete 1
```

---

### Mark Task Status

```bash
task-cli mark-in-progress 1
task-cli mark-done 1
```

---

### List Tasks

```bash
task-cli list
```

---

### List Tasks by Status

```bash
task-cli list done
task-cli list todo
task-cli list in-progress
```

---

## Requirements

* Java Development Kit (JDK) 21+ must be installed on your system

## Project Structure

```
task-cli/
│
├── task-cli.jar        # Compiled Java application
├── task-cli.cmd        # Command file to run CLI
└── tasks.json          # Auto-generated task storage file
```

---

## Setup Instructions

### 1. Extract the ZIP

Extract the project ZIP file to any directory of your choice.

---

### 2. Add to System PATH

To use the CLI globally from any terminal:

1. Locate the folder containing `task-cli.cmd`
2. Copy the folder path
3. Add it to your system environment variables:

#### On Windows:

* Open **Environment Variables**
* Edit the `Path` variable
* Add the folder path
* Save changes

---

### 3. Verify Installation

Open a new terminal and run:

```bash
task-cli
```

If configured correctly, the CLI should respond.

---

## Task Structure

Each task is stored in a JSON file with the following properties:

```json
[
  {
    "id": 1,
    "description": "Buy groceries",
    "status": "in-progress"
  },
  {
    "id": 2,
    "description": "Pay Electricity bill",
    "status": "todo"
  }
]
```

---

## Notes

* The `tasks.json` file is created automatically in the current directory if it does not exist.
* All operations update the JSON file immediately.

