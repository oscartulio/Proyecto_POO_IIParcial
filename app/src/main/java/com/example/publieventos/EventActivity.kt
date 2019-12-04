package com.example.publieventos

class  EventActivity{

    var id: Int? = null
    var title: String? = null
    var date: String? = null
    var time: String? = null
    var staff: String? = null
    var content: String? = null

    constructor(id: Int, title: String, date: String,time: String, staff: String ,content: String) {
        this.id = id
        this.title = title
        this.date = date
        this.time = time
        this.staff = staff
        this.content = content
    }

}