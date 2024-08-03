package com.elitec.italiana.domain.Entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ProductosPreferidos :RealmObject {
	@PrimaryKey var _id:ObjectId = ObjectId()
	var PreferidoId=0
}
