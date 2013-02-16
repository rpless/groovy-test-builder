package testbuilder.core

import junit.framework.TestCase
import junit.textui.TestRunner

class TestSuite {
    List<TestCase> cases = []

    void addTest(InternalTestCase c) {
        cases.add c
    }

    void run() {
        GroovyTestSuite suite = new GroovyTestSuite()
        cases.each {suite.addTest it}
        TestRunner.run suite
    }
}