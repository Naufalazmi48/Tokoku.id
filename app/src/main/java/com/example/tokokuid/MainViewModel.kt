package com.example.tokokuid

import androidx.lifecycle.ViewModel
import com.example.tokokuid.core.DataDummy
import com.example.tokokuid.core.modelpresentation.Item

class MainViewModel:ViewModel() {
     var data = ArrayList<Item>()
        get() {
        if(field.isNullOrEmpty()){
            field.addAll(DataDummy.getClothes().reversed())
        }
        return field
    }

    fun clearData(){
        data.clear()
    }
}