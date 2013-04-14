package testbuilder.core

import junit.framework.Test
import junit.textui.TestRunner

/**
 * The TestBuilder defines the Testing DSL.
 */
class TestBuilder {

    AllTestSuite suite = new AllTestSuite()
    GroovyTestSuite currentSuite = new GroovyTestSuite()
    private boolean inSuite = false
    Closure setup = {}
    Closure teardown = {}

    /**
     * { -> Void } -> InternalTestSuite
     * Build a InternalTestSuite.
     */
    static void build(Closure closure) {
        TestBuilder builder = new TestBuilder()
        builder.callClosure closure
        TestRunner.run builder.suite
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
        setup = {}
        teardown = {}
    }

    /**
     * String { -> Void }
     * Create a unit for testing.
     */
    void unit(String name, Closure closure) {
        Test test = InternalTestCase.create(name, closure)
        test.setup = setup
        test.teardown = teardown
        if (inSuite) {
            currentSuite.addTest test
        } else {
            suite.addTest test
        }
    }

    /**
     * { -> Void } -> Void
     *
     */
    void setup(Closure closure) {
        this.setup = closure
    }

    void teardown(Closure closure) {
        this.teardown = closure
    }

    /**
     * { -> Void } -> Void
     * Call the given closure in the context of the this class (and not its original context).
     */
    private void callClosure(Closure closure) {
        Closure clone = closure.clone() as Closure
        clone.delegate = this
        clone.resolveStrategy = Closure.DELEGATE_ONLY
        clone()
    }
}