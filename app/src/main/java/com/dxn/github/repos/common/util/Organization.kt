package com.dxn.github.repos.common.util

sealed class Organization {
    object JETBRAINS : Organization() { override fun invoke() = "JetBrains" }
    object SQUARE : Organization() { override fun invoke() = "JetBrains" }
    object SLACK : Organization() { override fun invoke() = "slackhq" }
    abstract operator fun invoke() : String
}