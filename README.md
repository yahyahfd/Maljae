# maljae, a project assignment system

## What is the problem solved by maljae?

Imagine that you have a fixed number of teachers to supervise the
programming projects of a bunch of students. Students are regrouped
into small teams and each teacher can supervise a limited number of
teams. In addition, imagine that each teacher only supervises a
specific programming task, and that the students sometimes prefer some
tasks over others. How to assign teams to each teacher to optimize the
students happiness? That's what `maljae` solves.

## How does maljae work?

The assignment process of `maljae` works in three steps:

1. The team creation: During this first phase, the students must create
   their teams and express which programming tasks they would like to
   get. There is a deadline for this step.

2. The assignment: After the deadline, the students choices are
   processed by an algorithm which decides the task assignment.

3. The reporting: The execution trace of the assignment algorithm
   is described in a report, so that each participant can check that
   its execution has been flawless and conform to its specification.

## What is the assignment algorithm used by maljae?

This part of the document is still work-in-progress.

## How maljae is supposed to be used?

maljae is made of two main components: a webserver to collect the team
information and a command-line tool to implement the task assignment
algorithm.

### Webserver

`maljae` server can be run with the following command:

```
java -jar maljae-server -p PORT
```

This command assumes that the current directory contains:

- the file `config.json` which describes how the server is configured.
  If this file does not exist, the server stops.

- the directory `data` which a JSON file for each registered team.
  If this directory does not exist, it is created by the server.

Once it is running, a webserver is waiting for requests on port
`PORT`.

### Command-line tool

When the deadline is reached, the students cannot change their team
descriptions. The files in `data` cannot be modified anymore. They
will be exploited to determine a task assignment.

By running:

```
java -jar maljae-assign
```

the administrator obtains a file named `assignments.json`. This file
contains the assignments and the execution trace of the
algorithm. Once this file is created, the server can notify the
students of the results using a dedicated web page.

## File formats

The data of `maljae` is stored in human-readable text files. The
administrator can modify these files as long as it respects the format
and the invariants described in this section. Otherwise, there is no
guarantee that `maljae` will work properly. In case of violation of
these expectations, there is always a way to recover a valid state
because the files are stored in a git repository. This git repository
is initialized when the server is launched for the first time.

The design of the data model is work-in-progress.

## License

This software is released under the MIT license.
