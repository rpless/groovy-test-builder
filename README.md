# Groovy Test Builder 
=====================
The Groovy Test Builder provides a small DSL that aims to streamline unit testing in Groovy.
Here is a simple example:
```groovy
TestBuilder.build { {
    unit 'Addition', {
        assertEquals 5 + 5, 10
        assertEquals 6 + 5, 11
    }
}
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
At this point you might be thinking "Why bother using the DSL, its only one line shorter than the JUnit class."
The real difference is not wasting time with boiler plate.
JUnit tests require a whole class for each test case, special annotations, and methods with specific names and signatures.
The Test Builder requires none of this. You simply name your test, write the test, and then run it.

The TestBuilder is built on top of JUnit, so you still have access to all of your favorite comparison methods.

Let's consider a slightly larger example:
```groovy
TestBuilder.build { {
    unit 'Addition', {
        assertEquals 5 + 5, 10
        assertEquals 6 + 5, 11
    }
    unit 'Array Length', {
        assertLength 4, new Object[4]
    }
}
```

In this script, there are two units that test different things.
You want these tests to separate logically, but multiple files for such small cases seems like a waste.
With the TestBuilder, you simple put them in the same script.
When the TestSuite that the Builder produces is run, then all of the test cases will be run.
The still remain independent of each other.
If one fails the other units will still run and be unaffected by the failure.

## Dependencies
The TestBuilder is dependent on Groovy 2.1.0 and JUnit 4.11.