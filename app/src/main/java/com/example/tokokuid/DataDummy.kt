package com.example.tokokuid

import com.example.tokokuid.model.Courier
import com.example.tokokuid.model.Province
import com.example.tokokuid.model.TypeSend

object DataDummy {
    fun getProvince():ArrayList<Province>{
        val list = ArrayList<Province>()
        val lombok = Province("Lombok",1)
        val jakarta = Province("Jakarta",2)
        val lombok_timur = Province("Lombok Timur",3)
        val aceh = Province("Aceh",4)
        val jogja = Province("Jogja",5)
        val medan = Province("Medan",6)
        val semarang = Province("Semarang",7)
        val serang = Province("LombSerangok",8)
        val gili = Province("Gili",9)
        val kuta = Province("Kuta",10)
        list.add(lombok)
        list.add(jakarta)
        list.add(lombok_timur)
        list.add(aceh)
        list.add(lombok)
        list.add(jogja)
        list.add(medan)
        list.add(semarang)
        list.add(serang)
        list.add(gili)
        list.add(kuta)
        return list
    }

    fun getCourier():ArrayList<Courier>{
        val jne = arrayListOf<TypeSend>(TypeSend("Yakin Esok Sampai",30000),TypeSend("Reguler",35000))
        val tiki = arrayListOf<TypeSend>(TypeSend("Esok Sampai",40000),TypeSend("Reg",35000))
        val list = arrayListOf<Courier>(Courier("JNE",jne),Courier("Tiki",tiki))
        return list
    }
}