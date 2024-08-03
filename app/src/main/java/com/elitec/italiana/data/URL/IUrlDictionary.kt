package com.elitec.italiana.data.URL

import com.elitec.italiana.domain.Entities.ElementTypes

interface IUrlDictionary {
	fun DevolverURLParcial(tipo: ElementTypes):String
}