# Groovy Test Builder 
=====================
The Groovy Test Builder provides a small DSL that aims to streamline unit testing in Groovy.
Here is a simple example:
```groovy
TestSuite tests = new TestBuilder().build {
    suite 'Math Tests', {
        unit 'Addition', {
            assertEquals 5 + 5, 10
            assertEquals 6 + 5, 11
        }
    }
}
 TestRunner.run tests
```

Compared to JUnit:

```groovy
class MathTest extends GroovyTestCase {
    @Test
    void testAddition() {
        assertEquals 5 + 5, 10
        assertEquals 6 + 5, 10
    }
}
```
At this point you might be thinking "Why bother using the DSL, the JUnit code is a line shorter." The real difference is
not wasting time with boiler plate. JUnit tests require a whole class per Test Case, special annotations, and methods with
specific names and signatures. The Test Builder requires none of this. You simply name your test, write the test, and then run it.

The TestBuilder is built on top of JUnit, so you still have access to all of your favorite comparison methods.

Let's consider a slightly larger example:
```groovy
TestSuite tests = new TestBuilder().build {
    suite 'Math Tests', {
        unit 'Addition', {
            assertEquals 5 + 5, 10
            assertEquals 6 + 5, 11
        }
    }
    suite 'Array Tests', {
        unit 'Array Length', {
            assertLength 4, new Object[4]
        }
    }
}
TestRunner.run tests
```

In this script, there are two suites that test different things. In regular JUnit, you would need to create separate
class files for each suite. In TestBuilder, you have the option to group your test suites into one file and run them all
in one shot.