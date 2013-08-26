import testbuilder.core.TestBuilder

TestBuilder.build {
    suite 'Math Tests', [foo:5], {
        unit 'Addition', {
            assertEquals foo + foo, 10
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