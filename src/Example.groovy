import testbuilder.core.TestBuilder

TestBuilder builder = new TestBuilder()
builder.suite(name : 'Basic Suite'){
    unit(name : 'Math Works') {
        assert 4 == 4
    }
}