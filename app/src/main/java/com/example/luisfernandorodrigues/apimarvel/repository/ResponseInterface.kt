package com.example.luisfernandorodrigues.apimarvel.repository

interface ResponseInterface<T> {

    fun success(response: T)

    fun error(message: String)
}