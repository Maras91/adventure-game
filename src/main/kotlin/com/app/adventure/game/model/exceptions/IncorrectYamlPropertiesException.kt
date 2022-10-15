package com.app.adventure.game.model.exceptions


class IncorrectYamlPropertiesException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(incorrectProperty: String, correctExamples: String) :
            super(String.format("the name from properties %s is incorrect valid responses are: %s",incorrectProperty,correctExamples))
}