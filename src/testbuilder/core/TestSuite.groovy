package testbuilder.core

import junit.textui.TestRunner

class TestSuite {
    List<GroovyTestCase> cases = []

    void addTest(GroovyTestCase c) {
        cases << c
    }

    void run() {
        cases.each {TestRunner.run it}
    }
}