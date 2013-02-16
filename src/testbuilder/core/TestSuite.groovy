package testbuilder.core

import junit.framework.TestCase
import junit.textui.TestRunner

/**
 * A TestSuite holds all of the TestCases and can run them.
 */
class TestSuite {
    List<TestCase> cases = []

    /**
     * InternalTestCase -> Void
     * Adds an InternalTestCase to this TestSuite.
     */
    void addTest(InternalTestCase c) {
        cases.add c
    }

    /**
     * -> Void
     * Run all of the TestCases in this TestSuite.
     */
    void run() {
        GroovyTestSuite suite = new GroovyTestSuite()
        cases.each {suite.addTest it}
        TestRunner.run suite
    }
}