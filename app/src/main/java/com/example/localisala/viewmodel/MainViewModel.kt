package com.example.localisala.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localisala.model.University
import com.example.localisala.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: MainRepository): ViewModel(){

    private val _universities = MutableLiveData<List<University>>()
    val universities : LiveData<List<University>> get() = _universities

    fun fetchData() {
        viewModelScope.launch {
            val universities = repository.getUniversities()
            _universities.postValue(universities)
        }
    }

}