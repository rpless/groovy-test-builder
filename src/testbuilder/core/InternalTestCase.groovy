package testbuilder.core

import org.hamcrest.CoreMatchers

import java.lang.reflect.Modifier

/**
 * An InternalTestCase is a GroovyTestCase that looks for its method to call in
 * its metaclass.
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
        MetaMethod method = metaClass.methods.find { it.name == getName() }
        if (method == null) {
            println "No such method ${getName()}"
            fail("Method ${getName()} not found")
        } else if (!Modifier.isPublic(method.getModifiers())) {
            fail("Method ${getName()} should be public")
        } else {
            method.invoke(this, new Object[0])
        }
    }
}