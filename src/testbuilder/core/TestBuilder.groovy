package testbuilder.core

class TestBuilder extends BuilderSupport {

    GroovyTestCase testCase = new GroovyTestCase()

    protected void setParent(Object parent, Object test) {
        if (test instanceof InternalTest) {
            testCase.metaClass."$test.name" = test.&unit
        }
    }

    protected Object createNode(Object name) {
        return null
    }

    protected Object createNode(Object name, Object value) {
        return null
    }

    protected Object createNode(Object name, Map attributes) {
        return null
    }

    protected Object createNode(Object name, Map attributes, Object value) {
        return null
    }
}