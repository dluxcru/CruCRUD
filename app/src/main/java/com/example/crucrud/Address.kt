package com.example.crucrud

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Address(
        @PrimaryKey var id: Long = 0,
        var lineOne: String = "",
        var lineTwo: String = "",
        var city: String = "",
        var state: String = "",
        var zip: String = ""
): RealmObject()
