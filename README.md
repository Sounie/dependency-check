# Dependency Checker

Example of checking whether an application includes a dependency on particular packages.

## Dependencies of this project
- Junit for unit tests.
- ClassGraph for scanning the code to build up relations.
- Gradlew for building

## Motivation
Some Java libraries and frameworks specify a broad range of dependencies. 
Sometimes those dependencies are only required for a subset of the uses of the library.
Sometimes the dependencies clash with a version of the same library that an application
already includes.

Maven, Gradle etc. allow us to specify some exclusions when bringing in a dependency.

Wouldn't it be nice if we could have confidence that our application will not break at
runtime when some untested path through a library is reached?


## Version history
Current implementation
 * Recursively checks from initial package, checking whether any dependency exists that would
   introduce a class from one of the unwanted packages, or any sub-package.
 * Unit tests attempt to check whether code that references apache hadoop mapreduce functionality
   would lead to the Jersey server or Jersey packages that are included as dependencies
   of that hadoop client artifact.

First attempt
 * Just starts from a specified package, then recursively steps through the detected dependencies
and accumulates the class names and checks whether a class is in one of the specified unwanted
packages.
A single object with 2 unit tests.  Not set up as a distributable artifact.

## Known limitations
- The recursive approach to accumulating dependency class information could potentially run out of stack.
- Don't think we will pick up on dependencies that are brought in by Reflection
- If a dependency brings in is dependencies as "shaded" classes, then those will not match against
  our specified exclusions.  This occurred as a potential gotcha when I read the 
  [documentation for hadoop 3.2.0](https://hadoop.apache.org/docs/r3.2.0/).
    
 

    

[]: https://hadoop.apache.org/docs/r3.2.0/

[]: https://hadoop.apache.org/docs/r3.2.0/