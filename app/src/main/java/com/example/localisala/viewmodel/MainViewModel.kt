package com.example.localisala.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localisala.model.Course
import com.example.localisala.model.Period
import com.example.localisala.model.University
import com.example.localisala.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: MainRepository): ViewModel(){

    private val _universities = MutableLiveData<List<University>>()
    val universities : LiveData<List<University>> get() = _universities

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    private val _period = MutableLiveData<List<Period>>()
    val periodes: LiveData<List<Period>> get() = _period

    fun fetchUniversityData() {
        viewModelScope.launch {
            val universitiesList = repository.getUniversities()
            _universities.postValue(universitiesList)
        }
    }

    fun fetchCourseData(idDocument: String){
        viewModelScope.launch {
            val courseList = repository.getCourses(idDocument)
            _courses.postValue(courseList)
            Log.e("retorno","$courseList")
            Log.e("idDocumento","$idDocument")
        }
    }

    fun fetchPeriodData(universityIdDocument: String, courseIdDocument: String){
        viewModelScope.launch {
            val periodList = repository.getPeriod(universityIdDocument, courseIdDocument)
            _period.postValue(periodList)

        }
    }

}