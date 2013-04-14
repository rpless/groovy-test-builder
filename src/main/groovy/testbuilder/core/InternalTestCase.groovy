package testbuilder.core

/**
 * An InternalTestCase is a GroovyTestCase that looks to call the method with the same
 * name as the String returned by getName()
 */
class InternalTestCase extends GroovyTestCase {

   Closure setup, teardown
    /**
     * String { -> Void } -> InternalTestCase
     * Create an InternalTestCase with the given name and the given closure to run as a test
     */
    static InternalTestCase create(String name, Closure c) {
        InternalTestCase testCase = new InternalTestCase()
        testCase.setName name
        testCase.metaClass."$name" = c
        return testCase
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
            fail "Method ${getName()} not found"
        }
    }

    protected void setUp() throws Exception {
        this.setup()
    }

    protected void tearDown() throws Exception {
        this.teardown()
    }
}