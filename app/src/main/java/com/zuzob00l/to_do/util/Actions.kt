package com.zuzob00l.to_do.util

enum class Actions
{
    ADD,
    DELETE,
    UPDATE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

fun String?.toAction(): Actions {

    return when {
        this == "ADD" -> {
            Actions.ADD
        }
        this == "DELETE" -> {
            Actions.DELETE
        }
        this == "UPDATE" -> {
            Actions.UPDATE
        }
        this == "DELETE_ALL" -> {
            Actions.DELETE_ALL
        }
        this == "NO_ACTION" -> {
            Actions.NO_ACTION
        }
        this == "UNDO" -> {
            Actions.UNDO
        }
        else -> Actions.NO_ACTION
    }
}