package com.elitec.italiana.domain.Entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Notificaciones:RealmObject {
	@PrimaryKey var id: ObjectId = ObjectId()
	var descripcion:String=""
	var fecha:String=""
	var icon:Int=0
}