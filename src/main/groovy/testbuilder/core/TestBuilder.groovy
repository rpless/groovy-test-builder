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
        closure.rehydrate([:], builder, builder).call()
        TestRunner.run builder.suite
    }

    void suite(String name, Closure closure) {
        suite name, [:], closure
    }

    /**
     * String Map { -> Void } -> Void
     * Create a test currentSuite
     */
    void suite(String name, Map properties, Closure closure) {
        currentSuite = new GroovyTestSuite()
        currentSuite.setName name
        inSuite = true
        closure.rehydrate(properties, this, this).call()
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
}