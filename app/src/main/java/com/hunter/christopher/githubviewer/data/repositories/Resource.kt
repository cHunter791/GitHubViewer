package com.hunter.christopher.githubviewer.data.repositories

class Resource<T> private constructor(val status: Resource.Status,
                                      val data: T?,
                                      val throwable: Throwable?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> =
                Resource(Status.SUCCESS, data, null)


        fun <T> error(throwable: Throwable?): Resource<T> =
                Resource(Status.ERROR, null, throwable)


        fun <T> loading(data: T?): Resource<T> =
                Resource(Status.LOADING, data, null)
    }
}