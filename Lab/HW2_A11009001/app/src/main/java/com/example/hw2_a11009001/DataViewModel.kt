package com.example.hw2_a11009001

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private val _dataList = MutableLiveData<List<Data>>()
    val dataList: LiveData<List<Data>>  = _dataList


    fun updateDataList(newDataList: List<Data>) {
        _dataList.value = newDataList
    }
    fun getDataListSize(): Int {
        return dataList.value?.size ?: 0
    }

}