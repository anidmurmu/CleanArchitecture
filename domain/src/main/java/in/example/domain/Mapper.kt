package `in`.example.domain

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
