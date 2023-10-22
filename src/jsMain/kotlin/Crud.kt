import react.*
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import kotlin.math.roundToInt

external interface CrudProps : Props{
    var students:List<List<Comparable<*>>>
}

val Crud = FC<CrudProps> { props ->
    var students by useState(
        listOf(
            listOf("Art", 4),
            listOf("Dima", 4),
            listOf("Gray", 4)
        )
    )

    // Функция для вычисления средней оценки всех студентов
    fun calculateAverage(): Double {
        if (students.isEmpty()) return 0.0
        val totalSum = students.map { it[1] as Int }.sum()
        return ((totalSum.toDouble() / students.size)*100).roundToInt()/100.00
    }

    fun increase(index:Int) {
        students = students.mapIndexed { i, s ->
            if (i == index) {
                listOf(s[0],s[1].toString().toInt() + 1)
            } else {
                s
            }
        }
    }

    fun decrase(index:Int) {
        students = students.mapIndexed { i, s ->
            if (i == index) {
                listOf(s[0],s[1].toString().toInt() - 1)
            } else {
                s
            }
        }
    }
    // Функция для отрисовки таблицы
    fun renderTable() {
        table {
            thead {
                tr {
                    th { +"Имя" }
                    th { +"Оценка" }
                }
            }
            tbody {
                students.forEachIndexed { index, student ->
                    tr {
                        td { +student[0].toString() }
                        td {
                            +student[1].toString()
                            useEffect {
                                setColor("red")
                            }
                            button {
                                disabled = (student[1].toString().toInt() == 5)
                                +"+"
                                onClick = { _ ->
                                    increase(index)
                                    renderTable()
                                }
                            }
                            button {
                                disabled = (student[1].toString().toInt() == 2)
                                +"-"
                                onClick = { _ ->
                                    decrase(index)
                                    renderTable()
                                }

                            }
                        }
                    }
                }
                // Добавляем строку со средним баллом
                tr {
                    td { +"Средний балл" }
                    td {
                        +calculateAverage().toString()
                    }
                }
            }
        }
    }
    renderTable() // Начальная отрисовка таблицы
}