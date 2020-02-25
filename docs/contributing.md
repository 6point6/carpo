# Contributing

We aim to be a quality driven, flexible and open project that will welcome changes. 

## Using GitHub Issues

We use GitHub issues to track bugs and enhancements.

If you are reporting a bug, please help to speed up problem diagnosis by providing as
much information as possible. Ideally, that would include a small sample project that
reproduces the problem.

## Principles

Here are a few design principles to keep in mind, they are not rules but will help

1. Try to favor immutability, its just easier
2. Don't be afraid of the core modules, if you need to add a root dependency or add something to common, that's fine if its the right approach. Its better to add things to the core if that's where they should go.
3. Don't be afraid of new module. Carpo is a modular tool set so if you need to add a new module thats fine to add one.
4. Favor code clarity over performance or cleverness. We all need to understand the code and a modern JVM offers great optimisation so its more important to write clean code.
5. Ask for help if you need it.

## Code Conventions and Housekeeping

None of these is essential for a pull request, but they will all help.  They can also be
added after the original pull request but before a merge.

* Make sure all new `.java` files to have a simple Javadoc class comment with at least an
  `@author` tag identifying you, and preferably at least a paragraph on what the class is
  for.
* Add yourself as an `@author` to the `.java` files that you modify substantially (more
  than cosmetic changes).
* Add some Javadocs to any public methods but all methods help.
* Include tests, as a quality driven project we need to have comprehensive tests for al of the code.
* Include an update to the [docs](https://github.com/6point6/carpo/tree/master/docs), that will help everyone understand your change.
* When writing a commit message please follow [these conventions](https://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html),
  if you are fixing an existing issue please add `Fixes gh-XXXX` at the end of the commit
  message (where `XXXX` is the issue number).
* Do not introduce a dependency on Lombok or other libraries that modify the code, use an IDE to create your getters and setters.