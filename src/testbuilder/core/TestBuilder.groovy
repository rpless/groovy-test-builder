package testbuilder.core

import junit.framework.Test
import junit.framework.TestSuite
import junit.textui.TestRunner

/**
 * The TestBuilder defines the Testing DSL.
 */
class TestBuilder {

    AllTestSuite suite = new AllTestSuite()
    GroovyTestSuite currentSuite = new GroovyTestSuite()
    private boolean inSuite = false

    /**
     * { -> Void } -> InternalTestSuite
     * Build a InternalTestSuite.
     */
    void build(Closure closure) {
        callClosure closure
        TestRunner.run suite
    }

    /**
     * String { -> Void } -> Void
     * Create a test currentSuite
     */
    void suite(String name, Closure closure) {
        currentSuite = new GroovyTestSuite()
        currentSuite.setName name
        inSuite = true
        callClosure closure
        suite.addTest currentSuite
        inSuite = false
    }

    /**
     * String { -> Void }
     * Create a unit for testing.
     */
    void unit(String name, Closure closure) {
        Test test = InternalTestCase.create(name, closure)
        if (inSuite) {
            currentSuite.addTest test
        } else {
            suite.addTest test
        }
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