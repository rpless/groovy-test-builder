import junit.framework.TestSuite
import junit.textui.TestRunner
import testbuilder.core.*

TestBuilder builder = new TestBuilder()
TestSuite tests = builder.build {
    suite 'Math Tests', {
        unit 'Addition', {
            assertEquals 5 + 5, 10
            assertEquals 6 + 5, 11
        }
        unit 'Subtraction', {
            assertEquals 5 - 5, 0
            assertEquals 6 - 5, 1
        }
        unit 'Multiplication', {
            assertEquals 5 * 3, 15
        }
    }
    unit 'Arrays', {
        assertLength 4, new Object[4]
    }
}
TestRunner.run tests