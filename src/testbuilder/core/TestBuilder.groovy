package testbuilder.core

import junit.framework.TestSuite

/**
 * The TestBuilder defines the Testing DSL.
 */
class TestBuilder {

    AllTestSuite suite = new AllTestSuite()
    GroovyTestSuite currentSuite = new GroovyTestSuite()

    /**
     * { -> Void } -> InternalTestSuite
     * Build a InternalTestSuite.
     */
    TestSuite build(Closure closure) {
        callClosure closure
        return suite
    }

    /**
     * String { -> Void } -> Void
     * Create a test currentSuite
     */
    void suite(String name, Closure closure) {
        currentSuite = new GroovyTestSuite()
        currentSuite.setName name
        callClosure closure
        suite.addTest currentSuite
    }

    /**
     * String { -> Void }
     * Create a unit for testing.
     */
    void unit(String name, Closure closure) {
        currentSuite.addTest InternalTestCase.create(name, closure)
    }

    /**
     * { -> Void } -> Void
     * Call the given closure in the context of the this class (and not its original context).
     */
    private void callClosure(Closure closure) {
        Closure clone = closure.clone()
        clone.delegate = this
        clone.resolveStrategy = Closure.DELEGATE_ONLY
        clone()
    }
}