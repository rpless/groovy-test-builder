package testbuilder.core

class TestBuilder {

    TestSuite suite = new TestSuite()
    GroovyTestCase currentCase

    TestSuite build(Closure closure) {
        callClosure closure
        return suite
    }

    def suite(String name, Closure closure) {
        if (currentCase != null) {
            suite.addTest currentCase
        }
        currentCase = new GroovyTestCase()
        callClosure closure
    }

    def unit(String name, Closure closure) {
        println "Unit: test$name"
        currentCase.metaClass."test$name" = closure
    }

    private callClosure(Closure closure) {
        Closure clone = closure.clone()
        clone.delegate = this
        clone.resolveStrategy = Closure.DELEGATE_ONLY
        clone()
    }
}