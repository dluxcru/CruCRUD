package com.example.crucrud

import com.example.crucrud.Address
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Person(
    @PrimaryKey var id: Long = 0,
    var name: String = "",
    var addresses: RealmList<Address> = RealmList()
): RealmObject()