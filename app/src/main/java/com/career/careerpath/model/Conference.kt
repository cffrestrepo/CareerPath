package com.career.careerpath.model

import java.io.Serializable
import java.util.*

class Conference : Serializable{

    lateinit var title : String
    lateinit var description : String
    lateinit var tag : String
    var datetime : Date? = null
    lateinit var speaker : String

}