package testbuilder.core

/**
 * The TestBuilder defines the Testing DSL. <p>
 *     The Grammar for the Testing DSL is as follows:
 *
 *     <Suite> = suite <String>, { <TestExpr> ... }
 *
 *     <TestExpr> = unit <String>, Closure
 *     The Closure is assumed to have valid JUnit test assertions
 */
class TestBuilder {

    TestSuite suite = new TestSuite()

    /**
     * { -> Void } -> TestSuite
     * Build a TestSuite.
     */
    TestSuite build(Closure closure) {
        callClosure closure
        return suite
    }

    /**
     * String { -> Void } -> Void
     * Create a test suite
     */
    void suite(String name, Closure closure) {
        callClosure closure
    }

    /**
     * String { -> Void }
     * Create a unit for testing.
     */
    void unit(String name, Closure closure) {
        suite.addTest InternalTestCase.create(name, closure)
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