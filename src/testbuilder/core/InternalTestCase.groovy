package testbuilder.core

import java.lang.reflect.Modifier

/**
 * An InternalTestCase is a GroovyTestCase that looks to call the method with the same
 * name as the String returned by getName()
 */
class InternalTestCase extends GroovyTestCase {

    /**
     * String { -> Void } -> InternalTestCase
     * Create an InternalTestCase with the given name and the given closure to run as a test
     */
    static InternalTestCase create(String name, Closure c) {
        InternalTestCase testCase = new InternalTestCase()
        testCase.setName name
        testCase.metaClass."$name" = c
        testCase
    }

    /**
     * -> Void
     * Overridden from the TestCase class, in order to have the testCase search for the method to run in the metaclass.
     * Throws an exception if the method does not exist or cannot be used.
     */
    protected void runTest() throws Throwable {
        if (this.respondsTo("${getName()}")) {
            this."${getName()}"()
        } else {
            fail("Method ${getName()} not found")
        }
    }
}