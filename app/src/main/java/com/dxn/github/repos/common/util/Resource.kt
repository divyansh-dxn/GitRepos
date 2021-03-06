package com.dxn.github.repos.common.util

sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Failure<T>(message: String) : Resource<T>(null, message)
    class Loading<T>(message: String? = null) : Resource<T>(null, message)
}