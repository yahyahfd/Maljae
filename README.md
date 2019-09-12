# maljae, a project assignment system

## What is the problem solved by maljae?

Imagine that you have a fixed number of teachers to supervise the
programming projects of a bunch of students. Students are regrouped
into small teams and each teacher can supervise a limited number of
teams. In addition, imagine that each teacher only supervises a limited
number of programming tasks, and that the students sometimes prefer some
tasks over others. How to assign teams to each teacher to optimize the
students happiness? That's what `maljae` solves.

## How does maljae work?

The assignment process of `maljae` works in three steps:

1. The team creation: During this first phase, the students must create
   their teams and express which programming tasks they would like to
   get by ordering all the tasks with respect to their preferences.
   There is a deadline for this step. Notice that teams cannot exceed
   a given number of members. They must also contain a minimum number
   of members.

2. The assignment: After the deadline, the students choices are
   processed by an algorithm which decides the task assignment.

3. The reporting: The execution trace of the assignment algorithm
   is described in a report, so that each participant can check that
   its execution has been flawless and conform to its specification.

## What is the assignment algorithm used by maljae?

While giving their task preferences, the teams are also asked to give
an integer. After the deadline, a seed is attributed to each team: it
is the sum of the integers given by all the other teams.

The assignment algorithm is made of the following steps:

1. The complete teams are ordered according to the smallest email address of
   their members (alphabetical order, characters being ordered with
   ascii order), say from `1` to `k`. The seed of the team `i` is denoted by `n_i`.
   
2. For each team `i` (in the previous order), consider the
   transposition `t_i` that exchanges `i` and `i +
   (n_i%(k-i-1))`. Consider then the permutation `t = t_k o t_{k-1} o
   ... o t_1`.
   
3. The order of the teams is given by the image of `(1,2,...,k)`
   by `t`.
   
4. Consider the teams in the order given in step 3. For each team, the
   assigned task is the first free one in its preference list of tasks.

5. The same steps are followed to order the teams that lack one
   member, and the obtained list is placed after the ordered list of
   complete teams. Then again for the teams that lack two members, etc.

## How maljae is supposed to be used?

maljae is made of two main components:
1. a webserver to collect the team information and to visualize task assignment.
2. a command-line tool to implement the task assignment algorithm.

### Webserver

`maljae` server can be run with the following command:

```
java -jar maljae-webserver -p PORT
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
