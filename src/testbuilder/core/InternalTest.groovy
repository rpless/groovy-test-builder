package testbuilder.core

class InternalTest {
    final String name
    final Closure test

    InternalTest(String name, Closure test) {
        this.name = name
        this.test = test
    }
}