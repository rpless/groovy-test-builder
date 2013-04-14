import core.TestBuilder

def foo = 0
TestBuilder.build {
    suite 'Math Tests', {
        setup {
            foo = 5
        }
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
        teardown {
            println 'tests are down'
        }
    }
    unit 'Arrays', {
        assertLength 4, new Object[4]
    }
}