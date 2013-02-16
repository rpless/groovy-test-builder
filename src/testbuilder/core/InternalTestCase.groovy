package testbuilder.core

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Modifier

class InternalTestCase extends GroovyTestCase {

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