package testbuilder.core

class TestBuilder {

    TestSuite suite = new TestSuite()
    InternalTestCase currentCase

    TestSuite build(Closure closure) {
        callClosure closure
        return suite
    }

    def suite(String name, Closure closure) {
        callClosure closure
    }

    def unit(String name, Closure closure) {
        InternalTestCase currentCase = new InternalTestCase()
        currentCase.setName name
        currentCase.metaClass."$name" = closure
        suite.addTest currentCase
    }

    private callClosure(Closure closure) {
        Closure clone = closure.clone()
        clone.delegate = this
        clone.resolveStrategy = Closure.DELEGATE_ONLY
        clone()
    }
}