import testbuilder.core.*

TestBuilder builder = new TestBuilder()
TestSuite tests = builder.build {
    suite 'Math Tests', {
        unit 'Addition', {
            assert 5 + 5  == 10
            assert 6 + 5 == 11
        }
        unit 'Subtraction', {
            assert 5 - 5 == 0
            assert 6 - 5 == 1
        }
    }
}
tests.run()